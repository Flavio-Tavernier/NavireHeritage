package fr.tavernier.navireheritage.classestechniques;

import fr.tavernier.navireheritage.classesmetier.Cargo;
import fr.tavernier.navireheritage.classesmetier.Navire;
import fr.tavernier.navireheritage.classesmetier.Port;
import fr.tavernier.navireheritage.classesmetier.Tanker;
import fr.tavernier.navireheritage.exceptions.GestionPortException;

public abstract class Test {
	
	
	public static void chargementInitial(Port port, Navire navire) {
		try {
			port.enregistrerArriveePrevue(navire);
		}
		catch (GestionPortException e) {
			System.out.println(e.getMessage());
		}
	}
	
	
	
	

}
