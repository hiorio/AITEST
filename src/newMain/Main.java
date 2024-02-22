package newMain;

import java.util.Scanner;

public class Main {

    static String[] jobArr = {"전사", "마법사", "궁수", "도적", "해적"};
    static String jobName;

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        System.out.print("캐릭터 이름 입력하세요 : ");
        String name = sc.nextLine();

        for (int i = 0; i < jobArr.length; i++) {
            System.out.println((i+1) + ". " + jobArr[i]);
        }

        jobSelectNumber(sc);

        Soldier soldier = soldierInfo(name);
        Monster monster = new Monster();
        Fight fight = new Fight(soldier, monster);

        while (true) {
            System.out.println(fight.getMonsterLv() + "레벨 몬스터를 만났다!");
            fight(sc, soldier, fight);
        }
    }

    private static void jobSelectNumber(Scanner sc) {
        System.out.print("직업 선택 : ");
        int jobSelectNumber = Integer.parseInt(sc.nextLine());
        
        switch (jobSelectNumber) {
            case 1:
                jobName = jobArr[Cons.JEONSA]; // 전사
                break;
            case 2:
                jobName = jobArr[Cons.MASIC]; // 마법사
                break;
            case 3:
                jobName = jobArr[Cons.HWAL]; // 궁수
                break;
            case 4:
                jobName = jobArr[Cons.DOJUK]; // 도적
                break;
            case 5:
                jobName = jobArr[Cons.HAEJUK]; // 해적
                break;
        }
    }

    /**
     * 캐릭터 정보 출력
     */
    private static Soldier soldierInfo(String name) {
        Soldier soldier = new Soldier(name, jobName);
        System.out.println("이름 : " + soldier.getName());
        System.out.println("직업 : " + soldier.getJob());
        System.out.println("레벨 : " + soldier.getLv());
        System.out.println("체력 : " + soldier.getHp());
        System.out.println("마력 : " + soldier.getMp());
        System.out.println("경험치 : " + soldier.getExp());
        return soldier;
    }

    private static void fight(Scanner sc, Soldier soldier, Fight fight) {
        while (true) {
            System.out.println("1. 공격");
            System.out.println("2. 스킬");
            System.out.println("3. 회복");
            System.out.println("4. 그만");

            if (fightEnd(soldier, fight)) break;

            int select = Integer.parseInt(sc.nextLine());
            if (select >= 5) {
                System.out.println("다시 입력하세요");
            }

            if (selectFightMenu(soldier, fight, select)) break;
        }
    }

    private static boolean selectFightMenu(Soldier soldier, Fight fight, int select) {
        switch (select) {
            case 1:
                // 공격
                fight.fight();
                System.out.println(soldier.getName() + " 체력 : (" + fight.getSoldierHp() + " / 100)");
                System.out.println("몬스터 체력 : " + fight.getMonsterHp());
                System.out.println();
                break;
            case 2:
                // 스킬
                fight.soldierSkillAttack();
                System.out.println(soldier.getName() + " 체력 : " + fight.getSoldierHp());
                System.out.println(soldier.getName() + " 마력 : " + fight.getSoldierHp());
                System.out.println("몬스터 체력 : " + fight.getMonsterHp());
                System.out.println();
                break;
            case 3:
                // 회복
                fight.soldierHealing();
                System.out.println(soldier.getName() + " 체력 : " + fight.getSoldierHp() + " 회복되었습니다.");
                System.out.println(soldier.getName() + " 마력 : " + fight.getSoldierMp() + " 회복되었습니다.");
                System.out.println();
                break;
        }
        if (select == 4) {
            // 게임 종료
            System.out.println("게임 종료합니다.");
            System.exit(0);
            return true;
        }
        return false;
    }

    private static boolean fightEnd(Soldier soldier, Fight fight) {
        if (fight.isMonsterDie()) {
            fight.fightEnd();
            System.out.println("몬스터가 죽었습니다");
            fight.monsterLvUp();

            fight.soldierExpUp();
            System.out.println(soldier.getName() + "의 경험치 : " + fight.getSoldierExp());

            fight.soldierMoneySum();
            System.out.println("총 획득한 돈 : " + fight.getSoldierMoney());
            return true;
        } else if (fight.isSoldierDie()) {
            fight.fightEnd();
            System.out.println(soldier.getName() + "가 죽었습니다. 최종 레벨 : " + soldier.getLv());
            System.out.println("게임을 종료합니다.");
            System.exit(0);
        }
        return false;
    }
}
