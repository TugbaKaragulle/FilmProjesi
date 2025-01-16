package filmProject.actions.user;

import filmProject.User;
import filmProject.actions.Action;

import java.util.Map;
import java.util.Scanner;

public class UserDeleteAction extends Action {
    private Scanner scan = new Scanner(System.in);
    private final Map<String, User> usersMap;

    public UserDeleteAction(String menuName, Map<String, User> usersMap) {
        super(menuName);
        this.usersMap = usersMap;
    }

    @Override
    public void execute() {
        System.out.println("Lütfen silmek istediginiz kullanicinin Kullanici adini giriniz :");

        do {
            String userName = scan.nextLine();
            if (usersMap.containsKey(userName)) {
                usersMap.remove(userName);
                break;
            } else {
                System.out.println("Sectiginiz kullanici sistemde kayitli degil ,  lütfen tekrar giriniz : ");
            }
        } while (true);

    }
}
