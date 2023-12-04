import java.util.ArrayList;
import java.util.Random;
/**
 * Write a description of class HraciaPlocha here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class HraciaPlocha {
    private Stlpec stlpec;
    private ArrayList<Stlpec> zoznamStlpcov;
    private ArrayList<String> zoznamPrekazok;
    private Random random;
    private Hra nadradenaTrieda;
    /**
     * Constructor for objects of class HraciaPlocha
     */
    public HraciaPlocha(Hra nadradenaTrieda) {
        this.zoznamPrekazok = new ArrayList<String>();
        this.zoznamPrekazok.add("auto");
        this.zoznamPrekazok.add("kamen");
        this.zoznamPrekazok.add("strom");
        this.zoznamPrekazok.add("ohen");
        this.zoznamPrekazok.add("jama");

        this.random = new Random();

        this.zoznamStlpcov = new ArrayList<Stlpec>();
        this.nadradenaTrieda = nadradenaTrieda;

        // vygeneruje stlpce, necha prve 3 prazdne (kvoli pripadnym koliziam s hracom)
        for (int i = 0; i < 7; i++) {
            if (i <= 3) {
                this.zoznamStlpcov.add(new Stlpec("", i * 100));
            } else {
                this.zoznamStlpcov.add(this.getNovyStlpec(i * 100));
            }
        }
    }

    public Stlpec getNovyStlpec(int x) {
        return new Stlpec(this.getNahodnaPrekazka(), x);
    }

    public String getNahodnaPrekazka() {
        return this.zoznamPrekazok.get(this.random.nextInt(this.zoznamPrekazok.size()));
    }

    public void tikHraciaPlocha() {
        // prida sa novy stlpec
        this.zoznamStlpcov.add(this.getNovyStlpec(700));
        
        // kazdy stlpec sa posunie dolava
        // posledny stlpec nalavo (mimo obrazovky) sa vymaze
        for (Stlpec stlpec : this.zoznamStlpcov) {
            if (stlpec != null && stlpec.getX() >= 100) {
                stlpec.zmenPolohu(stlpec.getX() - 100);
            } else if (stlpec.getX() < 100) {
                stlpec.vymaz();
            }
        }

        this.zoznamStlpcov.remove(0);
        this.nadradenaTrieda.presunHracaNavrch();
    }
}
