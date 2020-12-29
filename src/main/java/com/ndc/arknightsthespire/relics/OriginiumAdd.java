//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.ndc.arknightsthespire.relics;

import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.relics.Circlet;
import com.ndc.arknightsthespire.SPHandler;
import com.ndc.arknightsthespire.util.TextureLoader;

import java.util.AbstractList;
import java.util.prefs.AbstractPreferences;

public class OriginiumAdd extends CustomRelic {
    public static final String ID = "ats:Originium";
    private static final Texture IMG = TextureLoader.getTexture("img/relics/Originium.png");

    public OriginiumAdd() {
        super(ID, IMG, RelicTier.SPECIAL, LandingSound.CLINK);
        this.counter = 1;
    }

    @Override
    public void atBattleStartPreDraw() {
        SPHandler.addMaxSp(10 * this.counter);
    }

    @Override
    public void instantObtain() {
        AbstractPlayer p = AbstractDungeon.player;
        if (p != null && p.hasRelic("ats:Originium")) {
            AbstractRelic circ = p.getRelic("ats:Originium");
            ++circ.counter;
            circ.flash();
        } else {
            super.instantObtain();
        }
    }

    @Override
    public void obtain() {
        // Replace the starter relic, or just give the relic if starter isn't found

        AbstractPlayer p = AbstractDungeon.player;

        if (p != null && p.hasRelic("ats:Originium")) {
            AbstractRelic circ = p.getRelic("ats:Originium");
            ++circ.counter;
            circ.flash();
            this.hb.hovered = false;
        } else {
            super.obtain();
        }
    }

    @Override
    public String getUpdatedDescription() {
        return this.DESCRIPTIONS[0];
    }

    @Override
    public void onEquip() {
        this.flash();
    }

    @Override
    public AbstractRelic makeCopy() {
        return new OriginiumAdd();
    }
}
