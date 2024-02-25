package inaction;

import java.util.function.Function;

public class MyFunction {
    public static void main(String[] args) {
        /*MyFunctionInterface<String, Integer> myFunctionInterface
                = new MyFunctionInterface<String, Integer>() {
            @Override
            public String apply(Integer integer) {
                return String.valueOf(integer);
            }
        };
        System.out.println(myFunctionInterface.apply(23));*/

        MyFunctionInterface<String, Integer> myFunctionInterface = i -> String.valueOf(i);
        System.out.println(myFunctionInterface.apply(24));

        String s = castingString(i -> String.valueOf(i), 23);
        System.out.println(s);
    }

    static String castingString(Function<Integer, String> f, Integer i) {
        return f.apply(i);
    }

}

@FunctionalInterface
interface MyFunctionInterface<R, T> {
    R apply(T t);
}