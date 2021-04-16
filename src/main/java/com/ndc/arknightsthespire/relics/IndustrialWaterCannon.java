package com.ndc.arknightsthespire.relics;

import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.ndc.arknightsthespire.cards.base.CardSPBase;
import com.ndc.arknightsthespire.util.TextureLoader;

public class IndustrialWaterCannon extends CustomRelic {
    public static final String ID = "ats:Industrial Water Cannon";
    private static final Texture IMG = TextureLoader.getTexture("atsImg/relics/IndustrialWaterCannon.png");

    public IndustrialWaterCannon() {
        super(ID, IMG, RelicTier.UNCOMMON, LandingSound.SOLID); // this relic is uncommon and sounds magic when you click it
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];// DESCRIPTIONS pulls from your localization file
    }

    @Override
    public void onPlayCard(AbstractCard card, AbstractMonster m) {
        if(card instanceof CardSPBase) {
            CardSPBase c = (CardSPBase) card;
            if (c.isSpJustUsed) {
                flash();
                addToBot(new DrawCardAction(1));
            }
        }
    }

    @Override
    public AbstractRelic makeCopy() { // always override this method to return a new instance of your relic
        return new IndustrialWaterCannon();
    }

}