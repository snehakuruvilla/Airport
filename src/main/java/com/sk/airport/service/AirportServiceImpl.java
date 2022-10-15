package com.sk.airport.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sk.airport.entity.Country;
import com.sk.airport.repository.CountryRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class AirportServiceImpl implements AirportService {

	public static final int REPORT_TOP_BOTTOM_LIMIT = 10;

	@Autowired
	private CountryRepository countryRepo;

	@Override
	public Optional<Country> getRunwayFromCountry(String name) {
		log.debug("entering the service method getRunwayFromCountry");
		return countryRepo.findByNameOrCode(name, name);
	}

	@Override
	public List<com.sk.airport.service.ReportRow> getTopoTenAirports() {
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
