package com.andregomez.workshopmongo.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.andregomez.workshopmongo.domain.Post;

@Repository
public interface PostRepository extends MongoRepository<Post, String>{ // objeto postRepository será capaz de fazer operações básicas com os usuários, como: salvar, recuperar, deletar, atualizar... está tudo embutido no MongoRepository 

	@Query("{ 'title': { $regex: ?0, $options: 'i' } }") // Em options, a letra 'i' ignora caixa alta e baixa
	List<Post> searchTitle(String text);
	
	List<Post> findByTitleContainingIgnoreCase(String text); // Esse método procura no título a String indicada, e ignora caixa alta (IgnoreCase).
	
	@Query("{ $and: [ { date: {$gte: ?1} }, { date: { $lte: ?2} } , { $or: [ { 'title': { $regex: ?0, $options: 'i' } }, { 'body': { $regex: ?0, $options: 'i' } }, { 'comments.text': { $regex: ?0, $options: 'i' } } ] } ] }")
	List<Post> fullSearch(String text, Date minDate, Date maxDate); // São três parâmetros. O primeiro tem o valor de "0", o segundo de "1".
}
