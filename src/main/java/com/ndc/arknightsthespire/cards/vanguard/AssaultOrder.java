package com.ndc.arknightsthespire.cards.vanguard;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.ndc.arknightsthespire.CardColors;
import com.ndc.arknightsthespire.actions.CheckGainEnergy;
import com.ndc.arknightsthespire.cards.base.CardSPBase;
import com.ndc.arknightsthespire.cards.base.PositionType;

public class AssaultOrder extends CardSPBase {
    public static final String ID = "ats:Assault Order";
    public static final String IMG_PATH = "img/cards/AssaultOrder.png";
    public static final PositionType POSITION = PositionType.VANGUARD;
    private static final int COST = 0;
    private static final int SP = 5;
    private static final int UP_SP = 3;
    private static final int ENERGY = 2;

    public AssaultOrder() {
        super(ID, IMG_PATH, COST,
                CardType.SKILL, CardColors.AbstractCardEnum.DOCTOR_COLOR,
                CardRarity.COMMON, CardTarget.SELF, true, POSITION, true, 0, 0, 0, SP);
    }

    @Override
    public void useCard(AbstractPlayer p, AbstractMonster m, boolean isSpJustUsed) {
        if(isSpJustUsed) new CheckGainEnergy(2);
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
