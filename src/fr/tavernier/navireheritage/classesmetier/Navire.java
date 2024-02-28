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
