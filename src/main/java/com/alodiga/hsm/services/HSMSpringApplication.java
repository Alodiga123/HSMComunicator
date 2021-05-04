package com.alodiga.hsm.services;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;




@SpringBootApplication
public class HSMSpringApplication extends SpringBootServletInitializer {
	
	
	  @Override
	  protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
	    return application.sources(HSMSpringApplication.class);
	  }

	public static void main(String[] args) {
		System.out.println("Iniciando servicio de comunicación con la HSM");
		SpringApplication.run(HSMSpringApplication.class, args);
	}
}
