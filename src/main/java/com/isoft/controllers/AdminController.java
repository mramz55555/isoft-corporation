package com.isoft.controllers;

import com.isoft.models.App;
import com.isoft.models.Business;
import com.isoft.models.User;
import com.isoft.repositories.AppRepository;
import com.isoft.repositories.BusinessRepository;
import com.isoft.repositories.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RequestMapping("admin")
@Controller
public class AdminController {
    //CONSTANTS
    private static final String BUSINESS = "business";
    private static final String CURR_BUSINESS = "currBusiness";
    private static final String APP = "app";
    private static final String APPS_ADMIN = "apps-admin";
    private static final String CUSTOMER = "customer";
    private static final String APP_CUSTOMER = "appCustomer";
    private static final String CURR_APP = "currApp";
    private static final String APPS = "apps";
    private static final String REDIRECT_DISPLAY_BUSINESSES = "redirect:/admin/displayBusinesses";
    private static final String REDIRECT_DISPLAY_CUSTOMERS = "redirect:/admin/displayCustomers?businessId=";
    private static final String REDIRECT_DISPLAY_APPS = "redirect:/admin/displayApps";
    private static final String REDIRECT_VIEW_CUSTOMERS = "redirect:/admin/viewCustomers?id=";
    //REPOSITORIES
    private final BusinessRepository businessRepository;
    private final UserRepository userRepository;
    private final AppRepository appRepository;

    public AdminController(BusinessRepository businessRepository, UserRepository userRepository, AppRepository appRepository) {
        this.businessRepository = businessRepository;
        this.userRepository = userRepository;
        this.appRepository = appRepository;
    }

    //******business section******

    @GetMapping("displayBusinesses")
    public String displayBusinesses(Model model) {
        model.addAttribute(BUSINESS, new Business());
        model.addAttribute("businesses", businessRepository.findAll());
        return BUSINESS;
    }

    @PostMapping("addBusiness")
    public String addBusiness(@Valid @ModelAttribute(BUSINESS) Business business, BindingResult result) {
        if (result.hasErrors())
            return BUSINESS;
        businessRepository.save(business);
        return REDIRECT_DISPLAY_BUSINESSES;
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
        return REDIRECT_DISPLAY_BUSINESSES;
    }


    //******customer section******

    @GetMapping("displayCustomers")
    public String displayCustomers(@RequestParam int businessId, Model model, HttpSession session) {
        Optional<Business> optional = businessRepository.findById(businessId);
        if (optional.isPresent()) {
            Business currBusiness = optional.get();
            session.setAttribute(CURR_BUSINESS, currBusiness);

            model.addAttribute(BUSINESS, currBusiness);
            model.addAttribute(CUSTOMER, new User());

        } else
            throw new RuntimeException("Business not found");
        return CUSTOMER;
    }

    @PostMapping("addCustomer")
    public String addCustomer(@ModelAttribute(CUSTOMER) User user, Model model, HttpSession session) {
        String customerError = "customerError";
        Business currBusiness = (Business) session.getAttribute(CURR_BUSINESS);
        model.addAttribute(BUSINESS, session.getAttribute(CURR_BUSINESS));

        String userEmail = user.getEmail();
        if (userEmail.isBlank()) {
            model.addAttribute(customerError, "Customer email should not be blank");
            return REDIRECT_DISPLAY_CUSTOMERS + currBusiness.getId();
        }

        if (!checkNotAdmin(customerError, model, userEmail))
            return CUSTOMER;

        User userEntity = userRepository.getByEmail(user.getEmail());
        if (userEntity == null) {
            model.addAttribute(customerError, "customer does not exists");
            return CUSTOMER;
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

    //******Apps section********

    @GetMapping("displayApps")
    public String displayApps(Model model) {
        List<App> list = (List<App>) appRepository.findAll();
        model.addAttribute(APPS, list);
        model.addAttribute(APP, new App());
        return APPS_ADMIN;
    }

    @PostMapping("addNewApp")
    public String addApp(@Valid @ModelAttribute(APP) App app, BindingResult result, Model model) {
        List<App> list = (List<App>) appRepository.findAll();
        model.addAttribute(APPS, list);
        if (result.hasErrors()) {
            return APPS_ADMIN;
        }
        if (Double.parseDouble(app.getCost()) < 0) {
            result.addError(new ObjectError("invalid cost", "please provide a valid cost"));
            return APPS_ADMIN;
        }
        appRepository.save(app);
        return REDIRECT_DISPLAY_APPS;
    }

    @GetMapping("deleteApp")
    public String deleteApp(@RequestParam int id) {
        Optional<App> optional = appRepository.findById(id);
        if (optional.isPresent()) {
            App application = optional.get();
            for (User customer : application.getCustomers()) {
                customer.getApps().remove(application);
                userRepository.save(customer);
            }
            appRepository.delete(application);
        }
        return REDIRECT_DISPLAY_APPS;
    }

    @GetMapping("viewCustomers")
    public String viewCustomers(Model model, @RequestParam int id, HttpSession session) {
        Optional<App> optional = appRepository.findById(id);
        optional.ifPresent(app -> {
            session.setAttribute(CURR_APP, app);
            model.addAttribute(APP, app);
        });
        model.addAttribute(CUSTOMER, new User());
        return APP_CUSTOMER;
    }

    @PostMapping("addCustomerToApp")
    public String addCustomerToApp(@ModelAttribute(CUSTOMER) User user, Model model, HttpSession session) {

        App currApp = (App) session.getAttribute(CURR_APP);
        final String errorMessage = "errorMessage";
        model.addAttribute(APP, session.getAttribute(CURR_APP));
        model.addAttribute(CUSTOMER, new User());

        String userEmail = user.getEmail();

        if (userEmail.isBlank()) {
            model.addAttribute(errorMessage, "email must not be blank");
            return REDIRECT_VIEW_CUSTOMERS + currApp.getId();
        }

        if (!checkNotAdmin(errorMessage, model, userEmail))
            return APP_CUSTOMER;

        User customer = userRepository.getByEmail(userEmail);
        if (customer == null) {
            model.addAttribute(errorMessage, "invalid email");
            return APP_CUSTOMER;
        }

        currApp.getCustomers().add(customer);
        customer.getApps().add(currApp);
        userRepository.save(customer);
        appRepository.save(currApp);
        return REDIRECT_VIEW_CUSTOMERS + currApp.getId();
    }

    @GetMapping("deleteCustomerFromApp")
    public String deleteCustomerFromApp(@RequestParam int id, HttpSession session) {
        App currApp = (App) session.getAttribute(CURR_APP);
        Optional<User> optional = userRepository.findById(id);
        optional.ifPresent(customer -> {
            customer.getApps().remove(currApp);
            currApp.getCustomers().remove(customer);
            userRepository.save(customer);
            appRepository.save(currApp);
        });
        return REDIRECT_VIEW_CUSTOMERS + currApp.getId();
    }


    private boolean checkNotAdmin(String errorMessage, Model model, String userEmail) {
        if (userRepository.getByRoleName("ROLE_ADMIN").getEmail().equals(userEmail)) {
            model.addAttribute(errorMessage, "admin can not enroll in any app");
            return false;
        }
        return true;
    }
}