package util;

public class InputValidator {

    public static boolean isValidText(String value) {
        return value != null && !value.trim().isEmpty();
    }

    public static boolean isValidId(String id) {
        return id != null &&
                !id.trim().isEmpty() &&
                id.matches("[A-Za-z0-9]+");
    }

    public static boolean isValidContact(String contact) {
        return contact != null &&
                !contact.trim().isEmpty();
    }

    public static boolean isValidMenuChoice(int choice) {
        return choice >= 1 && choice <= 7;
    }
}
