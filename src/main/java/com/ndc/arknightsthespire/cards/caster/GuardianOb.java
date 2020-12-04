package com.ndc.arknightsthespire.cards.caster;

import com.evacipated.cardcrawl.mod.stslib.actions.common.StunMonsterAction;
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

public class GuardianOb extends CardSPBase {
    public static final String ID = "ats:Guardian Obelisk";
    public static final String IMG_PATH = "img/cards/GuardianObelisk.png";
    public static final PositionType POSITION = PositionType.CASTER;
    private static final int COST = 0;
    private static final int DAMAGE = 10;
    private static final int UP_DAMAGE = 5;
    private static final int SP = 15;

    public GuardianOb() {
        super(ID, IMG_PATH, COST,
                CardType.ATTACK, CardColors.AbstractCardEnum.DOCTOR_COLOR,
                CardRarity.UNCOMMON, CardTarget.ENEMY, true, POSITION, true);
        this.damage = this.baseDamage = DAMAGE;
        this.sp = this.baseSP = SP;

        this.setBackgroundTexture("img/512/caster_512.png", "img/1024/caster.png");

        this.setOrbTexture("img/orbs/cost.png", "img/orbs/cost_small.png");

    }

    @Override
    public void useCard(AbstractPlayer p, AbstractMonster m, boolean isSpJustUsed) {
        if(isSpJustUsed) {
            addToBot(new DamageAction(m, new DamageInfo(p, this.damage, DamageType.NORMAL), AttackEffect.BLUNT_HEAVY));
            addToBot(new StunMonsterAction(m, p, 1));
        }
    }

    @Override
    public AbstractCard makeCopy() {
        return new GuardianOb();
    }

    @Override
    public void upgradeCard() {
        this.upgradeDamage(UP_DAMAGE);
    }

}
