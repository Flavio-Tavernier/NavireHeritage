package fr.tavernier.navireheritage.classestechniques;

import fr.tavernier.navireheritage.classesmetier.Cargo;
import fr.tavernier.navireheritage.classesmetier.Croisiere;
import fr.tavernier.navireheritage.classesmetier.Navire;
import fr.tavernier.navireheritage.classesmetier.Port;
import fr.tavernier.navireheritage.classesmetier.Tanker;
import fr.tavernier.navireheritage.exceptions.GestionPortException;

public abstract class Test {
	
	public static void chargementInitial(Port port) {
		try {
			// cargos
			port.enregistrerArriveePrevue(new Cargo("IMO9780859", "CMA CGM A. LINCOLN", "43.43279 N", "134.76258 W",
					140872, 148992, 123000, "marchandises diverses"));
			port.enregistrerArriveePrevue(new Cargo("IMO9250098", "CONTAINERSHIPS VII", "54.35412 N", "5.3644 E", 10499,
					13965, 11000, "Matériel de loisirs"));
			port.enregistrerArriveePrevue(new Cargo("IMO9502910", "MAERSK EMERALD", "54.72202 N", "170.54304 W", 141754,
					141189, 137000, "marchandises diverses"));
			//croisiere
			port.enregistrerArriveePrevue(new Croisiere("IMO9241061", "RMS QUEEN MARY 2", "6.93393 N", "88.81366 E",
					149215, 19189, 17600, 'M', 2620));
			port.enregistrerArriveePrevue(new Croisiere("IMO9803613", "MSC GRANDIOSA", "43.34408 N", "5.32912 E",
					177000, 13610, 12000, 'M', 6300));
			port.enregistrerArriveePrevue(
					new Croisiere("IMO9351476", "CRUISE ROMA", "41.2781 N", "3.62948 E", 54310, 7500, 3501, 'M', 3501));
			//tankers
			port.enregistrerArriveePrevue(new Tanker("IMO9334076", "EJNAN", "41.23848 N", "3.83904 E", 95824, 78403,
					76000, "Liquefied natural gas (LNG)"));
			port.enregistrerArriveePrevue(new Tanker("IMO9380374", "CITRUS", "25.25954 N", "35.87807 E", 29295, 46938,
					42000, "Chemical/Oil Products"));
			port.enregistrerArriveePrevue(
					new Tanker("IMO9220952", "HARAD", "24.1269 N", "36.83576 E", 159990, 303115, 289000, "Crude Oil"));
			port.enregistrerArriveePrevue(new Tanker("IMO9197832", "KALAMOS", "24.7369 N", "36.23195 E", 149282, 281037,
					264000, "Crude Oil"));
			port.enregistrerArriveePrevue(new Tanker("IMO9379715", "NEW DRAGON", "26.94883 N", "50.20617 E", 156726,
					296370, 265400, "rude Oil Tanker"));

		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
	}
	
	
	
	public static void testEnregistrerArriveePrevue(Port port, Navire navire) {
		try {
			port.enregistrerArriveePrevue(navire);
		}
		catch (GestionPortException e) {
			System.out.println(e.getMessage());
		}
	}
	
	
	public static void testEnregistrerArrivee(Port port, Object object) {
		try {
			port.enregistrerArrivee(object);
		}
		catch (GestionPortException e) {
			System.out.println(e.getMessage());
		}
	}
	
	
	
	
	

}
