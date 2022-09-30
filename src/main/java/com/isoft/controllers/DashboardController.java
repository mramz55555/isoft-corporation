package com.isoft.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DashboardController {
    @Autowired
    private ConfigurableApplicationContext context;

    @GetMapping("dashboard")
    public String showDashboard(/*Model model, Authentication authentication*/) {
//        model.addAttribute("username",authentication.getName());
//        model.addAttribute("roles",authentication.getAuthorities());
//        ((LoginController)context.getBean("loginController")).setLoggedOut(false);
        return "dashboard";
    }
}
