package za.co.group.Dealerships.controller;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.ui.ConcurrentModel;
import org.springframework.ui.Model;
import za.co.group.Dealerships.domain.Country;
import za.co.group.Dealerships.domain.Dealership;
import za.co.group.Dealerships.domain.Province;
import za.co.group.Dealerships.service.CountryService;
import za.co.group.Dealerships.service.DealershipService;
import za.co.group.Dealerships.service.ProvinceService;

import java.util.List;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;

public class DealershipControllerTest {

    private DealershipController controller;

    @Mock
    private DealershipService dealershipService;

    @Mock
    private CountryService countryService;

    @Mock
    private ProvinceService provinceService;

    @Mock
    private List<Dealership> dealerships;

    @Mock
    private List<Country> countries;

    @Mock
    private List<Province> provinces;

    @Mock
    private Model model;

    @Before
    public void setUp() throws Exception {
        initMocks(this);
        controller = new DealershipController();

        ReflectionTestUtils.setField(controller,"dealershipService", dealershipService);
        ReflectionTestUtils.setField(controller,"countryService", countryService);
        ReflectionTestUtils.setField(controller,"provinceService", provinceService);
    }

    @Test
    public void testIfDependenciesAreCalledWhenEntryPageIsCalled() throws Exception {
        //setup
        String EXPECTED_VIEW = "dealerships";
        when(dealershipService.getAll()).thenReturn(dealerships);

        //execute
        String resultView = controller.dealerships(model);

        //verify
        Assert.assertEquals(EXPECTED_VIEW, resultView);
        verify(dealershipService, atLeastOnce()).getAll();

    }

    @Test
    public void testIfAllDependenciesAreCalledWhenAddingNewEntry() throws Exception {

        //setup
        String EXPECTED_VIEW = "addnew";
        when(countryService.getAll()).thenReturn(countries);
        when(provinceService.getByCountryCode(any(Country.class))).thenReturn(provinces);

        //execute

        String resultView = controller.addNewEntry(model);
        Assert.assertEquals(EXPECTED_VIEW, resultView);
        verify(countryService, atLeastOnce()).getAll();
        verify(provinceService, atLeastOnce()).getByCountryCode(any(Country.class));
    }

    @Test
    public void testAddNewDealershipWhenAllFieldsAreCorrect_AndSave() throws Exception {
        //setup
        String EXPECTED_VIEW = "redirect:dealerships";
        Dealership dealership = createAFullDealershipRecord();
        when(dealershipService.getAll()).thenReturn(dealerships);
        Model modela = new ConcurrentModel();

        String resultView = controller.add(dealership, modela, "");

        Assert.assertEquals(EXPECTED_VIEW, resultView);
        verify(dealershipService, atLeastOnce()).add(dealership);
        verify(dealershipService, atLeastOnce()).getAll();
        Assert.assertEquals(modela.getAttribute("dealerships"), dealerships);
    }

    @Test
    public void testAddNewDealershipWhenAllFieldsAreCorrect_AndCancelBeforeSaving() throws Exception {
        //setup
        String EXPECTED_VIEW = "redirect:dealerships";
        Dealership dealership = createAFullDealershipRecord();
        when(dealershipService.getAll()).thenReturn(dealerships);
        Model modela = new ConcurrentModel();

        String resultView = controller.add(dealership, modela, "Cancel");

        Assert.assertEquals(EXPECTED_VIEW, resultView);
        verify(dealershipService, atLeastOnce()).getAll();
        Assert.assertEquals(modela.getAttribute("dealerships"), dealerships);
    }

    @Test
    public void testAddNewDealershipWhenPostalCodeIsIncorrect() throws Exception {
        //setup
        String EXPECTED_VIEW = "addnew";
        Dealership dealership = createADealershipRecord_IncorrectPostalCode();
        when(countryService.getAll()).thenReturn(countries);
        when(provinceService.getByCountryCode(any(Country.class))).thenReturn(provinces);
        Model modela = new ConcurrentModel();

        String resultView = controller.add(dealership, modela, "");

        Assert.assertEquals(EXPECTED_VIEW, resultView);
        verify(countryService, atLeastOnce()).getAll();
        verify(provinceService, atLeastOnce()).getByCountryCode(any(Country.class));
        Assert.assertEquals(modela.getAttribute("countries"), countries);
        Assert.assertEquals(modela.getAttribute("provinces"), provinces);
        Assert.assertEquals(modela.getAttribute("wronglength"), true);
        Assert.assertEquals(modela.getAttribute("dealership"), dealership);
    }

    @Test
    public void testAddNewDealershipWhenNotAllFieldsProvided() throws Exception {
        //setup
        String EXPECTED_VIEW = "addnew";
        Dealership dealership = createADealershipRecord_NotAllFieldsProvided();
        when(countryService.getAll()).thenReturn(countries);
        when(provinceService.getByCountryCode(any(Country.class))).thenReturn(provinces);
        Model modela = new ConcurrentModel();

        String resultView = controller.add(dealership, modela, "");

        Assert.assertEquals(EXPECTED_VIEW, resultView);
        verify(countryService, atLeastOnce()).getAll();
        verify(provinceService, atLeastOnce()).getByCountryCode(any(Country.class));
        Assert.assertEquals(modela.getAttribute("countries"), countries);
        Assert.assertEquals(modela.getAttribute("provinces"), provinces);
        Assert.assertEquals(modela.getAttribute("notallprovided"), true);
        Assert.assertEquals(modela.getAttribute("dealership"), dealership);
        
    }

    private Dealership createADealershipRecord_NotAllFieldsProvided() {
        Dealership dealership = new Dealership();
        dealership.setDealershipId(1L);
        dealership.setDealershipName("Name");
        dealership.setLine1("line1");
        dealership.setLine2("line2");
        dealership.setSuburb("suburb");
       // dealership.setCity("City");
        dealership.setPostalCode("1123");
        dealership.setProvince("Province");
        dealership.setCountry("Country");
        return dealership;
    }

    private Dealership createADealershipRecord_IncorrectPostalCode() {
        Dealership dealership = new Dealership();
        dealership.setDealershipId(1L);
        dealership.setDealershipName("Name");
        dealership.setLine1("line1");
        dealership.setLine2("line2");
        dealership.setSuburb("suburb");
        dealership.setCity("City");
        dealership.setPostalCode("1");
        dealership.setProvince("Province");
        dealership.setCountry("Country");
        return dealership;
    }

    private Dealership createAFullDealershipRecord() {

        Dealership dealership = new Dealership();
        dealership.setDealershipId(1L);
        dealership.setDealershipName("Name");
        dealership.setLine1("line1");
        dealership.setLine2("line2");
        dealership.setSuburb("suburb");
        dealership.setCity("City");
        dealership.setPostalCode("1234");
        dealership.setProvince("Province");
        dealership.setCountry("Country");
        return dealership;
    }

}