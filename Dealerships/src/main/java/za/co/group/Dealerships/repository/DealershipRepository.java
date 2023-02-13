package za.co.group.Dealerships.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import za.co.group.Dealerships.domain.Dealership;

public interface DealershipRepository extends JpaRepository<Dealership, Long> {
    Dealership findByDealershipId(Long id);
}
