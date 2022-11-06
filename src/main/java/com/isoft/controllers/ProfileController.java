package com.isoft.controllers;

import com.isoft.models.Address;
import com.isoft.models.Profile;
import com.isoft.models.User;
import com.isoft.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller("Isoft-profile-controller")
public class ProfileController {
    private final UserService service;
    private User user;

    public ProfileController(UserService service) {
        this.service = service;
    }

    @PostMapping("submitChanges")
    public String submitChanges(@Valid @ModelAttribute("profile") Profile profile, BindingResult result) {
        if (result.hasErrors())
            return "profile";

        boolean emailChanged = false;
        if (!user.getEmail().equals(profile.getEmail())) {
            emailChanged = true;
            user.setEmail(profile.getEmail());
        }

        user.setName(profile.getName());
        user.setMobileNum(profile.getMobileNum());

        if (user.getAddress() == null)
            user.setAddress(new Address());

        Address userAddress = user.getAddress();
        Address profileAddress = profile.getAddress();

        userAddress.setAddressOne(profileAddress.getAddressOne());
        userAddress.setAddressTwo(profileAddress.getAddressTwo());
        userAddress.setCity(profileAddress.getCity());
        userAddress.setState(profileAddress.getState());
        userAddress.setZipCode(profileAddress.getZipCode());

        if (service.save(emailChanged, user, result) == null)
            return "profile";
        return "redirect:/displayProfile";
    }

    @GetMapping("displayProfile")
    public String displayProfile(Model model, HttpSession session) {
        Profile currProfile = new Profile();
        user = (User) session.getAttribute("loggedInUser");
        currProfile.setName(user.getName());
        currProfile.setEmail(user.getEmail());
        currProfile.setMobileNum(user.getMobileNum());
        if (user.getAddress() != null && user.getAddress().getId() > 0)
            currProfile.setAddress(user.getAddress());

        model.addAttribute("profile", currProfile);
        return "profile";
    }
}
