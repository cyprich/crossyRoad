import java.util.Random;
import java.util.ArrayList;
/**
 * Write a description of class Hra here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Hra {
    private Manazer manazer;
    private Kacka kacka;
    
    private Prekazka prekazka01;
    private Prekazka prekazka02;
    private Prekazka prekazka03;
    private Prekazka prekazka04;

    private ArrayList<ArrayList<String>> hraciaPlocha;
    public Hra() {
        this.manazer = new Manazer();
        this.kacka = new Kacka();
        this.prekazka01 = new Prekazka("strom");
        this.prekazka02 = new Prekazka("strom");
        this.prekazka03 = new Prekazka("kamen");
        this.prekazka04 = new Prekazka("kamen");

        
        this.manazer.spravujObjekt(this.kacka);
    }
}
