package com.example.dagger2sqlite.common;

public class Common {
    public static boolean intToBool(int i) {
        return i == 1;
    }

    public static int boolToInt(boolean b) {
        return b ? 1 : 0;
    }
}
