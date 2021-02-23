package com.ndc.arknightsthespire.actions;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.ndc.arknightsthespire.power.*;

public class ApplyAtkAction {

    AbstractPlayer p = AbstractDungeon.player;

    public static void applyPerTurn(int amount) {
        AbstractPlayer p = AbstractDungeon.player;
        if(amount != 0)
            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new AtkPerTurnPower(p, p, amount)));
    }

    public static void applyTurn(int amount) {
        AbstractPlayer p = AbstractDungeon.player;
        if(amount != 0)
            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new AtkTurnPower(p, p, amount)));
    }

    public static void applyAtk(int amount) {
        AbstractPlayer p = AbstractDungeon.player;
        if(p.hasPower(AttackPower.POWER_ID)) {
            if(p.getPower(AttackPower.POWER_ID) instanceof AttackPower) {
                ((AttackPower) p.getPower(AttackPower.POWER_ID)).stack(amount);
            }
        } else {
            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new AttackPower(p, amount)));
        }
    }

    public static void flash() {
        AbstractPlayer p = AbstractDungeon.player;
        if(p.hasPower(AttackPower.POWER_ID)) {
            p.getPower(AttackPower.POWER_ID).flash();
        }
    }
}
