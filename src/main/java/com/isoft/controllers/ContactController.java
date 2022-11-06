package com.isoft.controllers;

import com.isoft.conifg.AppProps;
import com.isoft.models.Contact;
import com.isoft.services.ContactService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Slf4j
@Controller
public class ContactController {
    private final ContactService service;
    private static final String REDIRECT_DISPLAY_MESSAGES = "redirect:/displayMessages/page/";
    private static final String CONTACT = "contact";
    private static final String MESSAGES = "messages";
    private AppProps appProps;

    public ContactController(ContactService service, AppProps appProps) {
        this.service = service;
        this.appProps = appProps;
    }

    @GetMapping(CONTACT)
    public String displayPage(Model model) {
        log.info("info inside contact page");
        log.warn("warn inside contact page");
        log.error("error inside contact page");
        log.debug("debug inside contact page");
        log.trace("trace inside contact page");

        log.info("page size: " + appProps.getPageSize());
        log.info("contacts info: " + appProps.getContact());
        log.info("contacts names: " + appProps.getNames());

        model.addAttribute("contact", new Contact());
        return CONTACT;

    }

    @GetMapping("closeMessage")
    public String closeMessage(@RequestParam int id, HttpSession session) {
        if (!service.closeMessage(id))
            return MESSAGES;
        return REDIRECT_DISPLAY_MESSAGES + session.getAttribute("pageNum") +
                "?sortField=" + session.getAttribute("sortField") + "&sortDir=" + session.getAttribute("sortDir");
    }

    @PostMapping("submitMsg")
    public /*ModelAndView*/ String saveContactRequest(
            @Valid Contact contact, Errors errors) {
        if (errors.hasFieldErrors()) {
            log.error("Some fields does not meet our conditions : " + errors);
            return CONTACT;
        }
        service.saveContactDetail(contact);
        return "redirect:/contact";
    }

    @RequestMapping("displayMessages/page/{pageNum}")
    public String displayMessages(Model model,
                                  @PathVariable int pageNum,
                                  @RequestParam String sortField,
                                  @RequestParam String sortDir, HttpSession session) {
        session.setAttribute("pageNum", pageNum);
        session.setAttribute("sortDir", sortDir);
        session.setAttribute("sortField", sortField);

        Page<Contact> contactPage = service.getOpenMessages(pageNum, sortField, sortDir);
        model.addAttribute("currentPage", pageNum);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("sortField", sortField);
        model.addAttribute("totalPages", contactPage.getTotalPages());
        model.addAttribute("openMessages", contactPage.getContent());
        model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");

        return MESSAGES;
    }
}
