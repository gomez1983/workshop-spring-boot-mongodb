package com.andregomez.workshopmongo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.andregomez.workshopmongo.domain.Post;

@Repository
public interface PostRepository extends MongoRepository<Post, String>{ // objeto postRepository será capaz de fazer operações básicas com os usuários, como: salvar, recuperar, deletar, atualizar... está tudo embutido no MongoRepository 

}
