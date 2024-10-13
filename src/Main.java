import model.User;
import service.UserService;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Lesson 1(java-core and collections)");
        handleUserInput();
    }

    private static final UserService userService = new UserService();
    private static User currentAuthorizedUser = null; // Текущий авторизованный пользователь
    private static String input = "";

    private static void printWelcomeMessage() {
        System.out.println("\nДля запуска команды следует ввести нужную команду и нажать клавишу Enter");
        System.out.println("#1 - Зарегистрируйтесь, чтобы создать новую учетную запись(клавиша - 1);");
        System.out.println("#2 - Для Входа в систем введите логин(E-mail) и пароль(клавиша - 2);");
        System.out.println("#3 - Удалить пользователя");
        System.out.println("#0 - Подтверждение выхода из системы(клавиша - 0).");
    }

    private static void printMessage() {
        System.out.println("\nВыберите действие:");
        System.out.println("1. Изменить имя");
        System.out.println("2. Изменить E-mail");
        System.out.println("3. Изменить пароль");
        System.out.println("0. Выход из личного кабинета");
        System.out.print("Введите номер действия: ");
    }

    private static void handleUserInput() {

        Scanner scan = new Scanner(System.in);
        String input;
        printWelcomeMessage();
        while (currentAuthorizedUser == null) {
            input = scan.nextLine().trim().toLowerCase();

            switch (input) {
                case "1":
                    System.out.println("Регистрация");
                    userService.registerUser(scan);
                    printWelcomeMessage();
                    break;
                case "2":
                    System.out.println("Авторизация");

                    currentAuthorizedUser = userService.authorizedUser(scan);
                    if (currentAuthorizedUser == null) {
                        System.out.println("Неверный email или пароль. Повторите вход!");
                        printWelcomeMessage();
                        break;
                    }
                    break;
                case "3":
                    System.out.println("Удаление");
                    userService.deleteUser(scan);
                    printWelcomeMessage();
                    break;
                case "0":
                    System.out.println("Выход из системы");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Неизвестная команда. Пожалуйста, попробуйте еще раз.");
                    break;
            }
        }
        userFunctions();
    }


    private static void userFunctions() {
        Scanner scan = new Scanner(System.in);

        printMessage();
        input = scan.nextLine().trim().toLowerCase();

        switch (input) {
            case "1":
                userService.updateUserName(scan);
                handleUserInput();
                break;
            case "2":

                userService.updateUserEmail(scan);
                currentAuthorizedUser = null;
                handleUserInput();
                break;
            case "0":
                System.out.println("Вы вышли из личного кабинета.");
                currentAuthorizedUser = null;
                handleUserInput();
                break;

            default:
                System.out.println("Неправильный выбор. Пожалуйста, попробуйте снова.");
                handleUserInput();
                break;
        }
    }
}