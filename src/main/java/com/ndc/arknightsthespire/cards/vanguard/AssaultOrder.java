package com.ndc.arknightsthespire.cards.vanguard;

import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.ndc.arknightsthespire.CardColors;
import com.ndc.arknightsthespire.cards.base.CardSPBase;
import com.ndc.arknightsthespire.cards.base.PositionType;

public class AssaultOrder extends CardSPBase {
    public static final String ID = "ats:Assault Order";
    public static final String IMG_PATH = "atsImg/cards/AssaultOrder.png";
    public static final PositionType POSITION = PositionType.VANGUARD;
    private static final int COST = 0;
    private static final int SP = 10;
    private static final int UP_SP = 7;

    public AssaultOrder() {
        super(ID, IMG_PATH, COST,
                CardType.SKILL, CardColors.AbstractCardEnum.DOCTOR_COLOR,
                CardRarity.COMMON, CardTarget.SELF, true, POSITION, true, 0, 0, 0, SP);
    }

    @Override
    public void useCard(AbstractPlayer p, AbstractMonster m, boolean isSpJustUsed) {
        if(isSpJustUsed) addToBot(new GainEnergyAction(2));
    }

    @Override
    public AbstractCard makeCopy() {
        return new AssaultOrder();
    }

    @Override
    public void upgradeCard() {
        this.upgradeSP(UP_SP);
    }

}
