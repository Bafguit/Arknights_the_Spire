//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.ndc.arknightsthespire.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.AbstractGameAction.ActionType;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.Settings;

public class WaitAnimAction extends AbstractGameAction {

    public WaitAnimAction(AbstractCreature a, float setDur) {
        this.setValues((AbstractCreature)null, a, 0);
        this.duration = setDur;
        this.startDuration = setDur;

        this.actionType = ActionType.WAIT;
    }

    public void update() {
        if(this.duration == this.startDuration) {
        }

        this.tickDuration();
    }
}
