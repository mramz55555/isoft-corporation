package com.isoft.services;

import com.isoft.models.Offer;
import com.isoft.repositories.OfferRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class OfferService {
    private OfferRepository repository;

    public OfferService(OfferRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public List<Offer> getOffers() {
        List<Offer> offers = new ArrayList<>();
        repository.findAll().forEach(offers::add);
        return offers;
    }
}
