package com.ndc.arknightsthespire;

import basemod.abstracts.CustomSavable;
import basemod.interfaces.*;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import com.ndc.arknightsthespire.cards.base.CardSPBase;

import java.util.ArrayList;

public class SPHandler implements PostDrawSubscriber, OnStartBattleSubscriber, PostEnergyRechargeSubscriber, OnCardUseSubscriber, PostBattleSubscriber, CustomSavable<Integer>, PreStartGameSubscriber, PostDeathSubscriber, PostCreateStartingRelicsSubscriber {
    private static int sp = 0;
    private static int maxSp = 10;
    private static int maxSpLimit = 50;
    private static int turnEnergy = 0;
    private static final int MAX_SP_UP_LIMIT = 120;
    private static final int MAX_SP_DOWN_LIMIT = 10;
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
    public void upgradeLimit() {
        AbstractPlayer p = AbstractDungeon.player;
        maxSpLimit = getMaxSpLimit();
    }
    
    public static int getMaxSpLimit() {
      int defMax = 50;
      AbstractPlayer p = AbstractDungeon.player;
      if(p.hasRelic("ats:Sanity Plus")) {
        defMax += 10;
      }
      if(p.hasRelic("ats:Tactical Delivery")) {
        defMax -= 20;
      }
      
      return defMax;
    }

    public static int getTurnEnergy() {
        return turnEnergy;
    }

    public static void addTurnEnergy(int e) {
        turnEnergy += e;
    }
    
    public static int getTurnAddSp() {
        return turnAddSp;
    }

    public static void addSp(int amount) {
        addSp(amount, false);
    }
    public static void removeSp(int amount) {
        removeSp(amount, false);
    }
    public static void setSp(int value) {
        setSp(value, false);
    }
    public static void addSp(int amount, boolean isCommand) {
        if(amount < 0) return;
        setSp(sp + amount, isCommand);
    }
    public static void removeSp(int amount, boolean isCommand) {
        if(amount < 0) return;
        setSp(Math.max(sp - amount, 0), isCommand); //It won't work if sp-amount < 0
    }
    public static void setSp(int value, boolean isCommand) {
        if(value < 0) return;
        sp = Math.max(Math.min(value, (isCommand ? MAX_SP_UP_LIMIT : maxSp)), 0);
    }

    public static void addMaxSp(int amount) {
        addMaxSp(amount, false);
    }
    public static void removeMaxSp(int amount) {
        removeMaxSp(amount, false);
    }
    public static void setMaxSp(int value) {
        setMaxSp(value, false);
    }
    public static void addMaxSp(int amount, boolean isCommand) {
        if(amount < 0) return;
        setMaxSp(maxSp + amount, isCommand);
    }
    public static void removeMaxSp(int amount, boolean isCommand) {
        if(amount < 0) return;
        setMaxSp(Math.max(maxSp - amount, 0), isCommand); //It won't work if maxSp-amount < 0
    }
    public static void setMaxSp(int value, boolean isCommand) {
        if(value < 0) return;
        maxSp = Math.max(Math.min(value, (isCommand ? MAX_SP_UP_LIMIT : maxSpLimit)), MAX_SP_DOWN_LIMIT);
    }

    public static void addSpSoon(int amount) {
        soonAddedSp += amount;
    }
    public static void updateSp() {
        addSp(soonAddedSp);
        soonAddedSp = 0;
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
        upgradeLimit();
        this.setSp(defaultSp + turnAddSp);
        System.out.println("Current SP: " + sp);
    }

    @Override
    public void receivePostBattle(AbstractRoom abstractRoom) {
        System.out.println("Saving...");
        sp = 0;
        maxSp = 10;
        turnEnergy = 0;
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
        turnEnergy = 0;
    }

    @Override
    public void receivePreStartGame() {
        maxSp = 10;
    }

    @Override
    public void receivePostDeath() {
        maxSp = 10;
        turnEnergy = 0;
    }

    @Override
    public void receivePostCreateStartingRelics(AbstractPlayer.PlayerClass playerClass, ArrayList<String> arrayList) {
        System.out.println("Saving...");
        sp = 0;
        maxSp = 10;
        turnEnergy = 0;
        System.out.println("Saved.");
    }
}
