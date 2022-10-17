package com.sk.airport.controller.test;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.sk.airport.dto.AirportDetailsDto;
import com.sk.airport.dto.CountryDetailsDto;
import com.sk.airport.dto.ReportRowDto;
import com.sk.airport.dto.RunwayRequestDto;
import com.sk.airport.utils.Navigation;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@ExtendWith(MockitoExtension.class)
public class AirportControllerTest {
	
	@Autowired
    private TestRestTemplate restTemplate ;
	
	AirportDetailsDto airportDetailsDto;
	

	@Test
    void getCountryName_found(){
        String url = Navigation.AIRPORT+"/countryname/Gree";
        HttpEntity httpEntity = new HttpEntity<>(new HttpHeaders());
        ResponseEntity responseEntity = restTemplate.exchange(url, HttpMethod.GET,httpEntity,Object.class);
        Assertions.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }
	
	@Test
    void getCountryName_not_found(){
		String url = Navigation.AIRPORT+"/countryname/test";
        HttpEntity httpEntity = new HttpEntity<>(new HttpHeaders());
        ResponseEntity responseEntity = restTemplate.exchange(url, HttpMethod.GET,httpEntity,Object.class);
        Assertions.assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
    }
	
	@Test
    void getCountryCode_found(){
        String url = Navigation.AIRPORT+"/countrycode/Gree";
        HttpEntity httpEntity = new HttpEntity<>(new HttpHeaders());
        ResponseEntity responseEntity = restTemplate.exchange(url, HttpMethod.GET,httpEntity,Object.class);
        Assertions.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }
	
	@Test
    void getCountryCode_not_found(){
		String url = Navigation.AIRPORT+"/countrycode/test";
        HttpEntity httpEntity = new HttpEntity<>(new HttpHeaders());
        ResponseEntity responseEntity = restTemplate.exchange(url, HttpMethod.GET,httpEntity,Object.class);
        Assertions.assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
    }
	
	@Test
	void list_runways_success() {
		String url = Navigation.AIRPORT + "/runways";
		RunwayRequestDto filter = new RunwayRequestDto();
		filter.setCode("PR");
		filter.setCountryName("Gree");
		filter.setPageNum(0);
		filter.setPageSize(10);
		ResponseEntity<CountryDetailsDto> responseEntity = restTemplate.postForEntity(url, filter, CountryDetailsDto.class);
		Assertions.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
		Assertions.assertEquals("PR", responseEntity.getBody().getCode());
	}
	
	@Test
    void getTopAirports_found(){
        String url = Navigation.AIRPORT+"/topairports";
        HttpEntity httpEntity = new HttpEntity<>(new HttpHeaders());
        ResponseEntity<ReportRowDto> responseEntity = restTemplate.exchange(url, HttpMethod.GET,httpEntity,ReportRowDto.class);
        Assertions.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }
	
	@Test
    void getTopAirports_not_found(){
        String url = Navigation.AIRPORT+"/topairports";
        HttpEntity httpEntity = new HttpEntity<>(new HttpHeaders());
        ResponseEntity<ReportRowDto> responseEntity = restTemplate.exchange(url, HttpMethod.GET,httpEntity,ReportRowDto.class);
        Assertions.assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
    }
	
	
}
