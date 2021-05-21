package com.mindfire.rentingit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Import;

import com.mindfire.rentingit.config.FileStorageConfig;
import com.mindfire.rentingit.config.SwagerConfiguration;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author ujjwalk
 * @author aditya
 *
 */
@SpringBootApplication
@EnableSwagger2
@Import(SwagerConfiguration.class)
@EnableConfigurationProperties({FileStorageConfig.class})
public class RentingItApplication {

	public static void main(String[] args) {
		SpringApplication.run(RentingItApplication.class, args);
	}
}
