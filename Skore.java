import fri.shapesge.BlokTextu;
import fri.shapesge.StylFontu;

/**
 * Predstavuje skore, ktore dosiahol hrac pri hrani hry
 * 
 * @author Peter Cyprich
 * @version 1.0 (2024-01-06)
 */
public class Skore {
    private int skore;
    private int hodnotaNaZnizenieSkore;
    private BlokTextu blokTextu;

    /**
     * Predstavuje skore, ktore dosiahol hrac pri hrani hry
     */
    public Skore() {
        this.skore = 0;
        this.hodnotaNaZnizenieSkore = 0;

        this.blokTextu = new BlokTextu("Skóre: " + this.skore, 10, 30);
        this.blokTextu.zmenFont("Arial", StylFontu.PLAIN, 24);
        this.blokTextu.zobraz();
    }

    /**
     * Zmeni skore na danu hodnotu
     * @param noveSkore hodnota, na ktoru sa ma zmenit skore
     */
    public void zmenSkore(int noveSkore) {
        this.skore = noveSkore;
        this.blokTextu.zmenText("Skóre: " + this.skore);
        this.resetujHodnotuNaZnizenieSkore();
        this.dajNavrch();
    }

    /**
     * Presunie text skore nad ostatne obrazky, aby bol viditelny
     */
    public void dajNavrch() {
        this.blokTextu.skry();
        this.blokTextu.zobraz();
    }

    /**
     * Metoda na znizenie skore, ked je hrac neaktivny
     * Vzdy, ked sa zavola metoda, zvysi sa hodnota atributu hodnotaNaZnizenieSkore
     * Ak atribut hodnotaNaZnizenieSkore dosiahne hodnotu 3, hracove skore sa znizi
     */
    public void tikSkore() {
        this.hodnotaNaZnizenieSkore += 1;
        if (this.hodnotaNaZnizenieSkore >= 3) {
            this.zmenSkore(this.skore - 10);
        }
        // aby hrac nemal skore menej ako 0
        if (this.skore < 0) {
            this.skore = 0;
            this.zmenSkore(this.skore);
        }
    }

    /**
     * Resetuje hodnotu atributu hodnotaNaZnizenieSkore na 0
     * Metoda sa zavola vzdy, ked sa hrac posunie
     */
    public void resetujHodnotuNaZnizenieSkore() {
        this.hodnotaNaZnizenieSkore = 0;
    }

    /**
     * Resetuje momentalne skore a atribut hodnotaNaZnizenieSkore na 0
     */
    public void restart() {
        this.skore = 0;
        this.zmenSkore(this.skore);
        this.resetujHodnotuNaZnizenieSkore();
    }

    public int getSkore() {
        return this.skore;
    }
}
