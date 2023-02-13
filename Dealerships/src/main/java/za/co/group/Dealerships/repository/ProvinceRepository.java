package za.co.group.Dealerships.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import za.co.group.Dealerships.domain.Country;
import za.co.group.Dealerships.domain.Province;
import za.co.group.Dealerships.domain.ProvinceId;

import java.util.List;

public interface ProvinceRepository extends JpaRepository<Province, ProvinceId> {
    List<Province> findByCountryCode(Country countryCode);
}
