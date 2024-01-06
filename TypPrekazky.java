
/**
 * Enumeration class TypPrekazky - write a description of the enum class here
 * 
 * @author (your name here)
 * @version (version number or date here)
 */
public enum TypPrekazky {
    AUTO("auto"),
    KAMEN("kamen"),
    STROM("strom"),
    OHEN("ohen"),
    JAMA("jama"),
    PRAZDNE("");

    private String cesta;
    private TypPrekazky(String nazovSuboru) {
        this.cesta = "pics/" + nazovSuboru + ".png";
    }

    public String getCesta() {
        return this.cesta;
    }
}


