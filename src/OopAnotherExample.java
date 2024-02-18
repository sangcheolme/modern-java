public class OopAnotherExample {

    public static void main(String[] args) {
//        CalculatorService c = new CalculatorService();
//        int result1 = c.calculate('+', 3, 2);
//        System.out.println("result = " + result1);
//
//        int result2 = c.calculate('-', 3, 2);
//        System.out.println("result = " + result2);

        System.out.println("==========OOP==========");
        CalculatorService service1 = new CalculatorService(new Addition());
        System.out.println(service1.calculate(5, 3));

        CalculatorService service2 = new CalculatorService(new SubStruct());
        System.out.println(service2.calculate(5, 3));

        System.out.println("==========FP==========");
        FpCalculateService fpService = new FpCalculateService();
        System.out.println(fpService.calculate(new Addition(), 3, 5));
        System.out.println(fpService.calculate(new SubStruct(), 3, 5));

        System.out.println(fpService.calculate((i1, i2) -> i1 + i2, 5, 3));
        System.out.println(fpService.calculate((i1, i2) -> i1 - i2, 5, 3));
        System.out.println(fpService.calculate((i1, i2) -> i1 * i2, 5, 3));
        System.out.println(fpService.calculate((i1, i2) -> i1 / i2, 5, 3));
    }
}

class CalculatorService {

    private final Calculate calculate;

    CalculatorService(Calculate calculate) {
        this.calculate = calculate;
    }

//    public int calculate(char calculate, int num1, int num2) {
//        if (calculate == '+') {
//            return num1 + num2;
//        } else if (calculate == '-') {
//            return num1 - num2;
//        } else if (calculate == '*') {
//            return num1 * num2;
//        } else if (calculate == '/') {
//            return num1 / num2;
//        } else {
//            throw new IllegalArgumentException("Unknown calculate " + calculate);
//        }
//    }

    public int calculate(int num1, int num2) {
        return calculate.calculate(num1, num2);
    }
}

interface Calculate {
    int calculate(int num1, int num2);
}

class Addition implements Calculate {

    @Override
    public int calculate(int num1, int num2) {
        return num1 + num2;
    }
}

class SubStruct implements Calculate {
    @Override
    public int calculate(int num1, int num2) {
        return num1 - num2;
    }
}

class FpCalculateService {
    public int calculate(Calculate cal, int num1, int num2) {
        return cal.calculate(num1, num2);
    }
}
