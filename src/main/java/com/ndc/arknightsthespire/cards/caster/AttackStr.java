package com.ndc.arknightsthespire.cards.caster;

import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.ndc.arknightsthespire.CardColors;
import com.ndc.arknightsthespire.cards.base.CardSPBase;
import com.ndc.arknightsthespire.cards.base.PositionType;

import static com.megacrit.cardcrawl.actions.AbstractGameAction.*;
import static com.megacrit.cardcrawl.cards.DamageInfo.*;

public class AttackStr extends CardSPBase {
    public static final String ID = "ats:Attack Strengthen";
    public static final String IMG_PATH = "img/cards/AttackStr.png";
    public static final PositionType POSITION = PositionType.CASTER;
    private static final int COST = 0;
    private static final int DAMAGE = 6;
    private static final int UP_DAMAGE = 2;
    private static final int SP = 3;
    private static final int UP_SP = 0;

    public AttackStr() {
        super(ID, IMG_PATH, COST,
                CardType.ATTACK, CardColors.AbstractCardEnum.DOCTOR_COLOR,
                CardRarity.COMMON, CardTarget.ENEMY, true, POSITION, true);
        this.damage = this.baseDamage = DAMAGE;
        this.sp = this.baseSP = SP;

        this.setBackgroundTexture("img/512/caster_512.png", "img/1024/caster.png");

        this.setOrbTexture("img/orbs/cost.png", "img/orbs/cost_small.png");

    }

    @Override
    public void useCard(AbstractPlayer p, AbstractMonster m, boolean isSpJustUsed) {
        if(isSpJustUsed) {
            addToBot(new DamageAction(m, new DamageInfo(p, this.damage, DamageType.NORMAL), AttackEffect.LIGHTNING));
        }
    }

    @Override
    public AbstractCard makeCopy() {
        return new AttackStr();
    }

    @Override
    public void upgradeCard() {
        this.upgradeDamage(UP_DAMAGE);
        this.upgradeSP(UP_SP);
    }

}
