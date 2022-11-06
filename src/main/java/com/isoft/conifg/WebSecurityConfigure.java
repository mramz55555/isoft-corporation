package com.isoft.conifg;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class WebSecurityConfigure extends WebSecurityConfigurerAdapter {

    public static final String CUSTOMER = "CUSTOMER";
    public static final String ADMIN = "ADMIN";
    public static final String ACTUATOR = "ACTUATOR";

    //
//    @Override
//    public void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.authenticationProvider(authProvider);
    //        auth.inMemoryAuthentication()
//                .withUser("Ahmad").password("123").roles(CUSTOMER_ROLE).and()
//                .withUser("Hossein").password("456").roles(ADMIN_ROLE).and()
//                .passwordEncoder(NoOpPasswordEncoder.getInstance());
//        auth.authenticationProvider()
//    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
//        http.authorizeRequests()
//                .mvcMatchers("/home").permitAll()
//                .mvcMatchers("/about").authenticated().and().formLogin().and().httpBasic();

//        http.csrf().disable()
        http.csrf().ignoringAntMatchers("/submitMsg", "/h2-console/**", "/public/**", "/api/**", "/rest-api/**", "/actuator/**")
                .and()
                .authorizeRequests()
//                .regexMatchers("/?(home(.html)?/?)?").authenticated()
//                .mvcMatchers("/h2-console/**").permitAll()
                .mvcMatchers("/actuator/**").hasRole(ACTUATOR)
                .mvcMatchers("/rest-api/**").authenticated()
                .mvcMatchers("/api/**").authenticated()
                .mvcMatchers("/admin/**").hasRole(ADMIN)
                .mvcMatchers("/customer/**").hasRole(CUSTOMER)
                .mvcMatchers("/submitChanges").authenticated()
                .mvcMatchers("/displayProfile").authenticated()
                .mvcMatchers("/h2-console/**").permitAll()
                .mvcMatchers("/public/**").permitAll()
                .mvcMatchers("/displayMessages").hasRole(ADMIN)
                .mvcMatchers("/dashboard").authenticated()
                .mvcMatchers("/home").permitAll()
                .mvcMatchers("/apps").permitAll()
                .mvcMatchers("/contact").permitAll()
                .mvcMatchers("/about").permitAll()
                .mvcMatchers("/offers/*").permitAll()
                .and().formLogin().loginPage("/login").permitAll()
                .defaultSuccessUrl("/dashboard").failureUrl("/login?error=true")
                .and().logout().permitAll()
                .logoutSuccessUrl("/login?logout=true").invalidateHttpSession(true)
                .and()
                .httpBasic();
//        http.headers().frameOptions().disable();  --> for h2 database
    }

    @Bean
    public PasswordEncoder getEncoder() {
        return new BCryptPasswordEncoder();
    }
}
