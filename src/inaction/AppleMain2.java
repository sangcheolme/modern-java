package inaction;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import static inaction.Color.*;

public class AppleMain2 {
    public static void main(String[] args) {
        List<Apple> list = new ArrayList<>();
        list.add(new Apple(GREEN, 160));
        list.add(new Apple(YELLOW, 180));
        list.add(new Apple(GREEN,80));
        list.add(new Apple(RED, 190));
        list.add(new Apple(BLUE, 60));
        list.add(new Apple(RED, 160));

        List<Apple> apples = filterApples(list, apple -> GREEN.equals(apple.getColor()));
        for (Apple apple : apples) {
            System.out.print(apple + ", ");
        }
        System.out.println();
        apples = filterApples(list, Apple::isHeavy);
        for (Apple apple : apples) {
            System.out.print(apple + ", ");
        }
    }

    /**
     * getColor == Green, filter
     * @param inventory
     * @return
     */
    public static List<Apple> filterApples(List<Apple> inventory,
                                           Predicate<Apple> p) {
        List<Apple> result = new ArrayList<>();
        for (Apple apple : inventory) {
            if (p.test(apple)) {
                result.add(apple);
            }
        }
        return result;
    }
}
