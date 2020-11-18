package com.ndc.arknightsthespire;

import basemod.interfaces.*;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import com.ndc.arknightsthespire.cards.CardSPBase;
import com.ndc.arknightsthespire.cards.PositionType;
import org.lwjgl.Sys;

import java.util.Iterator;

public class SPHandler implements PostDrawSubscriber, OnStartBattleSubscriber, PostEnergyRechargeSubscriber, OnCardUseSubscriber, PostBattleSubscriber {
    private static int sp = 0;
    private static int maxSp = 10;
    private static int diffSp = 0;
    private static int defaultSp = 0;
    private static int turnAddSp = 1;
    private static int cardAddSp = 1;
    private static int lastSPAmount;
    private static boolean isSpModeEnabled;

    private static int actualSp = 0; //This is useful when card use triggers addSp

    public static int getSp() {
        return sp;
    }

    public static int getMaxSp() {
        return maxSp;
    }
    public static int getDiffSp() {
        return diffSp;
    }
    public static void setSp(int value) {
        actualSp = sp;
        sp = value;
    }
    public static void addSp(int amount) {
        addSp(amount, false);
    }
    public static void addSp(int amount, boolean shouldBeAppliedInNextUpdate) {
        if(sp < maxSp) {
            actualSp = sp;
            if(sp + amount > maxSp) sp = maxSp;
            else sp += amount;
            if(!shouldBeAppliedInNextUpdate) actualSp = sp;
        }
    }
    public static void updateActualSp() {
        actualSp = sp;
    }
    public static void removeSp(int amount) {
        if(sp > 0) {
            actualSp = sp;
            if(sp - amount < 0) sp = 0;
            else sp -= amount;
        }
    }
    public static void addDiffSp(int amount) {
        diffSp += amount;
    }

    public static void setSpMode(boolean isEnabled) {
        isSpModeEnabled = isEnabled;
    }

    public static boolean toggleSpMode() {
        return isSpModeEnabled = !isSpModeEnabled;
    }

    public static boolean isSpModeEnabled() {
        return isSpModeEnabled;
    }

    public static int getActualSp() {
        return actualSp;
    }

    @Override
    public void receivePostDraw(AbstractCard abstractCard) {
        /*if(abstractCard instanceof CardSPBase) {
            //((CardSPBase) abstractCard).updateGlow(isSpModeEnabled);
            ((CardSPBase) abstractCard).stopGlowing();
        }*/
    }

    @Override
    public void receiveOnBattleStart(AbstractRoom abstractRoom) {
        this.setSp(defaultSp + turnAddSp);
        System.out.println("Current SP: " + sp);
    }

    @Override
    public void receivePostBattle(AbstractRoom abstractRoom) {
        System.out.println("Saving...");
        actualSp = sp;
        diffSp = 0;
        sp = 0;
        System.out.println("Saved.");
    }

    //
    @Override
    public void receiveCardUsed(AbstractCard abstractCard) {
        this.addSp(cardAddSp, true);
        System.out.println("Current SP: " + sp);
    }

    @Override
    public void receivePostEnergyRecharge() {
        this.addSp(turnAddSp);
    }
}
