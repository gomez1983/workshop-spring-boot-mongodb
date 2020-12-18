package com.andregomez.workshopmongo.resources;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.andregomez.workshopmongo.domain.User;

@RestController // Indica que essa classe é um recurso Rest
@RequestMapping(value="/users") //Serve para indicar o caminho do endPoint
public class UserResource {

	@RequestMapping(method=RequestMethod.GET) // Indica que esse método é um endPoint Rest no caminho "/users". GET é o método para obter informações no padrão Rest
	public ResponseEntity<List<User>> findAll() { // Esse objeto "ResponseEntity" encapsula toda uma estrutura necessária para retornar respostas HTTP com possíveis erros e cabeçalhos... 
		User maria = new User("1", "Maria Brown", "maria@gmail.com");
		User alex = new User("2", "Alex Gree", "alex@gmail.com");
		List<User> list = new ArrayList<>(); // No Java, list é uma interface e não pode ser instanciá-la. Para instanciá-la, tem que usar o ArrayList, que é uma implementação do List
		list.addAll(Arrays.asList(maria, alex)); // Adiciona na lista os objetos indicados no parenteses;
		return ResponseEntity.ok().body(list); // Instancia do ResponseEntity.ok retorna resposta HTTP com a resposta de sucesso. O Body é o corpo da resposta, no caso a lista completa.
	}
}
