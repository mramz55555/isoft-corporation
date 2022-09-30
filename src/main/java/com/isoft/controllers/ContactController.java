package com.isoft.controllers;

import com.isoft.models.Contact;
import com.isoft.services.ContactService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;

@Slf4j
@Controller
public class ContactController {
    private final ContactService service;

    public ContactController(ContactService service) {
        this.service = service;
    }

    @GetMapping("contact")
    public String displayPage(Model model) {
        model.addAttribute("contact", new Contact());
        return "contact";

    }
//    @PostMapping("submitMsg")
//    public ModelAndView saveRequest(@RequestParam String name,
//                                    @RequestParam String mobileNum,
//                                    @RequestParam String email,
//                                    @RequestParam String subject,
//                                    @RequestParam String message) {
//
//        log.info("name : " + name + "\n" +
//                "mobile number : " + mobileNum + "\n" +
//                "subject : " + subject + "\n" +
//                "email : " + email + "\n" +
//                "message : " + message);
//
//        return new ModelAndView("redirect:contact");
//    }

    @GetMapping("closeMessage")
    public String closeMessage(@RequestParam int id/*, Authentication authentication*/) {
        if (service.closeMessage(id/*, authentication.getName()*/))
            return "redirect:displayMessages";
        return "displayMessages";
    }

    @PostMapping("submitMsg")
    public /*ModelAndView*/ String saveContactRequest(
            @Valid Contact contact, Errors errors) {
        if (errors.hasFieldErrors()) {
            log.error("Some fields does not meet our conditions : " + errors);
            return "contact";
        }
        service.saveContactDetail(contact);
        return "redirect:contact";
    }

    @RequestMapping("displayMessages")
    public String displayMessages(Model model) {
        model.addAttribute("openMessages", service.getOpenMessages());
        return "messages";
    }
}
