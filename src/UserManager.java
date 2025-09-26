import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class UserManager {

    private Map<String, User> users = new HashMap<>();
    private Scanner scanner = new Scanner(System.in);

    private User currentUser = null;

    public User getCurrentUser() {
        return currentUser;
    }

    public boolean isLoggedIn() {
        return currentUser != null;
    }

    public void showAuthMenu() {
        while (true) {
            System.out.println("\n=== Добро пожаловать на сервер шизофрения ===");
            System.out.println("1. Регистрация");
            System.out.println("2. Вход");
            System.out.println("3. Выход");
            System.out.print("Выберите действие: ");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    registerUser();
                    break;
                case 2:
                    loginUser();
                    break;
                case 3:
                    System.out.println("Приходи еще!");
                    System.exit(0);
                default:
                    System.out.println("Там всего 3 цифры, выбери одну из трех долбаеб");
                    System.out.println(choice);
            }
            if (isLoggedIn()) {
                System.out.println("Добро пожаловать, " + currentUser.getUsername());
                break;
            }
        }
    }

    private void registerUser() {
        scanner.nextLine();
        System.out.println("\n---Регистрация---");
        System.out.println("Придумай логин: ");
        String username = scanner.nextLine();

        if (users.containsKey(username)) {
            System.out.println("Пользователь с таким ником уже есть в базе лудиков, придумай что-то 'оригинальное'");
            return;
        }

        System.out.println("Придумайте пароль: ");
        String password = scanner.nextLine();

        User newUser = new User(username, password);
        users.put(username, newUser);

        System.out.println("Рега прошла успешно, Теперь можешь войти.(но помни, если у тебя нет денег, то тебе тут не рады)");
    }

    private void loginUser() {
        scanner.nextLine();
        System.out.println("\n--- Вход ---");
        System.out.println("Введите логин: ");
        String username = scanner.nextLine();

        System.out.println("Введите пароль: ");
        String password = scanner.nextLine();

        User user = users.get(username);
        if (user != null && user.checkPassword(password)) {
            currentUser = user;
        } else {
            System.out.println("Неверный логин или пароль!");
        }
    }

    public void logout() {
        this.currentUser = null;
    }


}
