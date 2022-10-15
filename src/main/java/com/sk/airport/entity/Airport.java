package com.sk.airport.entity;

import java.util.List;

import javax.persistence.*;

import lombok.Data;

    @Entity
	@Table(name = "airports")
    @Data
	public class Airport {

	  @Id
	  private Integer id;
	  private String ident;
	  private String type;
	  private String name;
	  private Double latitude_deg;
	  private Double longitude_deg;
	  private Integer elevation_ft;
	  private String continent;
	  private String iso_country;
	  private String iso_region;
	  private String municipality;
	  private String scheduled_service;
	  private String gps_code;
	  private String iata_code;
	  private String local_code;
	  private String home_link;
	  private String wikipedia_link;
	  private String keywords;
	  
	  @OneToMany(targetEntity = Runways.class, cascade = CascadeType.ALL)
	  @JoinColumn(name = "airport_ref", referencedColumnName = "id")
	  private List<Runways> runways;
	  
}
