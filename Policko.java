import fri.shapesge.Obrazok;
/**
 * Jednotlive policka v hre, ktore predstavuju prekazky
 * Pouzivaju sa v triede Stlpec
 *
 * @author Peter Cyprich
 * @version 1.0 (2024-01-06)
 */
public class Policko {
    private Obrazok obrazok;
    private int x;
    private int y;

    /**
     * Vytvori policko s prekazkou
     *
     * @param cesta cesta ku obrazku prekazky
     * @param x x-ova suradnica policka (prekazky)
     * @param y y-ova suradnica policka (prekazky)
     */
    public Policko(String cesta, int x, int y) {
        this.x = x;
        this.y = y;
        this.obrazok = new Obrazok(cesta);
        this.obrazok.zmenPolohu(this.x, this.y);
        this.obrazok.zobraz();
    }

    /**
     * Zmeni polohu policka na suradnice zadane v parametri
     *
     * @param x x-ova suradnica, na ktoru sa ma policko posunut
     * @param y y-ova suradnica, na ktoru sa ma policko posunut
     */
    public void zmenPolohu(int x, int y) {
        this.x = x;
        this.y = y;
        this.obrazok.zmenPolohu(this.x, this.y);
    }

    /**
     * Zmeni obrazok prekazky
     *
     * @param cesta cesta ku obrazku prekazky
     */
    public void zmenObrazok(String cesta) {
        this.obrazok.zmenObrazok(cesta);
    }

    /**
     * Vrati hodnotu atributu x
     */
    public int getX() {
        return this.x;
    }

    /**
     * Vrati hodnotu atributu y
     */
    public int getY() {
        return this.y;
    }

    /**
     * Vymaze (skryje) policko
     */
    public void vymaz() {
        this.obrazok.skry();
    }
}
