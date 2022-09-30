package com.isoft.controllers;

import com.isoft.models.User;
import com.isoft.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping(value = "public", method = RequestMethod.GET)
public class PublicController {
    private final UserService service;

    public PublicController(UserService service) {
        this.service = service;
    }

    @GetMapping("register")
    public String showRegisterPage(Model model) {
        model.addAttribute("newUser", new User());
        return "register";
    }

    @PostMapping("createUser")
    public String createUser(@Valid @ModelAttribute("newUser") User newUser, Errors result) {
        if (result.hasErrors())
            return "register";
        return "redirect:login?register=true";

    }
}
