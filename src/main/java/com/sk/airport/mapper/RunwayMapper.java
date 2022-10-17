package com.sk.airport.mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.sk.airport.dto.RunwayDto;
import com.sk.airport.entity.Runways;
import com.sk.airport.exception.ExceptionMessage;
import com.sk.airport.exception.MapperNullObjectException;

@Component
public class RunwayMapper {
	
	public List<RunwayDto> fromEntityListToDtoList(List<Runways> entityList) {
		 if(entityList != null){
	           List<RunwayDto> result = new ArrayList<>();
	           entityList.forEach(item ->
	                   result.add(fromEntityToDto(item)));
	           return result;
	       }else {
	           throw new MapperNullObjectException(ExceptionMessage.CANNOT_MAP_NULL_MSG);
	       }
	}

	public RunwayDto fromEntityToDto(Runways entity) {
		 if(entity != null){
			 RunwayDto dto = new RunwayDto() ;
			 dto.setId(entity.getId());
			 dto.setAirport_ident(entity.getAirport_ident());
			 dto.setAirport_ref(entity.getAirport_ref());
			 dto.setLength_ft(entity.getLength_ft());
			 dto.setWidth_ft(entity.getWidth_ft());
			 dto.setSurface(entity.getSurface());
			 dto.setLighted(entity.getLighted());
			 dto.setClosed(entity.getClosed());
	         return dto;
	       }else{
	           throw new MapperNullObjectException(ExceptionMessage.CANNOT_MAP_NULL_MSG);
	       }
	}
	
}
