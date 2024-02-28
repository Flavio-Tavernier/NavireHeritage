package fr.tavernier.navireheritage.classesmetier;

import fr.tavernier.navireheritage.interfaces.INavCommercable;

public class Cargo extends Navire implements INavCommercable {
    private String typeFret;

    public Cargo(String imo, String nom, String latitude, String longitude, int tonnageDT, int tonnageDWT,
                 int tonnageActuel, String typeFret) {
        super(imo, nom, latitude, longitude, tonnageDT, tonnageDWT, tonnageActuel);
        this.typeFret = typeFret;
    }

    @Override
    public void decharger(int qte) {

    }

    @Override
    public void charger(int qte) {

    }

	public String getTypeFret() {
		return typeFret;
	}

	public void setTypeFret(String typeFret) {
		this.typeFret = typeFret;
	}
    
    
    
    
    
    
}
