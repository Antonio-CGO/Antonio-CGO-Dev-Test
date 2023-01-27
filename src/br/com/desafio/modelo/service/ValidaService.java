package br.com.desafio.modelo.service;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidaService {

	static Scanner scan = new Scanner(System.in);
	
	public static int ValidaScannerInt(String transforma) {
		while(true){
	        try{
	            int valor = Integer.parseInt(scan.next());
	            return valor;
	        }catch(Exception e){
	             System.out.print("Você dever inserir um numero de acordo com as opções! Tente novamente.");
	        }
	    }		
	}
	


	public static boolean validaPlaca(String placa) {
	    boolean result = false;

	    Pattern pattern = Pattern.compile("^[A-Z]{3}[0-9]{4}$");
	    Matcher mat = pattern.matcher(placa);
	    
	    if (!mat.matches()) {
	        result = false;
	        
	    } else {
	        result = true;

	    }
	    return result;
	}
	
	public static boolean validaModelo(String modelo) {
	    boolean result = false;

	    Pattern pattern = Pattern.compile("^(?=.*[A-Za-z])[A-Za-z0-9]{1,10}$");
	    Matcher mat = pattern.matcher(modelo);
	    
	    if (!mat.matches()) {
	        result = false;
	        
	    } else {
	        result = true;

	    }
	    return result;
	}

}
