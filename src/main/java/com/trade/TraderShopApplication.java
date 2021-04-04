package com.trade;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class TraderShopApplication {

	public static void main(String[] args) {
		SpringApplication.run(TraderShopApplication.class, args);
	}

}
