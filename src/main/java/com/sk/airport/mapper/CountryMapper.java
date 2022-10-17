package com.sk.airport.mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import com.sk.airport.dto.CountryDetailsDto;
import com.sk.airport.entity.Country;
import com.sk.airport.exception.ExceptionMessage;
import com.sk.airport.exception.MapperNullObjectException;

@Component
public class CountryMapper {
	
	private AirportMapper airportMapper;
	
	public CountryMapper(AirportMapper airportMapper) {
		this.airportMapper = airportMapper;
	}

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
	
	public List<CountryDetailsDto> fromEntityListToDtoList(List<Country> entityList) {
		if(entityList != null){
            List<CountryDetailsDto> result = new ArrayList<>();
            entityList.forEach(item ->
                    result.add(fromEntityToDto(item)));
            return result;
        }else {
            throw new MapperNullObjectException(ExceptionMessage.CANNOT_MAP_NULL_MSG);

        }
	}


}
