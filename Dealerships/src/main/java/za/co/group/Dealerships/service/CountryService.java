package za.co.group.Dealerships.service;

import za.co.group.Dealerships.domain.Country;

import java.util.List;

public interface CountryService {

    List<Country> getAll();

    Country getByCountryCode(String countryCode);
}

