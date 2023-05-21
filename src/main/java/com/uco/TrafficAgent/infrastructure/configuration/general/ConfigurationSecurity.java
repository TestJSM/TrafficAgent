package com.uco.TrafficAgent.infrastructure.configuration.general;

import com.uco.TrafficAgent.infrastructure.filter.FilterAuth;
import com.uco.TrafficAgent.infrastructure.service.token.ServiceValidateToken;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConfigurationSecurity {
    private static final String URL_PATTERN = "/*";

    @Bean
    public FilterRegistrationBean<FilterAuth> authenticationFilter(ServiceValidateToken tokenValidationService){
        FilterRegistrationBean<FilterAuth> registrationBean = new FilterRegistrationBean<>();

        registrationBean.setFilter(new FilterAuth(tokenValidationService, new String[]{"/user/login","/user/save",
                "/swagger-ui.html","/swagger-ui/index.html","/v3/api-docs/swagger-config","/v3/api-docs",
                "*.js","*.css","*.png"}));
        registrationBean.addUrlPatterns(URL_PATTERN);

        return registrationBean;
    }
}
