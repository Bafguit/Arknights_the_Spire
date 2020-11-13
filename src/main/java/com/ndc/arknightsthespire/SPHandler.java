package com.ndc.arknightsthespire;

public class SPHandler {
    private static int sp = 0;
    private static int addSp = 2;
    private static int maxSp = 10;
    private static boolean isSpModeEnabled;
    public static int diff_sp = 0;
    public static int default_SP = 0;
    public static int current_SP = 0;
    public static int receive_SP = 1;
    public static int receive_SP_cardUse = 1;
    public static int lastSPAmount;

    public static int getSp() {
        return sp;
    }
    public static int getAddSp() {
        return addSp;
    }
    public static int getMaxSp() {
        return maxSp;
    }

    public static void setSp(int sp1) {
        sp = sp1;
    }

    public static void addSp() {
        sp += addSp;
    }

    public static void addSp(int sp1) {
        sp += sp1;
    }

    public static void setAddSp(int addSp1) {
        addSp = addSp1;
    }

    public static boolean toggleSpMode() {
        return isSpModeEnabled = !isSpModeEnabled;
    }
}
