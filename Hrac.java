import fri.shapesge.Obrazok;
/**
 * Write a description of class Hrac here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Hrac {
    private Obrazok obrazok;
    private int x;
    private int y;
    /**
     * Constructor for objects of class Hrac
     */
    public Hrac() {
        this.x = 100;
        this.y = 300;
        this.obrazok = new Obrazok("pics/kacka.png");
        this.obrazok.zmenPolohu(this.x, this.y);
        this.obrazok.zobraz();
    }

    public void posunHracaHore() {
        if (this.y > 0) {
            this.y -= 100;
            this.obrazok.zmenPolohu(this.x, this.y);
        }
    }

    public void posunHracaDole() {
        if (this.y < 600) {
            this.y += 100;
            this.obrazok.zmenPolohu(this.x, this.y);
        }
    }

    // presunie (obrazok) hraca navrch vsetkych ostatnych obrazkov
    public void presunNavrch() {
        this.obrazok.skry();
        this.obrazok.zobraz();
    }

    public int getY() {
        return this.y;
    }
}
