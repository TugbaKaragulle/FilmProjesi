package filmProject.actions.user;

import filmProject.User;
import filmProject.actions.Action;

import java.util.Map;
import java.util.Scanner;

public class UserListAction extends Action {

    private Scanner scan = new Scanner(System.in);
    private final Map<String, User> usersMap;

    public UserListAction(String menuName, Map<String, User> usersMap) {
        super(menuName);
        this.usersMap = usersMap;
    }

    @Override
    public void execute() {
        System.out.println("Kayitli kullanicilar : ");
        for (String userName : usersMap.keySet()) {
            System.out.println(userName);
        }
    }
}
