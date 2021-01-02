package com.andregomez.workshopmongo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.andregomez.workshopmongo.domain.User;
import com.andregomez.workshopmongo.dto.UserDTO;
import com.andregomez.workshopmongo.repository.UserRepository;
import com.andregomez.workshopmongo.services.exception.ObjectNotFoundException;

@Service //Anotation que avisa ao Spring que a classe será um serviço que pode ser injetável em outras classes.
public class UserService {

	@Autowired // Essa declaração faz o serviço acessar o repositório
	private UserRepository repo; // Quando você declara um objeto dentro de uma classe usando o "Autowired", o Spring procura a definição do objeto (neste caso o repositório) e instancia o objeto. 
	
	public List<User> findAll() { // Método responsável por retornar todos os usuários do BD.
		return repo.findAll(); // Retorna todos os objetos do tipo definido (neste caso, usuário)
	}
	
	public User findById(String id) {
		Optional<User> obj = repo.findById(id); // Método do repositório onde você passa o Id e tem como retorno o usuário com o Id indicado.
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado"));
	}
	
	public User insert(User obj) { // Método para inserir usuário
		return repo.insert(obj); // Essa operação de inserção já tem pronta no Mongo.
	}

	public void delete(String id) {
		repo.deleteById(id); // Deleta por Id
	}
	
	public User update(User obj) {
		User newObj = findById(obj.getId());
		updateData(newObj, obj); // Pega os novos dados em obj e os copia para newObj
		return repo.save(newObj);
	}
	
	private void updateData(User newObj, User obj) {
		newObj.setName(obj.getName()); // Atualiza o newObj com os dados de name de obj
		newObj.setEmail(obj.getEmail()); // Atualiza o newObj com os dados de email de obj
	}

	public User fromDTO(UserDTO objDto) {
		return new User(objDto.getId(), objDto.getName(), objDto.getEmail());
	}
}
