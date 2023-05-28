package com.haison.Spring;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@OpenAPIDefinition(
		// Configure OpenApi Swagger
		info = @Info(
				title = "Spring Boot Rest API Documentation",
				description = "Spring Boot Rest API Documentation",
				version = "V1.0",
				contact = @Contact(
						name = "Hai Son",
						email = "hson22102000@gmail.com",
 						url = "https://www.facebook.com/profile.php?id=100026399469554"
				)
		)
)
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}


