package com.ndc.arknightsthespire.relics;

import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.HealAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.ndc.arknightsthespire.util.TextureLoader;

public class Gyao extends CustomRelic {
    public static final String ID = "ats:Gyao";
    private static final Texture IMG = TextureLoader.getTexture("img/relics/Gyao.png");

    public Gyao() {
        super(ID, IMG, RelicTier.RARE, LandingSound.CLINK); // this relic is uncommon and sounds magic when you click it
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0]; // DESCRIPTIONS pulls from your localization file
    }

    @Override
    public void onGainGold() {
        flash();
        AbstractDungeon.player.gainGold(15);
    }

    @Override
    public AbstractRelic makeCopy() { // always override this method to return a new instance of your relic
        return new Gyao();
    }

}