import java.util.ArrayList;
import java.util.Random;
/**
 * Vytvara jednotlive stlpce
 * Pouziva sa v triede Hra
 * 
 * @author Peter Cyprich
 */
public class HraciaPlocha {
    private Stlpec stlpec;
    private ArrayList<Stlpec> zoznamStlpcov;
    private Random random;
    private Hra nadradenaTrieda;
    /**
     * Vytvara jednotlive stlpce
     */
    public HraciaPlocha(Hra nadradenaTrieda) {
        this.random = new Random();

        this.zoznamStlpcov = new ArrayList<Stlpec>();
        this.nadradenaTrieda = nadradenaTrieda;
        this.generujPrazdnyZoznamStlpcov();
    }

    /**
     * Vygeneruje novy zoznam stlpcov
     * Prve 3 stlpce vytvori bez prekazky, kvoli pripadnym koliziam s hracom
     */
    private void generujPrazdnyZoznamStlpcov() {
        this.zoznamStlpcov.clear();
        for (int i = 0; i < 7; i++) {
            if (i <= 3) {
                this.zoznamStlpcov.add(new Stlpec(TypPrekazky.PRAZDNE, i * 100));
            } else {
                this.zoznamStlpcov.add(this.getNovyStlpec(i * 100));
            }
        }
    }

    /**
     * Vrati nahodnu prekazku
     */
    public TypPrekazky getNahodnaPrekazka() {
        return TypPrekazky.values()[this.random.nextInt(TypPrekazky.values().length)];
    }

    /**
     * Vrati stlpec s nahodnou prekazkou
     * @param x x-ova suradnica, na ktoru sa ma stlpec umiestnit
     */
    public Stlpec getNovyStlpec(int x) {
        return new Stlpec(this.getNahodnaPrekazka(), x);
    }

    /**
     * Posunie vsetky stlpce dolava o 100px
     * Metoda sa vyvovlava vzdy, ked sa hrac posunie dopredu
     * Vytvara iluziu pohybu hraca
     */
    public void posunHraciuPlochu() {
        // prida sa novy stlpec
        this.zoznamStlpcov.add(this.getNovyStlpec(700));
        
        // kazdy stlpec sa posunie dolava
        // nazov stlpecA je kvoli checkstyle, bolo treba dat iny nazov ako stlpec
        for (Stlpec stlpecA : this.zoznamStlpcov) {
            if (stlpecA != null && stlpecA.getX() >= 100) {
                stlpecA.zmenPolohu(stlpecA.getX() - 100);
            } else if (stlpecA.getX() < 100) {
                stlpecA.vymaz();
            }
        }

        // stlpec nalavo (mimo obrazovky) sa vymaze
        this.zoznamStlpcov.remove(0);
        this.nadradenaTrieda.presunHracaNavrch();
    }

    /**
     * Vrati stlpec, na ktorom sa nachadza hrac
     * Pouziva sa v triede Hra pri detekovani kolizii s prekazkami
     */
    public Stlpec getMomentalnyStlpec() {
        return this.zoznamStlpcov.get(1);
    }

    /**
     * Vrati stlpec, pred ktorym sa nachadza hrac
     * Pouziva sa v triede Hra pri detekovani kolizii s prekazkami
     */
    public Stlpec getNasledujuciStlpec() {
        return this.zoznamStlpcov.get(2);
    }

    public ArrayList<Stlpec> getZoznamStlpcov() {
        return this.zoznamStlpcov;
    }

    /**
     * Vymaze aktualne stlpce a vygeneruje nove tak, ako na zaciatku hry
     */
    public void restart() {
        for (Stlpec s: this.zoznamStlpcov) {
            s.vymaz();
        }
        this.generujPrazdnyZoznamStlpcov();
    }
}
