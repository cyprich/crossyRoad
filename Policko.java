import fri.shapesge.Obrazok;
/**
 * Write a description of class Policko here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Policko {
    private Obrazok obrazok;
    private int x;
    private int y;
    /**
     * Constructor for objects of class Policko
     */
    public Policko(String cesta, int x, int y) {
        this.x = x;
        this.y = y;
        this.obrazok = new Obrazok(cesta);
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
}
