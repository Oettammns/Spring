package com.example.demo;

import com.example.demo.Model.User;
import com.example.demo.Repository.UserRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class SpringExampleH2Application {

	public static void main(String[] args) {

		ConfigurableApplicationContext configurableApplicationContext = SpringApplication.run(SpringExampleH2Application.class, args);
		UserRepository userRepository = configurableApplicationContext.getBean(UserRepository.class);

		User user = new User("Matteo" , "Mansi" , "matteo.mansi@unito.com");
		userRepository.save(user);
	}

}
