//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.ndc.arknightsthespire.util;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.ndc.arknightsthespire.ArknightsTheSpire;
import com.ndc.arknightsthespire.ui.AtsTutorial;

import java.io.IOException;

public class MessageCaller extends AbstractGameAction {
    private float startingDuration;
    private DamageInfo info;
    private boolean isupgraded;
    public int code;

    public MessageCaller(int code) {
        this.startingDuration = Settings.ACTION_DUR_FAST;
        this.duration = this.startingDuration;
        this.code = code;
    }

    public void update() {
        if (ArknightsTheSpire.activeTutorials[this.code]) {
            AbstractDungeon.ftue = new AtsTutorial();
            ArknightsTheSpire.activeTutorials[this.code] = false;

            try {
                ArknightsTheSpire.saveData();
                this.isDone = true;
            } catch (IOException var2) {
                var2.printStackTrace();
                this.isDone = true;
            }
        }

    }
}
