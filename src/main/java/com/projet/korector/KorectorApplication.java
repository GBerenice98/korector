package com.projet.korector;

import com.projet.korector.entity.Project;
import com.projet.korector.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class KorectorApplication {

	public static void main(String[] args) {
		SpringApplication.run(KorectorApplication.class, args);
	}


}
