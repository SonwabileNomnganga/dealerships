package za.co.group.Dealerships.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.util.StringUtils;
import za.co.group.Dealerships.domain.Country;
import za.co.group.Dealerships.domain.Dealership;
import za.co.group.Dealerships.service.CountryService;
import za.co.group.Dealerships.service.DealershipService;
import za.co.group.Dealerships.service.ProvinceService;

import java.util.List;

@Slf4j
@Controller
public class DealershipController {

    public static final String BY_NAME = "name";
    public static final String BY_PROVINCE = "province";
    public static final String BY_COUNTRY = "country";
    public static final String ACTION_CANCEL = "Cancel";

    @Autowired
    private DealershipService dealershipService;

    @Autowired
    private CountryService countryService;

    @Autowired
    private ProvinceService provinceService;

    @GetMapping("/dealerships")
    public String dealerships(Model model){

        model.addAttribute("dealerships", dealershipService.getAll());
        return "dealerships";
    }

    @GetMapping("/addnew")
    public String addNewEntry( Model model){

        List<Country> countries = countryService.getAll();
        model.addAttribute("dealership", new Dealership());
        model.addAttribute("countries",  countries);
        model.addAttribute("provinces", provinceService.getByCountryCode(countries.get(0)));

        return "addnew";
    }

    @GetMapping("/add")
    public String add(@ModelAttribute(value="dealership") Dealership dealership, Model model, @RequestParam(value="action", required = false) String action){

        if(!ACTION_CANCEL.equals(action) && validPostalCode(dealership, model) && allFieldsProvided(dealership, model)) {

            dealershipService.add(dealership);
            model.addAttribute("dealerships", dealershipService.getAll());

            return "redirect:dealerships";
        }
        if(ACTION_CANCEL.equals(action)){
            model.addAttribute("dealerships", dealershipService.getAll());
            return "redirect:dealerships";
        }

        List<Country> countries = countryService.getAll();
        model.addAttribute("countries",  countries);
        model.addAttribute("provinces", provinceService.getByCountryCode(countries.get(0)));

        return "addnew";
    }


    @GetMapping(value="/dealership/update")
    public String update(@ModelAttribute(value="dealership") Dealership dealership,  Model model, @RequestParam(value="action", required = false) String action) {

        if(!ACTION_CANCEL.equals(action) && validPostalCode(dealership, model) && allFieldsProvided(dealership, model)) {
            log.info("to edit " + dealership.toString());
            dealershipService.add(dealership);
        }else{
            List<Country> countries = countryService.getAll();
            model.addAttribute("countries", countryService.getAll());
            for(Country country: countries){
                if(country.getCountryCode().equals(dealership.getCountry())) {
                    model.addAttribute("provinces", provinceService.getByCountryCode(country));
                    break;
                }
            }
            return "edit";
        }

        model.addAttribute("dealerships", dealershipService.getAll());

        return "dealerships";
    }

    @GetMapping("/search")
    public String search(@RequestParam(value="option", required=true) String option, @RequestParam(value="search", required=true) String search,
                         Model model){

        /**If time allowed I would have implemented the extra functionality below */

        if(BY_NAME.equals(option)) {
             //model.addAttribute("dealerships", dealershipService.findByDealershipName(search)
        }
        else if(BY_PROVINCE.equals(option)) {
             //model.addAttribute("dealerships", dealershipService.findByProvince(search)
        }
        else {
             //model.addAttribute("dealerships", dealershipService.findByCountry(search.toUpperCase())
        }

        return "dealerships";
    }

    @GetMapping(value="/dealership/edit")
    public String edit(@RequestParam(value="id") int id, Model model, @RequestParam(value="code") String code) {

        Dealership dealership = dealershipService.findByDealershipId(id);
        List<Country> countries = countryService.getAll();
        for(Country country: countries){
            if(country.getCountryCode().equals(code)) {
                model.addAttribute("provinces", provinceService.getByCountryCode(country));
                break;
            }
        }
        model.addAttribute("dealership", dealership);
        model.addAttribute("countries", countries);

        return "edit";
    }

    @GetMapping(value="/dealership/delete")
    public String delete(@RequestParam(value="id") int id, Model model) {

        dealershipService.delete(id);

        model.addAttribute("dealerships", dealershipService.getAll());
        model.addAttribute("deleted", true);

        return "dealerships";
    }

    private boolean validPostalCode(Dealership entry, Model model) {

        if(entry.getPostalCode().length() < 2 || entry.getPostalCode().length() > 7) {
            model.addAttribute("wronglength", true);
            model.addAttribute("dealership", entry);

            return false;
        }
        return true;
    }

    private boolean allFieldsProvided(Dealership entry, Model model) {

        if(StringUtils.isEmpty(entry.getDealershipName()) || StringUtils.isEmpty(entry.getLine1()) || StringUtils.isEmpty(entry.getLine2()) ||
                StringUtils.isEmpty(entry.getSuburb()) || StringUtils.isEmpty(entry.getCity()) || StringUtils.isEmpty(entry.getPostalCode()) ||
                StringUtils.isEmpty(entry.getCountry()) || StringUtils.isEmpty(entry.getProvince())) {
            model.addAttribute("notallprovided", true);
            model.addAttribute("dealership", entry);

            return false;
        }
        return true;

    }

}
