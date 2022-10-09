package com.isoft.controllers;

import com.isoft.models.Business;
import com.isoft.models.User;
import com.isoft.repositories.BusinessRepository;
import com.isoft.repositories.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequestMapping("admin")
@Controller
public class AdminController {
    //CONSTANTS
    private static final String REDIRECT_DISPLAY_BUSINESS = "redirect:/admin/displayBusinesses";
    private static final String REDIRECT_DISPLAY_CUSTOMERS = "redirect:/admin/displayCustomers?businessId=";
    private static final String BUSINESS = "business";
    private static final String CURR_BUSINESS = "currBusiness";
    private final BusinessRepository businessRepository;
    private final UserRepository userRepository;

    public AdminController(BusinessRepository businessRepository, UserRepository userRepository) {
        this.businessRepository = businessRepository;
        this.userRepository = userRepository;
    }

    //******business section******

    @GetMapping("displayBusinesses")
    public String displayBusinesses(Model model) {
        List<Business> businesses = new ArrayList<>();
        businessRepository.findAll().forEach(businesses::add);
        model.addAttribute(BUSINESS, new Business());
        model.addAttribute("businesses", businesses);
        return BUSINESS;
    }

    @PostMapping("addBusiness")
    public String addBusiness(@Valid @ModelAttribute(BUSINESS) Business business, BindingResult result) {
        if (result.hasErrors())
            return BUSINESS;
        businessRepository.save(business);
        return REDIRECT_DISPLAY_BUSINESS;
    }

    @GetMapping("deleteBusiness")
    public String deleteBusiness(@RequestParam int businessId) {
        Optional<Business> optional = businessRepository.findById(businessId);
        if (optional.isPresent()) {
            for (User user : optional.get().getCustomers()) {
                user.setBusiness(null);
                userRepository.save(user);
            }
            businessRepository.deleteById(businessId);
        }
        return REDIRECT_DISPLAY_BUSINESS;
    }


    //******customer section******

    @GetMapping("displayCustomers")
    public String displayCustomers(@RequestParam int businessId, Model model, HttpSession session) {
        Optional<Business> optional = businessRepository.findById(businessId);
        if (optional.isPresent()) {
            Business currBusiness = optional.get();
            session.setAttribute(CURR_BUSINESS, currBusiness);

            model.addAttribute(BUSINESS, currBusiness);
            model.addAttribute("customer", new User());

        } else
            throw new RuntimeException("Business not found");
        return "customer";
    }

    @PostMapping("addCustomer")
    public String addCustomer(@ModelAttribute("customer") User user, Model model, HttpSession session) {
        String customerError = "customerError";
        Business currBusiness = (Business) session.getAttribute(CURR_BUSINESS);

        if (user.getEmail().isBlank()) {
            model.addAttribute(customerError, "Customer email should not be blank");
            return REDIRECT_DISPLAY_CUSTOMERS + currBusiness.getId();
        }

        User userEntity = userRepository.getByEmail(user.getEmail());
        if (userEntity == null) {
            model.addAttribute(customerError, "customer does not exists");
            model.addAttribute(BUSINESS, session.getAttribute(CURR_BUSINESS));
            return "customer";
        }

        userEntity.setBusiness(currBusiness);
        currBusiness.getCustomers().add(userEntity);
        userRepository.save(userEntity);
        businessRepository.save(currBusiness);
        return REDIRECT_DISPLAY_CUSTOMERS + currBusiness.getId();
    }


    @GetMapping("deleteCustomer")
    public String deleteCustomer(@RequestParam int customerId, HttpSession session) {

        Business currBusiness = (Business) session.getAttribute(CURR_BUSINESS);
        Optional<User> optional = userRepository.findById(customerId);

        if (optional.isPresent()) {
            User userToDelete = optional.get();
            userToDelete.setBusiness(null);
            currBusiness.getCustomers().remove(userToDelete);
            businessRepository.save(currBusiness);
            userRepository.deleteById(customerId);
        } else
            throw new RuntimeException("Customer not found");
        return REDIRECT_DISPLAY_CUSTOMERS + currBusiness.getId();
    }


}