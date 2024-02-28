package fr.tavernier.navireheritage.interfaces;

public interface IStationable {

	public void enregistrerArriveePrevue(Object vehicule) throws Exception;
	
	public void enregistrerArrivee(Object vehicule) throws Exception;
	
	public void enregistrerDepart(String id) throws Exception;
	
	public boolean estAttendu(String id);
	
	public boolean estPresent(String id);
	
	public boolean estParti(String id);
	
	public Object getUnAttendu(String id) throws Exception;
	
	public Object getUnArrive(String id) throws Exception;
	
	public Object getUnParti(String id) throws Exception;
	
}
