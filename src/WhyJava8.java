import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class WhyJava8 {
    public static void main(String[] args) {
        final List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        // 1 : 2 : 3 : 4 ...
        int count = 0;
        for (Integer number : numbers) {
            System.out.print(number);
            if (count != numbers.size() - 1) {
                System.out.print(" : ");
            }
            count++;
        }

        // 위와 같은 코드
        System.out.println();
        String collect = numbers.stream()
                .map(String::valueOf)
                .collect(Collectors.joining(" : "));
        System.out.println(collect);
    }
}
