package fr.tavernier.navireheritage.classesmetier;

import fr.tavernier.navireheritage.interfaces.INavCommercable;

public class Tanker extends Navire implements INavCommercable {
	private String typeFluide;


	/**
	 * Constructeur de la classe Tanker faisant référence au Constructeur de la classe mère Navire
	 * @param imo - id du navire
	 * @param nom du navire
	 * @param latitude du navire
	 * @param longitude du navire
	 * @param tonnageDT du navire
	 * @param tonnageDWT du navire
	 * @param tonnageActuel du navire
	 * @param typeFluide du navire
	 */
	public Tanker(String imo, String nom, String latitude, String longitude, int tonnageDT, int tonnageDWT,
			int tonnageActuel, String typeFluide) {
		super(imo, nom, latitude, longitude, tonnageDT, tonnageDWT, tonnageActuel);
		this.typeFluide = typeFluide;
	}

	/**
	 * Permet de soustraire la quantité passée en paramètre au tonnage actuel du navire
	 * Si le tonnage actuel est supérieur à 0 et que la quantité que l'on souhaite retirer
	 * est inférieure ou égale à la quantité présente dans le navire
	 * @param qte que l'on souhaite retirer
	 */
	@Override
	public void decharger(int qte) {
		int tonnageActuel = this.getTonnageActuel();
		if (tonnageActuel > 0 && qte <= tonnageActuel) {
			this.setTonnageActuel(tonnageActuel - qte);
		}
	}


	/**
	 * Permet d'ajouter la quantité passée en paramètre au tonnage actuel du navire
	 * si la quantité maximale n'est pas atteinte
	 * @param qte que l'on souhaite ajouter
	 */
	@Override
	public void charger(int qte) {
		int tonnageActuel = this.getTonnageActuel();
		if (this.getTonnageDWT() < tonnageActuel + qte) {
			this.setTonnageActuel(tonnageActuel + qte);
		}
	}


	/**
	 * @return le type de fluide transporté par le Tanker
	 */
	public String getTypeFluide() {
		return typeFluide;
	}


	/**
	 * permet de définir le type de fluide transporté par un Tanker
	 * @param typeFluide que le Tanker transporte
	 */
	public void setTypeFluide(String typeFluide) {
		this.typeFluide = typeFluide;
	}


	/**
	 * @return True si le Tanker est catégorisé SuperTanker
	 * ou false si le navire est un Tanker "classique"
	 */
	public boolean estSuperTanker() {
		return this.getTonnageGT() > 130000;
	}

}
