package com.ndc.arknightsthespire.power;

import basemod.interfaces.CloneablePowerInterface;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.ndc.arknightsthespire.cards.CardSPBase;
import com.ndc.arknightsthespire.cards.PositionType;
import com.ndc.arknightsthespire.util.TextureLoader;

//Gain 1 dex for the turn for each card played.

public class Fate extends AbstractPower implements CloneablePowerInterface {
    public AbstractCreature source;

    public static final String POWER_ID = "Fate";
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    // We create 2 new textures *Using This Specific Texture Loader* - an 84x84 image and a 32x32 one.
    // There's a fallback "missing texture" image, so the game shouldn't crash if you accidentally put a non-existent file.
    private static final Texture tex84 = TextureLoader.getTexture("img/power/Fate_84.png");
    private static final Texture tex32 = TextureLoader.getTexture("img/power/Fate_32.png");
    private static PositionType lastCardPosition = PositionType.GUARD;

    public Fate(final AbstractCreature owner, final AbstractCreature source) {
        name = NAME;
        ID = POWER_ID;

        this.owner = owner;
        this.source = source;

        type = PowerType.BUFF;
        isTurnBased = true;

        // We load those txtures here.
        this.region128 = new TextureAtlas.AtlasRegion(tex84, 0, 0, 84, 84);
        this.region48 = new TextureAtlas.AtlasRegion(tex32, 0, 0, 32, 32);

        updateDescription();
    }

    @Override
    public void onUseCard(AbstractCard card, UseCardAction action) {
        AbstractCard c = card;
        if(c instanceof CardSPBase) {
            CardSPBase cSP = (CardSPBase) c;
            if(cSP.position == PositionType.CASTER && lastCardPosition == PositionType.CASTER) {
                flash();
                addToBot(new DrawCardAction(1));
            }
            lastCardPosition = cSP.position;
        }
    }

    @Override
    public void atEndOfTurn(boolean isPlayer) {
        if(isPlayer) lastCardPosition = PositionType.GUARD;
    }

    @Override
    public void onDeath() {
        lastCardPosition = PositionType.GUARD;
    }

    @Override
    public void onVictory() {
        lastCardPosition = PositionType.GUARD;
    }

    // Update the description when you apply this power. (i.e. add or remove an "s" in keyword(s))
    @Override
    public void updateDescription() {
        description = DESCRIPTIONS[0] + 1 + DESCRIPTIONS[1];
    }

    @Override
    public AbstractPower makeCopy() {
        return new Fate(owner, source);
    }
}
