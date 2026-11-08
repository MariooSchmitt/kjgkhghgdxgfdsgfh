
import java.util.*;
import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class RandomMegaProject {

    private static final Scanner scanner = new Scanner(System.in);
    private static final Random random = new Random();

    private static final List<User> users = new ArrayList<>();
    private static final List<String> activityLogs = new ArrayList<>();

    public static void main(String[] args) {

        System.out.println("====================================");
        System.out.println("       RANDOM JAVA MEGA PROJECT");
        System.out.println("====================================");

        initializeUsers();

        boolean running = true;

        while (running) {

            displayMenu();

            System.out.print("\nEnter your choice: ");

            try {
                int choice = scanner.nextInt();
                scanner.nextLine();

                switch (choice) {

                    case 1:
                        showUsers();
                        break;

                    case 2:
                        addUser();
                        break;

                    case 3:
                        generateRandomNumbers();
                        break;

                    case 4:
                        performCalculations();
                        break;

                    case 5:
                        showDateTime();
                        break;

                    case 6:
                        generateRandomPassword();
                        break;

                    case 7:
                        searchUser();
                        break;

                    case 8:
                        sortUsers();
                        break;

                    case 9:
                        showStatistics();
                        break;

                    case 10:
                        saveLogs();
                        break;

                    case 11:
                        showLogs();
                        break;

                    case 0:
                        running = false;
                        System.out.println("Exiting application...");
                        break;

                    default:
                        System.out.println("Invalid choice!");
                }

            } catch (InputMismatchException e) {

                System.out.println("Please enter a valid number.");
                scanner.nextLine();

            } catch (Exception e) {

                System.out.println("Unexpected error: " + e.getMessage());
            }
        }

        scanner.close();
    }

    private static void displayMenu() {

        System.out.println("\n========== MAIN MENU ==========");
        System.out.println("1. Display All Users");
        System.out.println("2. Add New User");
        System.out.println("3. Generate Random Numbers");
        System.out.println("4. Calculator");
        System.out.println("5. Display Date and Time");
        System.out.println("6. Generate Random Password");
        System.out.println("7. Search User");
        System.out.println("8. Sort Users");
        System.out.println("9. Display Statistics");
        System.out.println("10. Save Activity Logs");
        System.out.println("11. View Activity Logs");
        System.out.println("0. Exit");
        System.out.println("===============================");
    }

    private static void initializeUsers() {

        users.add(new User(101, "Alex", 22, "Developer"));
        users.add(new User(102, "John", 25, "Designer"));
        users.add(new User(103, "Sarah", 21, "Manager"));
        users.add(new User(104, "David", 28, "Engineer"));
        users.add(new User(105, "Emma", 24, "Analyst"));

        logActivity("Default users initialized.");
    }

    private static void showUsers() {

        System.out.println("\n========== USER LIST ==========");

        if (users.isEmpty()) {
            System.out.println("No users available.");
            return;
        }

        for (User user : users) {
            user.displayInformation();
        }

        logActivity("Displayed all users.");
    }

    private static void addUser() {

        System.out.println("\n========== ADD USER ==========");

        System.out.print("Enter ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        if (findUserById(id) != null) {
            System.out.println("User ID already exists.");
            return;
        }

        System.out.print("Enter Name: ");
        String name = scanner.nextLine();

        System.out.print("Enter Age: ");
        int age = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Enter Profession: ");
        String profession = scanner.nextLine();

        if (age < 0 || age > 120) {
            System.out.println("Invalid age.");
            return;
        }

        User user = new User(id, name, age, profession);

        users.add(user);

        System.out.println("User successfully added!");

        logActivity("New user added: " + name);
    }

    private static void generateRandomNumbers() {

        System.out.println("\n========== RANDOM NUMBERS ==========");

        System.out.print("How many numbers? ");

        int count = scanner.nextInt();

        if (count <= 0 || count > 1000) {
            System.out.println("Enter a number between 1 and 1000.");
            return;
        }

        System.out.print("Minimum value: ");
        int min = scanner.nextInt();

        System.out.print("Maximum value: ");
        int max = scanner.nextInt();

        if (min > max) {
            System.out.println("Invalid range.");
            return;
        }

        for (int i = 0; i < count; i++) {

            int number = random.nextInt(max - min + 1) + min;

            System.out.print(number + " ");
        }

        System.out.println();

        logActivity("Generated random numbers.");
    }

    private static void performCalculations() {

        System.out.println("\n========== CALCULATOR ==========");

        System.out.print("Enter first number: ");
        double a = scanner.nextDouble();

        System.out.print("Enter second number: ");
        double b = scanner.nextDouble();

        System.out.println("1. Addition");
        System.out.println("2. Subtraction");
        System.out.println("3. Multiplication");
        System.out.println("4. Division");
        System.out.println("5. Power");
        System.out.println("6. Modulus");

        System.out.print("Select operation: ");

        int operation = scanner.nextInt();

        double result;

        switch (operation) {

            case 1:
                result = a + b;
                break;

            case 2:
                result = a - b;
                break;

            case 3:
                result = a * b;
                break;

            case 4:

                if (b == 0) {
                    System.out.println("Cannot divide by zero.");
                    return;
                }

                result = a / b;
                break;

            case 5:
                result = Math.pow(a, b);
                break;

            case 6:

                if (b == 0) {
                    System.out.println("Cannot calculate modulus by zero.");
                    return;
                }

                result = a % b;
                break;

            default:
                System.out.println("Invalid operation.");
                return;
        }

        System.out.println("Result: " + result);

        logActivity("Calculator operation completed.");
    }

    private static void showDateTime() {

        LocalDateTime now = LocalDateTime.now();

        DateTimeFormatter formatter =
                DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

        System.out.println("\nCurrent Date and Time:");

        System.out.println(now.format(formatter));

        logActivity("Date and time displayed.");
    }

    private static void generateRandomPassword() {

        String characters =
                "ABCDEFGHIJKLMNOPQRSTUVWXYZ" +
                "abcdefghijklmnopqrstuvwxyz" +
                "0123456789" +
                "!@#$%^&*";

        System.out.print("Enter password length: ");

        int length = scanner.nextInt();

        if (length < 8 || length > 128) {
            System.out.println("Length must be between 8 and 128.");
            return;
        }

        StringBuilder password = new StringBuilder();

        for (int i = 0; i < length; i++) {

            int index = random.nextInt(characters.length());

            password.append(characters.charAt(index));
        }

        System.out.println("Generated Password: " + password);

        logActivity("Random password generated.");
    }

    private static void searchUser() {

        System.out.println("\n========== SEARCH USER ==========");

        System.out.print("Enter user ID: ");

        int id = scanner.nextInt();

        User user = findUserById(id);

        if (user != null) {

            System.out.println("User Found!");

            user.displayInformation();

        } else {

            System.out.println("User not found.");
        }

        logActivity("User search performed.");
    }

    private static User findUserById(int id) {

        for (User user : users) {

            if (user.getId() == id) {
                return user;
            }
        }

        return null;
    }

    private static void sortUsers() {

        users.sort(
            Comparator.comparing(User::getName)
        );

        System.out.println("Users sorted alphabetically.");

        showUsers();

        logActivity("Users sorted.");
    }

    private static void showStatistics() {

        System.out.println("\n========== STATISTICS ==========");

        if (users.isEmpty()) {
            System.out.println("No data available.");
            return;
        }

        int totalAge = 0;
        int minimumAge = Integer.MAX_VALUE;
        int maximumAge = Integer.MIN_VALUE;

        for (User user : users) {

            int age = user.getAge();

            totalAge += age;

            minimumAge = Math.min(minimumAge, age);

            maximumAge = Math.max(maximumAge, age);
        }

        double averageAge =
                (double) totalAge / users.size();

        System.out.println("Total Users: " + users.size());

        System.out.println("Average Age: " + averageAge);

        System.out.println("Youngest Age: " + minimumAge);

        System.out.println("Oldest Age: " + maximumAge);

        logActivity("Statistics displayed.");
    }

    private static void logActivity(String message) {

        String timestamp = LocalDateTime.now().toString();

        activityLogs.add(
                "[" + timestamp + "] " + message
        );
    }

    private static void saveLogs() {

        try (
            BufferedWriter writer =
                new BufferedWriter(
                    new FileWriter("activity_logs.txt")
                )
        ) {

            for (String log : activityLogs) {

                writer.write(log);

                writer.newLine();
            }

            System.out.println("Logs saved successfully.");

        } catch (IOException e) {

            System.out.println("File writing error: " + e.getMessage());
        }
    }

    private static void showLogs() {

        System.out.println("\n========== ACTIVITY LOGS ==========");

        if (activityLogs.isEmpty()) {

            System.out.println("No activity recorded.");

            return;
        }

        for (String log : activityLogs) {

            System.out.println(log);
        }
    }
}

