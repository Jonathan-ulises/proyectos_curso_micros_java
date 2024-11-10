package com.microservicios.microservicio_cliente_contactos_feign;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class MicroservicioClienteContactosFeignApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroservicioClienteContactosFeignApplication.class, args);
	}

}
