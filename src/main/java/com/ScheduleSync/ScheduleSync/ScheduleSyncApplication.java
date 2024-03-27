package com.ScheduleSync.ScheduleSync;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class ScheduleSyncApplication {

	public static void main(String[] args) {
		SpringApplication.run(ScheduleSyncApplication.class, args);
	}

	@GetMapping("/")
	public String apiRoute(){
		return "Hello from ScheduleSync";
	}
}
