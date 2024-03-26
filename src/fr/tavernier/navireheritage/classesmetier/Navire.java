package fr.tavernier.navireheritage.classesmetier;

public abstract class Navire {
    private String imo;
    private String nom;
    private String latitude;
    private String longitude;
    private int tonnageGT;
    private int tonnageDWT;
    private int tonnageActuel;


    public Navire(String imo, String nom, String latitude, String longitude, int tonnageGT, int tonnageDWT, int tonnageActuel) {
        this.imo = imo;
        this.nom = nom;
        this.latitude = latitude;
        this.longitude = longitude;
        this.tonnageGT = tonnageGT;
        this.tonnageDWT = tonnageDWT;
        this.tonnageActuel = tonnageActuel;
    }

    
    public boolean isMemeTypeStrict(Navire navire) {
		// on va recuperer la classe du navire courant 
    	Class c = this.getClass();
    	if (c.isInstance(navire)) {
    		// le navire passe en parametre est de la meme classe que le navrie courant
    		// on va verifier s'ils sont tanker
    		if (navire instanceof Tanker) {
    			// on sait que ce sont tous les deux des Tanker
    			// MAIS pour acceder aux membres de la sous classe Tanker (sous classe de Tanker),
    			// on doit catser le navire en Tanker
    			Tanker t = (Tanker) navire;
    			Tanker thisT = (Tanker) this;
    			// on retourne l'egalite booleenne des Tanker
    			return (t.estSuperTanker() == thisT.estSuperTanker());
    		}
    		return true;
    	}
    	return false;
	}
	


    public String getImo() {
        return this.imo;
    }
    public String getNom() {
        return this.nom;
    }

    public String getLatitude() {
        return this.latitude;
    }
    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }
    public String getLongitude() {
        return longitude;
    }
    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public int getTonnageGT() {
        return this.tonnageGT;
    }

    public int getTonnageDWT() {
        return this.tonnageDWT;
    }

    public int getTonnageActuel() {
        return this.tonnageActuel;
    }

    public void setTonnageActuel(int tonnageActuel) {
        this.tonnageActuel = tonnageActuel;
    }
}
