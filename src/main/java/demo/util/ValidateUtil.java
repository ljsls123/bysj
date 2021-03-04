package demo.util;


import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidateUtil {
    private static Pattern pattern = Pattern.compile("^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+$");

    public static boolean passwordFormatCheck(String password) {
        if (null == password) {
            return true;
        }
        boolean b = false;
        for (char ch : password.toCharArray()) {
            if (ch > 126 || ch < 33) {
                b = true;
                break;
            }
        }
        return password.length() != 32 || b;
    }

    public static boolean emailFormatCheck(String email) {
        if (null == email) {
            return true;
        }
        Matcher matcher = pattern.matcher(email);
        return !matcher.matches() || email.length() >= 50;
    }
}
