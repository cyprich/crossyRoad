public class Pokus {
    public static void main(String[] args) {
        for (TypPrekazky x: TypPrekazky.values()) {
            System.out.println(x.getCesta());
        }
        System.out.println(TypPrekazky.values().length);
    }
}
