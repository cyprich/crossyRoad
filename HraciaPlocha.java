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
        this.generujPrazdnyZoznamStlpcov();
    }

    // vygeneruje stlpce, necha prve 3 prazdne (kvoli pripadnym koliziam s hracom)
    public void generujPrazdnyZoznamStlpcov() {
        this.zoznamStlpcov.clear();
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

    public void posunHraciuPlochu() {
        // prida sa novy stlpec
        this.zoznamStlpcov.add(this.getNovyStlpec(700));
        
        // kazdy stlpec sa posunie dolava
        // posledny stlpec nalavo (mimo obrazovky) sa vymaze
        // nazov stlpecA je kvoli checkstyle, bolo treba dat iny nazov ako stlpec
        for (Stlpec stlpecA : this.zoznamStlpcov) {
            if (stlpecA != null && stlpecA.getX() >= 100) {
                stlpecA.zmenPolohu(stlpecA.getX() - 100);
            } else if (stlpecA.getX() < 100) {
                stlpecA.vymaz();
            }
        }

        this.zoznamStlpcov.remove(0);
        this.nadradenaTrieda.presunHracaNavrch();
    }

    // stlpec, na ktorom sa nachadza hrac (kolizie)
    public Stlpec getMomentalnyStlpec() {
        return this.zoznamStlpcov.get(1);
    }

    // stlpec, pred ktorym sa nachadza hrac (kolizie)
    public Stlpec getNasledujuciStlpec() {
        return this.zoznamStlpcov.get(2);
    }

    public ArrayList<Stlpec> getZoznamStlpcov() {
        return this.zoznamStlpcov;
    }

    public void restart() {
        for (Stlpec s: this.zoznamStlpcov) {
            s.vymaz();
        }
        this.generujPrazdnyZoznamStlpcov();
    }
}
