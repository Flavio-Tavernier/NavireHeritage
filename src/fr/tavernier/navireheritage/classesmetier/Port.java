package fr.tavernier.navireheritage.classesmetier;

import java.util.HashMap;
import java.util.Map;
import java.util.Vector;
import java.util.concurrent.ConcurrentHashMap;

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

	/**
	 * r��crit la m�thode toString afin d'afficher les informations du port
	 * @return string format�e
	 */
	@Override
	public String toString() {
		return "Port de " + this.nom + "\n\tCoordon�es GPS : " + this.latitude + " / " + this.longitude
				+ "\n\tNb portiques : " + this.nbPortique + "\n\tNb quais croisi�re : " + this.nbQuaisCroisiere
				+ "\n\tNb quais tankers : " + this.nbQuaisTanker + "\n\tNb quais super tankers : "
				+ this.nbQuaisSuperTanker + "\n\tNb Navires a quai : " + this.naviresArrives.size()
				+ "\n\tNb Navires attendus : " + this.naviresAttendus.size() + "\n\tNb Navires  partis : "
				+ this.naviresPartis.size() + "\n\tNb Navires En Attente : " + this.naviresEnAttentes.size()
				+ "\n\nNombre De Croisieres dans le port " + this.getNbCroisieres()
				+ "\nNombre De Cargos dans le port : " + this.getNbCargos() + "\nNombre De tankers dans le port : "
				+ this.getNbtankers() + "\nNombre De super tankers dans le port : " + this.getNbSuperTankers() + "\n";
	}

	public void dechargement(String idNavire, int qte) {

	}

	public void chargement(String idNavire, int qte) {

	}

	/**
	 * Ajoute le navire pass� en param�tre dans la liste des navires en attente
	 * @param navire objet navire
	 */
	private void ajoutNavireEnAttente(Navire navire) {
		this.naviresEnAttentes.put(navire.getImo(), navire);
		this.naviresAttendus.remove(navire.getImo());
	}

	/**
	 * @param idNavire string qui est l'imo du navire et repr�sente la clef dans la hashmap
	 * @return true si l'id de navire pass� en param�tre correspond � un objet navire en attente ou
	 * renvoie false dans le cas contraire
	 */
	public boolean estEnAttente(String idNavire) {
		return this.naviresEnAttentes.containsKey(idNavire);
	}


	/**
	 * @param idNavire string qui est l'imo du navire et repr�sente la clef dans la hashmap
	 * @return un objet navire dans le cas ou son id pass� en param�tre est pr�sent
	 * dans la liste des navires en attente
	 * @throws GestionPortException envoie d'une exception si l'id de navire pass� en param�tre est inconnu
	 */
	public Navire getUnAttente(String idNavire) throws GestionPortException {
		if (this.naviresEnAttentes.containsKey(idNavire)) {
			return this.naviresEnAttentes.get(idNavire);
		} else {
			throw new GestionPortException("id de navire en attente inconnu");
		}
	}


	/**
	 *
	 * @param idNavire string qui est l'imo du navire et repr�sente la clef dans la hashmap
	 * @return un objet navire dans le cas ou son id pass� en param�tre est pr�sent
	 * dans la liste des navires arriv�s dans le port
	 * @throws GestionPortException envoie d'une exception si l'id de navire pass� en param�tre est inconnu
	 */
	@Override
	public Navire getUnArrive(String idNavire) throws GestionPortException {
		if (this.naviresArrives.containsKey(idNavire)) {
			return this.naviresArrives.get(idNavire);
		} else {
			throw new GestionPortException("id de navire arrive inconnu");
		}
	}

	/**
	 * @param idNavire string qui est l'imo du navire et repr�sente la clef dans la hashmap
	 * @return un objet navire dans le cas ou son id pass� en param�tre est pr�sent
	 * 	 * dans la liste des navires partis
	 * @throws GestionPortException envoie d'une exception si l'id de navire pass� en param�tre est inconnu
	 */
	@Override
	public Navire getUnParti(String idNavire) throws GestionPortException {
		if (this.naviresPartis.containsKey(idNavire)) {
			return this.naviresPartis.get(idNavire);
		} else {
			throw new GestionPortException("id de navire parti inconnu");
		}
	}


	/**
	 * @return le nombre de Navire de type Croisiere present dans le port
	 */
	public int getNbCroisieres() {
		int compteurCroisieres = 0;

		for (Navire navire : naviresArrives.values()) {
			if (navire instanceof Croisiere) {
				compteurCroisieres++;
			}
		}
		return compteurCroisieres;
	}


	/**
	 * @return le nombre de Navire de type Cargo present dans le port
	 */
	public int getNbCargos() {
		int compteurCargos = 0;

		for (Navire navire : naviresArrives.values()) {
			if (navire instanceof Cargo) {
				compteurCargos++;
			}
		}
		return compteurCargos;
	}

	/**
	 * @return le nombre de Navire de type Tanker present dans le port
	 */
	public int getNbtankers() {
		int compteurTankers = 0;
		for (Navire navire : naviresArrives.values()) {
			if (navire instanceof Tanker && navire.getTonnageGT() <= 130000) {
				compteurTankers++;
			}
		}
		return compteurTankers;
	}

	/**
	 * @return le nombre de Navire de type Tanker cat�goris� en SuperTanker present dans le port
	 */
	public int getNbSuperTankers() {
		int compteurSuperTankers = 0;
		for (Navire navire : naviresArrives.values()) {
			if (navire instanceof Tanker && navire.getTonnageGT() > 130000) {
				compteurSuperTankers++;
			}
		}
		return compteurSuperTankers;
	}


	/**
	 * enregistre l'objet pass� en param�tre dans la liste des navire attendus dans le port
	 * @param vehicule - ici, le v�hicule sera un bateau
	 * @throws GestionPortException envoie d'une exception si le navire pass� en param�tre
	 * est d�j� pre�sent dans la liste des navires attentes ou arriv�s
	 */
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

	/**
	 * g�re l'arriv�e du navire pass� en param�tre
	 * Si le navire pass� en param�tre est d�j� pr�sent dans le port alors une erreur est envoy�e
	 * Si le navire pass� en param�tre n'est pas pr�sent dans la liste des navires attendus alors il est ajout�
	 * Une fois les conditions pr�c�dentes valid�es, le navire est envoy� dans une m�thode qui g�rera
	 * la suite de la gestion de son arriv�e
	 * @param vehicule - ici, le v�hicule sera un bateau
	 * @throws GestionPortException envoie d'une exception si le navire pass� en param�tre est d�j� pr�sent
	 * dans la liste des navire arriv�s
	 */
	@Override
	public void enregistrerArrivee(Object vehicule) throws GestionPortException {
		Navire navire = ((Navire) vehicule);
		String imoNavire = navire.getImo();

		if (this.estPresent(imoNavire)) {
			throw new GestionPortException("Navire deja present dans le port");
		}
		else if (!this.estAttendu(imoNavire)) {
			this.naviresAttendus.put(imoNavire, navire);
		}

		this.gererArriveeNavire(navire);
	}

	/**
	 * v�rifie le type de navire pass� en param�tre pour continuer la gestion de l'arriv�e
	 * @param navire � faire arriver
	 */
	public void gererArriveeNavire(Navire navire) {
		if (navire instanceof Croisiere) {
			gererCroisiere(navire);
		} else {
			gererNavireCommercable(navire);
		}
	}

	/**
	 * g�re l'arriv�e des navires de type croisiere
	 * @param navire � faire arriver
	 */
	public void gererCroisiere(Navire navire) {
		changementEtatNavire(navire);
	}

	/**
	 * Diff�rencie le type des navires commercables pour g�rer leurs arriv�es
	 * @param navire � faire arriver
	 */
	public void gererNavireCommercable(Navire navire) {
		if (navire instanceof Cargo) {
			this.gererCargo(navire);
		} else {
			this.gererTypeTanker(navire);
		}
	}

	/**
	 * Si le nombre de quais accueillant des navires de type Cargo est suffiant,
	 * g�re l'arriv�e des navires de type Cargo
	 * @param navire � faire arriver
	 */
	public void gererCargo(Navire navire) {
		if (this.getNbCargos() < this.nbQuaisTanker) {
			changementEtatNavire(navire);
		}
		else {
			this.ajoutNavireEnAttente(navire);
		}
	}

	/**
	 * Diff�rencie la cat�gorie des navires de type Tanker pour g�rer leurs arriv�es
	 * @param navire � faire arriver
	 */
	public void gererTypeTanker(Navire navire) {
		if (navire.getTonnageGT() <= 130000) {
			this.gererTanker(navire);
		} else {
			this.gererSuperTanker(navire);
		}
	}

	/**
	 * Si le nombre de quais accueillant des navires de type Tanker est suffiant,
	 * g�re l'arriv�e des navires de type Tanker
	 * @param navire � faire arriver
	 */
	public void gererTanker(Navire navire) {
		if (this.getNbtankers() < this.nbQuaisTanker) {
			changementEtatNavire(navire);
		}
		else {
			this.ajoutNavireEnAttente(navire);
		}
	}


	/**
	 * Si le nombre de quais accueillant des navires de type Tanker cat�gorie SuperTanker est suffiant,
	 * g�re l'arriv�e des navires de type Tanker cat�gorie SuperTanker
	 * @param navire � faire arriver
	 */
	public void gererSuperTanker(Navire navire) {
		if (this.getNbSuperTankers() < this.nbQuaisSuperTanker) {
			changementEtatNavire(navire);
		}
		else {
			this.ajoutNavireEnAttente(navire);
		}
	}

	/**
	 * permet de retirer le navire pass� en param�tre de la liste des navires attendus
	 * et d'ajouter ce m�me navire dans la liste des navires arriv�s
	 * @param navire � faire arriver
	 */
	public void changementEtatNavire(Navire navire) {
		this.naviresAttendus.remove(navire.getImo());
		this.naviresArrives.put(navire.getImo(), navire);
	}



	/**
	 * g�re le d�part du navire pass� en param�tre
	 * Si l'id du navire pass� en param�tre est pr�sent dans la port alors ce m�me navire est retir� de
	 * la liste des navires pr�sents et ajouter dans la liste des navires partis.
	 * Sinon une exception est envoy�e
	 * Afin de les g�rer, v�rifie si des objets Navire sont pr�sents dans la liste des navires en attentes
	 * @param idNavire - id du navire � faire partir
	 * @throws GestionPortException envoie d'une exception si le navire pass� en param�tre n'est pas pr�sent
	 * dans la liste des navire arriv�s
	 */
	@Override
	public void enregistrerDepart(String idNavire) throws GestionPortException {
		Navire navire = this.naviresArrives.get(idNavire);
		if (this.estPresent(idNavire)) {
			this.naviresArrives.remove(idNavire);
			this.naviresPartis.put(idNavire, navire);
		} else {
			throw new GestionPortException("Impossible d'enregistrer le d�part, " +	"le navire " + idNavire + " n'est pas present dans le port");
		}

		if (this.naviresEnAttentes.size() > 0) {
			gererNavireAttente(navire);
		}
	}


	/**
	 * g�re les navires pr�sent dans la liste des navires en attentes
	 * boucle les navires pr�sents dans la liste des navires en attente pour savoir si le navire
	 * pass� en param�tre (qui est parti) est du m�me type qu'un des navires de la liste des navires en attente
	 * Si le navire pass� en param�tre et un des navires contenu dans la liste des navires en attente sont
	 * du m�me type strict alors, on fait appel � une m�thode pour permutter son �tat
	 * @param navire - navire qui vient de partir
	 */
	private void gererNavireAttente(Navire navire) {
		// on va parcourir la liste des navires en attente jusqu'� 
		// soit trouver un navire du meme type strict 
		// ou ne pas trouver de navire du meme type strict
		// on va recuperer les valeurs de la hashmap dans une lsite
		
		int i = 0;
		
		Vector<Navire> naviresEnAttente = new Vector<Navire>(this.naviresEnAttentes.values());
		while (i < this.naviresEnAttentes.size() && !navire.isMemeTypeStrict(naviresEnAttente.get(i))) {
			i++;
		}

		if (i < this.naviresEnAttentes.size()) {
			// les navires sont du meme types strict, donc on permutte de navire en attente a navire arrive
			permutteAttenteArrive(naviresEnAttente.get(i));
		}
	}


	/**
	 * g�re l'arriv�e du navire et le retire de la liste des navires en attente
	 * @param navire - navire que l'on souhaite permutter
	 */
	private void permutteAttenteArrive(Navire navire) {
		gererArriveeNavire(navire);
		this.naviresEnAttentes.remove(navire.getImo(), navire);
	}


	/**
	 * @param idNavire - id du navire � faire partir
	 * @return true si l'id du navire pass� en param�tre est pr�sent dans la liste des navires attendus
	 * sinon renvoie false
	 */
	@Override
	public boolean estAttendu(String idNavire) {
		return this.naviresAttendus.containsKey(idNavire);
	}

	/**
	 * @param idNavire - id du navire � faire partir
	 * @return true si l'id du navire pass� en param�tre est pr�sent dans la liste des navires pr�sents
	 * sinon renvoie false
	 */
	@Override
	public boolean estPresent(String idNavire) {
		return this.naviresArrives.containsKey(idNavire);
	}

	/**
	 * @param idNavire - id du navire � faire partir
	 * @return true si l'id du navire pass� en param�tre est pr�sent dans la liste des navires partis
	 * sinon renvoie false
	 */
	@Override
	public boolean estParti(String idNavire) {
		return this.naviresPartis.containsKey(idNavire);
	}


	/**
	 * @param idNavire - id du navire � faire partir
	 * @return l'objet du navire dont l'id est pass� en param�tre s'il est pr�sent dans la liste des navires
	 * attendus sinon envoie une erreur
	 */
	@Override
	public Object getUnAttendu(String idNavire) throws GestionPortException {
		if (this.estAttendu(idNavire)) {
			return this.naviresAttendus.get(idNavire);
		} else {
			throw new GestionPortException("Le navire " + idNavire + " n'est pas dans la liste des attendus");
		}
	}


	/**
	 * getters et setters de la classe Port
	 */

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

	public Map<String, Navire> getNaviresAttendus() {
		return naviresAttendus;
	}

	public Map<String, Navire> getNaviresArrives() {
		return naviresArrives;
	}

	public Map<String, Navire> getNaviresPartis() {
		return naviresPartis;
	}

	public Map<String, Navire> getNaviresEnAttentes() {
		return naviresEnAttentes;
	}

}
