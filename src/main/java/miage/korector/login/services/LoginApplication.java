package com.projet.korector;

import com.projet.korector.controller.userController;
import com.projet.korector.entity.User;
import com.projet.korector.repository.UserRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages= {"com.projet.korector.services.implem.EnseignantServiceImp"})
public class KorectorApplication {
	private userController controller;
	public  void main(String[] args) {
		SpringApplication.run(KorectorApplication.class, args);

	}

	//@Override
	/*public void run(String [] args) {


		controller.saveUser(new User("adiagne","Awa","diagne","" +
				"adiagne@live.fr","adiagne97"));

		System.out.println("\nfindAll()");
		controller.findAllUser().forEach(x -> System.out.println(x));



	} */


}
