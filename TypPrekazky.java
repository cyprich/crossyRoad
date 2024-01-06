/**
 * Predstavuje mozne typy prekazok
 * 
 * @author Peter Cyprich
 * @version 1.0 (2024-01-06)
 */
public enum TypPrekazky {
    /**
     * Predstavuje auto v hre
     */
    AUTO("auto"),
    /**
     * Predstavuje kamen v hre
     */
    KAMEN("kamen"),
    /**
     * Predstavuje strom v hre
     */
    STROM("strom"),
    /**
     * Predstavuje ohen v hre
     */
    OHEN("ohen"),
    /**
     * Predstavuje jamu v hre
     */
    JAMA("jama"),
    /**
     * Predstavuje prazdne policka v hre
     */
    PRAZDNE("");

    private String cesta;
    TypPrekazky(String nazovSuboru) {
        this.cesta = "pics/" + nazovSuboru + ".png";
    }

    /**
     * Vrati hodnotu atributu cesta
     */
    public String getCesta() {
        return this.cesta;
    }
}
