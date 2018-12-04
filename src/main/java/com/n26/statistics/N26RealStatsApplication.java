package com.n26.statistics;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@EnableAsync
public class N26RealStatsApplication {

	public static void main(String[] args) {
		SpringApplication.run(N26RealStatsApplication.class, args);
	}
}
