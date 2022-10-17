package com.sk.airport.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.sk.airport.dto.ReportRowDto;
import com.sk.airport.entity.Country;

@Repository
public interface CountryRepository extends JpaRepository<Country, Long>, JpaSpecificationExecutor<Country> {

	Optional<Country> findByName(String name);
	
	Optional<Country> findByCode(String code);
	
	@Query(value = "SELECT name FROM countries c WHERE c.name like ?%",
		      nativeQuery = true)
	List<String> findByNameStartsWith(String name);
	
	@Query(value = "SELECT code FROM countries c WHERE c.code like ?%",
		      nativeQuery = true)
	List<String> findByCodeStartsWith(String code);
	
	 @Query(value = "SELECT c.name as name, count(*) as count " +
		              "FROM countries c INNER JOIN airports a ON c.code = a.iso_country " +
		              "GROUP BY c.name ORDER BY count DESC LIMIT ?1",
		      nativeQuery = true)
		  List<ReportRowDto> queryTopAirports(int limit);

	Optional<Country> findByNameOrCode(String name, String name2);

}
