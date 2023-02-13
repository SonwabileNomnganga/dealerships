package za.co.group.Dealerships.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.co.group.Dealerships.domain.Country;
import za.co.group.Dealerships.repository.CountryRepository;
import za.co.group.Dealerships.service.CountryService;

import java.util.List;

@Service
public class CountryServiceImpl implements CountryService {

    @Autowired
    private CountryRepository repo;

    @Override
    public List<Country> getAll() {
        return repo.findAll();
    }

    @Override
    public Country getByCountryCode(String countryCode) {
        return repo.findByCountryCode(countryCode);
    }
}
