package fr.tavernier.navireheritage.classesmetier;

import java.util.HashMap;

import fr.tavernier.navireheritage.exceptions.GestionPortException;
import fr.tavernier.navireheritage.interfaces.IStationable;

public class Port implements IStationable {
	private String nom;
	private String latitude;
	private String longitude;
	private int nbPortique;
	private int nbQuaisTanker;
	private int nbQuaisCroisiere;
	private int nbQuaisSuperTanker;
	private int nbQuaisPassager;
	private HashMap<String, Navire> naviresAttendus = new HashMap<>();
	private HashMap<String, Navire> naviresArrives = new HashMap<>();
	private HashMap<String, Navire> naviresPartis = new HashMap<>();
	private HashMap<String, Navire> naviresEnAttentes = new HashMap<>();

	public Port(String nom, String latitude, String longitude, int nbPortique, int nbQuaisCroisiere, int nbQuaisTanker,
			int nbQuaisSuperTanker) {
		this.nom = nom;
		this.latitude = latitude;
		this.longitude = longitude;
		this.nbPortique = nbPortique;
		this.nbQuaisCroisiere = nbQuaisCroisiere;
		this.nbQuaisTanker = nbQuaisTanker;
		this.nbQuaisSuperTanker = nbQuaisSuperTanker;
	}

	public String toString() {
		return "Port de " + this.nom + "\n\tCoordonées GPS : " + this.latitude + " / " + this.longitude
				+ "\n\tNb portiques : " + this.nbPortique + "\n\tNb quais croisiére : " + this.nbQuaisCroisiere
				+ "\n\tNb quais tankers : " + this.nbQuaisTanker + "\n\tNb quais super tankers : "
				+ this.nbQuaisSuperTanker + "\n\tNb Navires a quai : " + this.naviresArrives.size()
				+ "\n\tNb Navires attendus : " + this.naviresAttendus.size() + "\n\tNb Navires  partis : "
				+ this.naviresPartis.size() + "\n\tNb Navires En Attente : " + this.naviresEnAttentes.size()
				+ "\n\nNombre de croisiere dans le port " + this.getNbCroisieres()
				+ "\nNombre De Cargos dans le port : " + this.getNbCargos() + "\nNombre De tankers dans le port : "
				+ this.getNbtankers() + "\nNombre De super tankers dans le port : " + this.getNbSuperTankers() + "\n";
	}

	public void dechargement(String idNavire, int qte) {

	}

	public void chargement(String idNavire, int qte) {

	}

	private void ajoutNavireEnAttente(Navire navire) {
		this.naviresEnAttentes.put(navire.getImo(), navire);
		this.naviresAttendus.remove(navire.getImo());
	}

	public boolean estEnAttente(String idNavire) {
		if (this.naviresEnAttentes.containsKey(idNavire)) {
			return true;
		} else {
			return false;
		}
	}

	public Navire getUnAttente(String idNavire) throws GestionPortException {
		if (this.naviresEnAttentes.containsKey(idNavire)) {
			return this.naviresEnAttentes.get(idNavire);
		} else {
			throw new GestionPortException("id de navire en attente inconnu");
		}
	}

	@Override
	public Navire getUnArrive(String idNavire) throws GestionPortException {
		if (this.naviresArrives.containsKey(idNavire)) {
			return this.naviresArrives.get(idNavire);
		} else {
			throw new GestionPortException("id de navire arrive inconnu");
		}
	}

	@Override
	public Navire getUnParti(String idNavire) throws GestionPortException {
		if (this.naviresPartis.containsKey(idNavire)) {
			return this.naviresPartis.get(idNavire);
		} else {
			throw new GestionPortException("id de navire parti inconnu");
		}
	}
	
	public int getNbCroisieres() {
		int compteurCroisieres = 0;

		for (Navire navire : naviresArrives.values()) {
			if (navire instanceof Croisiere) {
				compteurCroisieres++;
			}
		}
		return compteurCroisieres;
	}
	
	

	public int getNbCargos() {
		int compteurCargos = 0;

		for (Navire navire : naviresArrives.values()) {
			if (navire instanceof Cargo) {
				compteurCargos++;
			}
		}
		return compteurCargos;
	}

	public int getNbtankers() {
		int compteurTankers = 0;
		for (Navire navire : naviresArrives.values()) {
			if (navire instanceof Tanker && navire.getTonnageGT() <= 130000) {
				compteurTankers++;
			}
		}
		return compteurTankers;
	}

	public int getNbSuperTankers() {
		int compteurSuperTankers = 0;
		for (Navire navire : naviresArrives.values()) {
			if (navire instanceof Tanker && navire.getTonnageGT() > 130000) {

				compteurSuperTankers++;
			}
		}
		return compteurSuperTankers;
	}

	
	@Override
	public void enregistrerArriveePrevue(Object vehicule) throws GestionPortException {
		if (vehicule instanceof Navire) {
			Navire navire = ((Navire) vehicule);
			if (naviresAttendus.containsKey(navire.getImo()) || naviresArrives.containsKey(navire.getImo())) {
				throw new GestionPortException("Le navire " + navire.getImo() + " est deja en attente ou arrive");
			} else {
				this.naviresAttendus.put(navire.getImo(), navire);
			}
		}
	}

	@Override
	public void enregistrerArrivee(Object vehicule) throws GestionPortException {
		Navire navire = ((Navire) vehicule);
		String imoNavire = navire.getImo();

		if (this.estAttendu(imoNavire) && !this.estPresent(imoNavire)) {
			this.gererArriveeNavire(navire);
		} else {
			throw new GestionPortException("Le navire " + imoNavire + " est deja arrive ou pas attendu");
		}

	}

