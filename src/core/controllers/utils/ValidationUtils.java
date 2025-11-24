package core.controllers.utils;

import java.util.regex.Pattern;

/**
 * Utilidades para validar datos de entrada
 */
public final class ValidationUtils {

    private static final Pattern NIT_PATTERN = Pattern.compile("\\d{3}\\.\\d{3}\\.\\d{3}-\\d");
    private static final Pattern ISBN_PATTERN = Pattern.compile("\\d{3}-\\d-\\d{2}-\\d{6}-\\d");

    private ValidationUtils() {
    }

    public static boolean isPlaceholder(String value) {
        return value != null && value.startsWith("Seleccione");
    }

    public static boolean isValidId(long id) {
        return id >= 0 && String.valueOf(id).length() <= 15;
    }

    public static boolean isValidNit(String nit) {
        return NIT_PATTERN.matcher(nit).matches();
    }

    public static boolean isValidIsbn(String isbn) {
        return ISBN_PATTERN.matcher(isbn).matches();
    }

    public static boolean isPositive(double value) {
        return value > 0;
    }

    public static boolean isBlank(String value) {
        return value == null || value.trim().isEmpty();
    }
}
