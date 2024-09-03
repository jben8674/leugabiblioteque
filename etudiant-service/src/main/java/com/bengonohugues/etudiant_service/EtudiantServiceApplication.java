package com.bengonohugues.etudiant_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class EtudiantServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(EtudiantServiceApplication.class, args);
	}

}
