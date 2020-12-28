package com.andregomez.workshopmongo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.andregomez.workshopmongo.domain.User;
import com.andregomez.workshopmongo.repository.UserRepository;

@Service //Anotation que avisa ao Spring que a classe será um serviço que pode ser injetável em outras classes.
public class UserService {

	@Autowired // Essa declaração faz o serviço acessar o repositório
	private UserRepository repo; // Quando você declara um objeto dentro de uma classe usando o "Autowired", o Spring procura a definição do objeto (neste caso o repositório) e instancia o objeto. 
	
	public List<User> findAll() { // Método responsável por retornar todos os usuários do BD.
		return repo.findAll(); // Retorna todos os objetos do tipo definido (neste caso, usuário)
		
	}
}
