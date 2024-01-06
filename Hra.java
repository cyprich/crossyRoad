import fri.shapesge.Manazer;
import javax.swing.JOptionPane;

/**
 * Dava dokopy vsetky triedy a vytvara tak funkcnu hru
 *
 * @author Peter Cyprich
 * @version 1.0 (2024-01-06)
 */
public class Hra {
    private HraciaPlocha hraciaPlocha;
    private Hrac hrac;
    private Manazer manazer;
    private boolean hracPrehral;
    private boolean animaciaOhna;
    private Skore skore;
    private float posunAuta;

    /**
     * Vytvara hru
     */
    public Hra() {
        this.hraciaPlocha = new HraciaPlocha(this, this.posunAuta);
        this.hrac = new Hrac();
        this.manazer = new Manazer();

        this.hracPrehral = false;
        this.animaciaOhna = true;

        this.skore = new Skore();

        this.posunAuta = 1;

        this.manazer.spravujObjekt(this.hrac);
        this.manazer.spravujObjekt(this.hraciaPlocha);
        this.manazer.spravujObjekt(this.skore);
        this.manazer.spravujObjekt(this);
    }

    /**
     * Presunie obrazok hraca nad ostatne obrazky, aby bol viditelny
     */
    public void presunHracaNavrch() {
        this.hrac.presunNavrch();
    }

    /**
     * Prida 1 skore
     */
    public void pridajSkore() {
        this.skore.zmenSkore(this.skore.getSkore() + 1);
    }

    /**
     * Detekovanie kolizii s prekazkami
     * Metoda sa vola vzdy, ked hrac stlaci sipku hore na klavesnici
     */
    public void posunHore() {
        if (!this.hracPrehral) {
            Stlpec momentalnyStlpec = this.hraciaPlocha.getMomentalnyStlpec();
            // ak sa hrac nenachadza na jednom z prvych troch stlpcov
            if (momentalnyStlpec.getPolicko() != null) {
                int hracY = this.hrac.getY();
                int polickoY = momentalnyStlpec.getPolicko().getY();
                // ak sa hrac pri posune hore bude nachadzat na rovnakom policku ako prekazka
                // resp. ak sa nachadza pod prekazkou
                if (hracY - 100 == polickoY) {
                    // ak prekazka, na ktorej sa bude hrac nachadzat bude jama, ohen, alebo auto - hra sa skonci
                    if (momentalnyStlpec.getTyp().equals(TypPrekazky.JAMA) || momentalnyStlpec.getTyp().equals(TypPrekazky.OHEN) || momentalnyStlpec.getTyp().equals(TypPrekazky.AUTO)) {
                        this.hrac.posunHracaHore();
                        this.gameOver();
                    // ak prekazka, na ktorej sa bude hrac nachadzat bude strom alebo kamen - nespravi nic. ak nie, posunie sa na policko
                    } else if (!momentalnyStlpec.getTyp().equals(TypPrekazky.STROM) && !momentalnyStlpec.getTyp().equals(TypPrekazky.KAMEN)) {
                        this.hrac.posunHracaHore();
                    }
                } else {
                    this.hrac.posunHracaHore();
                }
            } else {
                this.hrac.posunHracaHore();
            }
        }
    }

    /**
     * Detekovanie kolizii s prekazkami
     * Metoda sa vola vzdy, ked hrac stlaci sipku dole na klavesnici
     */
    public void posunDole() {
        if (!this.hracPrehral) {
            Stlpec momentalnyStlpec = this.hraciaPlocha.getMomentalnyStlpec();
            // ak sa hrac nenachadza na jednom z prvych troch stlpcov
            if (momentalnyStlpec.getPolicko() != null) {
                int hracY = this.hrac.getY();
                int polickoY = momentalnyStlpec.getPolicko().getY();
                // ak sa hrac pri posune dole bude nachadzat na rovnakom policku ako prekazka
                // resp. ak sa nachadza nad prekazkou
                if (hracY + 100 == polickoY) {
                    // ak prekazka, na ktorej sa bude hrac nachadzat bude jama, ohen, alebo auto - hra sa skonci
                    if (momentalnyStlpec.getTyp().equals(TypPrekazky.JAMA) || momentalnyStlpec.getTyp().equals(TypPrekazky.OHEN) || momentalnyStlpec.getTyp().equals(TypPrekazky.AUTO)) {
                        this.hrac.posunHracaDole();
                        this.gameOver();
                    // ak prekazka, na ktorej sa bude hrac nachadzat bude strom alebo kamen - nespravi nic. ak nie, posunie sa na policko
                    } else if (!momentalnyStlpec.getTyp().equals(TypPrekazky.STROM) && !momentalnyStlpec.getTyp().equals(TypPrekazky.KAMEN)) {
                        this.hrac.posunHracaDole();
                    }
                } else {
                    this.hrac.posunHracaDole();
                }
            } else {
                this.hrac.posunHracaDole();
            }
        }
    }

