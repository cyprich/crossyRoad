import java.util.Random;
/**
 * Write a description of class Prekazka here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Prekazka {
    private Random generator;
    private int x;
    private int y;
    private Obrazok obrazok;
    private String typPrekazky;

    public Prekazka(String typPrekazky) {
        this.generator = new Random();
        
        this.x = this.nahodnaPoziciaX();
        this.y = this.nahodnaPoziciaY();
        
        this.typPrekazky = typPrekazky;
        
        switch (this.typPrekazky) {
            case "strom":
                this.obrazok = new Obrazok("pics/strom.png");
                break;
            case "kamen":
                this.obrazok = new Obrazok("pics/kamen.png");
                break;
            default:
                System.out.println("Nespravny typ obrazku, vyberte z: \"strom\", \"kamen\"");
        }
        
        this.obrazok.zobraz();
        this.zmenPolohu(this.x, this.y);
    }
    
    public void zmenPolohu(int x, int y) {
        this.obrazok.zmenPolohu(x, y);
    }
    
    /** 
     * vyberie nahodnu poziciu na obrazovke
     * pozicia x musi byt >300, aby sa neprekryval s kackou
     * pozicia x musi byt zaokruhlena na 100, aby bola prekazka zarovnana
     */ 
    public int nahodnaPoziciaX() {
        return (this.generator.nextInt(3) + 3) * 100;
    }
    
    /**
     * podobne ako pri pozicii x, ale moze byt <300
     */
    public int nahodnaPoziciaY() {
        return (this.generator.nextInt(5) + 1) * 100;
    }
    
    public String getPoloha () {
        return "";
    }
}
