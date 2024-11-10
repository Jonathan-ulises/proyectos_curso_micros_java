package com.microservicios.servidor_configuracion;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@EnableConfigServer
@SpringBootApplication
public class ServidorConfiguracionApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServidorConfiguracionApplication.class, args);
	}

}
