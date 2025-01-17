package filmProject;

import filmProject.actions.Action;
import filmProject.actions.film.FilmAddAction;
import filmProject.actions.film.FilmDeleteAction;
import filmProject.actions.film.FilmListAction;
import filmProject.actions.game.GameStartNewAction;
import filmProject.actions.user.UserAddAction;
import filmProject.actions.user.UserDeleteAction;
import filmProject.actions.user.UserListAction;

import java.util.*;


public class Runner {
    public static final String RESET = "\u001B[0m";
    public static final String BOLD = "\u001B[1m";
    public static final String RED = "\u001B[31m";
    public static final String BLUE = "\u001B[34m";

    public static void main(String[] args) {


        List<String> films = new ArrayList<>(List.of(BOLD + "Avatar", "Gladiator", "Parazit", "Inception", "Titanic", "Joker",
                "Pianist", "Interstellar", "Shrek", "Soul" + RESET));

        //Login islemleri sirasinda hizlica user`lara erisebilmek icin HashMap kullandim
        Map<String, User> usersMap = new HashMap<>();
        //Default bir Admin user tanimladim.Sistemde bunun disinda yaratilacak tüm user`larin Normal User olmasini varsaydim
        String userName = "admin";
        String password = "1234";

        //Key=userName Value=User objesi -- UserMap `de deger ekleniyor.
        usersMap.put(userName, new User(userName, password, UserType.ADMIN));

        // Film ile ilgili islemler icin Action objeleri olusturuldu
        Action filmAddAction = new FilmAddAction(BOLD + "Film ekleme" + RESET, films);
        Action filmListAction = new FilmListAction(BOLD + "Film listeleme" + RESET, films);
        Action filmDeleteAction = new FilmDeleteAction(BOLD + "Film silme" + RESET, films);

        // Kullanici ile ilgili islemler icin Action objeleri olusturuldu
        Action userAddAction = new UserAddAction(BOLD + "Kullanici ekleme" + RESET, usersMap);
        Action userListAction = new UserListAction(BOLD + "Kullanici listeleme" + RESET, usersMap);
        Action userDeleteAction = new UserDeleteAction(BOLD + "Kullanici silme" + RESET, usersMap);

        // Oyun ile ilgili islemler icin Action objeleri olusturuldu
        Action gameAction = new GameStartNewAction(RED + "Oyun" + RESET, films);

        // Action objeleri kullanici tipine gore gruplanip hashmap'de eklendi. Boylece login olan kullanicinin tipine
        // gore yapabilecegi islemler listelenecek asagida
        //key : UserType Value : List<Action>
        Map<UserType, List<Action>> userTypeActionsMap = new HashMap<>();
        userTypeActionsMap.put(UserType.ADMIN,
                Arrays.asList(filmAddAction, filmListAction, filmDeleteAction, userListAction, userDeleteAction));
        userTypeActionsMap.put(UserType.NORMAL_USER,
                Arrays.asList(gameAction));

        // Login ve Kayit islemleri secimi icin UserLogin objesi olusturuldu
        UserLogin userLogin = new UserLogin(usersMap, userAddAction);

        Scanner scan = new Scanner(System.in);
        while (true) {
            // Kullanicinin login yada kayit olma secimi icin loginOrRegister metodu cagirildi.
            // Burada giris menusu ekrana bastiriliyor
            User loggedInUser = userLogin.loginOrRegister();

            while (true) {
                // Login olan kullanicinin kullanici tipine gore gerceklestirebilecegi islemler hashmap'den alindi
                List<Action> loggedInUsersActions = userTypeActionsMap.get(loggedInUser.getUserType());

                System.out.println();

                //Menüyü otomatik bastiran kisim
                System.out.println(RED + BOLD + "*** MENÜ ***" + RESET);
                // Menu login olan kullanicinin tipine gore gerceklestirebilecegi islemler menu olarak listelendi(for i)
                for (int i = 0; i < loggedInUsersActions.size(); i++) {
                    System.out.println(i + " - " + loggedInUsersActions.get(i).getMenuName());
                   //Admin -action                        User Action
                    //*** MENÜ ***                      //*** MENÜ ***
                    //0 - Film ekleme                   //0 - Oyun
                    //1 - Film listeleme                //1 - Kullanici Degistir
                    //2 - Film silme
                    //3 - Kullanici listeleme
                    //4 - Kullanici silme
                }
                // Login olan kullanici, kullanici degistirebilmesi icin menuye secenek eklendi (son islem)
                System.out.println(loggedInUsersActions.size() + BOLD + " - Kullanici Degistir" + RESET);
                System.out.println();


                //Switch case yerine dinamik method call
                try {
                    // Menuden kullanici tercihi alindi
                    int tercih = scan.nextInt();

                    // Kullanici son islemi (kullanici degistir) secerse bir onceki menu adimina gecmesi icin break yapildi
                    if (tercih == loggedInUsersActions.size()) {
                        break;
                    }

                    // Kullanici secimine gore ilgili aksiyon listeden get ediliyor ve execute metodu aksiyonu calistirmak icin cagriliyor
                    loggedInUsersActions.get(tercih).execute();
                } catch (Exception e) {
                    System.out.println(BLUE + "Lütfen 0 - " + loggedInUsersActions.size() + " arasinda bir sayi giriniz ." + RESET);
                    // nextInt() metodundan sonra scanner'in tamponunu bosaltmak icin cagrilmasi gerek
                    scan.nextLine();
                }

            }
        }

    }
}