    /**
     * Detekovanie kolizii s prekazkami
     * Metoda sa vola vzdy, ked hrac stlaci sipku hore alebo medzernik na klavesnici
     */
    public void posunDopredu() {
        if (!this.hracPrehral) {
            Stlpec nasledujuciStlpec = this.hraciaPlocha.getNasledujuciStlpec();
            // ak sa hrac nenachadza na jednom z prvych troch stlpcov
            if (nasledujuciStlpec.getPolicko() != null) {
                int hracY = this.hrac.getY();
                int polickoY = nasledujuciStlpec.getPolicko().getY();
                // ak sa hrac nachadza na na takej istej y-ovej suradnici ako prekazka v stlpci, na ktoru ide skocit
                // resp. ak sa nachadza pred prekazkou
                if (hracY == polickoY) {
                    // ak prekazka, na ktorej sa bude hrac nachadzat bude jama, ohen, alebo auto - hra sa skonci
                    if (nasledujuciStlpec.getTyp().equals(TypPrekazky.JAMA) || nasledujuciStlpec.getTyp().equals(TypPrekazky.OHEN) || nasledujuciStlpec.getTyp().equals(TypPrekazky.AUTO)) {
                        this.hraciaPlocha.posunHraciuPlochu();
                        this.gameOver();
                    // ak prekazka, na ktorej sa bude hrac nachadzat bude strom alebo kamen - nespravi nic. ak nie, posunie sa hracia plocha a prida sa skore
                    } else if (!nasledujuciStlpec.getTyp().equals(TypPrekazky.STROM) && !nasledujuciStlpec.getTyp().equals(TypPrekazky.KAMEN)) {
                        this.hraciaPlocha.posunHraciuPlochu();
                        this.pridajSkore();
                    }
                } else {
                    this.hraciaPlocha.posunHraciuPlochu();
                    this.pridajSkore();
                }
            } else {
                this.hraciaPlocha.posunHraciuPlochu();
                this.pridajSkore();
            }
        }
    }

    /**
     * Posun auta a detekovanie kolizii auta s hracom
     */
    public void tikAuto() {
        if (!this.hracPrehral) {
            for (Stlpec s : this.hraciaPlocha.getZoznamStlpcov()) {
                if (s.getAuto() != null) {
                    s.getAuto().posunAuto();
                }
            }

            // kolizie pri kazdom pohybe auta
            Stlpec momentalnyStlpec = this.hraciaPlocha.getMomentalnyStlpec();
            if (momentalnyStlpec.getAuto() != null) {
                int hracY = this.hrac.getY();
                int autoY = momentalnyStlpec.getAuto().getY();
                // ak je vzdialenost auta a hraca menej ako 80px
                // policko ma sice 100px, ale obrazky nie su na cele policko
                // ked bola hodnota 80 nastavena na hodnotu 100, hra sa skoncila aj ked sa hrac nedotykal auta
                if ((hracY - autoY < 80) && (hracY - autoY > -80)) {
                    this.gameOver();
                }
            }
        }
    }

    /**
     * Metoda, ktora sa zavola, ked hrac prehra
     * Zobrazi sa JOptionPane, ktory ponukne moznost hrat hru znovu alebo ukoncit hru
     */
    public void gameOver() {
        this.hracPrehral = true;
        while (true) {
            int hodnotaHratZnovu = JOptionPane.showConfirmDialog(null, "Prehrali ste! Prajete si hrať znova?");
            if (hodnotaHratZnovu == 0) {
                this.restart();
                break;
            } else if (hodnotaHratZnovu == 1) {
                // Zaciatok citacie
                /*
                 * COOPER, C. 2010. How to quit a java app from within the program [online]. Chris Cooper [cit. 2024-01-05]
                 * Dostupné na:
                 * https://stackoverflow.com/questions/2670956/how-to-quit-a-java-app-from-within-the-program
                */
                System.exit(0);
                // Koniec citacie

                break;
            }
        }
    }

    /**
     * Metoda, ktora sa zavola, ked chce hrac hrat hru znovu po prehre
     */
    public void restart() {
        this.hraciaPlocha.restart();
        this.skore.restart();
        this.hrac.restart();
        this.hrac.presunNavrch();
        this.posunAuta = 1;
        this.hracPrehral = false;
    }

    /**
     * Vytvara animaciu ohna
     * Boolean animaciaOhna rozhoduje o tom, ci obrazok bude normalny, alebo zrkadlovo obrateny, vytvara tak iluziu horenia
     * Metoda sa vola kazdych 500 milisekund
     */
    public void tikOhen() {
        if (!this.hracPrehral) {
            for (Stlpec s : this.hraciaPlocha.getZoznamStlpcov()) {
                if (s.getTyp().equals(TypPrekazky.OHEN)) {
                    if (this.animaciaOhna) {
                        s.getPolicko().zmenObrazok("pics/ohen_flip.png");
                        this.animaciaOhna = false;
                    } else {
                        s.getPolicko().zmenObrazok("pics/ohen.png");
                        this.animaciaOhna = true;
                    }
                }
            }
        }
    }

    /**
     * Zvysuje sa atribut posunAuta, ktory udava hodnotu, o ktoru sa auto posuva pri kazdom tiku
     */
    public void tikZrychliAuto() {
        this.posunAuta += 0.05;
        for (Stlpec s: this.hraciaPlocha.getZoznamStlpcov()) {
            if (s.getTyp().equals(TypPrekazky.AUTO)) {
                s.getAuto().setPosunAuta(this.posunAuta);
            }
        }
    }
}
