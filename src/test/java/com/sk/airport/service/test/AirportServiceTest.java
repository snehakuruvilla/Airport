package com.sk.airport.service.test;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.when;

import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.sk.airport.dto.CountryDetailsDto;
import com.sk.airport.dto.ReportRowDto;
import com.sk.airport.dto.RunwayRequestDto;
import com.sk.airport.entity.Country;
import com.sk.airport.mapper.CountryMapper;
import com.sk.airport.repository.CountryRepository;
import com.sk.airport.service.AirportService;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ExtendWith(MockitoExtension.class)
class AirportServiceTest {
	
	private AirportService airportService;
	
	@MockBean
	private CountryRepository countryRepo;

	@MockBean
	private CountryMapper countryMapper;
	
	
	@Test
    void getRunwayFromCountryTest(){
		RunwayRequestDto filter = new RunwayRequestDto();
		filter.setCode("PR");
		filter.setCountryName("Gree");
		filter.setPageNum(0);
		filter.setPageSize(10);

       when(countryRepo.findByNameOrCode(filter.getCountryName(),filter.getCode(), any(Pageable.class)))
               .thenReturn(Collections.singletonList(new Country()));
       when(countryMapper.fromEntityListToDtoList(anyList())).thenReturn(Collections.singletonList(new CountryDetailsDto()));
       List<CountryDetailsDto> res =airportService.getRunwayFromCountry(filter);
       Assertions.assertEquals(1L, res.size());
   }
	
	@Test
    void getTopoTenAirportsTest(){
		int limit = 10;
		
       when(countryRepo.queryTopAirports(limit))
               .thenReturn(Collections.singletonList(any()));
       List<ReportRowDto> res = airportService.getTopoTenAirports();
       Assertions.assertEquals(10L, res.size());
   }
	
	

}
