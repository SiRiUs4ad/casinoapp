import java.util.Random;
import java.util.Scanner;

public class CasinoApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        UserManager userManager = new UserManager();

        userManager.showAuthMenu();

        while (userManager.isLoggedIn()) {
            User currentUser = userManager.getCurrentUser();
            scanner.nextInt();

            System.out.println("\n=== Игровое меню ===");
            System.out.println("Ваш баланс: " + currentUser.getBalance());
            System.out.println("1. Сыграть в рулетку");
            System.out.println("2. Показать профиль");
            System.out.println("3. Выйти из аккаунта");
            System.out.println("Выберите действие: ");

            int choice = scanner.nextInt();
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
                    //System.out.println("Там всего 3 цифры, выбери одну из трех долбаеб");
                    System.out.println(choice);
            }
        }
    }


    private static void playRoulette(User user, Scanner scanner) {
        System.out.println("Ваш баланс: " + user.getBalance());
        System.out.print("Сделайте ставку: ");
        double bet = scanner.nextDouble();

        if (bet > user.getBalance()) {
            System.out.println("Недостаточно средств!");
            return;
        }

        System.out.println("\n=== Выберите вид ставки ===");
        System.out.println("1.  число");
        System.out.println("2.  цвет");
        System.out.println("3.  четность");
        System.out.println("4.  колонка 1");
        System.out.println("5.  колонка 2");
        System.out.println("6.  колонка 3");
        System.out.println("7.  1-18");
        System.out.println("8.  19-36");
        System.out.println("9.  Дюжина 1-12");
        System.out.println("10. Дюжина 13-24");
        System.out.println("11. Дюжина 25-36");

        int choice = scanner.nextInt();

        BetType betType = null;
        int chosenNum = -1;
        String chosenColor = null;
        int chosenParity = -1;
        double multiplier = 0;

        switch (choice) {
            case 1:
                betType = BetType.NUMBER;
                System.out.print("Введите число (0-36): ");
                chosenNum = scanner.nextInt();
                multiplier = 35;
                break;
            case 2:
                betType = BetType.COLOR;
                System.out.print("Выберите цвет (RED/BLACK): ");
                chosenColor = scanner.next().toUpperCase();
                multiplier = 2;
                break;
            case 3:
                betType = BetType.PARITY;
                System.out.print("Выберите четность (1 - четное, 2 - нечетное): ");
                chosenParity = scanner.nextInt();
                multiplier = 2;
                break;
            case 4:
                betType = BetType.COLUMN_1;
                multiplier = 3;
                break;
            case 5:
                betType = BetType.COLUMN_2;
                multiplier = 3;
                break;
            case 6:
                betType = BetType.COLUMN_3;
                multiplier = 3;
                break;
            case 7:
                betType = BetType.LOW;
                multiplier = 2;
                break;
            case 8:
                betType = BetType.HIGH;
                multiplier = 2;
                break;
            case 9:
                betType = BetType.DOZEN_1;
                multiplier = 3;
                break;
            case 10:
                betType = BetType.DOZEN_2;
                multiplier = 3;
                break;
            case 11:
                betType = BetType.DOZEN_3;
                multiplier = 3;
                break;
            default:
                System.out.println("Неверный выбор.");
                return;
        }

        Roulette roulette = new Roulette();
        roulette.spin();

        System.out.println("\n=== Результат ===");
        System.out.println("Выпало число: " + roulette.getResultNumber() +
                " (" + roulette.getResultColor() + ")");

        boolean win = roulette.checkWin(betType, chosenNum, chosenColor, chosenParity);

        if (win) {
            double winAmount = bet * multiplier;
            System.out.println("Поздравляем! Вы выиграли " + winAmount);
            user.setBalance(user.getBalance() + winAmount);
        } else {
            System.out.println("Вы проиграли!");
            user.setBalance(user.getBalance() - bet);
        }

        System.out.println("Ваш новый баланс: " + user.getBalance());


        //генерим число которое выпадет 
        //проверка на то какого оно цвета и четность 
        //проверка на ноль
        //проверка на 1-18, 19-36
        //проверка по столбцам

        //если выиграл user.setBalance(user.getBalance() + winAmount);
        //if notwin user.setBalance(user.getBalance() - bet)
    }

}
