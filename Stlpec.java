import java.util.Random;
import java.util.ArrayList;
import fri.shapesge.Manazer;
/**
 * V kazdom stlpci sa nachadza jedno policko, ktore predstavuje prekazku
 * Stlpec predstavuje plochu 100×700px - zadava sa iba x-ova suradnica, y-ova je cela vyska platna
 * Stlpce sa pouzivaju v triede HraciaPlocha
 * 
 * @author Peter Cyprich
 * @version 1.0 (2024-01-06)
 */
public class Stlpec {
    private Manazer manazer;
    private Random random;
    private Policko policko;
    private ArrayList<Policko> zoznamPolicok;
    private Auto auto;
    private int x;
    private TypPrekazky typ;
    private float posunAuta;

    /**
     * Vytvori stlpec
     *
     * @param typ typ prekazky, ktora sa bude nachadzat v stlpci
     * @param x x-ova suradnica stlpca
     * @param posunAuta pocet pixelov, o kolko sa ma obrazok triedy Auto posuvat
     */
    public Stlpec(TypPrekazky typ, int x, float posunAuta) {
        this.x = x;
        this.typ = typ;
        this.zoznamPolicok = new ArrayList<Policko>();
        this.random = new Random();

        this.posunAuta = posunAuta;

        this.manazer = new Manazer();
        this.manazer.spravujObjekt(this);

        switch (this.typ) {
            case KAMEN:
            case STROM:
            case JAMA:
            case OHEN:
                this.policko = new Policko(typ.getCesta(), this.x, this.getNahodnaPoloha());
                this.zoznamPolicok.add(this.policko);
                break;
            case AUTO:
                for (int i = 0; i < 7; i++) {
                    this.zoznamPolicok.add(new Policko("pics/cesta.png", this.x, i * 100));
                }
                this.auto = new Auto(this.x, this.posunAuta);
                this.manazer.spravujObjekt(this.auto);
                break;
                
            default:
                break;
        }
    }

    /**
     * Vrati nahodnu polohu, na ktorej sa ma vygenerovat prekazka v stlpci
     * Vratena poloha je v rozsahu od 0 do 600 (rozmer platna)
     * Vratena poloha je kvoli zarovnaniu zaokruhlena na 100
     */
    private int getNahodnaPoloha() {
        return this.random.nextInt(6) * 100;
    }

    /**
     * Zmeni x-ovu suradnicu stlpca
     * @param x x-ova suradnica, na ktoru sa ma stlpec posunut
     */
    public void zmenPolohu(int x) {
        this.x = x;
        for (Policko polickoA : this.zoznamPolicok) {
            polickoA.zmenPolohu(this.x, polickoA.getY());

        }
        if (this.auto != null) {
            this.auto.zmenPolohu(this.x, this.auto.getY());
        }
    }


    /**
     * Vymaze (skryje) vsetky policka v stlpci
     */
    public void vymaz() {
        // nazov polickoA je kvoli checkstyle, bolo treba dat iny nazov ako policko (tento nazov sa uz pouziva)
        for (Policko polickoA : this.zoznamPolicok) {
            polickoA.vymaz();
        }
        if (this.auto != null) {
            this.auto.vymaz();
        }
    }

    /**
     * Vrati hodnotu atributu x
     */
    public int getX() {
        return this.x;
    }

    /**
     * Vrati hodnotu atributu policko
     */
    public Policko getPolicko() {
        return this.policko;
    }

    /**
     * Vrati hodnotu atributu typ
     */
    public TypPrekazky getTyp() {
        return this.typ;
    }

    /**
     * Vrati hodnotu atributu auto
     */
    public Auto getAuto() {
        return this.auto;
    }
}
