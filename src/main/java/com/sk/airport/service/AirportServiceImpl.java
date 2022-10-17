package com.sk.airport.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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

	@Override
	public List<CountryDetailsDto> getRunwayFromCountry(RunwayRequestDto runwayRequestDto) {
		log.debug("Starting adding new recipe : {}", runwayRequestDto);
		Pageable page = PageRequest.of(runwayRequestDto.getPageNum(), runwayRequestDto.getPageSize());
		List<Country> entity = countryRepo.findByNameOrCode(runwayRequestDto.getCountryName(),
				runwayRequestDto.getCode(), page);
		return countryMapper.fromEntityListToDtoList(entity);
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
