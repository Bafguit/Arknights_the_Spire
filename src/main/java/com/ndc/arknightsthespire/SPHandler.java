package com.ndc.arknightsthespire;

public class SPHandler {
    private static int sp = 10;
    private static boolean isSpModeEnabled;

    public static int getSp() {
        return sp;
    }

    public static void setSp(int sp1) {
        sp = sp1;
    }

    public static void addSp(int sp1) {
        sp += sp1;
    }

    public static boolean toggleSpMode() {
        return isSpModeEnabled = !isSpModeEnabled;
    }
}
