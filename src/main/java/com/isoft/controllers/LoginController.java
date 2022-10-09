package com.isoft.controllers;

import lombok.Setter;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Setter
@Controller
public class LoginController {
//    private boolean isLoggedOut=false;

    @RequestMapping(value = "login", method = {RequestMethod.GET, RequestMethod.POST})
    public String showLogin(HttpServletRequest request,
                            @RequestParam(required = false) String error,
                            @RequestParam(required = false) String logout,
                            @RequestParam(required = false) String register,
//                            @Valid @RequestParam(required = false) @NotBlank(message = "username should not be blank") String username,
//                            @Valid @RequestParam(required = false) @NotBlank(message = "password should not be blank") String password,
                            Model model
            /*BindingResult result*/) {

//        if (true) throw new RuntimeException("exception occurred!");

        String errorMessage = "";

        //        if (result.hasErrors()) {
//            result.getAllErrors().forEach(e->errorMessage.add(e.getDefaultMessage()));
//            model.addAttribute("errors", errorMessage);
//        }

        if (register != null && register.equals("true"))
            errorMessage = "Registration successful! Now you can log in";
        if (error != null && error.equals("true"))
            errorMessage = "The username or password is incorrect";

        if (logout != null && logout.equals("true")) {
//            if (!isLoggedOut) {
            new SecurityContextLogoutHandler().logout(request, null, null);
            errorMessage = "You have been successfully logged out!";
//                isLoggedOut = true;
//            }
//            else
//                errorMessage="You are currently logged out!";
        }
        model.addAttribute("errorMessage", errorMessage);

        return "login";
    }

    /**
     * This method would call if any error occurred in the program otherwise it will redirect to login?error/logout=true
     *
     * @param request
     * @param model
     * @return login page's name
     */

    @GetMapping("/logout")
    public String logout(HttpServletRequest request, Model model) {
        new SecurityContextLogoutHandler().logout(request, null, null);
        model.addAttribute("errorMessage", "You have been successfully logged out!");
        return "login";
    }
}
