package fr.tavernier.navireheritage.classesmetier;

import fr.tavernier.navireheritage.interfaces.ICroiserable;

import java.util.HashMap;
import java.util.Vector;

public class Croisiere extends Navire implements ICroiserable {
	private char typeNavireCroisiere;
	private int nbPassagerMaxi;
	private HashMap<String, Passager> passagers = new HashMap<String, Passager>();

	public Croisiere(String imo, String nom, String latitude, String longitude, int tonnageGT, int tonnageDWT,
			int tonnageActuel, char typeNavireCroisiere, int nbPassagerMaxi) {
		super(imo, nom, latitude, longitude, tonnageGT, tonnageDWT, tonnageActuel);
		this.typeNavireCroisiere = typeNavireCroisiere;
		this.nbPassagerMaxi = nbPassagerMaxi;

	}
	
	public Croisiere(String imo, String nom, String latitude, String longitude, int tonnageGT, int tonnageDWT,
			int tonnageActuel, char typeNavireCroisiere, int nbPassagerMaxi, Vector<Passager> passagers) {
		super(imo, nom, latitude, longitude, tonnageGT, tonnageDWT, tonnageActuel);
		this.typeNavireCroisiere = typeNavireCroisiere;
		this.nbPassagerMaxi = nbPassagerMaxi;
		
		passagers.forEach(passager -> this.passagers.put(passager.getNumPasseport(), passager));
	}

	
	public char getTypeNavireCroisiere() {
		return this.typeNavireCroisiere;
	}

	public int getNbPasssagerMaxi() {
		return this.nbPassagerMaxi;
	}
	
	public HashMap<String, Passager> getPassagers() {
		return this.passagers;
	}


	public void embarquer(Vector<Object> navire) {

	}

	public void debarquer(Vector<Object> navire) {

	}


}









