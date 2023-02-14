package za.co.group.Dealerships.controller;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.springframework.test.util.ReflectionTestUtils;
import za.co.group.Dealerships.domain.Country;
import za.co.group.Dealerships.service.CountryService;
import za.co.group.Dealerships.service.ProvinceService;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.initMocks;

public class ProvinceControllerTest {

    private ProvinceController provinceController;
    @Mock
    private CountryService countryService;

    @Mock
    private ProvinceService provinceService;

    @Before
    public void setUp() {
        initMocks(this);
        provinceController = new ProvinceController();
        ReflectionTestUtils.setField(provinceController, "countryService", countryService);
        ReflectionTestUtils.setField(provinceController, "provinceService", provinceService);
    }

    @Test
    public void getProvincesByCountryCode_testIfAllDependenciesAreUsed() {

        provinceController.getProvincesByCountryCode(any(String.class));

        verify(countryService, atLeastOnce()).getByCountryCode(any(String.class));
        verify(provinceService, atLeastOnce()).getByCountryCode(any(Country.class));
    }
}