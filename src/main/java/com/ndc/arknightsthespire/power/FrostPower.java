package com.ndc.arknightsthespire.power;

import basemod.interfaces.CloneablePowerInterface;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.*;

//Gain 1 dex for the turn for each card played.

public class FrostPower extends AbstractPower implements CloneablePowerInterface {
    public AbstractCreature source;

    public static final String POWER_ID = "ats:Frost";
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    public boolean isRevived = false;

    public FrostPower(final AbstractCreature owner, int amount, boolean isUp) {
        name = NAME;
        ID = POWER_ID;

        this.owner = owner;
        this.amount = amount;
        this.isRevived = isUp;

        type = PowerType.BUFF;
        isTurnBased = true;

        this.loadRegion("noPain");

        updateDescription();
    }

    @Override
    public int onAttacked(DamageInfo info, int damageAmount) {
        if(info.owner != this.owner && info.type != DamageInfo.DamageType.THORNS && info.type != DamageInfo.DamageType.HP_LOSS) {
            flash();
            AbstractPower tp = this.owner.getPower("Artifact");
            this.addToBot(new ApplyPowerAction(info.owner, this.owner, new StrengthPower(info.owner, -this.amount), -this.amount));
            if (tp == null) {
                this.addToBot(new ApplyPowerAction(info.owner, this.owner, new LoseAtkPower(info.owner, this.amount), this.amount));
                if(this.isRevived) {
                    this.addToBot(new ApplyPowerAction(info.owner, this.owner, new DexterityPower(info.owner, -this.amount), -this.amount));
                    this.addToBot(new ApplyPowerAction(info.owner, this.owner, new LoseDefPower(info.owner, this.amount), this.amount));
                }
            } else if(this.isRevived) {
                this.addToBot(new ApplyPowerAction(info.owner, this.owner, new DexterityPower(info.owner, -this.amount), -this.amount));
                if(tp.amount < 2) {
                    this.addToBot(new ApplyPowerAction(info.owner, this.owner, new LoseDefPower(info.owner, this.amount), this.amount));
                }
            }
        }
        return damageAmount;
    }

    // Update the description when you apply this power. (i.e. add or remove an "s" in keyword(s))
    @Override
    public void updateDescription() {
        description = DESCRIPTIONS[0] + this.amount + (this.isRevived ? DESCRIPTIONS[2] : DESCRIPTIONS[1]);
    }

    @Override
    public AbstractPower makeCopy() {
        return new FrostPower(owner, amount, isRevived);
    }
}
