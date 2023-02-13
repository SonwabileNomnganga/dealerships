package za.co.group.Dealerships.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.co.group.Dealerships.domain.Country;
import za.co.group.Dealerships.domain.Province;
import za.co.group.Dealerships.repository.ProvinceRepository;
import za.co.group.Dealerships.service.ProvinceService;

import java.util.List;

@Service
public class ProvinceServiceImpl implements ProvinceService {

    @Autowired
    private ProvinceRepository repository;

    @Override
    public List<Province> getByCountryCode(Country countryCode) {
        return repository.findByCountryCode(countryCode);
    }

    @Override
    public List<Province> getAll() {
        return repository.findAll();
    }
}
