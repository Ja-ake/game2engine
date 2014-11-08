package edu.catlin.springerj.g2e.old;

import java.util.HashMap;
import java.util.Map;

public class StaticData {
    protected static Map<String, Short> shorts = new HashMap();
    protected static Map<String, Integer> integers = new HashMap();
    protected static Map<String, Long> longs = new HashMap();
    protected static Map<String, Float> floats = new HashMap();
    protected static Map<String, Double> doubles = new HashMap();
    protected static Map<String, Boolean> booleans = new HashMap();
    protected static Map<String, String> strings = new HashMap();
    protected static Map<String, Object> objects = new HashMap();
    
    public static void add(String name, short d) {
        if (!(shorts.containsKey(name)
                || integers.containsKey(name)
                || longs.containsKey(name)
                || floats.containsKey(name)
                || doubles.containsKey(name)
                || booleans.containsKey(name)
                || strings.containsKey(name)
                || objects.containsKey(name))) {
            shorts.put(name, d);
        }
    }

    public static void add(String name, int d) {
        if (!(shorts.containsKey(name)
                || longs.containsKey(name)
                || floats.containsKey(name)
                || doubles.containsKey(name)
                || booleans.containsKey(name)
                || strings.containsKey(name)
                || objects.containsKey(name))) {
            integers.put(name, d);
        }
    }

    public static void add(String name, long d) {
        if (!(shorts.containsKey(name)
                || integers.containsKey(name)
                || floats.containsKey(name)
                || doubles.containsKey(name)
                || booleans.containsKey(name)
                || strings.containsKey(name)
                || objects.containsKey(name))) {

            longs.put(name, d);
        }
    }

    public static void add(String name, float d) {
        if (!(shorts.containsKey(name)
                || integers.containsKey(name)
                || longs.containsKey(name)
                || doubles.containsKey(name)
                || booleans.containsKey(name)
                || strings.containsKey(name)
                || objects.containsKey(name))) {

            floats.put(name, d);
        }
    }

    public static void add(String name, double d) {
        if (!(shorts.containsKey(name)
                || integers.containsKey(name)
                || longs.containsKey(name)
                || floats.containsKey(name)
                || booleans.containsKey(name)
                || strings.containsKey(name)
                || objects.containsKey(name))) {

            doubles.put(name, d);
        }
    }

    public static void add(String name, boolean d) {
        if (!(shorts.containsKey(name)
                || integers.containsKey(name)
                || longs.containsKey(name)
                || floats.containsKey(name)
                || doubles.containsKey(name)
                || strings.containsKey(name)
                || objects.containsKey(name))) {

            booleans.put(name, d);
        }
    }

    public static void add(String name, String d) {
        if (!(shorts.containsKey(name)
                || integers.containsKey(name)
                || longs.containsKey(name)
                || floats.containsKey(name)
                || doubles.containsKey(name)
                || booleans.containsKey(name)
                || objects.containsKey(name))) {

            strings.put(name, d);
        }
    }

    public static void add(String name, Object d) {
        if (!(shorts.containsKey(name)
                || integers.containsKey(name)
                || longs.containsKey(name)
                || floats.containsKey(name)
                || doubles.containsKey(name)
                || booleans.containsKey(name)
                || strings.containsKey(name))) {

            objects.put(name, d);
        }
    }

    public static void remove(String name) {
        shorts.remove(name);
        integers.remove(name);
        longs.remove(name);
        floats.remove(name);
        doubles.remove(name);
        booleans.remove(name);
        strings.remove(name);
        objects.remove(name);
    }

    public static short getShort(String name) {
        return shorts.get(name);
    }

    public static int getInteger(String name) {
        return integers.get(name);
    }

    public static long getLong(String name) {
        return longs.get(name);
    }

    public static float getFloat(String name) {
        return floats.get(name);
    }

    public static double getDouble(String name) {
        return doubles.get(name);
    }

    public static boolean getBoolean(String name) {
        return booleans.get(name);
    }

    public static String getString(String name) {
        return strings.get(name);
    }

    public static Object get(String name) {
        Object ret = shorts.get(name);
        if (ret == null) {
            ret = integers.get(name);
        }
        if (ret == null) {
            ret = longs.get(name);
        }
        if (ret == null) {
            ret = floats.get(name);
        }
        if (ret == null) {
            ret = doubles.get(name);
        }
        if (ret == null) {
            ret = booleans.get(name);
        }
        if (ret == null) {
            ret = strings.get(name);
        }
        if (ret == null) {
            ret = objects.get(name);
        }

        return ret;
    }
}
