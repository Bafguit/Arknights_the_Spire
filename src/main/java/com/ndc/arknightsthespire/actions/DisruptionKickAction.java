//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.ndc.arknightsthespire.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.powers.*;
import com.megacrit.cardcrawl.powers.AbstractPower.PowerType;
import com.ndc.arknightsthespire.character.AtsEnum;
import com.ndc.arknightsthespire.power.BallistaPower;

import java.util.ArrayList;
import java.util.Iterator;

public class DisruptionKickAction extends AbstractGameAction {
    private AbstractCreature c;
    public static ArrayList unRemoval;

    public DisruptionKickAction(AbstractCreature c) {
        this.c = c;
        this.duration = 0.5F;
    }

    public void update() {
        Iterator var1 = this.c.powers.iterator();

        while(true) {
            AbstractPower p;
            do {
                if (!var1.hasNext()) {
                    this.isDone = true;
                    return;
                }

                p = (AbstractPower)var1.next();
            } while(unRemoval.contains(p.ID));

            if(p.type != AtsEnum.ATS_BASE) {
                this.addToTop(new RemoveSpecificPowerAction(this.c, this.c, p.ID));
            }
        }
    }

    static  {
        unRemoval = new ArrayList<String>();
        unRemoval.add(SplitPower.POWER_ID);
        unRemoval.add(ModeShiftPower.POWER_ID);
        unRemoval.add(CuriosityPower.POWER_ID);
        unRemoval.add(TimeWarpPower.POWER_ID);
        unRemoval.add(BeatOfDeathPower.POWER_ID);
        unRemoval.add(FadingPower.POWER_ID);
        unRemoval.add(MinionPower.POWER_ID);
        unRemoval.add(ShiftingPower.POWER_ID);
        unRemoval.add(StasisPower.POWER_ID);
        unRemoval.add(UnawakenedPower.POWER_ID);
        unRemoval.add(ThieveryPower.POWER_ID);
        unRemoval.add(PainfulStabsPower.POWER_ID);
        unRemoval.add(BallistaPower.POWER_ID);
    }
}
