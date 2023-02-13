package za.co.group.Dealerships.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import za.co.group.Dealerships.domain.Country;

public interface CountryRepository extends JpaRepository<Country, Long> {
    Country findByCountryCode(String countryCode);
}
