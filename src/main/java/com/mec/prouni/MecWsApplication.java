package com.mec.prouni;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MecWsApplication {

	public static void main(String[] args) {
		SpringApplication.run(MecWsApplication.class, args);
		// System.out.println(new BCryptPasswordEncoder().encode("abc123"));
	}

}
