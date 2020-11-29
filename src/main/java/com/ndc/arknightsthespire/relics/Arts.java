package com.ndc.arknightsthespire.relics;

import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.ndc.arknightsthespire.cards.CardSPBase;
import com.ndc.arknightsthespire.cards.PositionType;
import com.ndc.arknightsthespire.util.TextureLoader;

import static com.ndc.arknightsthespire.SPHandler.addSp;

public class Arts extends CustomRelic {
    public static final String ID = "ats:Arts";
    private static final Texture IMG = TextureLoader.getTexture("img/relics/Arts.png");
    public CardSPBase spC;

    public Arts() {
        super(ID, IMG, RelicTier.SHOP, LandingSound.FLAT); // this relic is uncommon and sounds magic when you click it
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0]; // DESCRIPTIONS pulls from your localization file
    }

    @Override
    public void onUseCard(AbstractCard card, UseCardAction useCardAction) {
        AbstractCard c = card;
        if(c instanceof CardSPBase) {
            spC = (CardSPBase) c;
            if(spC.position == PositionType.CASTER) {
                flash();
                addSp(1);
            }
        }
    }

    @Override
    public AbstractRelic makeCopy() { // always override this method to return a new instance of your relic
        return new Arts();
    }

}