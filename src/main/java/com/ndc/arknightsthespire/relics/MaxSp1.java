package com.ndc.arknightsthespire.relics;

import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.HealAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.ndc.arknightsthespire.SPHandler;
import com.ndc.arknightsthespire.util.TextureLoader;

public class MaxSp1 extends CustomRelic {
    public static final String ID = "ats:MaxSp1";
    private static final Texture IMG = TextureLoader.getTexture("img/relics/MaxSp1.png");

    public MaxSp1() {
        super(ID, IMG, RelicTier.COMMON, LandingSound.CLINK); // this relic is uncommon and sounds magic when you click it
        SPHandler.addMaxSp(10);
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0]; // DESCRIPTIONS pulls from your localization file
    }
    @Override
    public AbstractRelic makeCopy() { // always override this method to return a new instance of your relic
        return new MaxSp1();
    }

}