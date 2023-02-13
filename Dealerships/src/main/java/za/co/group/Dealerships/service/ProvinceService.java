package za.co.group.Dealerships.service;

import za.co.group.Dealerships.domain.Country;
import za.co.group.Dealerships.domain.Province;

import java.util.List;

public interface ProvinceService {

    List<Province> getByCountryCode(Country countryCode);

    Object getAll();
}
