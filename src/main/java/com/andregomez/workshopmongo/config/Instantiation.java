package com.andregomez.workshopmongo.config;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.andregomez.workshopmongo.domain.Post;
import com.andregomez.workshopmongo.domain.User;
import com.andregomez.workshopmongo.dto.AuthorDTO;
import com.andregomez.workshopmongo.dto.CommentDTO;
import com.andregomez.workshopmongo.repository.PostRepository;
import com.andregomez.workshopmongo.repository.UserRepository;

@Configuration // Anotação serve para o Spring entender essa é uma configuração.
public class Instantiation implements CommandLineRunner {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PostRepository postRepository;
	
	@Override
	public void run(String... args) throws Exception {
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy"); // Para formatar o padrão de datas de cada post
		sdf.setTimeZone(TimeZone.getTimeZone("GMT")); // Instancia a data considerando a data de Greenwich
		
		userRepository.deleteAll(); // Limpa a coleção de usuário no MongoDb. 
		postRepository.deleteAll(); // Limpa a coleção de post no MongoDb.
		
		User maria = new User(null, "Maria Brown", "maria@gmail.com"); // "null" em Id porque o BD vai gerar o Id. 
		User alex = new User(null, "Alex Green", "alex@gmail.com"); // "null" em Id porque o BD vai gerar o Id.
		User bob = new User(null, "Bob Grey", "bob@gmail.com"); // "null" em Id porque o BD vai gerar o Id.
		
		userRepository.saveAll(Arrays.asList(maria, alex, bob)); // Primeiro salva os usuários para que eles tenham um ID próprio.
		
		Post post1 = new Post(null, sdf.parse("21/03/2018"), "Partiu viagem", "Vou viajar para São Paulo. Abraços!", new AuthorDTO(maria));
		Post post2 = new Post(null, sdf.parse("23/03/2018"), "Bom dia", "Acordei feliz hoje!", new AuthorDTO(maria));
		
		CommentDTO c1 = new CommentDTO("Boa viagem, mano!", sdf.parse("21/03/2018"), new AuthorDTO(alex));
		CommentDTO c2 = new CommentDTO("Aproveite", sdf.parse("22/03/2018"), new AuthorDTO(bob));
		CommentDTO c3 = new CommentDTO("Tenha um ótimo dia!", sdf.parse("23/03/2018"), new AuthorDTO(alex));
		
		post1.getComments().addAll(Arrays.asList(c1, c2));
		post2.getComments().addAll(Arrays.asList(c3));
		
		postRepository.saveAll(Arrays.asList(post1, post2));
		
		maria.getPosts().addAll(Arrays.asList(post1, post2)); // Indica que, ao acessar Maria, apenas os posts 1 e 2 serão indicados.
		userRepository.save(maria); // Salva os posts na lista de Maria.
	}

}
