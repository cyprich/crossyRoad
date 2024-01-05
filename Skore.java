import fri.shapesge.BlokTextu;
import fri.shapesge.StylFontu;

/**
 * Write a description of class Skore here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Skore {
    /**
     * Constructor for objects of class Skore
     */
    private int skore;
    private int hodnotaNaZnizenieSkore;
    private BlokTextu blokTextu;
    public Skore() {
        this.skore = 0;
        this.hodnotaNaZnizenieSkore = 0;

        this.blokTextu = new BlokTextu("Skóre: " + this.skore, 10, 30);
        this.blokTextu.zmenFont("Arial", StylFontu.PLAIN, 24);
        this.blokTextu.zobraz();
    }

    public void zmenSkore(int noveSkore) {
        this.skore = noveSkore;
        this.blokTextu.zmenText("Skóre: " + this.skore);
        this.dajNavrch();
    }

    public int getSkore() {
        return this.skore;
    }

    public void dajNavrch() {
        this.blokTextu.skry();
        this.blokTextu.zobraz();
    }

    public void tikSkore() {
        // kazdu sekundu sa prida "hodnotaNaZnizenieSkore"
        // vzdy ked sa hrac posunie, "hodnotaNaZnizenieSkore" sa vynuluje
        // ak sa hrac nebude hybat 3 sekundy, jeho skore sa znizi o 10
        this.hodnotaNaZnizenieSkore += 1;
        if (this.skore > 0 && this.hodnotaNaZnizenieSkore >= 3) {
            this.zmenSkore(this.skore - 10);
        }
    }

    public void resetujHodnotuNaZnizenieSkore() {
        this.hodnotaNaZnizenieSkore = 0;
    }

    public void restart() {
        this.skore = 0;
        this.zmenSkore(0);
        this.resetujHodnotuNaZnizenieSkore();
    }
}
