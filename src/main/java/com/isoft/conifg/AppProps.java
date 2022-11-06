package com.isoft.conifg;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Min;
import java.util.List;
import java.util.Map;

@Component
@Getter
@Setter
@PropertySource("classpath:application_uat.properties")
@ConfigurationProperties(prefix = "isoft")
@Validated
public class AppProps {
    @Min(value = 1, message = "page size must be greater than zero")
    private int pageSize;
    private List<String> names;
    private Map<String, Object> contact;
}
