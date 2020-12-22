package com.ndc.arknightsthespire.actions;

import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class CheckGainEnergy {
    public CheckGainEnergy(int amount) {
        AbstractPlayer p = AbstractDungeon.player;
        AbstractDungeon.actionManager.addToBottom(new GainEnergyAction(amount));
        checkDefend(p);
        checkUrsus(p);
    }

    public static void checkDefend(AbstractPlayer p) {
        if(p.hasPower("ats:Defend Order")) {
            int b = p.getPower("ats:Defend Order").amount;
            p.getPower("ats:Defend Order").flash();
            AbstractDungeon.actionManager.addToBottom(new GainBlockAction(p, b));
        }
    }

    public static void checkUrsus(AbstractPlayer p) {
        if(p.hasPower("ats:Roar Of Ursus")) {
            p.getPower("ats:Roar Of Ursus").flash();
            AbstractDungeon.actionManager.addToBottom(new GainEnergyAction(1));
            checkDefend(p);
        }
    }
}
