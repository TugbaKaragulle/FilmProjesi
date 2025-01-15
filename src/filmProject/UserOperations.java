package filmProject;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Scanner;

public class UserOperations implements Operation {
    private Scanner scan = new Scanner(System.in);
    private Map<String, User> users = new HashMap<>();
    //Uygulamada tanimli olan create edilmis kullanicilari ututuyor.

    public UserOperations() {
        String userName = "admin";
        String password = "1234";
        users.put(userName, new User(userName, password, UserType.ADMIN));
    }

    public User login() {

        User user;
        int secim;

        do {
            System.out.println("Lütfen kullanici adini giriniz :");
            String userName = scan.nextLine();
            System.out.println("Lütfen sifrenizi giriniz : ");
            String password = scan.nextLine();
            if (!users.containsKey(userName) || !users.get(userName).getPassword().equals(password)) {
                System.out.println("Gecersiz kullanici adi veya sifre !");

                do {
                    System.out.println("Sisteme kayit olmak icin 1`e tekrar denemek icin 2 `ye basiniz : ");

                    secim = scan.nextInt();
                    scan.nextLine();
                    switch (secim) {
                        case 1 -> {
                            create();
                            System.out.println("Kullanici basariyla yaratildi, giris yapiniz");
                        }
                        case 2 -> {
                        }
                        default -> System.out.println("Gecersiz secim, Lutfen tekrar deneyin");
                    }

                } while (secim != 2 && secim != 1);

            } else {
                user = users.get(userName);
                System.out.println("Basarili giris yapildi.");
                break;
            }
        } while (true);

        return user;
    }

    @Override
    public void list() {
        System.out.println("Kayitli kullanicilar : ");
        for (String userName : users.keySet()) {
            System.out.println(userName);
        }
    }

    @Override
    public void create() {

        //UserName
        System.out.print("Lütfen kullanici adini giriniz : ");
        String userName;
        do {
            userName = scan.nextLine();
            if (users.containsKey(userName)) {
                System.out.println("Var olan bir kullanici adi, lütfen yeni bir kullanici ismi deneyin !");
            } else {
                break;
            }
        } while (true);


        //Password
        System.out.print("Lütfen sifrenizi giriniz :");
        String password;
        do {
            password = scan.nextLine();
            if (password.length() < 4) {
                System.out.println("Sifre uzunlugu en az  4 karakter olmali ! Lütfen tekrar giriniz : ");
            } else {
                break;
            }
        } while (true);

        User user = new User(userName, password, UserType.USER);
        users.put(userName, user);

    }

    @Override
    public void delete() {
        System.out.println("Lütfen silmek istediginiz kullanicinin Kullanici adini giriniz :");

        do {
            String userName = scan.nextLine();
            if (users.containsKey(userName)) {
                users.remove(userName);
                break;
            } else {
                System.out.println("Sectiginiz kullanici sistemde kayitli degil ,  lütfen tekrar giriniz : ");
            }
        } while (true);

    }


}