class User {

    private int id;
    private String name;
    private int age;
    private String profession;

    public User(int id, String name, int age, String profession) {

        this.id = id;
        this.name = name;
        this.age = age;
        this.profession = profession;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getProfession() {
        return profession;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public void displayInformation() {

        System.out.println("----------------------------");

        System.out.println("ID: " + id);

        System.out.println("Name: " + name);

        System.out.println("Age: " + age);

        System.out.println("Profession: " + profession);

        System.out.println("----------------------------");
    }

    @Override
    public String toString() {

        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", profession='" + profession + '\'' +
                '}';
    }
}

class MathematicsUtility {

    public static long factorial(int number) {

        if (number < 0) {
            throw new IllegalArgumentException(
                    "Negative numbers are not allowed."
            );
        }

        long result = 1;

        for (int i = 1; i <= number; i++) {

            result *= i;
        }

        return result;
    }

    public static boolean isPrime(int number) {

        if (number <= 1) {
            return false;
        }

        for (int i = 2; i <= Math.sqrt(number); i++) {

            if (number % i == 0) {
                return false;
            }
        }

        return true;
    }

    public static int fibonacci(int position) {

        if (position < 0) {
            throw new IllegalArgumentException(
                    "Position cannot be negative."
            );
        }

        if (position <= 1) {
            return position;
        }

        int first = 0;
        int second = 1;

        for (int i = 2; i <= position; i++) {

            int next = first + second;

            first = second;

            second = next;
        }

        return second;
    }

    public static double calculateCircleArea(double radius) {

        return Math.PI * radius * radius;
    }
}

class ArrayUtility {

    public static int findMaximum(int[] array) {

        if (array.length == 0) {
            throw new IllegalArgumentException("Array is empty.");
        }

        int maximum = array[0];

        for (int value : array) {

            if (value > maximum) {
                maximum = value;
            }
        }

        return maximum;
    }

    public static int findMinimum(int[] array) {

        if (array.length == 0) {
            throw new IllegalArgumentException("Array is empty.");
        }

        int minimum = array[0];

        for (int value : array) {

            if (value < minimum) {
                minimum = value;
            }
        }

        return minimum;
    }

    public static double calculateAverage(int[] array) {

        if (array.length == 0) {
            return 0;
        }

        int sum = 0;

        for (int value : array) {

            sum += value;
        }

        return (double) sum / array.length;
    }

    public static void displayArray(int[] array) {

        for (int value : array) {

            System.out.print(value + " ");
        }

        System.out.println();
    }
}
