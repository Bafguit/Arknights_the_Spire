package com.ndc.arknightsthespire;

public class SPHandler {
    private int sp;
    private boolean isSpModeEnabled;

    public int getSp() {
        return sp;
    }

    public void addSp(int sp) {
        this.sp = sp;
    }

    public boolean toggleSpMode() {
        return isSpModeEnabled = !isSpModeEnabled;
    }
}
