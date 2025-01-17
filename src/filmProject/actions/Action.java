package filmProject.actions;

// Bu abstract class kullanicilarin gerceklestirebilecegi islemleri genellemek icin olusturuldu.
// Ayrica kullanici secimlerini dinamik olarak alabilmek icin menuName isimli bir field eklendi.
// Bu field menude ekrana yazdiriliyor
public abstract class Action {
    private String menuName;

    // Abstract class'in constructor'u ilk olarak child class constructor'unda cagirilir
    public Action(String menuName) {
        this.menuName = menuName;
    }

    // Aksiyonun ana sorumlulugunu yerine getirdigi metod, alt siniflar tarafindan implemente edilmeli
    public abstract void execute();

    public String getMenuName() {
        return menuName;
    }
}
