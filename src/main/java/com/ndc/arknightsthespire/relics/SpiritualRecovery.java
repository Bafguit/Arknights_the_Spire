package com.ndc.arknightsthespire.relics;

import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.ndc.arknightsthespire.SPHandler;
import com.ndc.arknightsthespire.util.TextureLoader;

public class SpiritualRecovery extends CustomRelic {
    public static final String ID = "Spiritual Recovery";
    private static final Texture IMG = TextureLoader.getTexture("img/relics/beta.png");
    private static final Texture OUTLINE = TextureLoader.getTexture("img/relics/outline/beta.png");
    private static final int SP_RECOVER = 1;

    public SpiritualRecovery() {
        super(ID, IMG, OUTLINE, RelicTier.COMMON, LandingSound.MAGICAL); // this relic is uncommon and sounds magic when you click it
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0] + SP_RECOVER + DESCRIPTIONS[1]; // DESCRIPTIONS pulls from your localization file
    }

    @Override
    public int onPlayerHeal(int healAmount) {
        flash();
        SPHandler.addSp(SP_RECOVER);

        return healAmount;
    }

    @Override
    public AbstractRelic makeCopy() { // always override this method to return a new instance of your relic
        return new SpiritualRecovery();
    }

}