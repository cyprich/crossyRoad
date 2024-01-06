import fri.shapesge.Manazer;

import javax.swing.JOptionPane;

/**
 * Write a description of class Hra here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Hra {
    private HraciaPlocha hraciaPlocha;
    private Hrac hrac;
    private Manazer manazer;

    private boolean hracPrehral;
    private boolean animaciaOhna;
    private Skore skore;

    /**
     * Constructor for objects of class Hra
     */
    public Hra() {
        this.hraciaPlocha = new HraciaPlocha(this);
        this.hrac = new Hrac();
        this.manazer = new Manazer();

        this.hracPrehral = false;
        this.animaciaOhna = true;

        this.skore = new Skore();

        this.manazer.spravujObjekt(this.hrac);
        this.manazer.spravujObjekt(this.hraciaPlocha);
        this.manazer.spravujObjekt(this.skore);
        this.manazer.spravujObjekt(this);
    }

    public void presunHracaNavrch() {
        this.hrac.presunNavrch();
    }

    public void setHracPrehral(boolean hodnota) {
        this.hracPrehral = hodnota;
    }

    public boolean getHracPrehral() {
        return this.hracPrehral;
    }

    public void pridajSkore() {
        this.skore.zmenSkore(this.skore.getSkore() + 1);
    }

    // kolizie, ked hrac chce ist hore
    public void posunHore() {
        if (!this.hracPrehral) {
            Stlpec momentalnyStlpec = this.hraciaPlocha.getMomentalnyStlpec();
            if (momentalnyStlpec.getPolicko() != null) {
                int hracY = this.hrac.getY();
                int polickoY = momentalnyStlpec.getPolicko().getY();
                if (hracY - 100 == polickoY) {
                    if (momentalnyStlpec.getTyp().equals(TypPrekazky.JAMA) || momentalnyStlpec.getTyp().equals(TypPrekazky.OHEN)) {
                        this.hrac.posunHracaHore();
                        this.gameOver();
                    } else if (momentalnyStlpec.getTyp().equals(TypPrekazky.AUTO)) {
                        System.out.println(momentalnyStlpec.getAuto().getY());
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

    // kolizie, ked hrac chce ist dole
    public void posunDole() {
        if (!this.hracPrehral) {
            Stlpec momentalnyStlpec = this.hraciaPlocha.getMomentalnyStlpec();
            if (momentalnyStlpec.getPolicko() != null) {
                int hracY = this.hrac.getY();
                int polickoY = momentalnyStlpec.getPolicko().getY();
                if (hracY + 100 == polickoY) {
                    if (momentalnyStlpec.getTyp().equals(TypPrekazky.JAMA) || momentalnyStlpec.getTyp().equals(TypPrekazky.OHEN)) {
                        this.hrac.posunHracaDole();
                        this.gameOver();
                    } else if (momentalnyStlpec.getTyp().equals(TypPrekazky.AUTO)) {
                        System.out.println(momentalnyStlpec.getAuto().getY());
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

    // kolizie, ked hrac chce ist dopredu
    public void posunDopredu() {
        if (!this.hracPrehral) {
            Stlpec nasledujuciStlpec = this.hraciaPlocha.getNasledujuciStlpec();
            if (nasledujuciStlpec.getPolicko() != null) {
                int hracY = this.hrac.getY();
                int polickoY = nasledujuciStlpec.getPolicko().getY();
                if (hracY == polickoY) {
                    if (nasledujuciStlpec.getTyp().equals(TypPrekazky.JAMA) || nasledujuciStlpec.getTyp().equals(TypPrekazky.OHEN)) {
                        this.hraciaPlocha.posunHraciuPlochu();
                        this.gameOver();
                    } else if (nasledujuciStlpec.getTyp().equals(TypPrekazky.AUTO)) {
                        System.out.println(nasledujuciStlpec.getAuto().getY());
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
                if ((hracY - autoY < 80) && (hracY - autoY > -80)) {
                    this.gameOver();
                }
            }
        }
    }

    public void gameOver() {
        this.hracPrehral = true;
        while (true) {
            int hodnotaHratZnovu = JOptionPane.showConfirmDialog(null, "Prehrali ste! Prajete si hrať znova?");
            if (hodnotaHratZnovu == 0) {
                this.restart();
                break;
            } else if (hodnotaHratZnovu == 1) {
                /*
                 * COOPER, C. 2010. How to quit a java app from within the program [online]. Chris Cooper [cit. 2024-01-05]
                 * Dostupné na:
                 * https://stackoverflow.com/questions/2670956/how-to-quit-a-java-app-from-within-the-program
                */
                System.exit(0);
                /*
                 * Koniec citacie
                */
                break;
            }
        }
    }

    public void restart() {
        this.hraciaPlocha.restart();
        this.skore.restart();
        this.hrac.restart();
        this.hrac.presunNavrch();
        this.hracPrehral = false;
    }

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
}
