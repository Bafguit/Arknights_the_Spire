package com.ndc.arknightsthespire.relics;

import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.ndc.arknightsthespire.SPHandler;
import com.ndc.arknightsthespire.util.TextureLoader;

public class RhineCircuitry extends CustomRelic {
    public static final String ID = "ats:Rhine Circuitry";
    private static final Texture IMG = TextureLoader.getTexture("atsImg/relics/RhineCircuity.png");

    public RhineCircuitry() {
        super(ID, IMG, RelicTier.COMMON, LandingSound.MAGICAL); // this relic is uncommon and sounds magic when you click it
        this.counter = 0;
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0] + 2 + DESCRIPTIONS[1]; // DESCRIPTIONS pulls from your localization file
    }

    @Override
    public void onUseCard(AbstractCard card, UseCardAction action) {
        this.counter++;
        if(this.counter == 6) {
            flash();
            SPHandler.addSp(2);
            this.counter = 0;
        }
    }

    @Override
    public AbstractRelic makeCopy() { // always override this method to return a new instance of your relic
        return new RhineCircuitry();
    }

}