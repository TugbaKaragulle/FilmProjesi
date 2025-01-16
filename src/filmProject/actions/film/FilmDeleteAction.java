package filmProject.actions.film;

import filmProject.actions.Action;

import java.util.List;
import java.util.Scanner;

public class FilmDeleteAction extends Action {

    private Scanner scan = new Scanner(System.in);
    private final List<String> films;

    public FilmDeleteAction(String menuName, List<String> films) {
        super(menuName);
        this.films = films;
    }

    @Override
    public void execute() {
        System.out.println("Lütfen silmek istediginiz Filmi giriniz : ");
        String film = scan.nextLine();
        films.remove(film);
    }
}
