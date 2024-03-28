package fr.tavernier.navireheritage.interfaces;

import java.util.Vector;

import fr.tavernier.navireheritage.classesmetier.Passager;
import fr.tavernier.navireheritage.exceptions.GestionPortException;

public interface ICroiserable {


    public void embarquer(Vector<Passager> passagers) throws GestionPortException;

    public Vector<Passager> debarquer(Vector<Passager> passagers);


}