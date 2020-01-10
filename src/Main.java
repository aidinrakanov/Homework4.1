import java.sql.SQLOutput;
import java.util.Arrays;
import java.util.Random;

public class Main {
    public static int bossHealth = 3000;
    public static int bossDamage = 150;
    public static String bossDefenceType = "";
    public static int[] heroesHealth = {300, 250, 200, 250, 1000, 200, 250, 300};
    public static int[] heroesDamage = {40, 40, 30, 0, 10, 30, 20, 30};
    public static int docHeal = 10;
    public static String[] heroesAttackType = {"Physical",
            "Magical", "Mental", "Doctor", "Tank", "Nimble", "Berserk", "Thor"};


    public static void main(String[] args) {
        printStatistics();
        while (!isFinished()) {
            round();
        }
    }

    public static void changeBossDefence() {
        Random r = new Random();
        int randomIndex = r.nextInt(heroesAttackType.length); //0, 1, 2
        bossDefenceType = heroesAttackType[randomIndex];
    }

    public static void round() {
        changeBossDefence();
        stun();
        ability();
        Defense();
        bossHit();
        returnDamage();
        heroesHit();
        heal();

        printStatistics();

    }

    public static boolean isFinished() {
        if (bossHealth <= 0) {
            System.out.println("Heroes won!!!");
            return true;
        }
        for (int i = 0; i < heroesHealth.length; i++)

            if (heroesHealth[0] <= 0 && heroesHealth[1] <= 0 && heroesHealth[2] <= 0 &&
                    heroesHealth[3] <= 0 && heroesHealth[4] <= 0 && heroesHealth[5] <= 0 &&
                    heroesHealth[6] <= 0 && heroesHealth[7] <= 0) {
                System.out.println("Boss won!!!");
                return true;
            }
        return false;

    }

    public static void stun() {
        Random r = new Random();
        int miss = r.nextInt(4);
        if (miss == 0) {
            bossDamage = miss;
            System.out.println("Тор оглушил " + miss);
        } else {
            System.out.println("Тор не оглушил");
            if (heroesHealth[7] - bossDamage < 0) {
                heroesHealth[7] = 0;
            }
        }
    }

    public static void Defense() {

        for (int i = 0; i < heroesHealth.length; i++)
            if (heroesHealth[i] > 0 && bossHealth > 0) {
                Random r = new Random();
                int Def = r.nextInt(50);
                heroesHealth[4] = (bossDamage - Def) * 8;
                if (heroesHealth[i] == 500) {
                    continue;
                }
                heroesHealth[i] = heroesHealth[i] + Def;
                if (heroesHealth[4] - bossDamage < 0) {
                    heroesHealth[4] = 0;
                }
            }
    }

    public static void ability() {
        Random r = new Random();
        int miss = r.nextInt(2);
        if (miss == 0) {
            heroesHealth[5] = heroesHealth[5] + bossDamage / 3;
            System.out.println("Ловкач увернулся " + miss);
        } else {
            System.out.println("Босс попал");
            if (heroesHealth[5] - bossDamage < 0) {
                heroesHealth[5] = 0;

            }
        }
    }

    public static void returnDamage() {
        Random r = new Random();
        int coeff = r.nextInt(50) + 10;
        heroesHealth[6] = (heroesHealth[6] - bossDamage + coeff) / 4;
        heroesDamage[6] = heroesDamage[6] + coeff;
        if (heroesHealth[6] - bossDamage < 0) {
            heroesHealth[6] = 0;
        }
    }


    public static void heal() {
        for (int i = 0; i < heroesHealth.length; i++) {
            if (heroesHealth[i] > 0 && bossHealth > 0) {
                Random r = new Random();
                int multiply = r.nextInt(5) + 2;
                heroesHealth[i] = heroesHealth[i] + docHeal * multiply;
                System.out.println(heroesAttackType[3] + " heal " + docHeal * multiply + "  " + heroesAttackType[i]);

                if (heroesHealth[i] - bossDamage < 0) {
                    heroesHealth[i] = 0;

                }
            }
        }
    }

    public static void bossHit() {
        for (int i = 0; i < heroesHealth.length; i++) {
            if (heroesHealth[i] > 0 && bossHealth > 0) {
                if (heroesHealth[i] - bossDamage < 0) {
                    heroesHealth[i] = 0;
                } else {
                    heroesHealth[i] = heroesHealth[i] - bossDamage;
                }
            }
        }
    }

    public static void heroesHit() {
        for (int i = 0; i < heroesDamage.length; i++) {
            if (heroesHealth[i] > 0 && bossHealth > 0) {
                if (bossDefenceType.equals(heroesAttackType[i])) {
                    Random r = new Random();
                    int coef = r.nextInt(7) + 2; // 0,1,2,3,4,5
                    if (bossHealth - heroesDamage[i] * coef < 0) {
                        bossHealth = 0;
                    } else {
                        bossHealth = bossHealth - heroesDamage[i] * coef;
                    }
                    System.out.println(heroesAttackType[i] +
                            " critically hit " + heroesDamage[i] * coef + "\n" + "_________________");
                } else {
                    if (bossHealth - heroesDamage[i] < 0) {
                        bossHealth = 0;
                    } else {
                        bossHealth = bossHealth - heroesDamage[i];

                    }
                }
            }
        }
    }

    public static void printStatistics() {
        System.out.println("_________________");
        System.out.println("Boss health: " + bossHealth);
        System.out.println("Warrior health: " + heroesHealth[0]);
        System.out.println("Magic health: " + heroesHealth[1]);
        System.out.println("Kinetic health: " + heroesHealth[2]);
        System.out.println("Doctor health: " + heroesHealth[3]);
        System.out.println("Tank health: " + heroesHealth[4]);
        System.out.println("Nimble health: " + heroesHealth[5]);
        System.out.println("Berserk health: " + heroesHealth[6]);
        System.out.println("Thor health: " + heroesHealth[7]);

        System.out.println("_________________");
    }
}
