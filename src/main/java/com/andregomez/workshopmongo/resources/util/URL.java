package com.andregomez.workshopmongo.resources.util;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

public class URL {

	
	public static String decodeParam(String text) {
		try {
			return URLDecoder.decode(text, "UTF-8");// Retorna String decodificada caso ela tenha espaço
		} catch (UnsupportedEncodingException e) {
			return ""; // Retorna String vazia caso dê algum problema
		}
	}
}
