package fr.tavernier.navireheritage.classesmetier;

import fr.tavernier.navireheritage.interfaces.INavCommercable;

public class Tanker extends Navire implements INavCommercable {
	private String typeFluide;

	public Tanker(String imo, String nom, String latitude, String longitude, int tonnageDT, int tonnageDWT,
			int tonnageActuel, String typeFluide) {
		super(imo, nom, latitude, longitude, tonnageDT, tonnageDWT, tonnageActuel);
		this.typeFluide = typeFluide;
	}

	@Override
	public void decharger(int qte) {
		// TODO Auto-generated method stub

	}

	@Override
	public void charger(int qte) {
		// TODO Auto-generated method stub

	}

	public String getTypeFluide() {
		return typeFluide;
	}

	public void setTypeFluide(String typeFluide) {
		this.typeFluide = typeFluide;
	}

}
