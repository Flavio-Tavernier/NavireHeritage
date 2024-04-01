package fr.tavernier.navireheritage.classesmetier;

public class Passager {
	private String numPasseport;
	private String nom;
	private String preonm;
	private String nationalite;


	/**
	 * @return le numéro du passeport, représente l'id dans le HashMap
	 */
	public String getNumPasseport() {
		return numPasseport;
	}

	/**
	 * @return le nom du passager
	 */
	public String getNom() {
		return nom;
	}

	/**
	 * @return le prénom du passager
	 */
	public String getPreonm() {
		return preonm;
	}

	/**
	 * @return la nationalité du passager
	 */
	public String getNationalite() {
		return nationalite;
	}
	
}
