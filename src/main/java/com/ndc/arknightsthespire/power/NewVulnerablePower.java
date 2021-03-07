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

public class NewVulnerablePower extends AbstractPower {
    public static final String POWER_ID = "Vulnerable";
    private static final PowerStrings powerStrings;
    public static final String NAME;
    public static final String[] DESCRIPTIONS;
    private boolean justApplied = false;
    private static final float EFFECTIVENESS = 1.5F;
    private static final int EFFECTIVENESS_STRING = 50;

    public NewVulnerablePower(AbstractCreature owner, int amount, boolean isSourceMonster) {
        this.name = NAME;
        this.ID = POWER_ID;
        this.owner = owner;
        this.amount = amount;
        this.updateDescription();
        this.loadRegion("vulnerable");
        if (AbstractDungeon.actionManager.turnHasEnded && isSourceMonster) {
            this.justApplied = true;
        }

        this.type = PowerType.DEBUFF;
        this.isTurnBased = true;
    }

    public void atEndOfRound() {
        if (this.justApplied) {
            this.justApplied = false;
        } else {
            if (this.amount == 0) {
                this.addToBot(new RemoveSpecificPowerAction(this.owner, this.owner, "Vulnerable"));
            } else {
                this.addToBot(new ReducePowerAction(this.owner, this.owner, "Vulnerable", 1));
            }

        }
    }

    public void updateDescription() {
        if (this.amount == 1) {
            if (this.owner != null && this.owner.isPlayer && AbstractDungeon.player.hasRelic("Odd Mushroom")) {
                this.description = DESCRIPTIONS[0] + 25 + DESCRIPTIONS[1] + this.amount + DESCRIPTIONS[2];
            } else if (this.owner != null && !this.owner.isPlayer && AbstractDungeon.player.hasRelic("Paper Frog")) {
                this.description = DESCRIPTIONS[0] + 75 + DESCRIPTIONS[1] + this.amount + DESCRIPTIONS[2];
            } else {
                this.description = DESCRIPTIONS[0] + 50 + DESCRIPTIONS[1] + this.amount + DESCRIPTIONS[2];
            }
        } else if (this.owner != null && this.owner.isPlayer && AbstractDungeon.player.hasRelic("Odd Mushroom")) {
            this.description = DESCRIPTIONS[0] + 25 + DESCRIPTIONS[1] + this.amount + DESCRIPTIONS[3];
        } else if (this.owner != null && !this.owner.isPlayer && AbstractDungeon.player.hasRelic("Paper Frog")) {
            this.description = DESCRIPTIONS[0] + 75 + DESCRIPTIONS[1] + this.amount + DESCRIPTIONS[3];
        } else {
            this.description = DESCRIPTIONS[0] + 50 + DESCRIPTIONS[1] + this.amount + DESCRIPTIONS[3];
        }

    }

    public float atDamageReceive(float damage, DamageType type) {
        if (type == DamageType.NORMAL || type == AtsEnum.ARTS || type == AtsEnum.PHYS) {
            if (this.owner.isPlayer && AbstractDungeon.player.hasRelic("Odd Mushroom")) {
                return damage * 1.25F;
            } else {
                return this.owner != null && !this.owner.isPlayer && AbstractDungeon.player.hasRelic("Paper Frog") ? damage * 1.75F : damage * 1.5F;
            }
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
