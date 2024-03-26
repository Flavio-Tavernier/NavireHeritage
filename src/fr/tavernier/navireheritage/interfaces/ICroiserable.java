package fr.tavernier.navireheritage.interfaces;

import java.util.Vector;

import fr.tavernier.navireheritage.classesmetier.Passager;

public interface ICroiserable {


    public void embarquer(Passager passager);

    public void debarquer(Passager passager);


}