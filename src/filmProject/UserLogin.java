package filmProject;

import filmProject.actions.Action;

import java.util.Map;
import java.util.Scanner;

public class UserLogin {

    private Scanner scan = new Scanner(System.in);
    private final Map<String, User> usersMap;
    private final Action userAddAction;

    public UserLogin(Map<String, User> usersMap, Action userAddAction) {
        this.usersMap = usersMap;
        this.userAddAction = userAddAction;
    }

    public User login() {

        User user;
        int secim;

        do {
            System.out.println("Lütfen kullanici adini giriniz :");
            String userName = scan.nextLine();
            System.out.println("Lütfen sifrenizi giriniz : ");
            String password = scan.nextLine();
            if (!usersMap.containsKey(userName) || !usersMap.get(userName).getPassword().equals(password)) {
                System.out.println("Gecersiz kullanici adi veya sifre !");

                do {
                    System.out.println("Sisteme kayit olmak icin 1`e tekrar denemek icin 2 `ye basiniz : ");

                    secim = scan.nextInt();
                    scan.nextLine();
                    switch (secim) {
                        case 1 -> {
                            userAddAction.execute();
                            System.out.println("Kullanici basariyla yaratildi, giris yapiniz");
                        }
                        case 2 -> {
                        }
                        default -> System.out.println("Gecersiz secim, Lutfen tekrar deneyin");
                    }

                } while (secim != 2 && secim != 1);

            } else {
                user = usersMap.get(userName);
                System.out.println("Basarili giris yapildi.");
                break;
            }
        } while (true);

        return user;
    }
}