	public void gererArriveeNavire(Navire navire) {
		if (navire instanceof Croisiere) {
			gererCroisiere(navire);
		} else {
			gererNavireCommercable(navire);
		}
	}

	public void gererCroisiere(Navire navire) {
		changementEtatNavire(navire);
	}

	public void gererNavireCommercable(Navire navire) {
		if (navire instanceof Cargo) {
			this.gererCargo(navire);
		} else {
			this.gererTypeTanker(navire);
		}
	}

	public void gererCargo(Navire navire) {
		if (this.getNbCargos() < this.nbQuaisTanker) {
			changementEtatNavire(navire);
		}
		else {
			this.ajoutNavireEnAttente(navire);
		}
	}

	public void gererTypeTanker(Navire navire) {
		if (navire.getTonnageGT() <= 130000) {
			this.gererTanker(navire);
		} else {
			this.gererSuperTanker(navire);
		}
	}

	public void gererTanker(Navire navire) {
		if (this.getNbtankers() < this.nbQuaisTanker) {
			changementEtatNavire(navire);
		}
		else {
			this.ajoutNavireEnAttente(navire);
		}
	}

	public void gererSuperTanker(Navire navire) {
		if (this.getNbSuperTankers() < this.nbQuaisSuperTanker) {
			changementEtatNavire(navire);
		}
		else {
			this.ajoutNavireEnAttente(navire);
		}
	}

	public void changementEtatNavire(Navire navire) {
		this.naviresAttendus.remove(navire.getImo());
		this.naviresArrives.put(navire.getImo(), navire);
	}

	@Override
	public void enregistrerDepart(String idNavire) throws GestionPortException {
		Navire navire = this.naviresArrives.get(idNavire);
		if (this.naviresArrives.containsKey(idNavire)) {
			this.naviresArrives.remove(idNavire);
			this.naviresPartis.put(idNavire, navire);
		} else {
			throw new GestionPortException("Impossible d'enregistrer le départ, " +
					"le navire " + idNavire + " n'est pas present dans le port");
		}

		if (this.naviresEnAttentes.size() > 0) {
			gererNavireEnAttente(navire);
		}
		// Navire navire = this.naviresArrives.get(imoNavire);
		// this.changementEtatNavire(navire);
	}
	

	public void gererNavireEnAttente(Navire navire) {
		if (navire instanceof Cargo) {
			gererCargoEnAttente();
		} else if (navire instanceof Tanker) {
			gererTankerEnAttente();
		}

	}

	public void gererCargoEnAttente() {
		for (Navire navire : this.naviresEnAttentes.values()) {
			if (navire instanceof Cargo) {
				this.naviresEnAttentes.remove(navire.getImo(), navire);
				gererArriveeNavire(navire);
			}
		}
	}

	public void gererTankerEnAttente() {
		for (Navire navire : this.naviresEnAttentes.values()) {
			if (navire instanceof Tanker && navire.getTonnageGT() <= 130000) {
				this.naviresEnAttentes.remove(navire.getImo(), navire);
				gererArriveeNavire(navire);
			}
			else {
				gererSuperTankerEnAttente();
			}
		}
	}

	public void gererSuperTankerEnAttente() {
		for (Navire navire : this.naviresEnAttentes.values()) {
			if (navire instanceof Tanker) {
				this.naviresEnAttentes.remove(navire.getImo(), navire);
				gererArriveeNavire(navire);
			}
		}
	}


	@Override
	public boolean estAttendu(String idNavire) {
		return this.naviresAttendus.containsKey(idNavire);
	}

	@Override
	public boolean estPresent(String idNavire) {
		return this.naviresArrives.containsKey(idNavire);
	}

	@Override
	public boolean estParti(String idNavire) {
		return this.naviresPartis.containsKey(idNavire);
	}

	@Override
	public Object getUnAttendu(String idNavire) throws GestionPortException {
		if (this.estAttendu(idNavire)) {
			return this.naviresAttendus.get(idNavire);
		} else {
			throw new GestionPortException("Le navire " + idNavire + " n'est pas dans la liste des attendus");
		}
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getLatitude() {
		return latitude;
	}

	public String getLongitude() {
		return longitude;
	}

	public int getNbPortique() {
		return nbPortique;
	}

	public void setNbPortique(int nbPortique) {
		this.nbPortique = nbPortique;
	}

	public int getNbQuaisTanker() {
		return nbQuaisTanker;
	}

	public int getNbQuaisCroisiere() {
		return nbQuaisCroisiere;
	}

	public void setNbQuaisCroisiere(int nbQuaisCroisiere) {
		this.nbQuaisCroisiere = nbQuaisCroisiere;
	}

	public int getNbQuaisSuperTanker() {
		return nbQuaisSuperTanker;
	}

	public void setNbQuaisSuperTanker(int nbQuaisSuperTanker) {
		this.nbQuaisSuperTanker = nbQuaisSuperTanker;
	}

	public int getNbQuaisPassager() {
		return nbQuaisPassager;
	}

	public void setNbQuaisPassager(int nbQuaisPassager) {
		this.nbQuaisPassager = nbQuaisPassager;
	}

	public HashMap<String, Navire> getNaviresAttendus() {
		return naviresAttendus;
	}

	public HashMap<String, Navire> getNaviresArrives() {
		return naviresArrives;
	}

	public HashMap<String, Navire> getNaviresPartis() {
		return naviresPartis;
	}

	public HashMap<String, Navire> getNaviresEnAttentes() {
		return naviresEnAttentes;
	}

}
