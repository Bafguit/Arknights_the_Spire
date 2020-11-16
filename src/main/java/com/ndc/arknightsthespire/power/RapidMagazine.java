//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.ndc.arknightsthespire.power;


import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.AbstractPower.PowerType;
import java.util.Iterator;

public class RapidMagazine extends AbstractPower {
    public static final String POWER_ID = "Rapid Magazine";
    private static final PowerStrings powerStrings;
    public static final String NAME;
    public static final String[] DESCRIPTIONS;

    public RapidMagazine(AbstractCreature owner, int amount) {
        this.name = NAME;
        this.ID = POWER_ID;
        this.owner = owner;
        this.amount = amount;
        this.type = PowerType.BUFF;
        this.updateDescription();
        this.isTurnBased = true;
        this.loadRegion("flameBarrier");
    }

/*    public void onUseCard(AbstractCard card, UseCardAction action) {
        if (card instanceof abs_mku_card) {
            if (this.amount == 1) {
                squeenyUtils.atb(new RemoveSpecificPowerAction(this.owner, this.owner, this));
                Iterator var3 = squeenyUtils.p().powers.iterator();

                while(var3.hasNext()) {
                    AbstractPower p = (AbstractPower)var3.next();
                    if (p instanceof onLoseOverheatSubscriber) {
                        ((onLoseOverheatSubscriber)p).onLoseOverheat();
                    }
                }
            } else {
                squeenyUtils.atb(new ReducePowerAction(this.owner, this.owner, this, 1));
            }
        }

    }*/

    public void updateDescription() {
        if (this.amount == 1) {
            this.description = DESCRIPTIONS[0];
        } else {
            this.description = String.format(DESCRIPTIONS[1], this.amount);
        }

    }

    static {
        powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
        NAME = powerStrings.NAME;
        DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    }
}
