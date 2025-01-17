package filmProject.actions.game;

import filmProject.actions.Action;

import java.util.List;
import java.util.Scanner;

public class GameStartNew extends Action {
    private final List<String> films;
    private Scanner scan = new Scanner(System.in);
    int filmNo;

    public GameStartNew(String menuName, List<String> films) {
        super(menuName);
        this.films = films;
    }

    @Override
    public void execute() {
        oyunuOyna(films);
    }

    public void hakSayisi(List<String> films, int filmNo) {
        // Seçilen filmin harf sayısını boşlukları hariç hesaplayan method. Replace ile bosluklari hiclikle degistirdik ve dogru harf sayisina ulastik
        String filmAdi = films.get(filmNo - 1).replace(" ", "");
        System.out.println("Seçtiğiniz film " + filmAdi.length() + " harfli.");
    }

    public void kullanicininFilmNoGirisKontrolu(List<String> films) {
        // Kullanıcıdan geçerli bir film numarası almayi saglayan method.
        // Kullanici harf girerse, 0 , eksi deger ya da listedeki film sayisindan büyük bir rakam girerse bu kontrol devreye girecek
        System.out.print("Lütfen tahmin için 1 ile " + films.size() + " arasında bir film numarası giriniz: ");

        do {
            if (scan.hasNextInt()) { // Girilen değerin rakam olup olmadığını kontrol ediyoruz
                filmNo = scan.nextInt();
                scan.nextLine(); //
                if (filmNo >= 1 && filmNo <= films.size()) { // eger girilen aralik dogruysa
                    hakSayisi(films, filmNo); // filmin harf sayisini ekrana yazdiriyoruz
                    break; // dogru girerse döngü biter
                } else {
                    System.out.println("Tahmin için 1 ile " + films.size() + " arasında bir sayı giriniz:"); //Films.size() methoduyla dinamik hale getirdik.
                    // Admin film ekler ya da cikarirsa kodumuz bundan olumsuz etkilenmeyecek
                }
            } else {
                System.out.println("Yalnızca 1 ile " + films.size() + " arasında bir sayı girmelisiniz:");
                scan.nextLine(); // Hatali giriste temizleme yapar
            }
        } while (true); // Kullanicidan dogru girisi alana kadar döngümüz devam eder
    }

    public void oyunuOyna(List<String> films) {

        System.out.println("***************OYUN BAŞLIYOR****************");

        //Kullanicidan kontrollü sekilde tahmin etmek istedigi film nosunu aliyoruz
        kullanicininFilmNoGirisKontrolu(films);

        // bosluklarla birlikte secilen filmin adini alip toLoverCase ile kücük harfe ceviriyoruz
        String filmAdi = films.get(filmNo - 1).toLowerCase();
        // replace methoduyla film ismindeki bosluklari kaldirip, gecici isminde bir stringe atiyoruz
        String tempFilmAdi = filmAdi.replace(" ", "");

        // Gizli film karakterleri (başlangıçta boşluklar hariç '_')
        char[] gizliFilm = new char[filmAdi.length()]; //bosluklarla birlikte film adinin uzunlugunda bir char arr olusturup bosluklar atiyorum
        for (int i = 0; i < gizliFilm.length; i++) {
            gizliFilm[i] = (filmAdi.charAt(i) == ' ') ? ' ' : '_'; // Burada ternary kullandik.
            // eger filmin karakterinde bosluk varsa bosluk eklenecek, yoksa alt cizgi eklenir
        }

        int kalanHak = tempFilmAdi.length() * 2; // eger film adindan bosluk varsa, bosluklari kaldirip onu gecici stringe atmistik. amac burada hak sayisini dogru yazdirmaktir
        // tahmin hakki harf sayisinin 2 kati
        int dogruTahminSayisi = 0; // sayac yöntemiyle dogru tahmini ekrana yazdiracagiz
        int yanlisTahminSayisi = 0; // ayni sekilde yanlis tahmini de yazdiracagiz
        boolean filmBulundu = false; // filmin adi dogru oldugunda true'ya dönecek

        while (kalanHak > 0 && !filmBulundu) { // Kullanicinin tahmin hakki bitene ya da filmi bulana kadar döngü devam eder
            // her seferinde filmin adini, tahmin hakkini ve harf tahmin edin yazisini ekrana getiren kisim:
            System.out.println("Filmin Adı: " + String.valueOf(gizliFilm)); // karakter arr'yi tekbir stringe cevirir ve dogru bilinen harflerle filmi ekranda gösterir
            System.out.println("Tahmin hakkınız: " + kalanHak);
            System.out.print("Bir harf tahmin edin: ");

            // Kullanıcıdan harf aliyoruz ve char olarak kaydediyoruz
            char tahmin = scan.nextLine().toLowerCase().charAt(0);

            boolean dogruTahmin = false; // Kullanici tahminlerinin doğru olup olmadığını kontrol eder, dogru olunca true'ya döner

            // Tahmin edilen harfin kontolünü saglayan for loopumuz
            for (int i = 0; i < filmAdi.length(); i++) {
                if (filmAdi.charAt(i) == tahmin) { // Eğer tahmin dogru ise
                    gizliFilm[i] = tahmin; // harf arrayimize eklenir
                    dogruTahmin = true; //ve bayragimiz true olur
                }
            }

            if (dogruTahmin) { // Doğru tahmin durumunda //bayragimiz true iseee
                dogruTahminSayisi++; // dogru tahmin sayimiz artacak
                System.out.println("Doğru tahmin!"); //ekrana yazdiracak
            } else { // yanlis iseee
                yanlisTahminSayisi++; // yanlis tahmin sayisi artck
                System.out.println("Yanlış tahmin!"); //ekrana yazdiracak
            }

            kalanHak--; // kalan hak her seferinde düser

            // Kullanici dogru ve yanlis tahmin sayisini görebilecek
            System.out.println("Doğru tahmin sayısı: " + dogruTahminSayisi);
            System.out.println("Yanlış tahmin sayısı: " + yanlisTahminSayisi);
            System.out.println("                                "); //bu bosluk estetik dursun diye :-)

            // char arr stringe dönüsüp de bosluklar kaldirildiginda, bosluklari kaldirilmis film adimizla esit olursa film dogru bilinmis olacak
            if (String.valueOf(gizliFilm).replace(" ", "").equals(tempFilmAdi)) {
                filmBulundu = true; //Bir bayrak da filmin bulundugunu anlamamiz icin
            }
        }

        // Oyunun sonucunu yazdırıyoruz
        if (filmBulundu) { //filmbulundu bayragimiz true olursa
            System.out.println("Tebrikler! Filmi buldunuz: " + filmAdi.toUpperCase()); //ekrana tabrik mesajimiyi sonuc olarak ekleriz
        } else {
            System.out.println("Tahmin hakkınız maalesef bitti! Film: " + filmAdi.toUpperCase()); //bulunamadiginda ekrana gelecek olan sonuc mesaji
        }
    }
}
