import java.util.Random;
import java.util.ArrayList;
import fri.shapesge.Manazer;
/**
 * Write a description of class Stlpec here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Stlpec {
    private Manazer manazer;
    private Random random;
    private Policko policko;
    private ArrayList<Policko> zoznamPolicok;
    private Auto auto;
    private int x;
    /**
     * Constructor for objects of class Stlpec
     */
    public Stlpec(String typ, int x) {
        this.x = x;
        this.zoznamPolicok = new ArrayList<Policko>();
        this.random = new Random();
        this.manazer = new Manazer();

        switch (typ) {
            case "kamen":
            case "strom":
            case "ohen":
            case "jama":
                this.zoznamPolicok.add(new Policko(this.getCestaKObrazku(typ), this.x, this.getNahodnaPoloha()));
                break;
            case "auto":
                for (int i = 0; i < 7; i++) {
                    this.zoznamPolicok.add(new Policko(this.getCestaKObrazku("cesta"), this.x, i * 100));
                }
                this.auto = new Auto(this.x);
                this.manazer.spravujObjekt(this.auto);
                
            default:
                break;
        }
    }

    // zmeni typ obrazku na cestu k obrazku
    private String getCestaKObrazku(String s) {
        return "pics/" + s + ".png";
    }

    // vrati nahodnu polohu od 0 do 600, zaokruhlenu na 100 (kvoli zarovnaniu)
    private int getNahodnaPoloha() {
        return this.random.nextInt(6) * 100;
    }

    public void zmenPolohu(int x) {
        this.x = x;
        for (Policko policko : zoznamPolicok) {
            policko.zmenPolohu(this.x, policko.getY());

        }
        if (this.auto != null) {
            this.auto.zmenPolohu(this.x, this.auto.getY());
        }
    }

    public int getX() {
        return this.x;
    }

    public void vymaz() {
        for (Policko policko : zoznamPolicok) {
            policko.vymaz();
        }
        if (this.auto != null) {
            this.auto.vymaz();
        }
    }
}
