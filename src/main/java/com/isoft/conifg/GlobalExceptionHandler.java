package com.isoft.conifg;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice(annotations = Controller.class)
@Order(2)
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public String showErrorPage(Model model, Exception exception) {
        model.addAttribute("errorMessage", exception.getMessage());
        exception.printStackTrace();
        return "error";
    }
}
