import fri.shapesge.Manazer;
/**
 * Write a description of class Hra here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Hra {
    private HraciaPlocha hraciaPlocha;
    private Hrac hrac;
    private Manazer manazer;
    /**
     * Constructor for objects of class Hra
     */
    public Hra() {
        this.hraciaPlocha = new HraciaPlocha(this);
        this.hrac = new Hrac();
        this.manazer = new Manazer();

        this.manazer.spravujObjekt(hrac);
        this.manazer.spravujObjekt(hraciaPlocha);
    }

    public void presunHracaNavrch() {
        this.hrac.presunNavrch();
    }

}
