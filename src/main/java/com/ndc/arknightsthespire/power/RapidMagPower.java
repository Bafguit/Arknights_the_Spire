package com.ndc.arknightsthespire.power;

import basemod.interfaces.CloneablePowerInterface;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.ndc.arknightsthespire.cards.base.CardSPBase;
import com.ndc.arknightsthespire.cards.base.PositionType;
import com.ndc.arknightsthespire.util.TextureLoader;

import java.util.Iterator;

//Gain 1 dex for the turn for each card played.

public class RapidMagPower extends AbstractPower implements CloneablePowerInterface {
    public AbstractCreature source;

    public static final String POWER_ID = "ats:Rapid Magazine";
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    // We create 2 new textures *Using This Specific Texture Loader* - an 84x84 image and a 32x32 one.
    // There's a fallback "missing texture" image, so the game shouldn't crash if you accidentally put a non-existent file.
    private static final Texture tex84 = TextureLoader.getTexture("atsImg/power/RapidMagazine_84.png");
    private static final Texture tex32 = TextureLoader.getTexture("atsImg/power/RapidMagazine_32.png");

    public RapidMagPower(final AbstractCreature owner, final AbstractCreature source, int amount) {
        name = NAME;
        ID = POWER_ID;

        this.owner = owner;
        this.source = source;
        this.amount = amount;

        type = PowerType.BUFF;
        isTurnBased = true;

        // We load those txtures here.
        this.region128 = new TextureAtlas.AtlasRegion(tex84, 0, 0, 84, 84);
        this.region48 = new TextureAtlas.AtlasRegion(tex32, 0, 0, 32, 32);

        updateDescription();

        Iterator var1 = AbstractDungeon.player.hand.group.iterator();

        while (var1.hasNext()) {
            AbstractCard c = (AbstractCard) var1.next();
            if(c instanceof CardSPBase) {
                CardSPBase card = (CardSPBase) c;
                if (card.position == PositionType.SNIPER && card.canUseSP == true) {
                    card.changeSpForBattle(-this.amount);
                    if(card.spCard != null) {
                        card.spCard.changeSpForBattle(-this.amount);
                    }
                }
            }
        }
        System.out.println(RapidMagPower.POWER_ID + " Applied Successfully.");
    }

    @Override
    public void onCardDraw(AbstractCard c) {
        if(c instanceof CardSPBase) {
            CardSPBase card = (CardSPBase) c;
            if (card.position == PositionType.SNIPER && card.canUseSP == true) {
                card.changeSpForBattle(-this.amount);
                if(card.spCard != null) {
                    card.spCard.changeSpForBattle(-this.amount);
                }
            }
        }
    }

    // Update the description when you apply this power. (i.e. add or remove an "s" in keyword(s))
    @Override
    public void updateDescription() {
            description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];
    }

    @Override
    public AbstractPower makeCopy() {
        return new RapidMagPower(owner, source, amount);
    }
}
