package fr.tavernier.navireheritage.exceptions;

import java.time.format.DateTimeFormatter;

public class GestionPortException extends Exception {
	
	DateTimeFormatter dateHeureActuel = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss"); 
	
	public GestionPortException(String message) {
		super("Erreur le dateHeureActuel\n" + message);
	}
	

}
