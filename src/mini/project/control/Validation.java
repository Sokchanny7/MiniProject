package mini.project.control;

public class Validation {

    public static boolean isInrank(int min, int max, int value) {
        return (min < value && value < max);
    }
    public static boolean isInrank(long min, long max, long value) {
        return (min < value && value < max);
    }
}
