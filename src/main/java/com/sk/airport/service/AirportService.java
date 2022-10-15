package com.sk.airport.service;

import java.util.List;
import java.util.Optional;

import com.sk.airport.entity.Country;

public interface AirportService {

	public Optional<Country> getRunwayFromCountry(String name);
	
	public SearchCountry searchCountryByName(String name);
	
	public SearchCountry searchCountryByCode(String code);
	
	public List<ReportRow> getTopoTenAirports();

}
