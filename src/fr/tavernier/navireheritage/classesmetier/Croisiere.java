package fr.tavernier.navireheritage.classesmetier;

import fr.tavernier.navireheritage.exceptions.GestionPortException;
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


	 
	/**
	 * Ajoute les passagers passés en parametre à la liste des passagers du navire 
	 * A condition que la capacite du navire soit suffisante pour accueillir 
	 * tous les passagers passés en parametre
	 * 
	 * @param passagers - les passagers attendus dans le navire
	 */
	@Override
	public void embarquer(Vector<Passager> passagers) {
		if (this.nbPassagerMaxi >= this.passagers.size() + passagers.size()) {
			for (Passager passager : passagers) {
				this.embarquer(passager);
			}
		}
		else {
			throw new GestionPortException("nombre passagers maximums atteint");
		}
	}
	
	/**
	 * ajoute un passager a la liste des passagers present dans le navire
	 * @param passager à embarquer
	 */
	public void embarquer(Passager passager) {
		this.passagers.put(passager.getNumPasseport(), passager);
	}
	

	/**
	 * debarque les passagers contenus dans la liste passee en parametre
	 * renvoie une liste des passagers qui n'etaient pas presents dans le bateau
	 */
	@Override
	public Vector<Passager> debarquer(Vector<Passager> passagers) {
		Vector<Passager> passagersNonPresents = new Vector<Passager>();
		
		for (Passager passager : passagers) {
			if (!this.debarquer(passager)) {
				// on rajoute dans la liste à retourner 
				// le passager à debarquer qui n'est pas dans le navire
				passagersNonPresents.add(passager);
			}
		}
		
		return passagersNonPresents;
	}
	
	/**
	 * debarquer le passager passé paramètre 
	 * @param passager
	 * @return true si le passager a été débarqué ou false si le passager 
	 * à débarquer n'était pas présent dans le navire
	 */
	public boolean debarquer(Passager passager) {
		return this.passagers.remove(passager.getNumPasseport()) != null;
	}


}









