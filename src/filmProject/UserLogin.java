package filmProject;

import filmProject.actions.Action;
import filmProject.actions.user.UserAddAction;

import java.util.Map;
import java.util.Scanner;

public class UserLogin {
    public static final String RESET = "\u001B[0m";
    public static final String CYAN = "\u001B[36m";
    public static final String BOLD = "\u001B[1m";
    public static final String RED = "\u001B[31m";
    public static final String GREEN = "\u001B[32m";
    public static final String BLUE = "\u001B[34m";

    private Scanner scan = new Scanner(System.in);
    private final Map<String, User> usersMap;
    private final Action userAddAction;

    public UserLogin(Map<String, User> usersMap, Action userAddAction) {
        this.usersMap = usersMap;
        this.userAddAction = userAddAction;
    }

    public User loginOrRegister() {
        System.out.println(RED + BOLD + "*** Film tahmin oyununa hosgeldiniz ***" + RESET);

        // user objesi asagida return edildigi icin ilk deger verilmesi gerek, cunku switch icerisinde her bir case'de deger
        // set edilmiyor, o nedenle compiler hata veriyor
        User user = null;
        int secim;

        do {
            System.out.println(CYAN + "1 - Giris Yap" + RESET);
            System.out.println(CYAN + "2 - Kayit Ol" + RESET);
            System.out.println(CYAN + "3 - Cikis Yap" + RESET);

            secim = scan.nextInt();
            scan.nextLine();

            switch (secim) {
                case 1 -> user = login();
                case 2 -> {
                    userAddAction.execute();
                    System.out.println(RED + "Kullanici basariyla yaratildi" + RESET);
                    // Yaratilan kullanici getLastAddedUser metodu ile alinir ve loginOrRegister metodunu cagiran yere return
                    // edilir, boylece otomatik login islemi gerceklestirilir
                    user = ((UserAddAction) userAddAction).getLastAddedUser();
                }
                case 3 -> {
                    System.out.println();
                    System.out.println(BOLD + "Oyun sonlandiriliyor" + RESET);
                    // Bu menu adiminda uygulamanin basari ile durdurulmasi gerek o nedenle exit metodu cagriliyor
                    System.exit(0);
                }
                default -> {
                    System.out.println();
                    System.out.println(RED + "Gecersiz secim, lutfen tekrar deneyin" + RESET);
                    System.out.println();
                }
            }

        } while (secim != 2 && secim != 1);

        return user;
    }


    public User login() {

        User user;
        do {
            System.out.println();
            System.out.println(GREEN + "Lütfen kullanici adinizi giriniz : " + RESET);
            String userName = scan.nextLine();
            System.out.println(GREEN + "Lütfen sifrenizi giriniz : " + RESET);
            String password = scan.nextLine();
            if (!usersMap.containsKey(userName) || !usersMap.get(userName).getPassword().equals(password)) {
                System.out.println(BOLD + "Gecersiz kullanici adi veya sifre !" + RESET);
            } else {
                user = usersMap.get(userName);
                System.out.println(BLUE + "Basarili giris yapildi." + RESET);
                break;
            }
        } while (true);

        return user;
    }
}
