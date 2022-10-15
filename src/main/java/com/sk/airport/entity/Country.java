package com.sk.airport.entity;

import java.util.List;

import javax.persistence.*;

import org.hibernate.annotations.NaturalId;

import lombok.Data;

@Entity
@Table(name = "countries")
@Data
public class Country implements java.io.Serializable{

  @Id
  private Long id;

  private String code;
  private String name;
  private String continent;
  private String wikipedia_link;
  private String keywords;

  
  @OneToMany(targetEntity = Airport.class, cascade = CascadeType.ALL)
  @JoinColumn(name = "iso_country", referencedColumnName = "code")
  private List<Airport> airports;

}
