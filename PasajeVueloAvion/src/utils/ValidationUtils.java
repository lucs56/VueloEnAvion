package utils;

import java.util.regex.Pattern;

public class ValidationUtils {

    // Expresión regular para validar correos electrónicos
    private static final Pattern EMAIL_PATTERN = 
        Pattern.compile("^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$");

    // Método para validar un correo electrónico
    public static boolean isValidEmail(String email) {
        return EMAIL_PATTERN.matcher(email).matches();
    }

    // Método para validar una contraseña (ejemplo simple: debe tener al menos 6 caracteres)
    public static boolean isValidPassword(String password) {
        return password != null && password.length() >= 6;
    }
}
