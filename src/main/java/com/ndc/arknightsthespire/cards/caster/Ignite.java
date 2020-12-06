package com.ndc.arknightsthespire.cards.caster;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.ndc.arknightsthespire.CardColors;
import com.ndc.arknightsthespire.cards.base.CardSPBase;
import com.ndc.arknightsthespire.cards.base.PositionType;
import com.ndc.arknightsthespire.power.BurnPower;

public class Ignite extends CardSPBase {
    public static final String ID = "ats:Ignite";
    public static final String IMG_PATH = "img/cards/Ignite.png";
    public static final PositionType POSITION = PositionType.CASTER;
    private static final int COST = 0;
    private static final int DAMAGE = 10;
    private static final int SP = 5;
    private static final int UP_SP = 3;
    private static final int BURN = 2;
    private static final int UP_BURN = 1;

    public Ignite() {
        super(ID, IMG_PATH, COST,
                CardType.ATTACK, CardColors.AbstractCardEnum.DOCTOR_COLOR,
                CardRarity.UNCOMMON, CardTarget.ENEMY, true, POSITION, true, DAMAGE, 0, BURN, SP);
    }

    @Override
    public void useCard(AbstractPlayer p, AbstractMonster m, boolean isSpJustUsed) {
        if(isSpJustUsed) {
            addToBot(new DamageAction(m, new DamageInfo(p, this.damage), AbstractGameAction.AttackEffect.FIRE));
            addToBot(new ApplyPowerAction(m, p, new BurnPower(m, p, 2), 2));
        }
    }

    @Override
    public AbstractCard makeCopy() {
        return new Ignite();
    }

    @Override
    public void upgradeCard() {
        this.upgradeMagicNumber(UP_BURN);
        this.upgradeSP(UP_SP);
    }

}
