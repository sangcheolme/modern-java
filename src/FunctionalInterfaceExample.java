import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FunctionalInterfaceExample {

    public static void functionalInterfaceExample() {
        // Function -> 매개변수 O, 리턴 O
        Function<String, Integer> anoFunction = new Function<String, Integer>() {
            @Override
            public Integer apply(String s) {
                return Integer.parseInt(s);
            }
        };
        anoFunction.apply("3");

        Function<String, Integer> function = Integer::parseInt;
        System.out.println("i = " + function.apply("3"));

        // identity -> 자기 자신을 그대로 반환
        // Function<Integer, Integer> identity = i -> i;
        Function<Integer, Integer> identity = Function.identity();
        System.out.println("i1 = " + identity.apply(100));

        // Consumer -> 매개변수 O, 리턴 X
        Consumer<String> consumer = new Consumer<String>() {
            @Override
            public void accept(String s) {
                System.out.println("hello " + s);
            }
        };
        consumer.accept("kevin");

        Consumer<String> greeting = s -> System.out.println("hello " + s);
        greeting.accept("kevin");

        // Predicate -> 매개변수 O, 리턴 boolean
        Predicate<Integer> predicate = new Predicate<Integer>() {
            @Override
            public boolean test(Integer integer) {
                return integer >= 10;
            }
        };
        System.out.println(predicate.test(11) ? "10 이상" : "10 미만");

        Predicate<Integer> ten = i -> i >= 10;
        System.out.println(ten.test(9) ? "10 이상" : "10 미만");

        // filter 메서드 직접 만들기
        List<Integer> list = Stream.iterate(-3, a -> a + 1)
                .limit(10)
                .collect(Collectors.toList());

        Predicate<Integer> isPositive = i -> i < 0;
        List<Integer> filter = filter(list, isPositive);
        System.out.println("filter = " + filter);

        // Supplier -> 매개변수 X, 리턴 O
        Supplier<String> helloSupplier = () -> "Hello ";
        System.out.println(helloSupplier.get() + "world");

        // 언제 사용할까? lazy evaluation
        long start = System.currentTimeMillis();
        printValidIndex(1, () -> getVeryExpensiveValue());
        printValidIndex(-1, () -> getVeryExpensiveValue());
        printValidIndex(-2, () -> getVeryExpensiveValue());
        long end = System.currentTimeMillis();
        System.out.println("It took " + (end - start) / 1000 + " seconds.");
    }

    private static String getVeryExpensiveValue() {
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return "Kevin";
    }

//    private static void printValidIndex(int number, String value) {
    private static void printValidIndex(int number, Supplier<String> supplierValue) {
        if (number >= 0) {
            System.out.println("The value is " + supplierValue.get() + ".");
        } else {
            System.out.println("Invalid");
        }
    }

    private static <T> List<T> filter(List<T> list, Predicate<T> filter) {
        List<T> result = new ArrayList<>();
        for (T t : list) {
            if (filter.test(t)) {
                result.add(t);
            }
        }
        return result;
    }

    private static <T1, T2, T3> void println(T1 t1, T2 t2, T3 t3, Function3<T1, T2, T3, String> function) {
        System.out.println(function.apply(t1, t2, t3));
    }

    public static void main(String[] args) {
        // 사용자 지정 Functional Interface -> 인자 3개를 입력 받아서 출력하기
        // 매개변수의 값에 따라 제네릭 타입 추론 -> 유연성이 좋아짐!
        println(4, 3, 3, (i1, i2, i3) -> String.valueOf(i1 + i2 + i3));
        println("Area is ", 12, 120, (message, length, width) -> message + (length * width));
        println(1L, "Kevin", "test@email.com",
                (id, name, email) -> "User info: ID = " + id + ", name = " + name + ", email = " + email);

        // BigDecimal -> String
        BigDecimalToCurrency bigDecimalToCurrency = bd -> "$" + bd;
        System.out.println(bigDecimalToCurrency.toCurrency(new BigDecimal("120.00")));

        // FunctionalInterface 제약사항
        // FunctionalInterface의 추상메서드가 제네릭 타입을 사용하는 경우 람다식을 사용할 수 없다.
        //InvalidFunctionalInterface invalid = bd -> "$" + bd; // error
        InvalidFunctionalInterface invalid = new InvalidFunctionalInterface() {
            @Override
            public <T> String makeString(T value) {
                return "$" + value;
            }
        };
        System.out.println(invalid.makeString(new BigDecimal("120.00")));

        ValidFunctionalInterface<Double> valid = input -> "$" + input;
        System.out.println(valid.makeString(120.00));
    }
}

/**
 * 매개변수 3개를 받는 Functional Interface
 */
@FunctionalInterface
interface Function3<T1, T2, T3, R> {
    R apply(T1 t1, T2 t2, T3 t3);
}

@FunctionalInterface
interface BigDecimalToCurrency {
    String toCurrency(BigDecimal value);
}

@FunctionalInterface
interface InvalidFunctionalInterface {
    <T> String makeString(T value);
}

@FunctionalInterface
interface ValidFunctionalInterface<T> {
    String makeString(T value);
}