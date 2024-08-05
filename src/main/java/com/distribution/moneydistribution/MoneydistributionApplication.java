package com.distribution.moneydistribution;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

//@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@EnableJpaAuditing
@SpringBootApplication
public class MoneydistributionApplication {

	public static void main(String[] args) {
		SpringApplication.run(MoneydistributionApplication.class, args);
	}

}
