package filmProject.actions.game;

import filmProject.actions.Action;

public class GameStartAction extends Action {
    public GameStartAction(String menuName) {
        super(menuName);
    }

    @Override
    public void execute() {
        System.out.println("Oyun baslasin...");
    }
}
