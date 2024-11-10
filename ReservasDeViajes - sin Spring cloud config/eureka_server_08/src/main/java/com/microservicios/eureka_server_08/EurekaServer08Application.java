package com.microservicios.eureka_server_08;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class EurekaServer08Application {

	public static void main(String[] args) {
		SpringApplication.run(EurekaServer08Application.class, args);
	}

}
