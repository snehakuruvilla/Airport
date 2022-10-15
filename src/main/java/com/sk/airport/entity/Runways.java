package com.sk.airport.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "runways")
@Data
public class Runways{

  @Id
  private Integer id;
  private Integer airport_ref;
  private String airport_ident;
  private Integer length_ft;
  private Integer width_ft;
  private String surface;
  private Boolean lighted;
  private Boolean closed;
  private String le_ident;
  private Double le_latitude_deg;
  private Double le_longitude_deg;
  private Integer le_elevation_ft;
  private Double le_heading_degT;
  private Integer le_displaced_threshold_ft;
  private String he_ident;
  private Double he_latitude_deg;
  private Double he_longitude_deg;
  private Integer he_elevation_ft;
  private Double he_heading_degT;
  private Integer he_displaced_threshold_ft;


}
