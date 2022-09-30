package com.isoft.conifg;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;

@Configuration
public class WebSecurityConfigure extends WebSecurityConfigurerAdapter {

    public static final String USER_ROLE = "USER";
    public static final String ADMIN_ROLE = "ADMIN";
//    public static final String ANONYMOUS_ROLE = "anonymous";

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("Ahmad").password("123").roles(USER_ROLE).and()
                .withUser("Hossein").password("456").roles(ADMIN_ROLE).and()
                .passwordEncoder(NoOpPasswordEncoder.getInstance());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
//        http.authorizeRequests()
//                .mvcMatchers("/home").permitAll()
//                .mvcMatchers("/about").authenticated().and().formLogin().and().httpBasic();

//        http.csrf().disable()
        http.csrf().ignoringAntMatchers("/submitMsg", "/h2-console/**", "/public/**")
                .and()
                .authorizeRequests()
//                .regexMatchers("/?(home(.html)?/?)?").authenticated()
//                .mvcMatchers("/h2-console/**").permitAll()
                .mvcMatchers("/h2-console/**").permitAll()
                .mvcMatchers("/public/**").permitAll()
                .mvcMatchers("/displayMessages").hasRole(ADMIN_ROLE)
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
                .and().httpBasic();
        http.headers().frameOptions().disable();
    }
}
