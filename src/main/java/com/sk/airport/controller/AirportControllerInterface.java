package com.sk.airport.controller;

import java.util.List;

import javax.validation.constraints.NotNull;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.sk.airport.dto.CountryDetailsDto;
import com.sk.airport.dto.ReportRowDto;
import com.sk.airport.dto.RunwayRequestDto;
import com.sk.airport.entity.Country;
import com.sk.airport.service.SearchCountry;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

public interface AirportControllerInterface {

	@Operation(summary = "Runways from countrys", description = "Get all runways coresponding to countries", tags = { "Runways" }, responses = {
			@ApiResponse(description = "Success", responseCode = "200", content = @Content(mediaType = "application/json", schema = @Schema(implementation = CountryDetailsDto.class))),
			@ApiResponse(description = "Bad Request", responseCode = "400"),
			@ApiResponse(description = "Internal error", responseCode = "500", content = @Content) })
	public ResponseEntity<List<CountryDetailsDto>> getRunwayFromCountry(@RequestBody @Validated @NotNull RunwayRequestDto runwayRequestDto);
	
	
	@Operation(summary = "Top Airports", description = "Get Top Ten Countries with highest Airports", tags = { "Country" }, responses = {
			@ApiResponse(description = "Success", responseCode = "200", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ReportRowDto.class))),
			@ApiResponse(description = "Bad Request", responseCode = "400"),
			@ApiResponse(description = "Internal error", responseCode = "500", content = @Content) })
	ResponseEntity<List<ReportRowDto>> getTopAirports();
	
	@Operation(summary = "Country Name", description = "Country Name", tags = { "Country" }, responses = {
			@ApiResponse(description = "Success", responseCode = "200", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Country.class))),
			@ApiResponse(description = "Bad Request", responseCode = "400"),
			@ApiResponse(description = "Internal error", responseCode = "500", content = @Content) })
	ResponseEntity<SearchCountry> getCountryName(@RequestParam String name);
	
	@Operation(summary = "Country Code", description = "Country Code", tags = { "Country" }, responses = {
			@ApiResponse(description = "Success", responseCode = "200", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Country.class))),
			@ApiResponse(description = "Bad Request", responseCode = "400"),
			@ApiResponse(description = "Internal error", responseCode = "500", content = @Content) })
	ResponseEntity<SearchCountry> getCountryCode(@RequestParam String name);
	
	
}
