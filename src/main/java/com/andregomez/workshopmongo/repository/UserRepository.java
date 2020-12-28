package com.andregomez.workshopmongo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.andregomez.workshopmongo.domain.User;

@Repository
public interface UserRepository extends MongoRepository<User, String>{ // m objeto UserRepository será capaz de fazer operações básicas com os usuários, como: salvar, recuperar, deletar, atualizar... está tudo embutido no MongoRepository 

}
