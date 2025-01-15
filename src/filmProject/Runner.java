package filmProject;

public class Runner {
    public static void main(String[] args) {
        UserOperations userOperations=new UserOperations();
        FilmOperations filmOperations=new FilmOperations();

        User user = userOperations.login();

    }
}
