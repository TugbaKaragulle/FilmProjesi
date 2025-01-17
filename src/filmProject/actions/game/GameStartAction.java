package filmProject.actions.game;

import filmProject.actions.Action;

import java.util.List;
import java.util.Scanner;

public class GameStartAction extends Action {

    public static final String RESET = "\u001B[0m";
    public static final String CYAN = "\u001B[36m";
    public static final String BOLD = "\u001B[1m";
    public static final String RED = "\u001B[31m";
    public static final String BLUE = "\u001B[34m";

    private Scanner scan = new Scanner(System.in);
    // Buradaki final referansa sadece bir kere obje atanabilmesini sagliyor. Obje uyerinde degisiklik yapabiliriz
    // film ekleme cikarma gibi ama new ile yeni bir obje yaratip referansa assign edemeyiz
    private final List<String> films;

    // Butun film aksiyonlari ayni film listesi uyerinde calisabilsin diye constructor'dan film listesi aliniyor
    public GameStartAction(String menuName, List<String> films) {
        super(menuName);
        this.films = films;
    }

    @Override
    public void execute() {
        System.out.println(RED + "**** OYUN BASLASIN ****" + RESET);
        while (true) {
            System.out.print(BOLD + "Lütfen 0 ile " + (films.size() - 1) + " arasinda bir sayi giriniz : " + RESET);
            try {
                int secim = scan.nextInt();
                scan.nextLine();

                // Beklenen sayi araligi disinda bir sayi girilirse tekrar secim yapilmasi icin continue cagirilir
                // ve dongunun basina donulur
                if (secim < 0 || secim >= films.size()) {
                    continue;
                }

                String secilenFilm = films.get(secim);
                int tahminHakki = secilenFilm.length() * 2;

                // failed flag'i kullanici basarisiz olursa oyun sonucunu ve filmi ekrana yazdirmak icin kullanilir
                boolean failed = true;
                for (int i = tahminHakki; i > 0; i--) {
                    System.out.println(CYAN + "Kalan tahmin sayisi : " + i + "/" + tahminHakki + RESET);
                    System.out.println(BOLD + "Tahmininizi giriniz ( " + secilenFilm.length() + " harf ) :" + RESET);
                    String yeniSecim = scan.nextLine();
                    if (yeniSecim.equalsIgnoreCase(secilenFilm)) {
                        System.out.println(BLUE + "Tebrikler " + (tahminHakki - i + 1) + ". denemede bildiniz :)" + RESET);

                        failed = false;
                        break;
                    } else {
                        System.out.println(BOLD + "Tahmin basarisiz :( " + RESET);
                        System.out.println("");
                    }
                }

                if (failed) {
                    System.out.println("\u001B[1m" + "Kaybettiniz, Beklenen Film  : " + secilenFilm.toUpperCase() + RESET);
                }
                break;
            } catch (Exception e) {
                scan.nextLine();
            }
        }
    }
}
