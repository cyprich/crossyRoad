import fri.shapesge.Obrazok;
import java.util.Random;
/**
 * Write a description of class Auto here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Auto {
    private Obrazok obrazok;
    private Random random;
    private int x;
    private int y;
    /**
     * Constructor for objects of class Auto
     */
    public Auto(int x) {
        this.random = new Random();
        this.x = x;
        this.y = this.random.nextInt(6) * 100;
        this.obrazok = new Obrazok("pics/auto.png");
        this.obrazok.zmenPolohu(this.x, this.y);
        this.obrazok.zobraz();
    }

    public void zmenPolohu(int x, int y) {
        this.x = x;
        this.y = y;
        this.obrazok.zmenPolohu(this.x, this.y);
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public void vymaz() {
        this.obrazok.skry();
    }

    public void tikAuto() {
        if (this.y < 700) {
            this.y += 1;
        } else {
            this.y = -100;
        }
        this.obrazok.zmenPolohu(this.x, this.y);
    }
}
