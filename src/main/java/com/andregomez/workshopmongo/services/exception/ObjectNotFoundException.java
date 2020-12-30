package com.andregomez.workshopmongo.services.exception;

public class ObjectNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L; // Exceção padrão do Java em que o compilador não exige tratamento
	
	public ObjectNotFoundException(String msg) { // Sobreposição do construtor básico que recebe uma String com a mensagem padrão
		super(msg); // Repassa a mensagem pra superclasse RuntimeException
	}
}