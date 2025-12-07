package kevin.hero_manager;

public class Hero {
    private int id;
    private String name;
    private HeroClass hClass;
    private int hp;
    private SpecialAbility sa;

    public Hero(){
        this.id = 0;
        this.name = "Kevin";
        this.hClass = HeroClass.MAGE;
        this.hp = 200;
        this.sa = SpecialAbility.DOOM;
    }

    public Hero(String name, HeroClass hClass, int hp, SpecialAbility sa){
        this.id = 0;
        this.name = name;
        this.hClass = hClass;
        this.hp = hp;
        this.sa = sa;
    }

    public Hero(int id, String name, HeroClass hClass, int hp, SpecialAbility sa){
        this.id = id;
        this.name = name;
        this.hClass = hClass;
        this.hp = hp;
        this.sa = sa;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public HeroClass gethClass() {
        return hClass;
    }

    public int getHp() {
        return hp;
    }

    public SpecialAbility getSa() {
        return sa;
    }

    public void setName(String name) {
        if (!name.isBlank() && !name.isEmpty()){
            this.name = name;
        }

    }

    public void sethClass(HeroClass hClass) {
        if (!(hClass == null)){
            this.hClass = hClass;
        }
    }

    public void setHp(int hp) {
        if (hp > 10){
            this.hp = hp;
        }
    }

    public void setSa(SpecialAbility sa) {
        if (!(sa == null)){
            this.sa = sa;
        }
    }

    @Override
    public String toString() {
        String info = "Name: " + this.name +
                " - Class: " + hClass.toString() +
                " - Health Points: " + this.hp +
                " - Special Ability: " + this.sa.toString();

        return info;
    }

    public String toCSV() {
        String info = this.id + "," + this.name + "," + this.hClass.toString() + "," + this.hp + "," + this.sa.toString();

        return info;
    }
}
