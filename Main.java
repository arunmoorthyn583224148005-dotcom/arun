import java.util.*;

interface Payment {
    void pay(double amount);
}

@FunctionalInterface
interface Discount {
    double calculate(double price);
}

class Product {
    private int id;
    private String name;
    private double price;

    Product(int id, String name, double price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public void display() {
        System.out.println(id + " " + name + " ₹" + price);
    }
}

class UPI implements Payment {

    public void pay(double amount) {
        System.out.println("Payment successful: ₹" + amount);
    }
}

public class Main {

    public static void main(String[] args) {

        // 1. ARRAYLIST
        ArrayList<Product> products = new ArrayList<>();

        products.add(new Product(1, "Laptop", 50000));
        products.add(new Product(2, "Mobile", 20000));
        products.add(new Product(3, "Shoes", 2000));
        products.add(new Product(4, "Watch", 3000));

        // 2. DISPLAY PRODUCTS
        System.out.println("===== PRODUCTS =====");

        for (Product p : products) {
            p.display();
        }

        // 3. LAMBDA EXPRESSION
        Discount discount = price -> price * 0.90;

        // 4. SELECT PRODUCT
        Scanner sc = new Scanner(System.in);

        System.out.print("\nEnter product ID: ");
        int id = sc.nextInt();

        Product selected = null;

        for (Product p : products) {
            if (p.getId() == id) {
                selected = p;
            }
        }

        // 5. EXCEPTION HANDLING
        try {

            if (selected == null) {
                throw new Exception("Product not found!");
            }

            System.out.println(
                "You selected: " + selected.getName()
            );

            // 6. DISCOUNT
            double finalPrice =
                    discount.calculate(selected.getPrice());

            System.out.println(
                "Original Price: ₹" +
                selected.getPrice()
            );

            System.out.println(
                "After 10% Discount: ₹" +
                finalPrice
            );

            // 7. PAYMENT INTERFACE
            Payment payment = new UPI();

            payment.pay(finalPrice);

        } catch (Exception e) {

            System.out.println(
                "Error: " + e.getMessage()
            );
        }

        // 8. STREAM API
        System.out.println("\n===== PRODUCTS ABOVE ₹5000 =====");

        products.stream()
                .filter(p -> p.getPrice() > 5000)
                .forEach(p -> p.display());

        sc.close();
    }
}