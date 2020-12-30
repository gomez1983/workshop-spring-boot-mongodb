package com.andregomez.workshopmongo.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.andregomez.workshopmongo.domain.User;
import com.andregomez.workshopmongo.repository.UserRepository;

@Configuration // Anotação serve para o Spring entender essa é uma configuração.
public class Instantiation implements CommandLineRunner {

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public void run(String... args) throws Exception {
		
		userRepository.deleteAll(); // Limpa a coleção no MongoDb.
		
		User maria = new User(null, "Maria Brown", "maria@gmail.com"); // "null" em Id porque o BD vai gerar o Id. 
		User alex = new User(null, "Alex Green", "alex@gmail.com"); // "null" em Id porque o BD vai gerar o Id.
		User bob = new User(null, "Bob Grey", "bob@gmail.com"); // "null" em Id porque o BD vai gerar o Id.
		
		userRepository.saveAll(Arrays.asList(maria, alex, bob));
	}

}
