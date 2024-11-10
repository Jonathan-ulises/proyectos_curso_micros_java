package com.microservicios.microservicios_cliente_contactos_05;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.http.client.support.BasicAuthenticationInterceptor;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class MicroserviciosClienteContactos05Application {

	public static void main(String[] args) {
		SpringApplication.run(MicroserviciosClienteContactos05Application.class, args);
	}

	@Bean
	@LoadBalanced
	public RestTemplate template() {
		// BasicAuthenticationInterceptor interceptor;
		// interceptor = new BasicAuthenticationInterceptor("admin", "admin");
		// RestTemplate template = new RestTemplate();
		// template.getInterceptors().add(interceptor);
		// return template;
		return new RestTemplate();
	}

}
