package com.rentingit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

import com.rentingit.config.SwagerConfiguration;

@SpringBootApplication
@EnableSwagger2
@Import(SwagerConfiguration.class)
public class RentingItApplication {

	public static void main(String[] args) {
		SpringApplication.run(RentingItApplication.class, args);
	}

}
