package utils;

public final class AnsiString {

    public static String red(String value) {
        return "\033[31m" + value + "\033[0m";
    }

    public static String green(String value) {
        return "\033[32m" + value + "\033[0m";
    }

}
