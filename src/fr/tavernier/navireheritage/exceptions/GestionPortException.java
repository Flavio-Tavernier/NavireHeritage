package fr.tavernier.navireheritage.exceptions;

import java.time.format.DateTimeFormatter;

public class GestionPortException extends RuntimeException {
	

	
	public GestionPortException(String message) {
		super("Erreur le 16/03/2024\n" + message + "\n");
	}
	

}
