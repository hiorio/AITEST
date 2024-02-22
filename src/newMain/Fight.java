package newMain;

public class Fight {
    private static final int FULL_HP = 100;

    private boolean isFight;
    private int monsterHp;
    public int monsterLv;
    private int soldierHp;
    private int soldierMp;
    private int soldierExp;
    private int soldierMoney;

    /**
     * 생성자 초기화
     */
    public Fight(Soldier soldier, Monster monster) {
        isFight = true;

        soldierHp = soldier.getHp();
        soldierMp = soldier.getMp();
        soldierExp = soldier.getExp();
        soldierMoney = soldier.getMoney();

        monsterHp = monster.getHp();
        monsterLv = monster.getLv();
    }

    public boolean isFight() {
        return isFight;
    }

    public void fight() {
        monsterHp -= 15;
        soldierHp -= 5;
        monsterRandomHealing();
    }

    public void soldierSkillAttack() {
        if (soldierMp == 0) {
            System.out.println("마력이 부족해요");
        } else {
            soldierMp -= 10;
            monsterHp -= 25;
            monsterAttack();
            monsterRandomHealing();
        }
    }

    /**
     * 몬스터 체력이 25 이하일 경우 랜덤으로 몬스터 체력 25 회복
     */
    public void monsterRandomHealing() {
        if (monsterHp <= 25) {
            int rnd = (int) (Math.random() * 5); // 0 ~ 5까지 랜덤
            switch (rnd) {
                case 1:
                    monsterHp *= 0.25; // 몬스터 25% 회복
                    System.out.println("몬스터 체력 25 회복!");
                    System.out.println("몬스터 체력 : " + monsterHp);
                    break;
            }
        }
    }

    /**
     * 몬스터가 용사에게 15 공격
     */
    public void monsterAttack() {
        soldierHp -= 15;
    }

    // 몬스터가 죽었나?
    public boolean isMonsterDie() {
        if (monsterHp <= 0) return true; // 몬스터가 죽었다!
        return false; // 안 죽었네
    }

    public boolean isSoldierDie() {
        if (soldierHp <= 0) return true; // 용사가 죽었다!
        return false; // 게임 계속
    }

    // 체력 회복 스킬
    public void soldierHealing() {
        int healingHp = FULL_HP - soldierHp;
        int healingMp = FULL_HP - soldierMp;

        if (soldierHp == FULL_HP) {
            System.out.println("체력이 다 꽉찼습니다.");
        } else if (soldierMp == FULL_HP) {
            System.out.println("체력이 다 꽉찼습니다.");
        }

        if (soldierHp == FULL_HP && soldierMp == FULL_HP) {
            System.out.println("체력이 다 꽉찼습니다.");
        }

        soldierHp += healingHp; // 체력이 빈 만큼 회복
        soldierMp += healingMp;
    }

    public void soldierExpUp() {
        soldierExp *= 12;
    }

    public void monsterLvUp() {
        int i = 1;
        i++;
        monsterLv++;
        monsterHp *= i;
    }

    public void soldierMoneySum() {
        soldierMoney *= 13;
    }

    public void fightEnd() {
        isFight = false;
    }

    public int getMonsterHp() {
        return monsterHp;
    }

    public int getSoldierHp() {
        return soldierHp;
    }

    public int getSoldierExp() {
        return soldierExp;
    }

    public int getSoldierMoney() {
        return soldierMoney;
    }

    public int getSoldierMp() {
        return soldierMp;
    }

    public int getMonsterLv() {
        return monsterLv;
    }
}
