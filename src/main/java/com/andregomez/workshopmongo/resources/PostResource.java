package com.andregomez.workshopmongo.resources;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.andregomez.workshopmongo.domain.Post;
import com.andregomez.workshopmongo.resources.util.URL;
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
	
	@RequestMapping(value="/titlesearch", method=RequestMethod.GET) // Indica que esse método tem o valor de um "title" e é um endPoint Rest no caminho "/users". GET é o método para obter informações no padrão Rest
	public ResponseEntity<List<Post>> findByTitle(@RequestParam(value="text", defaultValue="") String text) { // Procura numa lista de post o valor do parâmetro indicado
		text = URL.decodeParam(text); // Decodifica o texto
		List<Post> list = service.findByTitle(text); // A lista de psot recebe o resultado da busca
		return ResponseEntity.ok().body(list); // retorna uma resposta cujo corpo vai ser a lista acima
	}
	
	@RequestMapping(value="/fullsearch", method=RequestMethod.GET)
 	public ResponseEntity<List<Post>> fullSearch(
 			@RequestParam(value="text", defaultValue="") String text,
 			@RequestParam(value="minDate", defaultValue="") String minDate,
 			@RequestParam(value="maxDate", defaultValue="") String maxDate) {
		text = URL.decodeParam(text); // Decodifica o texto
		Date min = URL.convertDate(minDate, new Date(0L)); // Trata a data mínima convertendo a data. Caso dê problema, ele gera uma data mínima (0L);
		Date max = URL.convertDate(maxDate, new Date()); // Trata a data máxima convertendo a data. Caso dê problema, ele gera uma com o instante atual do sistema "newDate";
		List<Post> list = service.fullSearch(text, min, max);
		return ResponseEntity.ok().body(list);
	}
}