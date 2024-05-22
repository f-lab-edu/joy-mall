package com.mini.joymall;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan
public class JoyMallApplication {

	public static void main(String[] args) {
		SpringApplication.run(JoyMallApplication.class, args);
	}
}