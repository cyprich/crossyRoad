import fri.shapesge.Obrazok;
import fri.shapesge.Manazer;
import java.util.Random;
/**
 * Specialny typ policka (prekazky)
 * 
 * @author Peter Cyprich
 * @version 1.0 (2024-01-06)
 */
public class Auto {
    private Obrazok obrazok;
    private Random random;
    private int x;
    private int y;
    private float posunAuta;

    /**
     * Vytvori auto
     * @param x x-ova suradnica, na ktoru sa ma auto umiestnit
     * @param posunAuta pocet pixelov, o kolko sa ma auto posuvat
     */
    public Auto(int x, float posunAuta) {
        this.random = new Random();
        this.x = x;
        this.y = this.random.nextInt(6) * 100;
        this.obrazok = new Obrazok("pics/auto.png");
        this.obrazok.zmenPolohu(this.x, this.y);
        this.obrazok.zobraz();

        this.posunAuta = posunAuta;
    }

    /**
     * Presunie polohu obrazku auta na danu polohu
     * @param x x-ova suradnica, na ktoru sa ma auto posunut
     * @param y y-ova suradnica, na ktoru sa ma auto posunut
     */
    public void zmenPolohu(int x, int y) {
        this.x = x;
        this.y = y;
        this.obrazok.zmenPolohu(this.x, this.y);
    }

    /**
     * Vymaze (skryje) auto
     */
    public void vymaz() {
        this.obrazok.skry();
    }

    /**
     * Metoda, ktora sa vola kazdych 10 milisekund
     * Posunie auto o 1px smerom nadol, vytvara iluziu jazdenia auta
     */
    public void posunAuto() {
        if (this.y < 700) {
            this.y += this.posunAuta;
        } else {
            this.y = -100;
        }
        this.obrazok.zmenPolohu(this.x, this.y);
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public void setPosunAuta(float hodnota) {
        this.posunAuta = hodnota;
    }
}
