//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.ndc.arknightsthespire.power;

import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.AbstractCard.CardType;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.monsters.beyond.WrithingMass;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.AbstractPower.PowerType;
import com.megacrit.cardcrawl.powers.ReactivePower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.ndc.arknightsthespire.monsters.act3.boss.Patirot;

public class ForwardingPower extends AbstractPower {
    public static final String POWER_ID = "ats:Forwarding";
    private static final PowerStrings powerStrings;
    public static final String NAME;
    public static final String[] DESCRIPTIONS;
    public boolean isAttack = false;

    public ForwardingPower(AbstractCreature owner, int amount) {
        this.name = NAME;
        this.ID = POWER_ID;
        this.owner = owner;
        this.amount = amount;
        this.updateDescription();
        this.loadRegion("backAttack2");
        this.type = PowerType.BUFF;
    }

    public void updateDescription() {
        this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];
    }

    public void onUseCard(AbstractCard card, UseCardAction action) {
        if (card.baseBlock > 0) {
            if(this.owner instanceof Patirot) {
                Patirot m = (Patirot) this.owner;
                if(!this.isAttack) {
                    this.flash();
                    m.isBlockLast = true;
                    m.rollMove();
                    this.isAttack = true;
                }
            }
        }
    }

    @Override
    public void atStartOfTurn() {
    }

    @Override
    public void atEndOfRound() {
        if(!this.isAttack) {
            if (this.amount < 2) {
                if (this.owner instanceof Patirot) {
                    Patirot m = (Patirot) this.owner;
                    m.isBlockLast = true;
                    this.addToBot(new RollMoveAction(m));
                }
                this.addToBot(new RemoveSpecificPowerAction(this.owner, this.owner, this));
            } else {
                this.amount--;
                this.updateDescription();
            }
        }
        this.isAttack = false;
    }

    static {
        powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
        NAME = powerStrings.NAME;
        DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    }
}
