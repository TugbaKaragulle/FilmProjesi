package filmProject.actions.film;

import filmProject.actions.Action;

import java.util.List;
import java.util.Scanner;

public class FilmDeleteAction extends Action {

    private Scanner scan = new Scanner(System.in);
    private final List<String> films;
    public static final String RESET = "\u001B[0m";
    public static final String BOLD = "\u001B[1m";
    public static final String BLUE = "\u001B[34m";

    // Butun film aksiyonlari ayni film listesi uyerinde calisabilsin diye constructor'dan film listesi aliniyor
    public FilmDeleteAction(String menuName, List<String> films) {
        // Abstract class'in constructor'u ilk olarak child class constructor'unda cagirilir
        super(menuName);
        this.films = films;
    }

    @Override
    public void execute() {
        System.out.println(BLUE + BOLD + "Lütfen silmek istediginiz Filmi giriniz : " + RESET);
        String film = scan.nextLine();
        films.remove(film);
    }
}
