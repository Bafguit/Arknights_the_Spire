package com.ndc.arknightsthespire.relics;

import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.HealAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.ndc.arknightsthespire.util.TextureLoader;

public class Lavender extends CustomRelic {
    public static final String ID = "Lavender";
    private static final Texture IMG = TextureLoader.getTexture("img/relics/Lavender.png");

    public Lavender() {
        super(ID, IMG, RelicTier.COMMON, LandingSound.FLAT); // this relic is uncommon and sounds magic when you click it
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0] + 1 + DESCRIPTIONS[1]; // DESCRIPTIONS pulls from your localization file
    }

    @Override
    public void onPlayerEndTurn() {
        flash();
        addToBot(new HealAction(AbstractDungeon.player, AbstractDungeon.player, 1));
    }

    @Override
    public AbstractRelic makeCopy() { // always override this method to return a new instance of your relic
        return new Lavender();
    }

}