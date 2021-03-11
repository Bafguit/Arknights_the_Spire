package com.ndc.arknightsthespire.relics;

import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.ndc.arknightsthespire.cards.base.CardSPBase;
import com.ndc.arknightsthespire.cards.base.PositionType;
import com.ndc.arknightsthespire.util.TextureLoader;

import static com.ndc.arknightsthespire.SPHandler.addSp;

public class Snipe extends CustomRelic {
    public static final String ID = "ats:Snipe";
    private static final Texture IMG = TextureLoader.getTexture("atsImg/relics/Snipe.png");
    public CardSPBase spC;

    public Snipe() {
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
            if(spC.position == PositionType.SNIPER) {
                flash();
                addSp(1);
            }
        }
    }

    @Override
    public AbstractRelic makeCopy() { // always override this method to return a new instance of your relic
        return new Snipe();
    }

}