package com.ndc.arknightsthespire.power;

import basemod.interfaces.CloneablePowerInterface;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.brashmonkey.spriter.Player;
import com.evacipated.cardcrawl.mod.stslib.powers.abstracts.TwoAmountPower;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.DexterityPower;
import com.ndc.arknightsthespire.cards.base.CardSPBase;
import com.ndc.arknightsthespire.character.AtsEnum;
import com.ndc.arknightsthespire.util.DamageT;
import com.ndc.arknightsthespire.util.TextureLoader;

import static com.megacrit.cardcrawl.cards.DamageInfo.*;

//Gain 1 dex for the turn for each card played.

public class ArmourPower extends AbstractPower implements CloneablePowerInterface {
    public AbstractCreature source;

    public static final String POWER_ID = "ats:Armour";
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    public int amount2 = 0;
    protected Color aColor = Color.YELLOW.cpy();
    protected Color rColor = Color.CYAN.cpy();

    public ArmourPower(final AbstractCreature owner, int armour, int resistance) {
        name = NAME;
        ID = POWER_ID;

        this.owner = owner;
        this.amount = resistance;
        this.amount2 = armour;

        type = AtsEnum.ATS_BASE;
        isTurnBased = true;

        this.loadRegion("dexterity");

        updateDescription();

    }

    public void stack(int aStack, int rStack) {
        this.amount = Math.min(Math.max(this.amount + rStack, 0), 100);
        this.amount2 = Math.max(this.amount2 + aStack, 0);
    }

    @Override
    public void update(int a) {
        this.updateDescription();
    }

    public int getAmount(boolean isRes) {
        if(isRes) return Math.min(Math.max(Math.round(getFinalResistance()), 0), 100);
        else return Math.max(Math.round(getFinalArmour()), 0);
    }

    @Override
    public int onAttackedToChangeDamage(DamageInfo info, int damage) {

        if(info.type == AtsEnum.ARTS) {
            float dm = (float) damage * ((float)(100 - this.getAmount(true)) / 100.0F);
            return Math.round(Math.max(dm, 1.0F));
        } else if(info.type == AtsEnum.PHYS || info.type == DamageType.NORMAL) {
            return Math.max(damage - this.getAmount(false), 1);
        }
        /*
        if(!this.owner.isPlayer && info.type != DamageType.HP_LOSS) {
            if(this.owner instanceof AbstractMonster) {
                AbstractMonster.Intent i = ((AbstractMonster) this.owner).intent;
                AbstractPlayer p = AbstractDungeon.player;
                if(p.hasPower(this.ID)) {
                    if(p.getPower(this.ID) instanceof ArmourPower) {
                        if(i == AtsEnum.ATTACK_ARTS || i == AtsEnum.ATTACK_ARTS_BUFF || i == AtsEnum.ATTACK_ARTS_DEBUFF) {
                            float dm = damage * ((100.0F - (float) ((ArmourPower) p.getPower(this.ID)).getAmount(true)) / 100.0F);
                            return Math.max(dm, 1.0F);
                        } else if(i == AbstractMonster.Intent.ATTACK || i == AbstractMonster.Intent.ATTACK_BUFF || i == AbstractMonster.Intent.ATTACK_DEBUFF || i == AbstractMonster.Intent.ATTACK_DEFEND) {
                            return Math.max(damage - ((ArmourPower) p.getPower(this.ID)).getAmount(false), 1.0F);
                        }

                    }
                }
            }
        }*/
        return damage;
    }
/*
    @Override
    public float atDamageReceive(float damage, DamageType damageType, AbstractCard card) {
        if(!this.owner.isPlayer) {
            if (card instanceof CardSPBase) {
                CardSPBase c = (CardSPBase) card;
                if (damageType == DamageType.NORMAL && (int) damage >= 1) {
                    if (c.dmType.equals(AtsEnum.DamageType.ARTS)) {
                        float dm = damage * ((100.0F - (float) this.getAmount(true)) / 100.0F);
                        return this.atDamageReceive(Math.max(dm, 1.0F), damageType);
                    } else {
                        return this.atDamageReceive(Math.max(damage - this.getAmount(false), 1.0F), damageType);
                    }
                }
            }
        }

        return this.atDamageReceive(damage,damageType);
    }
*/
    public void renderAmount(SpriteBatch sb, float x, float y, Color c) {
        FontHelper.renderFontRightTopAligned(sb, FontHelper.powerAmountFont, Integer.toString(getAmount(true)), x, y, this.fontScale, rColor);
        FontHelper.renderFontRightTopAligned(sb, FontHelper.powerAmountFont, Integer.toString(getAmount(false)), x, y + 15.0F * Settings.scale, this.fontScale, aColor);
    }

    public float getFinalArmour() {
        int pa = 100;
        int a = 0;
        if(this.owner.hasPower(ArmPerTurnPower.POWER_ID)) {
            pa += this.owner.getPower(ArmPerTurnPower.POWER_ID).amount;
        }
        if(this.owner.hasPower(ArmTurnPower.POWER_ID)) {
            a += this.owner.getPower(ArmTurnPower.POWER_ID).amount;
        }
        return this.amount2 * ((float)pa / 100.0F) + a;
    }

    public float getFinalResistance() {
        int pa = 100;
        int a = 0;
        if(this.owner.hasPower(ResPerTurnPower.POWER_ID)) {
            pa += this.owner.getPower(ResPerTurnPower.POWER_ID).amount;
        }
        if(this.owner.hasPower(ResTurnPower.POWER_ID)) {
            a += this.owner.getPower(ResTurnPower.POWER_ID).amount;
        }
        return this.amount + a * ((float)pa / 100.0F);
    }

    public String getArmString() {
        String des = "";
        if(this.owner.hasPower(ArmPerTurnPower.POWER_ID) || this.owner.hasPower(ArmTurnPower.POWER_ID)) {
            des = " (" + this.amount2;
            if(this.owner.hasPower(ArmPerTurnPower.POWER_ID)) {
                des += " x " + (100 + this.owner.getPower(ArmPerTurnPower.POWER_ID).amount) + "%";
            }
            if(this.owner.hasPower(ArmTurnPower.POWER_ID)) {
                des += (this.owner.getPower(ArmTurnPower.POWER_ID).amount >= 0 ? " + " : " - ") + this.owner.getPower(ArmTurnPower.POWER_ID).amount;
            }
            des += ")";
        }

        return des;
    }

    public String getResString() {
        String des = "";
        if(this.owner.hasPower(ArmPerTurnPower.POWER_ID) || this.owner.hasPower(ArmTurnPower.POWER_ID)) {
            des = " (" + this.amount;
            if(this.owner.hasPower(ResTurnPower.POWER_ID)) {
                des += (this.owner.getPower(ResTurnPower.POWER_ID).amount >= 0 ? " + " : " - ") + this.owner.getPower(ResTurnPower.POWER_ID).amount;
            }
            if(this.owner.hasPower(ResPerTurnPower.POWER_ID)) {
                des += " x " + (100 + this.owner.getPower(ResPerTurnPower.POWER_ID).amount) + "%";
            }
            des += ")";
        }

        return des;
    }

    // Update the description when you apply this power. (i.e. add or remove an "s" in keyword(s))
    @Override
    public void updateDescription() {
        description = DESCRIPTIONS[0] + this.getAmount(false) + getArmString() + DESCRIPTIONS[1] + this.getAmount(true) + "%" + getResString() + DESCRIPTIONS[2];
    }

    @Override
    public AbstractPower makeCopy() {
        return new ArmourPower(owner, amount, amount2);
    }
}
