package fr.tavernier.navireheritage.classesmetier;

public abstract class Navire {
    private String imo;
    private String nom;
    private String latitude;
    private String longitude;
    private int tonnageGT;
    private int tonnageDWT;
    private int tonnageActuel;


    /**
     * Constructeur de la classe Navire
     * @param imo - id du navire
     * @param nom du navire
     * @param latitude du navire
     * @param longitude du navire
     * @param tonnageGT du navire
     * @param tonnageDWT du navire
     * @param tonnageActuel du navire
     */
    public Navire(String imo, String nom, String latitude, String longitude, int tonnageGT, int tonnageDWT, int tonnageActuel) {
        this.imo = imo;
        this.nom = nom;
        this.latitude = latitude;
        this.longitude = longitude;
        this.tonnageGT = tonnageGT;
        this.tonnageDWT = tonnageDWT;
        this.tonnageActuel = tonnageActuel;
    }


    /**
     * permet de savoir si deux navires sont du même type strict
     * @param navire que l'on souhaite comparer avec l'objet manipulé
     * @return True si le navire passé en paramètre est du même type strict que le navire actuellement manipulé
     * ou false dans le cas ou les navires ne sont pas du même type strict
     */
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


    /**
     * @return l'imo du navire
     */
    public String getImo() {
        return this.imo;
    }

    /**
     * @return le nom du navire
     */
    public String getNom() {
        return this.nom;
    }

    /**
     * @return la latitude du navire
     */
    public String getLatitude() {
        return this.latitude;
    }

    /**
     * permet de définir la latitude d'un navire
     * @param latitude du navire
     */
    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    /**
     * @return la longitude du navire
     */
    public String getLongitude() {
        return longitude;
    }

    /**
     * permet de définir la longitude du navire
     * @param longitude du navire
     */
    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }


    /**
     * @return le tonnageGT du navire
     */
    public int getTonnageGT() {
        return this.tonnageGT;
    }

    /**
     * @return le tonnageDWT du navire
     */
    public int getTonnageDWT() {
        return this.tonnageDWT;
    }

    /**
      * @return le tonnageActuel du navire
     */
    public int getTonnageActuel() {
        return this.tonnageActuel;
    }

    /**
     * permet de définir le tonnageActuel du navire
     * @param tonnageActuel du navire
     */
    public void setTonnageActuel(int tonnageActuel) {
        this.tonnageActuel = tonnageActuel;
    }
}
