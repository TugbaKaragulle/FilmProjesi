package filmProject.actions.film;

import filmProject.actions.Action;

import java.util.List;

public class FilmListAction extends Action {

    private final List<String> films;

    public FilmListAction(String menuName, List<String> films) {
        super(menuName);
        this.films = films;
    }

    @Override
    public void execute() {
        System.out.print("Kayitli Filmler : ");
        for (String film : films){
            System.out.println(film);
        }
    }
}
