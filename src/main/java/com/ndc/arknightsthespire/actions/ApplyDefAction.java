package com.ndc.arknightsthespire.actions;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.ndc.arknightsthespire.power.*;

public class ApplyDefAction {

    public static void applyPerTurn(AbstractCreature target, AbstractCreature source, int armAmount, int resAmount) {
        if(armAmount != 0)
            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(target, source, new ArmPerTurnPower(target, source, armAmount)));
        if(resAmount != 0)
            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(target, source, new ResPerTurnPower(target, source, resAmount)));
    }

    public static void applyTurn(AbstractCreature target, AbstractCreature source, int armAmount, int resAmount) {
        if(armAmount != 0)
            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(target, source, new ArmTurnPower(target, source, armAmount)));
        if(resAmount != 0)
            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(target, source, new ResTurnPower(target, source, resAmount)));
    }

    public static void applyDef(AbstractCreature target, int armAmount, int resAmount) {
        if(target.hasPower(ArmourPower.POWER_ID)) {
            if(target.getPower(ArmourPower.POWER_ID) instanceof ArmourPower) {
                ((ArmourPower) target.getPower(ArmourPower.POWER_ID)).stack(armAmount, resAmount);
            }
        } else {
            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(target, target, new ArmourPower(target, armAmount, resAmount)));
        }
    }

    public static void flash(AbstractCreature target) {
        if(target.hasPower(ArmourPower.POWER_ID)) {
            target.getPower(ArmourPower.POWER_ID).flash();
        }
    }
}
