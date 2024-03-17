package fr.tavernier.navireheritage.programme;

import fr.tavernier.navireheritage.classesmetier.Cargo;
import fr.tavernier.navireheritage.classesmetier.Croisiere;
import fr.tavernier.navireheritage.classesmetier.Port;
import fr.tavernier.navireheritage.classesmetier.Tanker;
import fr.tavernier.navireheritage.classestechniques.Test;
import fr.tavernier.navireheritage.exceptions.GestionPortException;

public class Programme {
    public static void main(String[] args) throws GestionPortException {
        Port port = new Port("Marseille", "43.2976N", "5.3471E", 4, 3, 2, 4);
        Test.chargementInitial(port);


        Test.testEnregistrerArriveePrevue(port, new Cargo("IMO9780859", "CMA CGM A. LINCOLN", "43.43279 N", "134.76258 W",
                140872, 148992, 123000, "marchandises diverses"));


        Croisiere croisiere1 = new Croisiere("IMO9241061", "RMS QUEEN MARY 2", "6.93393 N", "88.81366 E", 149215, 19189, 17600, 'M', 2620);
        Test.testEnregistrerArrivee(port, croisiere1);

        Croisiere croisiere2 = new Croisiere("IMO0000000", "RMS QUEEN MARY 2", "6.93393 N", "88.81366 E", 149215, 19189, 17600, 'M', 2620);
        Test.testEnregistrerArrivee(port, croisiere2);

        Croisiere croisiere3 = new Croisiere("IMO9241061", "RMS QUEEN MARY 2", "6.93393 N", "88.81366 E", 149215, 19189, 17600, 'M', 2620);
        Test.testEnregistrerArrivee(port, croisiere3);



        Tanker tanker1 = new Tanker("IMO9334076", "EJNAN", "41.23848 N", "3.83904 E", 95824, 78403,
                76000, "Liquefied natural gas (LNG)");
        Test.testEnregistrerArrivee(port,tanker1);

        Tanker tanker2 = new Tanker("IMO7334076", "EJNAN", "41.23848 N", "3.83904 E", 95824, 78403,
                76000, "Liquefied natural gas (LNG)");
        Test.testEnregistrerArriveePrevue(port, tanker2);
        Test.testEnregistrerArrivee(port,tanker2);

        Tanker tanker3 = new Tanker("IMO7534076", "EJNAN", "41.23848 N", "3.83904 E", 95824, 78403,
                76000, "Liquefied natural gas (LNG)");
        Test.testEnregistrerArriveePrevue(port, tanker3);
        Test.testEnregistrerArrivee(port,tanker3);

        Tanker superTanker2 = new Tanker("IMO9197832", "KALAMOS", "24.7369 N", "36.23195 E", 149282, 281037,
                264000, "Crude Oil");
        Test.testEnregistrerArrivee(port,superTanker2);

        Tanker superTankeranker3 = new Tanker("IMO9220952", "HARAD", "24.1269 N", "36.83576 E", 159990, 303115, 289000, "Crude Oil");
        Test.testEnregistrerArrivee(port,superTankeranker3);

        Tanker superTankeranker4 = new Tanker("IMO9379715", "NEW DRAGON", "26.94883 N", "50.20617 E", 156726,
               296370, 265400, "rude Oil Tanker");
        Test.testEnregistrerArrivee(port,superTankeranker4);


        Test.testEnregistrerDepart(port,"IMO9241061");

        Test.testEnregistrerDepart(port, "IMO9379715");

        Test.testEnregistrerDepart(port, "IMO9334076");

        System.out.println(port);
    }
}