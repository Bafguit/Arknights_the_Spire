//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.ndc.arknightsthespire.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.ndc.arknightsthespire.ArknightsTheSpire;

public class AtsSFX extends AbstractGameAction {
    private String key;
    private float pitchVar;
    private boolean adjust;
    public AtsSound atsS = new AtsSound();

    public AtsSFX(String key) {
        this(key, 0.0F, false);
    }

    public AtsSFX(String key, float pitchVar) {
        this(key, pitchVar, false);
    }

    public AtsSFX(String key, float pitchVar, boolean pitchAdjust) {
        this.pitchVar = 0.0F;
        this.adjust = false;
        this.key = key;
        this.pitchVar = pitchVar;
        this.adjust = pitchAdjust;
        this.actionType = ActionType.WAIT;
    }

    public void update() {
        if (!this.adjust) {
            atsS.play(this.key, this.pitchVar);
        } else {
            atsS.playA(this.key, this.pitchVar);
        }

        this.isDone = true;
    }
}
