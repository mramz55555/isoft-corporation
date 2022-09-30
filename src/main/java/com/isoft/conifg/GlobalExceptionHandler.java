package com.isoft.conifg;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public String showErrorPage(Model model, Exception exception) {
        model.addAttribute("errorMessage", exception.getMessage());
        exception.printStackTrace();
        return "error";
    }
}
