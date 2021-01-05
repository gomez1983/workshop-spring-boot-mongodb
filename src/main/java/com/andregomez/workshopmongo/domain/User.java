package com.andregomez.workshopmongo.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="user") // Essa classe corresponde à uma coleção do MongoDB
public class User implements Serializable { // Serializable serve para que os objetos sejam convertidos em bytes para trafegar em rede e ser gravado em arquivo. 

	private static final long serialVersionUID = 1L;
	
	@Id // Em cima do atributo que for a Chave
	private String id;
	private String name;
	private String email;

	
//--Anotation @DBRef referencia uma outra coleção do MONGODB ao Spring Data. "laze = true" vai carregar os usuários sem carregar cada post deles, garantindo que não vai haver excesso de tráfico na rede.
	@DBRef(lazy = true) 
	private List<Post> posts = new ArrayList<>(); // Lista de posts é uma "coleção". Por isso está sendo iniciada aqui.
	
	public User() {
	}

	public User(String id, String name, String email) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<Post> getPosts() {
		return posts;
	}

	public void setPosts(List<Post> posts) {
		this.posts = posts;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}
