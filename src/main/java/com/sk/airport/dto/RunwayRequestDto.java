package com.sk.airport.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

import lombok.Data;

@Data
public class RunwayRequestDto {

	private String countryName;
	
	private String code;
	
	@PositiveOrZero
    @NotNull
    private Integer pageNum;

    @PositiveOrZero
    @NotNull
    private Integer pageSize ;
	
}
