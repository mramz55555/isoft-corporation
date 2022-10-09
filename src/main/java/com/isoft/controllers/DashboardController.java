package com.isoft.controllers;

import com.isoft.repositories.UserRepository;
import com.isoft.security.UserPassAuthProvider;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;

@Controller
public class DashboardController {

    private final UserRepository repository;

    public DashboardController(UserRepository repository) {
        this.repository = repository;
    }

    @RequestMapping(value = "dashboard", method = {RequestMethod.GET, RequestMethod.POST})
    public String showDashboard(HttpSession session/*Model model, Authentication authentication*/) {
        session.setAttribute("loggedInUser", repository.getByEmail(UserPassAuthProvider.currEmail));
//        model.addAttribute("username",authentication.getName());
//        model.addAttribute("roles",authentication.getAuthorities());
//        ((LoginController)context.getBean("loginController")).setLoggedOut(false);
        return "dashboard";
    }
}
