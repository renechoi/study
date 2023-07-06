package org.designpattern.memento;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

public class Gamer {
    private int money;
    private ArrayList<String> fruits = new ArrayList<>();
    private Random random = new Random();
    private static String[] fruitsname = {
        "사과", "포도", "바나나", "귤",
    };
    public Gamer(int money) {
        this.money = money;
    }
    public int getMoney() {
        return money;
    }
    public void bet() {
        int dice = random.nextInt(6) + 1;          // 주사위를 던진다.
        if (dice == 1) {                                 // 1-> 돈 증가
            money += 100;
            System.out.println("1-> 돈 증가.");
        } else if (dice == 2) {                         // 2-> 돈 반감
            money /= 2;
            System.out.println("2-> 돈 반감.");
        } else if (dice == 6) {                     // 6-> 과일 받기
            String f = getFruit();
            System.out.println("6 -> 과일 받기.");
            fruits.add(f);
        } else {                                    // 그밖에 -> 아무일도 없음
            System.out.println("그밖에는 아무 일도 없음.");
        }
    }
    public Memento createMemento() {                    // 스냅샷 찍기
        Memento memento = new Memento(money);
        Iterator<String> fruits = this.fruits.iterator();
        while (fruits.hasNext()) {
            String fruit = fruits.next();
            if (fruit.startsWith("맛있다")) {         // 과일은 맛있는 것만 보존
                memento.addFruit(fruit);
            }
        }
        return memento;
    }
    public void restoreMemento(Memento memento) {       // undo
        this.money = memento.money;
        this.fruits = memento.fruits;
    }
    public String toString() {
        return "[money = " + money + ", fruits = " + fruits + "]";
    }
    private String getFruit() {                     // 과일 한 개 얻기
        String prefix = "";
        if (random.nextBoolean()) {
            prefix = "맛있다.";
        }
        return prefix + fruitsname[random.nextInt(fruitsname.length)];
    }
}