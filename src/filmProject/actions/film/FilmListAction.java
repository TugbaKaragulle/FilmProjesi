package filmProject.actions.film;

import filmProject.actions.Action;

import java.util.List;

public class FilmListAction extends Action {

    private final List<String> films;
    public static final String RESET = "\u001B[0m";
    public static final String BOLD = "\u001B[1m";
    public static final String BLUE = "\u001B[34m";

    // Butun film aksiyonlari ayni film listesi uyerinde calisabilsin diye constructor'dan film listesi aliniyor
    public FilmListAction(String menuName, List<String> films) {
        // Abstract class'in constructor'u ilk olarak child class constructor'unda cagirilir
        super(menuName);
        this.films = films;
    }

    @Override
    public void execute() {
        System.out.print(BLUE + BOLD + "Kayitli Filmler : " + RESET);
        for (String film : films) {
            System.out.println(film);
        }
    }
}
