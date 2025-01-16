package filmProject.actions.film;

import filmProject.actions.Action;

import java.util.List;
import java.util.Scanner;

public class FilmAddAction extends Action {
    private Scanner scan = new Scanner(System.in);
    private final List<String> films;

    public FilmAddAction(String menuName, List<String> films) {
        super(menuName);
        this.films = films;
    }

    @Override
    public void execute() {
        System.out.println("Lütfen eklemek istediginiz Filmi giriniz : ");
        String yeniFilm = scan.nextLine();
        films.add(yeniFilm);
    }
}
