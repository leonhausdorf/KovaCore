package de.zevyx.iriscore.utils;

public class Util {

    public static boolean randomCalculation(int maxvalue) {
        int min = 1;
        int range = maxvalue - min + 1;

        int rand = (int) (Math.random() * range) + min;
        return rand == 1;
    }

}
