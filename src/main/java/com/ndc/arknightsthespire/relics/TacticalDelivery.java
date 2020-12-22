package com.ndc.arknightsthespire.relics;

import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.ndc.arknightsthespire.SPHandler;
import com.ndc.arknightsthespire.util.TextureLoader;

public class TacticalDelivery extends CustomRelic {
    public static final String ID = "ats:Tactical Delivery";
    private static final Texture IMG = TextureLoader.getTexture("img/relics/TacticalDelivery.png");

    public TacticalDelivery() {
        super(ID, IMG, RelicTier.BOSS, LandingSound.FLAT); // this relic is uncommon and sounds magic when you click it
        SPHandler.addMaxSp(-10);
    }

    @Override
    public void onEnergyRecharge() {
        addToBot(new GainEnergyAction(1));
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0]; // DESCRIPTIONS pulls from your localization file
    }
    @Override
    public AbstractRelic makeCopy() { // always override this method to return a new instance of your relic
        return new TacticalDelivery();
    }

}