import fri.shapesge.Obrazok;
/**
 * Vytvara hraca
 * 
 * @author Peter Cyprich
 * @version 1.0 (2024-01-06)
 */
public class Hrac {
    private Obrazok obrazok;
    private int x;
    private int y;

    /**
     * Vytvara hraca
     */
    public Hrac() {
        this.x = 100;
        this.y = 300;
        this.obrazok = new Obrazok("pics/kacka.png");
        this.obrazok.zmenPolohu(this.x, this.y);
        this.obrazok.zobraz();
    }

    /**
     * Posunie hraca o 100px (jedno policko) smerom hore
     * Ak je hrac na uplnom vrchu platna, nestane sa nic (inak by vysiel von z obrazovky)
     */
    public void posunHracaHore() {
        if (this.y > 0) {
            this.y -= 100;
            this.obrazok.zmenPolohu(this.x, this.y);
        }
    }

    /**
     * Posunie hraca o 100px (jedno policko) smerom dole
     * Ak je hrac na uplnom spodku platna, nestane sa nic (inak by vysiel von z obrazovky)
     */
    public void posunHracaDole() {
        if (this.y < 600) {
            this.y += 100;
            this.obrazok.zmenPolohu(this.x, this.y);
        }
    }

    /**
     * Presunie obrazok hraca nad ostatne obrazky, aby bol viditelny
     */
    public void presunNavrch() {
        this.obrazok.skry();
        this.obrazok.zobraz();
    }

    public int getY() {
        return this.y;
    }

    /**
     * Presunie hraca na pouziciu, na ktorej bol na zaciatku hry
     */
    public void restart() {
        this.y = 300;
        this.obrazok.zmenPolohu(this.x, this.y);
    }
}
