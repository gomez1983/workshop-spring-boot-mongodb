package com.andregomez.workshopmongo.resources;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.andregomez.workshopmongo.domain.User;
import com.andregomez.workshopmongo.dto.UserDTO;
import com.andregomez.workshopmongo.services.UserService;

@RestController // Indica que essa classe é um recurso Rest
@RequestMapping(value="/users") //Serve para indicar o caminho do endPoint
public class UserResource {

	@Autowired
	private UserService service; // Esse é o serviço acessado pelo controlador REST
		
	@RequestMapping(method=RequestMethod.GET) // Indica que esse método é um endPoint Rest no caminho "/users". GET é o método para obter informações no padrão Rest
	public ResponseEntity<List<UserDTO>> findAll() { // Esse objeto "ResponseEntity" encapsula toda uma estrutura necessária para retornar respostas HTTP com possíveis erros e cabeçalhos... 
		List<User> list = service.findAll(); // Busca no BD os usuários e guarda na lista.
		List<UserDTO> listDto = list.stream().map(x -> new UserDTO(x)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDto); // Devolte a lista na resposta da requisição
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET) // Indica que esse método tem o valor de um "Id" e é um endPoint Rest no caminho "/users". GET é o método para obter informações no padrão Rest
	public ResponseEntity<UserDTO> findById(@PathVariable String id) { // O tipo de retorno do método é "UserDTO". O método recebe como argumento um id. E esse ID tem que casar com o ID da URL, por isso o "@PathVariable"
		User obj = service.findById(id); // O objeto User recebe o findById
		return ResponseEntity.ok().body(new UserDTO(obj)); // Converte "obj" pra "UserDTO"
	}
	
}
