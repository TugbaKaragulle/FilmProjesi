package filmProject;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FilmOperations implements Operation {
   private Scanner scan = new Scanner(System.in);
   private List<String> films = new ArrayList<>(List.of("Avatar", "Gladiator", "Parazit", "Inception", "Titanic", "Joker",
            "Pianist", "Interstellar", "Shrek", "Soul"));

    @Override
    public void list() {
        System.out.print("Kayitli Filmler : ");
        for (String film : films){
            System.out.println(film);
        }
    }

    @Override
    public void create() {

        System.out.println("Lütfen eklemek istediginiz Filmi giriniz : ");
        String yeniFilm = scan.nextLine();
        films.add(yeniFilm);

    }

    @Override
    public void delete() {
        System.out.println("Lütfen silmek istediginiz Filmi giriniz : ");
        String film = scan.nextLine();
        films.remove(film);
    }
}
