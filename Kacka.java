
/**
 * Write a description of class Kacka here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Kacka {
    private int x;
    private int y;
    private Obrazok obrazok;
    
    public Kacka() {
        this.x = 0;
        this.y = 0;
        
        this.obrazok = new Obrazok("pics/kacka.png");
        this.obrazok.zobraz();
        
        this.zmenPolohu(100, 300);
    }
    
    public void zmenPolohu(int x, int y) {
        this.obrazok.zmenPolohu(x, y);
    }
    
    public String getPoloha(){
        return "" + this.x + this.y;
    }
}
