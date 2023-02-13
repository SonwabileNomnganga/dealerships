package za.co.group.Dealerships.service;

import za.co.group.Dealerships.domain.Dealership;

import java.util.List;

public interface DealershipService {

    List<Dealership> getAll();

    void add(Dealership dealership);

    void delete(int dealershipId);

    Dealership findByDealershipId(int id);
}
