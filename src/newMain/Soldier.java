package newMain;

public class Soldier {
    private int lv = 1; // 레벨
    private int hp = 100; // 체력
    private int mp = 100; // 마력
    private int exp = 0; // 경험치
    private int money = 0; // 돈
    private String name; // 이름
    private String job; // 직업

    // 초기화
    public Soldier(String name, String job) {
        this.name = name;
        this.job = job;
    }

    public String getName() {
        return name;
    }

    public String getJob() {
        return job;
    }

    public int getExp() {
        return exp;
    }

    public int getLv() {
        return lv;
    }

    public int getMp() {
        return mp;
    }

    public int getHp() {
        return hp;
    }

    public int getMoney() {
        return money;
    }
}