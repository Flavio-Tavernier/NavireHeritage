package fr.tavernier.navireheritage.programme;

import fr.tavernier.navireheritage.classesmetier.Cargo;
import fr.tavernier.navireheritage.classesmetier.Port;
import fr.tavernier.navireheritage.classestechniques.Test;

public class Programme {
	public static void main(String[] args) {
		Port port = new Port("Marseille", "43.2976N", "5.3471E", 4, 3, 2, 4);
		
		Cargo cargo = new Cargo("IMO9780859", "CMA CGMA A LINCOLN", "43.43279 N", "134.76258", 140872, 148992, 23000, "marchandises diverses");

		Test.chargementInitial(port, cargo);
		
		System.out.println(port);
		
	}

}
