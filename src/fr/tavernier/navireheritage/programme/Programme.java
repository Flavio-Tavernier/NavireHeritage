package fr.tavernier.navireheritage.programme;

import fr.tavernier.navireheritage.classesmetier.Cargo;
import fr.tavernier.navireheritage.classesmetier.Port;
import fr.tavernier.navireheritage.classestechniques.Test;
import fr.tavernier.navireheritage.exceptions.GestionPortException;

public class Programme {
	public static void main(String[] args) {
		Port port = new Port("Marseille", "43.2976N", "5.3471E", 4, 3, 2, 4);
		
		Cargo cargo = new Cargo("IMO9780859", "CMA CGMA A LINCOLN", "43.43279 N", "134.76258", 140872, 148992, 23000, "marchandises diverses");

		Test.chargementInitial(port);
		
		Test.testEnregistrerArriveePrevue(port,new Cargo("IMO9780859","CMA CGM A. LINCOLN","43.43279 N","134.76258 W", 140872,148992,123000,"marchandises diverses"));
		
		try {
			Test.testEnregistrerArrivee(port, port.getUnAttendu("IMO9241061"));
			//Test.testEnregistrerArrivee(port, "IMO0000000");
		} catch (GestionPortException e) {
			System.out.println(e.getMessage());
		}
		
		System.out.println(port);
	}
}
