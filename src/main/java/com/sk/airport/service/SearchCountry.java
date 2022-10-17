package com.sk.airport.service;

import java.util.Collections;
import java.util.List;

import lombok.Data;

@Data
public class SearchCountry {

	private final String country;
	private final List<String> suggestions;

	public SearchCountry(String country) {
		this.country = country;
		this.suggestions = Collections.emptyList();
	}

	public SearchCountry(List<String> suggestions) {
		this.suggestions = suggestions;
		this.country = null;
	}

}
