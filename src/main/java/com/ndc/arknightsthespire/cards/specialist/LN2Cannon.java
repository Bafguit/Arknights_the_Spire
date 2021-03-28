package com.ndc.arknightsthespire.cards.specialist;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.common.DiscardAction;
import com.megacrit.cardcrawl.actions.unique.ExhaustAllNonAttackAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.ndc.arknightsthespire.CardColors.AbstractCardEnum;
import com.ndc.arknightsthespire.actions.AttackPerNonAttackCard;
import com.ndc.arknightsthespire.cards.base.CardSPBase;
import com.ndc.arknightsthespire.cards.base.PositionType;

public class LN2Cannon extends CardSPBase {
    public static final String ID = "ats:LN2 Cannon";
    public static final String IMG_PATH = "atsImg/cards/LN2Cannon.png";
    public static final PositionType POSITION = PositionType.SPECIALIST;
    private static final int COST = 2;
    private static final int D = 5;
    private static final int U_D = 2;

    public LN2Cannon() {
        super(ID, IMG_PATH, COST,
                CardType.ATTACK, AbstractCardEnum.DOCTOR_COLOR,
                CardRarity.UNCOMMON, CardTarget.ALL_ENEMY, POSITION, D, 0, 0, 0);
        this.isMultiDamage = true;
    }

    @Override
    public void useCard(AbstractPlayer p, AbstractMonster m, boolean isSpJustUsed) {
        addToBot(new AttackPerNonAttackCard(this.multiDamage, "HYDPUMP"));
    }

    @Override
    public AbstractCard makeCopy() {
        return new LN2Cannon();
    }

    @Override
    public void upgradeCard() {
        this.upgradeDamage(U_D);
    }

}
