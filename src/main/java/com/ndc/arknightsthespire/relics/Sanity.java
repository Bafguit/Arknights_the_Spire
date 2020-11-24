package com.ndc.arknightsthespire.relics;

import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.ndc.arknightsthespire.SPHandler;
import com.ndc.arknightsthespire.cards.CardSPBase;
import com.ndc.arknightsthespire.util.TextureLoader;

public class Sanity extends CustomRelic {
    public static final String ID = "Sanity";
    private static final Texture IMG = TextureLoader.getTexture("img/relics/Sanity.png");

    public boolean used;

    public Sanity() {
        super(ID, IMG, RelicTier.STARTER, LandingSound.FLAT); // this relic is uncommon and sounds magic when you click it
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0]; // DESCRIPTIONS pulls from your localization file
    }

    @Override
    public void onUseCard(AbstractCard c, UseCardAction useCardAction) {
        if(c instanceof CardSPBase) {
            CardSPBase card = (CardSPBase) c;
            if (card.isSpJustUsed && !used) {
                flash();
                SPHandler.addSp(Math.round(card.baseSP / 2));
                used = true;
            }
        }
    }

    @Override
    public void atTurnStart() {
        used = false;
    }

    @Override
    public AbstractRelic makeCopy() { // always override this method to return a new instance of your relic
        return new Sanity();
    }

}