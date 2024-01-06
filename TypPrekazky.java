
/**
 * Predstavuje mozne typy prekazok
 * 
 * @author Peter Cyprich
 * @version 1.0 (2024-01-06)
 */
public enum TypPrekazky {
    AUTO("auto"),
    KAMEN("kamen"),
    STROM("strom"),
    OHEN("ohen"),
    JAMA("jama"),
    PRAZDNE("");

    private String cesta;
    TypPrekazky(String nazovSuboru) {
        this.cesta = "pics/" + nazovSuboru + ".png";
    }

    public String getCesta() {
        return this.cesta;
    }
}
