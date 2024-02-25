package inaction;

import java.util.ArrayList;
import java.util.List;

import static inaction.Color.*;

public class AppleMain {
    public static void main(String[] args) {
        List<Apple> list = new ArrayList<>();
        list.add(new Apple(GREEN, 160));
        list.add(new Apple(YELLOW, 180));
        list.add(new Apple(GREEN,80));
        list.add(new Apple(RED, 190));
        list.add(new Apple(BLUE, 60));
        list.add(new Apple(RED, 160));

        List<Apple> apples = filterGreenApples(list);
        for (Apple apple : apples) {
            System.out.print(apple + ", ");
        }
        System.out.println();
        apples = filterHeavyApples(list);
        for (Apple apple : apples) {
            System.out.print(apple + ", ");
        }
    }

    public static List<Apple> filterGreenApples(List<Apple> inventory) {
        List<Apple> result = new ArrayList<>();
        for (Apple apple : inventory) {
            if (apple.getColor().equals(GREEN)) {
                result.add(apple);
            }
        }
        return result;
    }

    public static List<Apple> filterHeavyApples(List<Apple> inventory) {
        List<Apple> result = new ArrayList<>();
        for (Apple apple : inventory) {
            if (apple.getWeight() > 150) {
                result.add(apple);
            }
        }
        return result;
    }
}
