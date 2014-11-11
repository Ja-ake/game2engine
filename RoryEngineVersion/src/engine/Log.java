package engine;

public abstract class Log {

    public static boolean VISIBLE;

    public static void print(String s) {
        if (VISIBLE) {
            System.out.println(s);
        }
    }
}
