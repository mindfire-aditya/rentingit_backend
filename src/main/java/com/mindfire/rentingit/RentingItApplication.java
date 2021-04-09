package com.mindfire.rentingit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

import com.mindfire.rentingit.config.SwagerConfiguration;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
@Import(SwagerConfiguration.class)
public class RentingItApplication {

	public static void main(String[] args) {
		SpringApplication.run(RentingItApplication.class, args);
	}

}
