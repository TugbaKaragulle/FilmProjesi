package filmProject;

import filmProject.actions.Action;
import filmProject.actions.film.FilmAddAction;
import filmProject.actions.film.FilmDeleteAction;
import filmProject.actions.film.FilmListAction;
import filmProject.actions.game.GameStartAction;
import filmProject.actions.user.UserAddAction;
import filmProject.actions.user.UserDeleteAction;
import filmProject.actions.user.UserListAction;

import java.util.*;

public class Runner {
    public static void main(String[] args) {


        List<String> films = new ArrayList<>(List.of("Avatar", "Gladiator", "Parazit", "Inception", "Titanic", "Joker",
                "Pianist", "Interstellar", "Shrek", "Soul"));
        Map<String, User> usersMap = new HashMap<>();
        //Default bir user tanimladik.
        String userName = "admin";
        String password = "1234";
        usersMap.put(userName, new User(userName, password, UserType.ADMIN));

        Action filmAddAction = new FilmAddAction("Film ekleme", films);
        Action filmListAction = new FilmListAction("Film listeleme", films);
        Action filmDeleteAction = new FilmDeleteAction("Film silme", films);

        Action userAddAction = new UserAddAction("Kullanici ekleme", usersMap);
        Action userListAction = new UserListAction("Kullanici listeleme", usersMap);
        Action userDeleteAction = new UserDeleteAction("Kullanici silme", usersMap);

        Action gameAction = new GameStartAction("Oyun");

        Map<UserType, List<Action>> userTypeActions = new HashMap<>();
        userTypeActions.put(UserType.ADMIN,
                Arrays.asList(filmAddAction, filmListAction, filmDeleteAction, userListAction, userDeleteAction));
        userTypeActions.put(UserType.NORMAL_USER, Arrays.asList(gameAction));

        UserLogin userLogin = new UserLogin(usersMap, userAddAction);

        Scanner scan = new Scanner(System.in);
        User loggedInUser = userLogin.login();

        while (true) {
            List<Action> loggedInUsersActions = userTypeActions.get(loggedInUser.getUserType());

            System.out.println();
            System.out.println("****MENÜ****");
            for (int i = 0; i < loggedInUsersActions.size(); i++) {
                System.out.println(i + " - " + loggedInUsersActions.get(i).getMenuName());
            }
            System.out.println(loggedInUsersActions.size() + " - Cikis");
            System.out.println();

            try {
                int tercih = scan.nextInt();

                if (tercih == loggedInUsersActions.size()) {
                    break;
                }

                loggedInUsersActions.get(tercih).execute();
            } catch (Exception e) {
                System.out.println("Lütfen 0 - " + loggedInUsersActions.size() + " arasinda bir sayi giriniz .");
                scan.nextLine();
            }

        }

    }
}
