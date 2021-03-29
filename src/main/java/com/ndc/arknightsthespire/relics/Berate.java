package com.ndc.arknightsthespire.relics;

import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.ndc.arknightsthespire.SPHandler;
import com.ndc.arknightsthespire.util.TextureLoader;

public class Berate extends CustomRelic {
    public static final String ID = "ats:Berate";
    private static final Texture IMG = TextureLoader.getTexture("atsImg/relics/Berate.png");

    public Berate() {
        super(ID, IMG, RelicTier.COMMON, LandingSound.SOLID); // this relic is uncommon and sounds magic when you click it
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0] + 1 + DESCRIPTIONS[1]; // DESCRIPTIONS pulls from your localization file
    }

    @Override
    public void onUseCard(AbstractCard card, UseCardAction action) {
        if(card.type == AbstractCard.CardType.ATTACK) {
            flash();
            SPHandler.addSp(1);
        }
    }

    @Override
    public AbstractRelic makeCopy() { // always override this method to return a new instance of your relic
        return new Berate();
    }

}