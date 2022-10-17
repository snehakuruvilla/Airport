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
 
}
