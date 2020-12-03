package com.ndc.arknightsthespire.actions;

import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class CheckGainEnergy {
    public CheckGainEnergy(int amount) {
        AbstractPlayer p = AbstractDungeon.player;
        int b = p.getPower("ats:Defend Order").amount;
        AbstractDungeon.actionManager.addToBottom(new GainEnergyAction(amount));
        checkDefend(p, b);
        checkUrsus(p);
    }

    public static void checkDefend(AbstractPlayer p, int b) {
        if(p.hasPower("ats:Defend Order")) {
            p.getPower("ats:Defend Order").flash();
            AbstractDungeon.actionManager.addToBottom(new GainBlockAction(p, b));
        }
    }

    public static void checkUrsus(AbstractPlayer p) {
        int b = p.getPower("ats:Defend Order").amount;
        if(p.hasPower("ats:Roar of Ursus")) {
            p.getPower("ats:Roar of Ursus").flash();
            AbstractDungeon.actionManager.addToBottom(new GainEnergyAction(1));
            checkDefend(p, b);
        }
    }
}
