package com.gft.peopleservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class PeopleServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(PeopleServiceApplication.class, args);
	}

}
