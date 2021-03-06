package com.ndc.arknightsthespire.relics;

import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.ndc.arknightsthespire.SPHandler;
import com.ndc.arknightsthespire.util.TextureLoader;

public class AbilityAura extends CustomRelic {
    public static final String ID = "ats:Ability Aura";
    private static final Texture IMG = TextureLoader.getTexture("atsImg/relics/AbilityAura.png");

    public AbilityAura() {
        super(ID, IMG, RelicTier.COMMON, LandingSound.CLINK); // this relic is uncommon and sounds magic when you click it
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0] + 3 + DESCRIPTIONS[1]; // DESCRIPTIONS pulls from your localization file
    }

    @Override
    public void atTurnStart() {
        flash();
        SPHandler.addSp(3);
    }

    @Override
    public AbstractRelic makeCopy() { // always override this method to return a new instance of your relic
        return new AbilityAura();
    }

}