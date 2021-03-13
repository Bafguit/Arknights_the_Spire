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

    public WaitAnimAction(AbstractCreature source, float setDur) {
        this.setValues((AbstractCreature)null, source, 0);
        this.duration = setDur;
        this.startDuration = setDur;

        this.actionType = ActionType.WAIT;
    }

    public void update() {
        this.tickDuration();
    }
}
