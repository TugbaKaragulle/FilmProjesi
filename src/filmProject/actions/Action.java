package filmProject.actions;

public abstract class Action {
    private String menuName;

    public Action(String menuName) {
        this.menuName = menuName;
    }

    public abstract void execute();

    public String getMenuName() {
        return menuName;
    }
}
