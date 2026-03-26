package com.lucasmanoel.bffagendador;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class BffAgendadorApplication {

	public static void main(String[] args) {
		SpringApplication.run(BffAgendadorApplication.class, args);
	}

}
