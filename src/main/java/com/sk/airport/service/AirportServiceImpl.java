package com.sk.airport.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.sk.airport.dto.CountryDetailsDto;
import com.sk.airport.dto.ReportRowDto;
import com.sk.airport.dto.RunwayRequestDto;
import com.sk.airport.entity.Country;
import com.sk.airport.mapper.CountryMapper;
import com.sk.airport.repository.CountryRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class AirportServiceImpl implements AirportService {

	@Autowired
	private CountryRepository countryRepo;
	
	private CountryMapper countryMapper;
	
	public AirportServiceImpl(CountryRepository countryRepo, CountryMapper countryMapper) {
		this.countryRepo = countryRepo;
		this.countryMapper = countryMapper;
	}
	
	public static final int REPORT_TOP_BOTTOM_LIMIT = 10;

	/*
	 * @Override public CountryDetailsDto Sneha(RunwayRequestDto runwayRequestDto) {
	 * log.debug("entering the service method getRunwayFromCountry");
	 * Optional<Country> country =
	 * countryRepo.findByNameOrCode(runwayRequestDto.getCountryName(),
	 * runwayRequestDto.getCode()); return mapPage() }
	 */
	
	@Override
	public Page<CountryDetailsDto> getRunwayFromCountry(RunwayRequestDto runwayRequestDto) {
		log.debug("Starting adding new recipe : {}", runwayRequestDto);
		return countryMapper.mapPage(countryRepo.findAll((Specification<Country>) (root, query, criteriaBuilder) ->
		prepareRunway(runwayRequestDto, root, criteriaBuilder),
        PageRequest.of(runwayRequestDto.getPageNum(),
        		runwayRequestDto.getPageSize())));
	}
	
	public Predicate prepareRunway(RunwayRequestDto runwayRequestDto, Root<Country> root, CriteriaBuilder criteriaBuilder) {
		List<Predicate> predicateList = new ArrayList<>();
		
		if(runwayRequestDto.getCountryName() !=null && !runwayRequestDto.getCountryName().isBlank()) {
			predicateList.add(criteriaBuilder.like(root.get("name"), "%" + runwayRequestDto.getCountryName()+ "%"));
		}
		
		if(runwayRequestDto.getCode() !=null && !runwayRequestDto.getCode().isBlank()) {
			predicateList.add(criteriaBuilder.like(root.get("code"), "%" + runwayRequestDto.getCode()+ "%"));
		}

        return criteriaBuilder.and(predicateList.toArray(new Predicate[predicateList.size()]));
	}

	@Override
	public List<ReportRowDto> getTopoTenAirports() {
		log.debug("entering the service method getTopoTenAirports");
		return countryRepo.queryTopAirports(REPORT_TOP_BOTTOM_LIMIT);
	}

	@Override
	public SearchCountry searchCountryByName(String name) {
		log.debug("entering the service method searchCountryByName");
		SearchCountry result;
		Optional<Country> countryOpt = countryRepo.findByName(name);

		if (countryOpt.isPresent()) {
			result = new SearchCountry(countryOpt.get().getName());
		} else {
			List<String> suggestions = countryRepo.findByNameStartsWith(name);
			result = new SearchCountry(suggestions);
		}
		return result;
	}

	@Override
	public SearchCountry searchCountryByCode(String code) {
		log.debug("entering the service method searchCountryByCode");
		SearchCountry result;
		Optional<Country> countryOpt = countryRepo.findByCode(code);

		if (countryOpt.isPresent()) {
			result = new SearchCountry(countryOpt.get().getCode());
		} else {
			List<String> suggestions = countryRepo.findByCodeStartsWith(code);
			result = new SearchCountry(suggestions);
		}
		return result;
	}

}
