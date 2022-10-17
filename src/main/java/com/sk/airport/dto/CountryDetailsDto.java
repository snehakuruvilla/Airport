package com.sk.airport.dto;

import java.util.List;

import lombok.Data;

@Data
public class CountryDetailsDto {
	
	public CountryDetailsDto(){
	}
	
	public CountryDetailsDto(String code,String name) {
		this.code=code;
		this.name=name;
	}

	  private Long id;
	  private String code;
	  private String name;
	  private String continent;
	  private List<AirportDetailsDto> airportDetails;
}
