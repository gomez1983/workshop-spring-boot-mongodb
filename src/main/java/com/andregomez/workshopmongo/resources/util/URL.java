package com.andregomez.workshopmongo.resources.util;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class URL {

	
	public static String decodeParam(String text) {
		try {
			return URLDecoder.decode(text, "UTF-8");// Retorna String decodificada caso ela tenha espaço
		} catch (UnsupportedEncodingException e) {
			return ""; // Retorna String vazia caso dê algum problema
		}
	}
	
	// Método para tratar datas recebidas. Converte a data
	public static Date convertDate(String textDate, Date defaultValue) { // Recebe a data em String e também em formato padrão caso haja falha de conversão
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		sdf.setTimeZone(TimeZone.getTimeZone("GMT")); // Padrão de horário de Greenwich
		try {
			return sdf.parse(textDate);
		} catch (ParseException e) {
			return defaultValue;
		}		
	}
}
