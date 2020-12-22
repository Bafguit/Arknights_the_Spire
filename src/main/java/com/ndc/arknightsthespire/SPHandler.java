package com.ndc.arknightsthespire;

import basemod.abstracts.CustomSavable;
import basemod.interfaces.*;
import com.badlogic.gdx.graphics.g3d.particles.ResourceData;
import com.google.gson.reflect.TypeToken;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import com.megacrit.cardcrawl.saveAndContinue.SaveAndContinue;
import com.ndc.arknightsthespire.cards.base.CardSPBase;

import java.lang.reflect.Type;

public class SPHandler implements PostDrawSubscriber, OnStartBattleSubscriber, PostEnergyRechargeSubscriber, OnCardUseSubscriber, PostBattleSubscriber, CustomSavable<Integer>, PreStartGameSubscriber, PostDeathSubscriber {
    private static int sp = 0;
    private static int maxSp = 10;
    private static int maxSpLimit = 40;
    private static int diffSp = 0;
    private static int defaultSp = 0;
    private static int turnAddSp = 1;
    private static int cardAddSp = 1;
    private static int lastSPAmount;
    private static boolean isSpModeEnabled;

    private static int soonAddedSp = 0; //This is useful when card use triggers addSp

    public static int getSp() {
        return sp;
    }

    public static int getMaxSp() {
        return maxSp;
    }
    public static boolean getUpToMaxSp() {
        if(maxSp >= maxSpLimit) return false;
        else return true;
    }
    public static void upgradeLimit() {maxSpLimit = 50;}
    public static int getDiffSp() {
        return diffSp;
    }
    public static int getTurnAddSp() {
        return turnAddSp;
    }
    public static void setSp(int value) {
        sp = value;
    }
    public static void addSp(int amount) {
        sp = Math.min(sp + amount, maxSp);
    }
    public static void addMaxSp(int amount) {
        maxSp = Math.min(maxSp + amount, maxSpLimit);
    }
    public static void addSpSoon(int amount) {
        soonAddedSp += amount;
    }
    public static void updateSp() {
        addSp(soonAddedSp);
        soonAddedSp = 0;
    }
    public static void removeSp(int amount) { sp = Math.max(sp - amount, 0); }
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

    @Override
    public Integer onSave()
    {
        System.out.println("@@Saving MaxSP...");
        return maxSp;
        // Return the location of the card in your deck. AbstractCard cannot be serialized so we use an Integer instead.
    }

    @Override
    public void onLoad(Integer savedMaxSp) {
        System.out.println("@@Loading MaxSP...");
        if(savedMaxSp == null) {
            maxSp = 20;
        }
        else maxSp = savedMaxSp;
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
        diffSp = 0;
        sp = 0;
        System.out.println("Saved.");
    }

    //
    @Override
    public void receiveCardUsed(AbstractCard abstractCard) {
        if(abstractCard instanceof CardSPBase)
            addSpSoon(cardAddSp);
        else
            addSp(cardAddSp);
        System.out.println("Current SP: " + sp);
    }

    @Override
    public void receivePostEnergyRecharge() {
        this.addSp(turnAddSp);
    }

    @Override
    public void receivePreStartGame() {
        maxSp = 10;
    }

    @Override
    public void receivePostDeath() {
        maxSp = 10;
    }
}
