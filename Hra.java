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

    /**
     * Constructor for objects of class Hra
     */
    public Hra() {
        this.hraciaPlocha = new HraciaPlocha(this);
        this.hrac = new Hrac();
        this.manazer = new Manazer();

        this.manazer.spravujObjekt(this.hrac);
        this.manazer.spravujObjekt(this.hraciaPlocha);
        this.manazer.spravujObjekt(this);

        this.hracPrehral = false;
        this.animaciaOhna = true;
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

    // KOLIZIE
    public void posunHore() {
        if (!this.hracPrehral) {
            Stlpec momentalnyStlpec = this.hraciaPlocha.getMomentalnyStlpec();
            if (momentalnyStlpec.getPolicko() != null) {
                int hracY = this.hrac.getY();
                int polickoY = momentalnyStlpec.getPolicko().getY();
                if (hracY - 100 == polickoY) {
                    if (momentalnyStlpec.getTyp().equals("jama") || momentalnyStlpec.getTyp().equals("ohen")) {
                        this.hrac.posunHracaHore();
                        this.gameOver();
                    } else if (momentalnyStlpec.getTyp().equals("auto")) {
                        System.out.println(momentalnyStlpec.getAuto().getY());
                    } else if (!momentalnyStlpec.getTyp().equals("strom") && !momentalnyStlpec.getTyp().equals("kamen")) {
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

    public void posunDole() {
        if (!this.hracPrehral) {
            Stlpec momentalnyStlpec = this.hraciaPlocha.getMomentalnyStlpec();
            if (momentalnyStlpec.getPolicko() != null) {
                int hracY = this.hrac.getY();
                int polickoY = momentalnyStlpec.getPolicko().getY();
                if (hracY + 100 == polickoY) {
                    if (momentalnyStlpec.getTyp().equals("jama") || momentalnyStlpec.getTyp().equals("ohen")) {
                        this.hrac.posunHracaDole();
                        this.gameOver();
                    } else if (momentalnyStlpec.getTyp().equals("auto")) {
                        System.out.println(momentalnyStlpec.getAuto().getY());
                    } else if (!momentalnyStlpec.getTyp().equals("strom") && !momentalnyStlpec.getTyp().equals("kamen")) {
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

    public void posunDopredu() {
        if (!this.hracPrehral) {
            Stlpec nasledujuciStlpec = this.hraciaPlocha.getNasledujuciStlpec();
            if (nasledujuciStlpec.getPolicko() != null) {
                int hracY = this.hrac.getY();
                int polickoY = nasledujuciStlpec.getPolicko().getY();
                if (hracY == polickoY) {
                    if (nasledujuciStlpec.getTyp().equals("jama") || nasledujuciStlpec.getTyp().equals("ohen")) {
                        this.hraciaPlocha.posunHraciuPlochu();
                        this.gameOver();
                    } else if (nasledujuciStlpec.getTyp().equals("auto")) {
                        System.out.println(nasledujuciStlpec.getAuto().getY());
                    } else if (!nasledujuciStlpec.getTyp().equals("strom") && !nasledujuciStlpec.getTyp().equals("kamen")) {
                        this.hraciaPlocha.posunHraciuPlochu();
                    }
                } else {
                    this.hraciaPlocha.posunHraciuPlochu();
                }
            } else {
                this.hraciaPlocha.posunHraciuPlochu();
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
                https://stackoverflow.com/questions/2670956/how-to-quit-a-java-app-from-within-the-program
                */
                System.exit(0);
                break;
            }
        }
    }

    public void restart() {
        this.hraciaPlocha.restart();
        this.hrac.restart();
        this.hrac.presunNavrch();
        this.hracPrehral = false;
    }

    public void tikOhen() {
        if (!this.hracPrehral) {
            for (Stlpec s : this.hraciaPlocha.getZoznamStlpcov()) {
                if (s.getTyp().equals("ohen")) {
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
