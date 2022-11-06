package com.isoft.controllers;

import com.isoft.models.Offer;
import com.isoft.services.OfferService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static com.isoft.models.Offer.Type;


@Controller
public class OfferController {
    private OfferService service;

    public OfferController(OfferService service) {
        this.service = service;
    }

    @GetMapping("offers/{display}")
    public String showOffers(@RequestParam(required = false) boolean festival,
                             @RequestParam(required = false) boolean azadi,
                             @PathVariable(required = false) String display,
                             Model model) {
        model.addAttribute("festival", festival);
        model.addAttribute("azadi", azadi);
        model.addAttribute("display", display);

        List<Offer> offers = service.getOffers();
//        List<Offer> offers = Arrays.asList(
//                new Offer(50, LocalDate.of(2022, 2, 1), "eid al-fitr", Offer.Type.FESTIVAL),
//                new Offer(39, LocalDate.of(2022, 4, 12), "Arash kamangir's eid ", Offer.Type.AZADI),
//                new Offer(66, LocalDate.of(2022, 6, 24), "eid al-qadir", Offer.Type.FESTIVAL),
//                new Offer(49, LocalDate.of(2022, 11, 22), "22 bahman's holiday", Offer.Type.AZADI),
//                new Offer(35, LocalDate.of(2023, 1, 1), "eid noroz", Offer.Type.FESTIVAL)
//        );

        for (Type type : Type.values())
            model.addAttribute(type.name(), offers.stream().filter(offer -> offer.getType().equals(type)).
                    sorted(Comparator.comparing(Offer::getDate)).collect(Collectors.toList()));
        return "offers";
    }
            }
