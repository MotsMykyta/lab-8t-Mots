package ua.opnu;
import java.util.Arrays;
import java.util.function.Predicate;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    // TASK 4
    @SuppressWarnings("unchecked")
    public static <T> T[] filter(T[] input, Predicate<T> p) {
        T[] result = (T[]) new Object[input.length];

        int counter = 0;
        for (T item : input) {
            if (p.test(item)) {
                result[counter] = item;
                counter++;
            }
        }

        return Arrays.copyOfRange(result, 0, counter);
    }

    // TASK 5
    public static <T extends Comparable<T>, V extends T> boolean contains(T[] array, V element) {
        for (T item : array) {
            if (item != null && item.equals(element)) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        // Task 1
        MyOptional<String> middleName = new MyOptional<>();
        System.out.println(middleName);
        System.out.println("isPresent: " + middleName.isPresent());
        System.out.println("orElse: " + middleName.orElse("немає"));

        MyOptional<String> username = new MyOptional<>("admin");
        System.out.println(username);
        System.out.println("isPresent: " + username.isPresent());
        System.out.println("get(): " + username.get());
        System.out.println("orElse: " + username.orElse("guest"));

        try {
            String test = middleName.get();
            System.out.println("unexpected: " + test);
        } catch (IllegalStateException ex) {
            System.out.println("Очікуваний виняток: " + ex.getMessage());
        }

        System.out.println("");
        // 2 Task
        try {
            MyOptional<String> broken = new MyOptional<>(null);
            System.out.println("unexpected: " + broken);
        } catch (IllegalArgumentException ex) {
            System.out.println("Правильно не дозволив null: " + ex.getMessage());
        }

        BookData[] books = {
                new BookData("Java Core", "Cay Horstmann", 10, 45.0),
                new BookData("Clean Code", "Robert Martin", 20, 96.0),
                new BookData("Algorithms", "Robert Sedgewick", 10, 48.0),
                new BookData("Design Patterns", "GoF", 5, 22.5)
        };
        Arrays.sort(books);
        for (BookData book : books) {
            System.out.println(book);
        }

        System.out.println("");
        // 3 Task
        Printer myPrinter = new Printer();
        Integer[] intArray = {1, 2, 3};
        String[] stringArray = {"Hello", "World"};
        myPrinter.printArray(intArray);
        myPrinter.printArray(stringArray);

        System.out.println("");
        // TASK 4
        Integer[] numbers = {1, 2, 3, 4, 5, 6, 7, 8};
        Object[] evenNumbers = filter(numbers, n -> n % 2 == 0);
        System.out.println("Парні числа: " + Arrays.toString(evenNumbers));
        String[] words = {"Java", "Python", "C++", "JavaScript"};
        Object[] longWords = filter(words, w -> w.length() > 4);
        System.out.println("Довгі слова: " + Arrays.toString(longWords));

        System.out.println("");
        // TASK 5
        System.out.println("Містить 'Java': " + contains(words, "Java"));
        System.out.println("Містить 'Rust': " + contains(words, "Rust"));
        System.out.println("Містить 5: " + contains(numbers, 5));

        System.out.println("");
        // TASK 6
        GenericTwoTuple<String, Integer> studentRating = new GenericTwoTuple<>("Моц Микита", 100);
        System.out.println("TwoTuple: " + studentRating);

        GenericThreeTuple<String, String, Double> product = new GenericThreeTuple<>("Ноутбук", "Dell", 25999.99);
        System.out.println("ThreeTuple: " + product);
        System.out.println("Товар: " + product.tuple.first + ", бренд: " + product.tuple.second + ", ціна: " + product.three);
    }
}
