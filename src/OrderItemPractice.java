import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

public class OrderItemPractice {

    public static void main(String[] args) {
        Product productA = new Product(1L, "A", new BigDecimal("10.00"));
        Product productB = new Product(2L, "B", new BigDecimal("23.00"));
        Product productC = new Product(3L, "C", new BigDecimal("17.43"));
        Product productD = new Product(4L, "D", new BigDecimal("21.33"));
        Product productE = new Product(5L, "E", new BigDecimal("15.20"));
        List<Product> products = Arrays.asList(
                productA, productB, productC, productD, productE
        );

//        List<Product> result = new ArrayList<>();
//        for (Product product : products) {
//            if (product.getPrice().compareTo(new BigDecimal(20)) >= 0) {
//                result.add(product);
//            }
//        }
//        System.out.println(result);
        BigDecimal val = new BigDecimal("10");
        System.out.println(
                filter(products, product -> product.getPrice().compareTo(val) >= 0)
        );

        System.out.println(
                filter(products, product -> product.getPrice().compareTo(new BigDecimal("10")) <= 0)
        );
        List<Product> expensiveProducts = filter(products, product -> product.getPrice().compareTo(new BigDecimal("20")) > 0);

        List<DiscountedProduct> discountedProducts = map(expensiveProducts, product
                -> new DiscountedProduct(product.getId(), product.getName(),
                product.getPrice().multiply(new BigDecimal("0.5"))));
        System.out.println(discountedProducts);
        System.out.println("expensiveProducts = " + expensiveProducts);
        System.out.println("discountedProducts = " + discountedProducts);

        Predicate<Product> lessThanOrEqualTo11 = product -> product.getPrice().compareTo(new BigDecimal("11")) <= 0;
        System.out.println(
                filter(discountedProducts, lessThanOrEqualTo11)
        );

        System.out.println(
                filter(products, lessThanOrEqualTo11)
        );

        // Product의 total price 구하기
        /*
        List<BigDecimal> prices = map(products, Product::getPrice);
        BigDecimal total = BigDecimal.ZERO;
        for (BigDecimal price : prices) {
            total = total.add(price);
        }
        System.out.println("total = " + total);
         */

        BigDecimal total = total(products, Product::getPrice);
        System.out.println("total = " + total);

        BigDecimal discountedProductTotal = total(discountedProducts, product -> product.getPrice());
        System.out.println("discountedProductTotal = " + discountedProductTotal);

        Order order = new Order(1L, "on-1234", Arrays.asList(
                new OrderItem(1L, productA, 2),
                new OrderItem(2L, productC, 1),
                new OrderItem(3L, productD, 10)
        ));

        System.out.println("order total = " + order.totalPrice());

        List<OrderItem> items = order.getItems();
        BigDecimal totalOrder = BigDecimal.ZERO;
        for (OrderItem item : items) {
            totalOrder = totalOrder.add(item.getProduct().getPrice().multiply(BigDecimal.valueOf(item.quantity)));
        }
        System.out.println("totalOrder = " + totalOrder);
    }

    private static <T> BigDecimal total(List<T> list, Function<T, BigDecimal> mapper) {
        BigDecimal total = BigDecimal.ZERO;
        for (T t : list) {
            total = total.add(mapper.apply(t));
        }
        return total;
    }

    private static <T, R> List<R> map(List<T> list, Function<T, R> function) {
        List<R> result = new ArrayList<>();
        for (T t : list) {
            result.add(function.apply(t));
        }
        return result;
    }

    private static <T> List<T> filter(List<T> list, Predicate<? super T> predicate) {
        List<T> result = new ArrayList<>();
        for (T t : list) {
            if (predicate.test(t)) {
                result.add(t);
            }
        }
        return result;
    }

    @Data
    @AllArgsConstructor
    static class Product {
        private Long id;
        private String name;
        private BigDecimal price;
    }

    @ToString(callSuper = true)
    static class DiscountedProduct extends Product {
        public DiscountedProduct(Long id, String name, BigDecimal price) {
            super(id, name, price);
        }
    }

    @Data
    @AllArgsConstructor
    static class OrderItem {
        private Long id;
        private Product product;
        private int quantity;

        public BigDecimal getItemTotal() {
            return product.getPrice().multiply(new BigDecimal(quantity));
        }
    }

    @Data
    @AllArgsConstructor
    static class Order {
        private Long id;
        private String orderNumber;
        private List<OrderItem> items;

        public BigDecimal totalPrice() {
            return total(items, item -> item.getItemTotal());
        }
    }
}
