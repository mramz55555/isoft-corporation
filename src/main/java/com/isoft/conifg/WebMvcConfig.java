package com.isoft.conifg;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    private static final String HOME = "home";
    private static final String APPS = "apps";
    private static final String ABOUT = "about";
    private static final String OFFERS = "offers";

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController(HOME).setViewName(HOME);
        registry.addViewController("").setViewName(HOME);
        registry.addViewController(APPS).setViewName(APPS);
        registry.addViewController(ABOUT).setViewName(ABOUT);
        registry.addViewController(OFFERS).setViewName(OFFERS);
        registry.addViewController("displayMessages").setViewName("messages");
    }
}
