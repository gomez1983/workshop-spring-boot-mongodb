package com.andregomez.workshopmongo.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.andregomez.workshopmongo.domain.Post;
import com.andregomez.workshopmongo.services.PostService;

@RestController // Indica que essa classe é um recurso Rest
@RequestMapping(value="/posts") //Serve para indicar o caminho do endPoint
public class PostResource {

	@Autowired
	private PostService service; // Esse é o serviço acessado pelo controlador REST
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET) // Indica que esse método tem o valor de um "Id" e é um endPoint Rest no caminho "/users". GET é o método para obter informações no padrão Rest
	public ResponseEntity<Post> findById(@PathVariable String id) { // O tipo de retorno do método é "UserDTO". O método recebe como argumento um id. E esse ID tem que casar com o ID da URL, por isso o "@PathVariable"
		Post obj = service.findById(id); // O objeto User recebe o findById
		return ResponseEntity.ok().body(obj); // Converte "obj" pra "UserDTO"
	}
}