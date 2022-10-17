package com.sk.airport.dto;

import java.util.List;

import com.sk.airport.entity.Runways;

import lombok.Data;

@Data
public class AirportDetailsDto {

	  private String type;
	  private String name;
	  private String iso_country;
	  private String iso_region;
	  private List<RunwayDto> runwayList;
}
