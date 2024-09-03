package com.bengonohugues.emprunt_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class EmpruntServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(EmpruntServiceApplication.class, args);
	}

}
