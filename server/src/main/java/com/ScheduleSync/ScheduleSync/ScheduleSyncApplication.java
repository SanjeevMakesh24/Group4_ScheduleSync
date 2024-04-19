package com.ScheduleSync.ScheduleSync;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
@RestController
public class ScheduleSyncApplication {
	@Bean
	public RestTemplate getRestTemplate() {
		return new RestTemplate();
	}


	public static void main(String[]args) {
		SpringApplication.run(ScheduleSyncApplication.class, args);
	}

	@GetMapping(value = "/")
	public String apiRoute() {
		return "Hello from ScheduleSync";
	}

	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**")
						.allowedOrigins("*")
						.allowedMethods("*")
						.allowedHeaders("*")
						.allowCredentials(false).maxAge(3600);
			}
		};

	}
}
