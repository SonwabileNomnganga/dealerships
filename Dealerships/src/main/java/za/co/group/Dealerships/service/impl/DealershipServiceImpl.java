package za.co.group.Dealerships.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.co.group.Dealerships.domain.Dealership;
import za.co.group.Dealerships.repository.DealershipRepository;
import za.co.group.Dealerships.service.DealershipService;

import java.util.List;

@Service
public class DealershipServiceImpl implements DealershipService {

    @Autowired
    private DealershipRepository dealershipRepository;

    @Override
    public List<Dealership> getAll() {

        return dealershipRepository.findAll();
    }

    @Override
    public void add(Dealership dealership) {

        dealershipRepository.saveAndFlush(dealership);
    }

    @Override
    public void delete(int dealershipId) {
        dealershipRepository.deleteById(Long.valueOf(dealershipId));
    }

    @Override
    public Dealership findByDealershipId(int id) {

        return dealershipRepository.findByDealershipId(Long.valueOf((long)id));
    }

}
