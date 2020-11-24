package com.ndc.arknightsthespire.relics;

import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.ndc.arknightsthespire.cards.CardSPBase;
import com.ndc.arknightsthespire.util.TextureLoader;

public class IndustrialWaterCannon extends CustomRelic {
    public static final String ID = "Industrial Water Cannon";
    private static final Texture IMG = TextureLoader.getTexture("img/relics/IndustrialWaterCannon.png");

    public IndustrialWaterCannon() {
        super(ID, IMG, RelicTier.UNCOMMON, LandingSound.SOLID); // this relic is uncommon and sounds magic when you click it
        this.counter = 0;
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];// DESCRIPTIONS pulls from your localization file
    }

    @Override
    public void onUseCard(AbstractCard card, UseCardAction action) {
        if(card instanceof CardSPBase) {
            CardSPBase c = (CardSPBase) card;
            if (c.isSpJustUsed) {
                this.counter++;
                if (this.counter == 3) {
                    flash();
                    addToBot(new DrawCardAction(1));
                    this.counter = 0;
                }
            }
        }
    }

    @Override
    public void onVictory() {
        this.counter = 0;
    }

    @Override
    public AbstractRelic makeCopy() { // always override this method to return a new instance of your relic
        return new IndustrialWaterCannon();
    }

}