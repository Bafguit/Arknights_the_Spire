package com.ndc.arknightsthespire.cards.vanguard;

import com.evacipated.cardcrawl.mod.stslib.actions.common.StunMonsterAction;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.cards.red.Dropkick;
import com.megacrit.cardcrawl.cards.red.HeavyBlade;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.vfx.combat.VerticalImpactEffect;
import com.ndc.arknightsthespire.CardColors;
import com.ndc.arknightsthespire.actions.AtsSFX;
import com.ndc.arknightsthespire.actions.KillVanguard;
import com.ndc.arknightsthespire.cards.base.CardSPBase;
import com.ndc.arknightsthespire.cards.base.PositionType;

import java.util.Iterator;

public class SkullCrusher extends CardSPBase {
    public static final String ID = "ats:Skull Crusher";
    public static final String IMG_PATH = "atsImg/cards/SkullCrusher.png";
    public static final PositionType POSITION = PositionType.VANGUARD;
    private static final int COST = 2;
    private static final int DAMAGE = 14;
    private static final int UP_DAMAGE = 4;
    public boolean canStun = false;

    public SkullCrusher() {
        super(ID, IMG_PATH, COST,
                CardType.ATTACK, CardColors.AbstractCardEnum.DOCTOR_COLOR,
                CardRarity.UNCOMMON, CardTarget.ENEMY, false, POSITION, true, DAMAGE, 0, 3, 15);
    }

    public void triggerOnGlowCheck() {
        if(this.et >= 1) {
            if(shouldUseSp()) {
                this.glowColor = AbstractCard.GOLD_BORDER_GLOW_COLOR.cpy();
            }
            this.canStun = true;
        } else {
            this.glowColor = this.baseColor.cpy();
            this.canStun = false;
        }
    }

    public void applyPowers() {
        AbstractPower strength = AbstractDungeon.player.getPower("Strength");
        if (strength != null) {
            strength.amount *= this.magicNumber;
        }

        super.applyPowers();
        if (strength != null) {
            strength.amount /= this.magicNumber;
        }

    }

    public void calculateCardDamage(AbstractMonster mo) {
        AbstractPower strength = AbstractDungeon.player.getPower("Strength");
        if (strength != null) {
            strength.amount *= this.magicNumber;
        }

        super.calculateCardDamage(mo);
        if (strength != null) {
            strength.amount /= this.magicNumber;
        }

    }

    @Override
    public void useCard(AbstractPlayer p, AbstractMonster m, boolean isSpJustUsed) {
        if (m != null) {
            this.addToBot(new VFXAction(new VerticalImpactEffect(m.hb.cX + m.hb.width / 4.0F, m.hb.cY - m.hb.height / 4.0F)));
        }

        this.addToBot(new DamageAction(m, new DamageInfo(p, this.damage, DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.BLUNT_HEAVY));
        if(isSpJustUsed && this.canStun) {
            this.addToBot(new StunMonsterAction(m, p));
        }
    }

    @Override
    public AbstractCard makeCopy() {
        return new SkullCrusher();
    }

    @Override
    public void upgradeCard() {
        this.upgradeDamage(UP_DAMAGE);
    }

}
