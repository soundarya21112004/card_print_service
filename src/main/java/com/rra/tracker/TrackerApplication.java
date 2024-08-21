package com.rra.tracker;

import com.rra.tracker.controller.SendEmail;
import com.rra.tracker.repository.PrintMasterRepository;
import com.rra.tracker.repository.PrintSlaveRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

@SpringBootApplication
@EnableScheduling
@EnableAsync
public class TrackerApplication extends SpringBootServletInitializer {
	private static final long MILLIS_IN_A_DAY = 1000 * 60 * 60 * 24;
	public static void main(String[] args) throws IOException {

		SpringApplication.run(TrackerApplication.class, args);



	}

}
