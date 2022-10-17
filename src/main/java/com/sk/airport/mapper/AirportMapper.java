package com.sk.airport.mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.sk.airport.dto.AirportDetailsDto;
import com.sk.airport.entity.Airport;
import com.sk.airport.exception.ExceptionMessage;
import com.sk.airport.exception.MapperNullObjectException;

@Component
public class AirportMapper {

	private RunwayMapper runwayMapper;

	public AirportMapper(RunwayMapper runwayMapper) {
		this.runwayMapper = runwayMapper;
	}

	public List<AirportDetailsDto> fromEntityListToDtoList(List<Airport> entityList) {
		if (entityList != null) {
			List<AirportDetailsDto> result = new ArrayList<>();
			entityList.forEach(item -> result.add(fromEntityToDto(item)));
			return result;
		} else {
			throw new MapperNullObjectException(ExceptionMessage.CANNOT_MAP_NULL_MSG);
		}
	}

	public AirportDetailsDto fromEntityToDto(Airport entity) {
		if (entity != null) {
			AirportDetailsDto dto = new AirportDetailsDto();
			dto.setName(entity.getName());
			dto.setType(entity.getType());
			dto.setIso_country(entity.getIso_country());
			dto.setIso_region(entity.getIso_region());
			dto.setRunwayList(runwayMapper.fromEntityListToDtoList(entity.getRunways()));
			return dto;
		} else {
			throw new MapperNullObjectException(ExceptionMessage.CANNOT_MAP_NULL_MSG);
		}
	}
}
