package com.sk.airport.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sk.airport.entity.Country;
import com.sk.airport.service.AirportService;
import com.sk.airport.service.ReportRow;
import com.sk.airport.service.SearchCountry;
import com.sk.airport.utils.Navigation;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping(Navigation.AIRPORT)
@Slf4j
public class AirportController {
	
	public AirportService airportService;
	
	public AirportController(AirportService airportService) {
		this.airportService = airportService;
	}

	@GetMapping("/countryrunway")
	public Optional<Country> getRunwayFromCountry(@RequestParam String companyName) {
		log.debug("entering the getRunwayFromCountry method");
		return airportService.getRunwayFromCountry(companyName);
	}
	
	@GetMapping("/topairports")
	public List<ReportRow> getTopAirports(){
		log.debug("entering the getTopAirports method");
		return airportService.getTopoTenAirports();
	}
	
	@GetMapping("/countryname")
	public SearchCountry getCountryName(@RequestParam String name){
		log.debug("entering the getCountryName method");
		return airportService.searchCountryByName(name);
	}
	
	@GetMapping("/countrycode")
	public SearchCountry getCountryCode(@RequestParam String code){
		log.debug("entering the getCountryCode method");
		return airportService.searchCountryByCode(code);
	} 
	
}
