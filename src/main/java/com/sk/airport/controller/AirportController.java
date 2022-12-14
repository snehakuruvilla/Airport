package com.sk.airport.controller;

import java.util.List;

import javax.validation.constraints.NotNull;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sk.airport.dto.CountryDetailsDto;
import com.sk.airport.dto.ReportRowDto;
import com.sk.airport.dto.RunwayRequestDto;
import com.sk.airport.service.AirportService;
import com.sk.airport.service.SearchCountry;
import com.sk.airport.utils.Navigation;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping(Navigation.AIRPORT)
@Slf4j
public class AirportController implements AirportControllerInterface {

	private AirportService airportService;

	public AirportController(AirportService airportService) {
		this.airportService = airportService;
	}

	@PostMapping("/runways")
	public ResponseEntity<List<CountryDetailsDto>> getRunwayFromCountry(@RequestBody @Validated @NotNull RunwayRequestDto runwayRequestDto) {
		log.info("Starting getRunwayFromCountry method {}", runwayRequestDto);
		List<CountryDetailsDto> dtoList = airportService.getRunwayFromCountry(runwayRequestDto);
		return ResponseEntity.ok(dtoList);
	}

	@GetMapping("/topairports")
	public ResponseEntity<List<ReportRowDto>> getTopAirports() {
		log.info("Starting getTopAirports method {}");
		return ResponseEntity.ok(airportService.getTopoTenAirports());
	}

	@GetMapping("/countryname")
	public ResponseEntity<SearchCountry> getCountryName(@RequestParam String name) {
		log.info("Starting getCountryName method {}");
		return ResponseEntity.ok(airportService.searchCountryByName(name));
	}

	@GetMapping("/countrycode")
	public ResponseEntity<SearchCountry> getCountryCode(@RequestParam String code) {
		log.info("Starting getCountryCode method {}");
		return ResponseEntity.ok(airportService.searchCountryByCode(code));
	}

}
