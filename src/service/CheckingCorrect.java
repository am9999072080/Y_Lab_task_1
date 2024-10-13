package service;

public class CheckingCorrect {
    public boolean isCorrectName(String name) {
        if (name.matches("[A-Z][a-z]+")) {
            return true;
        } else {
            System.out.println("Ошибка ввода имени");
            return false;
        }
    }

    public boolean isCorrectEmail(String email) {

        if (email.matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
            return true;

        } else {
            System.out.println("Ошибка ввода E-mail");
            return false;
        }
    }

    public boolean isCorrectPassword(String password) {
        if (password.matches("^[a-zA-Z0-9]+$")) {//(?!.*\W) – пароль должен состоять только из букв и цифр
            return true;
        } else {
            System.out.println("Ошибка ввода пароля");
            return false;
        }
    }
}
