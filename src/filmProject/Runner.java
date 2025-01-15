package filmProject;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Runner {
    public static void main(String[] args) {
        UserOperations userOperations = new UserOperations();
        FilmOperations filmOperations = new FilmOperations();
        Scanner scan = new Scanner(System.in);
        User loggedInUser = userOperations.login();

        if (loggedInUser.getUserType().equals(UserType.NORMAL_USER)) {
            //Burada oyunu baslat.(Methodlari cagir. )
        } else {
            boolean flag = true;
            while (flag) {
                System.out.println("****MENÜ****");
                System.out.println("1- Filimleri Görüntüle");
                System.out.println("2 - Film Ekle ");
                System.out.println("3 - Film Sil");
                System.out.println("4 - Kullanicilari Görüntüle");
                System.out.println("5 - Kullanici Sil");
                System.out.println("6- Cikis");
                try {
                    int tercih = scan.nextInt();

                    switch (tercih) {
                        case 1:
                            filmOperations.list();
                            break;
                        case 2:
                            filmOperations.create();
                            break;
                        case 3:
                            filmOperations.delete();
                            break;
                        case 4:
                            userOperations.list();
                            break;
                        case 5:
                            userOperations.delete();
                            break;
                        case 6:
                            System.out.println("Cikis yapiliyor.");
                            flag = false;
                            break;

                        default:
                            System.out.println("Yanlis secim lütfen tekrar deneyin .");

                    }
                } catch (InputMismatchException e) {
                    System.out.println("Lütfen 1 - 6 arasinda bir sayi giriniz .");
                    scan.nextLine();
                }

            }


        }

    }
}
