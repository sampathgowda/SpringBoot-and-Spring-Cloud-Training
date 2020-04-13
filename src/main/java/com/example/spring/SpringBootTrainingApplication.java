package com.example.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
//@ComponentScan("com.example.spring,com.data.jpa.entity,com.data.jpa.repository,om.Spring.order.config")
@SpringBootApplication
public class SpringBootTrainingApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootTrainingApplication.class, args);
	}

}
