package com.sk.airport.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.sk.airport.dto.CountryDetailsDto;
import com.sk.airport.dto.ReportRowDto;
import com.sk.airport.dto.RunwayRequestDto;

public interface AirportService {

	public Page<CountryDetailsDto> getRunwayFromCountry(RunwayRequestDto runwayRequestDto);
	
	public SearchCountry searchCountryByName(String name);
	
	public SearchCountry searchCountryByCode(String code);
	
	public List<ReportRowDto> getTopoTenAirports();
	
	}
