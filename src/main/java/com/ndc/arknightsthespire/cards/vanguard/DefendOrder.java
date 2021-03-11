package com.ndc.arknightsthespire.cards.vanguard;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.ndc.arknightsthespire.CardColors;
import com.ndc.arknightsthespire.cards.base.CardSPBase;
import com.ndc.arknightsthespire.cards.base.PositionType;
import com.ndc.arknightsthespire.power.DefendOrderPower;

public class DefendOrder extends CardSPBase {
    public static final String ID = "ats:Defend Order";
    public static final String IMG_PATH = "atsImg/cards/DefendOrder.png";
    public static final PositionType POSITION = PositionType.VANGUARD;
    private static final int COST = 1;
    private static final int BLOCK = 4;
    private static final int UP_BLOCK = 1;

    public DefendOrder() {
        super(ID, IMG_PATH, COST,
                CardType.POWER, CardColors.AbstractCardEnum.DOCTOR_COLOR,
                CardRarity.UNCOMMON, CardTarget.SELF, POSITION, 0, 0, BLOCK, 0);
    }

    @Override
    public void useCard(AbstractPlayer p, AbstractMonster m, boolean isSpJustUsed) {
        addToBot(new ApplyPowerAction(p, p, new DefendOrderPower(p, p, this.magicNumber), this.magicNumber));
    }

    @Override
    public AbstractCard makeCopy() {
        return new DefendOrder();
    }

    @Override
    public void upgradeCard() {
        this.upgradeMagicNumber(UP_BLOCK);
    }

}
