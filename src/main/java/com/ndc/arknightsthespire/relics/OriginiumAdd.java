//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.ndc.arknightsthespire.relics;

import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.relics.Circlet;
import com.ndc.arknightsthespire.SPHandler;
import com.ndc.arknightsthespire.util.TextureLoader;

public class OriginiumAdd extends CustomRelic {
    public static final String ID = "ats:Originium";
    private static final Texture IMG = TextureLoader.getTexture("img/relics/Originium.png");

    public OriginiumAdd() {
        super(ID, IMG, RelicTier.SPECIAL, LandingSound.CLINK);
        SPHandler.addMaxSp(10);
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
