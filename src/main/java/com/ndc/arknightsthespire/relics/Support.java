package com.ndc.arknightsthespire.relics;

import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.BufferPower;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.ndc.arknightsthespire.cards.CardSPBase;
import com.ndc.arknightsthespire.cards.PositionType;
import com.ndc.arknightsthespire.util.TextureLoader;

import javax.smartcardio.Card;

import static com.ndc.arknightsthespire.SPHandler.addSp;

public class Support extends CustomRelic {
    public static final String ID = "ats:Support";
    private static final Texture IMG = TextureLoader.getTexture("img/relics/Support.png");
    public CardSPBase spC;

    public Support() {
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
            if(spC.position == PositionType.SUPPORT) {
                flash();
                addSp(1);
            }
        }
    }

    @Override
    public AbstractRelic makeCopy() { // always override this method to return a new instance of your relic
        return new Support();
    }

}