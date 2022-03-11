package ru.eremenko.promotion;

import org.springdoc.core.GroupedOpenApi;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class PromotionApplication {

	public static void main(String[] args) {
		SpringApplication.run(PromotionApplication.class, args);
	}

	@Bean
	public GroupedOpenApi publicUserApi() {
		return GroupedOpenApi.builder()
				.group("Promotion")
				.pathsToMatch("/promo/**")
				.build();
	}
}
