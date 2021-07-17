package com.ndc.arknightsthespire.power;

import basemod.interfaces.CloneablePowerInterface;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.DoubleDamagePower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.ndc.arknightsthespire.cards.base.CardSPBase;
import com.ndc.arknightsthespire.character.AtsEnum;

import javax.smartcardio.Card;

import java.util.Iterator;

import static com.megacrit.cardcrawl.cards.DamageInfo.DamageType;

//Gain 1 dex for the turn for each card played.

public class AttackPower extends AbstractPower implements CloneablePowerInterface {
    public AbstractCreature source;

    public static final String POWER_ID = "ats:Attack";
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    public int charAtk = 6;
    public int baseAmount;

    public AttackPower(final AbstractCreature owner, int attack) {
        name = NAME;
        ID = POWER_ID;

        this.owner = owner;
        this.baseAmount = attack;
        this.amount = attack;

        type = AtsEnum.ATS_BASE;
        isTurnBased = true;

        this.loadRegion("strength");

        updateDescription();
    }

    public void stack(int amount) {
        this.baseAmount = Math.max(this.amount + amount, 1);
    }

    @Override
    public void update(int a) {
        this.updateDescription();
        this.amount = getAmount();
        this.charAtk = this.amount;
    }

    public int getAmount() {
        return Math.max(Math.round(getFinalAttack()), 1);
    }

    public float getFinalAttack() {
        int pa = 100;
        int a = 0;
        if(this.owner.hasPower(AtkPerTurnPower.POWER_ID)) {
            pa += this.owner.getPower(AtkPerTurnPower.POWER_ID).amount;
        }
        if(this.owner.hasPower(SeriousPower.POWER_ID)) {
            pa += this.owner.getPower(SeriousPower.POWER_ID).amount;
        }
        if(this.owner.hasPower(DoubleDamagePower.POWER_ID)) {
            pa += 100;
        }
        if(this.owner.hasPower(AtkTurnPower.POWER_ID)) {
            a += this.owner.getPower(AtkTurnPower.POWER_ID).amount;
        }
        return this.baseAmount * ((float)pa / 100.0F) + a;
    }

    public String getAtkString() {
        String des = "";
        if(this.owner.hasPower(AtkPerTurnPower.POWER_ID) || this.owner.hasPower(SeriousPower.POWER_ID) || this.owner.hasPower(AtkTurnPower.POWER_ID)) {
            int pa = 100;
            des = " (" + this.baseAmount;
            if (this.owner.hasPower(AtkPerTurnPower.POWER_ID) || this.owner.hasPower(SeriousPower.POWER_ID)) {
                if (this.owner.hasPower(AtkPerTurnPower.POWER_ID)) {
                    pa += this.owner.getPower(AtkPerTurnPower.POWER_ID).amount;
                }
                if (this.owner.hasPower(SeriousPower.POWER_ID)) {
                    pa += this.owner.getPower(SeriousPower.POWER_ID).amount;
                }
                if(this.owner.hasPower(DoubleDamagePower.POWER_ID)) {
                    pa += 100;
                }
                des += " x " + pa + "%";
            }
            if (this.owner.hasPower(AtkTurnPower.POWER_ID)) {
                des += (this.owner.getPower(AtkTurnPower.POWER_ID).amount >= 0 ? " + " : " - ") + this.owner.getPower(AtkTurnPower.POWER_ID).amount;
            }
            des += ")";
        }

        return des;
    }

    // Update the description when you apply this power. (i.e. add or remove an "s" in keyword(s))
    @Override
    public void updateDescription() {
        description = DESCRIPTIONS[0] + this.getAmount() + getAtkString() + DESCRIPTIONS[1];
    }

    @Override
    public AbstractPower makeCopy() {
        return new AttackPower(owner, amount);
    }
}
