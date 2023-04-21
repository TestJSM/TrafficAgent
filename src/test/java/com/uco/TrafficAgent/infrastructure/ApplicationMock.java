package com.uco.TrafficAgent.infrastructure;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@ComponentScan("com.uco")
@EnableJpaRepositories(basePackages = "com.uco")
public class ApplicationMock {
}
