package com.sk.airport.mapper;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import com.sk.airport.dto.CountryDetailsDto;
import com.sk.airport.entity.Country;
import com.sk.airport.exception.ExceptionMessage;
import com.sk.airport.exception.MapperNullObjectException;

@Component
public class CountryMapper {
	
	private AirportMapper airportMapper;

	public CountryDetailsDto fromEntityToDto(Country entity) {
		 if(entity != null){
			 CountryDetailsDto dto  = new CountryDetailsDto();
			    dto.setId(entity.getId());
			    dto.setCode(entity.getCode());
			    dto.setName(entity.getName());
			    dto.setContinent(entity.getContinent());
			    dto.setAirportDetails(airportMapper.fromEntityListToDtoList(entity.getAirports()));
	            return dto;
	        }else{
	            throw new MapperNullObjectException(ExceptionMessage.CANNOT_MAP_NULL_MSG);
	        }
	}
	
	
	public Page<CountryDetailsDto> mapPage(Page<Country> entityPage) {
        if(entityPage != null){
            return entityPage.map(this::fromEntityToDto);
        }else {
            throw new MapperNullObjectException(ExceptionMessage.CANNOT_MAP_NULL_MSG);
        }
    }

}
