package com.ndc.arknightsthespire.power;

import basemod.interfaces.CloneablePowerInterface;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.RollMoveAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.BufferPower;
import com.megacrit.cardcrawl.powers.LoseStrengthPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.ndc.arknightsthespire.actions.AtsSFX;
import com.ndc.arknightsthespire.actions.PlayAnimationAction;
import com.ndc.arknightsthespire.actions.WaitAnimAction;
import com.ndc.arknightsthespire.monsters.act1.boss.Crown;
import com.ndc.arknightsthespire.util.TextureLoader;

import java.util.Random;

//Gain 1 dex for the turn for each card played.

public class EvadePower extends AbstractPower implements CloneablePowerInterface {
    public AbstractCreature source;

    public static final String POWER_ID = "ats:Evade";
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    private int str;
    private int cnt;

    public EvadePower(final AbstractCreature owner, int cnt, int str) {
        name = NAME;
        ID = POWER_ID;

        this.owner = owner;
        this.amount = 0;
        this.cnt = cnt;
        this.str = str;

        type = PowerType.BUFF;
        isTurnBased = true;

        this.loadRegion("blur");

        updateDescription();
    }

    @Override
    public int onAttackedToChangeDamage(DamageInfo info, int damageAmount) {
        if(damageAmount > 0 && info.type != DamageInfo.DamageType.HP_LOSS && info.type != DamageInfo.DamageType.THORNS && !this.owner.hasPower(BufferPower.POWER_ID)) {
            this.amount++;
            if(this.amount == this.cnt) {
                this.flash();
                this.addToBot(new ApplyPowerAction(this.owner, this.owner, new BufferPower(this.owner, 1), 1, true));
                this.addToBot(new ApplyPowerAction(this.owner, this.owner, new StrengthPower(this.owner, this.str), this.str, true));
                this.addToBot(new ApplyPowerAction(this.owner, this.owner, new CrStrPower(this.owner, this.str), this.str, true));
                this.amount = 0;
            }
        }
        return damageAmount;
    }

    // Update the description when you apply this power. (i.e. add or remove an "s" in keyword(s))
    @Override
    public void updateDescription() {
        description = DESCRIPTIONS[0] + this.cnt + DESCRIPTIONS[1] + this.str + DESCRIPTIONS[2];
    }

    @Override
    public AbstractPower makeCopy() {
        return new EvadePower(owner, amount, str);
    }
}
