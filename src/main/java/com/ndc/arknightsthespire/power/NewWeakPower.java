//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.ndc.arknightsthespire.power;

import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo.DamageType;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.AbstractPower.PowerType;
import com.ndc.arknightsthespire.character.AtsEnum;

public class NewWeakPower extends AbstractPower {
    public static final String POWER_ID = "Weakened";
    private static final PowerStrings powerStrings;
    public static final String NAME;
    public static final String[] DESCRIPTIONS;
    private boolean justApplied = false;
    private static final int EFFECTIVENESS_STRING = 25;

    public NewWeakPower(AbstractCreature owner, int amount, boolean isSourceMonster) {
        this.name = NAME;
        this.ID = POWER_ID;
        this.owner = owner;
        this.amount = amount;
        this.updateDescription();
        this.loadRegion("weak");
        if (isSourceMonster) {
            this.justApplied = true;
        }

        this.type = PowerType.DEBUFF;
        this.isTurnBased = true;
        this.priority = 99;
    }

    public void atEndOfRound() {
        if (this.justApplied) {
            this.justApplied = false;
        } else {
            if (this.amount == 0) {
                this.addToBot(new RemoveSpecificPowerAction(this.owner, this.owner, "Weakened"));
            } else {
                this.addToBot(new ReducePowerAction(this.owner, this.owner, "Weakened", 1));
            }

        }
    }

    public void updateDescription() {
        if (this.amount == 1) {
            if (this.owner != null && !this.owner.isPlayer && AbstractDungeon.player.hasRelic("Paper Crane")) {
                this.description = DESCRIPTIONS[0] + 40 + DESCRIPTIONS[1] + this.amount + DESCRIPTIONS[2];
            } else {
                this.description = DESCRIPTIONS[0] + 25 + DESCRIPTIONS[1] + this.amount + DESCRIPTIONS[2];
            }
        } else if (this.owner != null && !this.owner.isPlayer && AbstractDungeon.player.hasRelic("Paper Crane")) {
            this.description = DESCRIPTIONS[0] + 40 + DESCRIPTIONS[1] + this.amount + DESCRIPTIONS[3];
        } else {
            this.description = DESCRIPTIONS[0] + 25 + DESCRIPTIONS[1] + this.amount + DESCRIPTIONS[3];
        }

    }

    public float atDamageGive(float damage, DamageType type) {
        if (type == DamageType.NORMAL || type == AtsEnum.ARTS || type == AtsEnum.PHYS) {
            return !this.owner.isPlayer && AbstractDungeon.player.hasRelic("Paper Crane") ? damage * 0.6F : damage * 0.75F;
        } else {
            return damage;
        }
    }

    static {
        powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
        NAME = powerStrings.NAME;
        DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    }
}
