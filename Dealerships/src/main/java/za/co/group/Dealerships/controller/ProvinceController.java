package za.co.group.Dealerships.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import za.co.group.Dealerships.domain.Country;
import za.co.group.Dealerships.domain.Dealership;
import za.co.group.Dealerships.domain.Province;
import za.co.group.Dealerships.service.CountryService;
import za.co.group.Dealerships.service.ProvinceService;

import java.util.List;

@Slf4j
@RestController
public class ProvinceController {

    @Autowired
    private CountryService countryService;

    @Autowired
    private ProvinceService provinceService;

    @RequestMapping(value="/get/{code}", method= RequestMethod.GET, produces = "application/json")
    public List<Province> getProvincesByCountryCode(@PathVariable(value = "code", required = true)String code) {

        log.info("getting provinces for country" + code);

        Country country =  countryService.getByCountryCode(code);
        return provinceService.getByCountryCode(country);
    }
}
