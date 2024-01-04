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

        this.manazer.spravujObjekt(this.hrac);
        this.manazer.spravujObjekt(this.hraciaPlocha);
        this.manazer.spravujObjekt(this);
    }

    public void presunHracaNavrch() {
        this.hrac.presunNavrch();
    }

    public void posunHore() {
        System.out.println(this.hraciaPlocha.getMomentalnyStlpec());
    }

    // ked hrac pojde dopredu
    public void posunHraciuPlochu() {
        System.out.println(this.hrac.getY());
        System.out.println(this.hraciaPlocha.getNasledujuciStlpec().getPolicko());
    }

}
