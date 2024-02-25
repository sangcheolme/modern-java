package inaction;

import java.util.ArrayList;
import java.util.List;

import static java.util.Comparator.comparing;

public class Ex1 {
    public static void main(String[] args) {
        List<Apple> inventory = getApples();
        print(inventory);
        /*inventory.sort(new Comparator<Apple>() {
            @Override
            public int compare(Apple o1, Apple o2) {
                return o1.getWeight().compareTo(o2.getWeight());
            }
        });
        print(inventory);*/

        inventory.sort(comparing(Apple::getWeight));
        print(inventory);
    }

    private static List<Apple> getApples() {
        List<Apple> inventory = new ArrayList<>();
        inventory.add(new Apple(10));
        inventory.add(new Apple(5));
        inventory.add(new Apple(15));
        return inventory;
    }

    private static void print(List<Apple> inventory) {
        for (Apple apple : inventory) {
            System.out.println(apple.getWeight());
        }
    }

    static class Apple {
        private final Integer weight;

        public Apple(Integer weight) {
            this.weight = weight;
        }

        public Integer getWeight() {
            return weight;
        }
    }
}
