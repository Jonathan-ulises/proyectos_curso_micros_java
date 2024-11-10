package com.microservicios.microservicios_cliente_contactos_05;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.function.client.WebClient;

@SpringBootApplication
public class MicroserviciosClienteContactos05Application {

	public static void main(String[] args) {
		SpringApplication.run(MicroserviciosClienteContactos05Application.class, args);
	}

	@Bean
	public WebClient getClient() {
		return WebClient.create();
	}

}
