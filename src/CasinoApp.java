import java.util.Scanner;

public class CasinoApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        UserManager userManager = new UserManager();

        userManager.showAuthMenu();

        while (userManager.isLoggedIn()) {
            User currentUser = userManager.getCurrentUser();

            System.out.println("\n=== Игровое меню ===");
            System.out.println("Ваш баланс: " + currentUser.getBalance());
            System.out.println("1. Сыграть в рулетку");
            System.out.println("2. Показать профиль");
            System.out.println("3. Выйти из аккаунта");
            System.out.println("Выберите действие: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    playRoulette(currentUser, scanner);
                    break;
                case 2:
                    System.out.println(currentUser);
                    break;
                case 3:
                    System.out.println("Вы вышли из аккаунта");
                    userManager.logout();
                default:
                    System.out.println("Там всего 3 цифры, выбери одну из трех долбаеб");
                    System.out.println(choice);
            }
        }
    }

    private static void playRoulette(User user, Scanner scanner) {
        System.out.println("Игра не доступна. проводится обновление...");
        //логика игры
        System.out.println("Ваш баланс: " + user.getBalance());
        System.out.println("Сделайте ставку: ");
        double bet = scanner.nextDouble();

        if (bet > user.getBalance()) {
            System.out.println("Недостаточно средств!");
            return;
        }

        //если выиграл user.setBalance(user.getBalance() + winAmount);
        //if notwin user.setBalance(user.getBalance() - bet)
    }

}