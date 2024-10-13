package service;

import model.User;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class UserService {
    private final CheckingCorrect checking = new CheckingCorrect();
    private final Map<String, User> users = new HashMap<>();


    public void registerUser(Scanner scanner) throws RuntimeException {
        System.out.print("Введите имя (name): ");
        String name = scanner.nextLine().trim();
        if (checking.isCorrectName(name)) {
            System.out.print("Введите логин (email): ");
            String email = scanner.nextLine().trim();
            if (checking.isCorrectEmail(email)) {
                System.out.print("Введите пароль (password): ");
                String password = scanner.nextLine().trim();
                if (checking.isCorrectPassword(password)) {
                    User user = new User(name, email, password);
                    users.put(email, user);
                    System.out.println("Поздравляем!!!!\nПользователь зарегистрирован.");
                    System.out.println("Теперь вы можете войти в систему используя логин (email) и пароль!");
                } else {
                    throw new RuntimeException("Неизвестная ошибка. Повторно запустите приложение!");
                }
            }
        }
    }

    public User authorizedUser(Scanner scanner) {
        System.out.print("Введите логин (email): ");
        String email = scanner.nextLine().trim();
        if (checking.isCorrectEmail(email)) {
            System.out.print("Введите пароль (password): ");
            String password = scanner.nextLine().trim();
            if (checking.isCorrectPassword(password)) {
                User user = users.get(email);
                if (users.containsKey(email) && user.getPassword().equals(password)) {
                    System.out.println("Авторизация прошла успешно!");
                    System.out.println("Добро пожаловать, " + user.getName() + "!");
                    return user;
                }
            }
        } else {
            System.out.println("Неверный email или пароль. Повторите вход!");

        }
        return null;
    }

    public void updateUserName(Scanner scanner) {
        System.out.print("Введите новое имя:");
        String newName = scanner.nextLine().trim();
        User user = new User();
        user.setName(newName);
        users.replace(user.getEmail(), user);
        System.out.println("Имя обновлен. Ваше новое имя: " + user.getName());
    }

    public void updateUserEmail(Scanner scanner) {
        System.out.print("Введите новый логин(Email)");
        String newEmail = scanner.nextLine().trim();
        User user = new User();
        user.setEmail(newEmail);
        users.replace(user.getEmail(), user);
        System.out.println("Логин(Email) обновлен. Новый логин(Email): " + user.getEmail());
    }

    public void deleteUser(Scanner scanner) {
        System.out.print("Для удаления введите логин(Email)");
        String email = scanner.nextLine().trim();
        if (checking.isCorrectEmail(email)) {
            System.out.print("Введите пароль (password): ");
            String password = scanner.nextLine().trim();
            if (checking.isCorrectPassword(password)) {
                User user = users.get(email);
                if (users.containsKey(email) && user.getPassword().equals(password)) {
                    users.remove(user.getEmail());
                    System.out.println("Аккаунт удален.");
                }
            }
        } else {
            System.out.println("Неверный email или пароль. Повторите вход!");
        }
    }
}
