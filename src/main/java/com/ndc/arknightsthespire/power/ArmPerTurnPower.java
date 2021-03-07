package com.ndc.arknightsthespire.power;

import basemod.interfaces.CloneablePowerInterface;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.ndc.arknightsthespire.character.AtsEnum;
import com.ndc.arknightsthespire.util.TextureLoader;

//Gain 1 dex for the turn for each card played.

public class ArmPerTurnPower extends AbstractPower implements CloneablePowerInterface {
    public AbstractCreature source;

    public static final String POWER_ID = "ats:Arm Per Turn";
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    private boolean justApplied = false;

    public ArmPerTurnPower(final AbstractCreature owner, final AbstractCreature source, int amount, boolean isM) {
        name = NAME;
        ID = POWER_ID;

        this.owner = owner;
        this.source = source;
        this.amount = amount;
        this.justApplied = isM;
        this.canGoNegative = true;


        type = PowerType.BUFF;
        isTurnBased = true;

        // We load those txtures here.
        this.loadRegion("dexterity");

        updateDescription();
    }

    public ArmPerTurnPower(AbstractCreature owner, AbstractCreature source, int amount) {
        this(owner, source, amount, false);
    }

    public void stackPower(int stackAmount) {
        this.amount = Math.max(this.amount + stackAmount, -999);
        if(this.amount < 0) this.type = PowerType.DEBUFF;
        else this.type = PowerType.BUFF;
    }

    @Override
    public void atEndOfRound() {
        if (this.justApplied) {
            this.justApplied = false;
        } else {
            this.addToBot(new RemoveSpecificPowerAction(this.owner, this.owner, this.ID));
        }
    }

    @Override
    public void updateDescription() {
        description = DESCRIPTIONS[0] + this.amount + (isNegative() ? DESCRIPTIONS[2] : DESCRIPTIONS[1]);
        name = isNegative() ? DESCRIPTIONS[4] : DESCRIPTIONS[3];
    }

    public boolean isNegative() {
        return this.amount < 0;
    }

    @Override
    public AbstractPower makeCopy() {
        return new ArmPerTurnPower(owner, source, amount);
    }
}
