package com.ndc.arknightsthespire.power;

import basemod.interfaces.CloneablePowerInterface;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.ndc.arknightsthespire.SPHandler;
import com.ndc.arknightsthespire.cards.CardSPBase;
import com.ndc.arknightsthespire.cards.PositionType;
import com.ndc.arknightsthespire.util.TextureLoader;

//Gain 1 dex for the turn for each card played.

public class SoulAbsorption extends AbstractPower implements CloneablePowerInterface {
    public AbstractCreature source;
    public boolean isUped;

    public static final String POWER_ID = "Soul Absorption";
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    public static PositionType LAST_POSITION;

    // We create 2 new textures *Using This Specific Texture Loader* - an 84x84 image and a 32x32 one.
    // There's a fallback "missing texture" image, so the game shouldn't crash if you accidentally put a non-existent file.
    private static final Texture tex84 = TextureLoader.getTexture("img/power/placeholder_power84.png");
    private static final Texture tex32 = TextureLoader.getTexture("img/power/placeholder_power32.png");

    public SoulAbsorption(final AbstractCreature owner, final AbstractCreature source, boolean isUp) {
        name = NAME;
        ID = POWER_ID;
        isUped = isUp;

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
            LAST_POSITION = cSP.position;
        }
    }

    @Override
    public void onAttack(DamageInfo info, int damageAmount, AbstractCreature target) {
        if(!target.isPlayer && damageAmount > 0) {
            if(!isUped && LAST_POSITION == PositionType.CASTER) {
                CardSPBase.checkGainBlock(damageAmount);
                addToBot(new RemoveSpecificPowerAction(AbstractDungeon.player, AbstractDungeon.player, "Soul Absorption"));
            }
            else if(isUped) {
                CardSPBase.checkGainBlock(damageAmount);
                addToBot(new RemoveSpecificPowerAction(AbstractDungeon.player, AbstractDungeon.player, "Soul Absorption"));
            }
        }
    }

    // Update the description when you apply this power. (i.e. add or remove an "s" in keyword(s))
    @Override
    public void updateDescription() {
        if(isUped) description = DESCRIPTIONS[1];
        else description = DESCRIPTIONS[0];
    }

    @Override
    public void atEndOfTurn(boolean isPlayer) {
        if(isPlayer) addToBot(new RemoveSpecificPowerAction(AbstractDungeon.player, AbstractDungeon.player, "Soul Absorption"));
    }

    @Override
    public AbstractPower makeCopy() {
        return new SoulAbsorption(owner, source, isUped);
    }
}
