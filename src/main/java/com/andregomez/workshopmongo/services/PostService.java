package com.andregomez.workshopmongo.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.andregomez.workshopmongo.domain.Post;
import com.andregomez.workshopmongo.repository.PostRepository;
import com.andregomez.workshopmongo.services.exception.ObjectNotFoundException;

@Service //Anotation que avisa ao Spring que a classe será um serviço que pode ser injetável em outras classes.
public class PostService {

	@Autowired // Essa declaração faz o serviço acessar o repositório
	private PostRepository repo; // Quando você declara um objeto dentro de uma classe usando o "Autowired", o Spring procura a definição do objeto (neste caso o repositório) e instancia o objeto. 
	
	public Post findById(String id) {
		Optional<Post> obj = repo.findById(id); // Método do repositório onde você passa o Id e tem como retorno o usuário com o Id indicado.
		return obj.orElseThrow(() -> new ObjectNotFoundException("Post não encontrado"));
	}
	
	public List<Post> findByTitle(String text) {
		return repo.searchTitle(text);
	}
	
	public List<Post> fullSearch(String text, Date minDate, Date maxDate) { // A busca até uma determinada data tem que ser até às 24h deste dia, e não na primeira hora do dia (no caso, 0h)
		maxDate = new Date(maxDate.getTime() + 24 * 60 * 60 * 1000); // Acrecenta 1 dia na data máxima para que se resolva a questão acima
		return repo.fullSearch(text, minDate, maxDate);
	}
}
