package com.ndc.arknightsthespire.cards.vanguard;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.ndc.arknightsthespire.CardColors;
import com.ndc.arknightsthespire.cards.base.CardSPBase;
import com.ndc.arknightsthespire.cards.base.PositionType;
import com.ndc.arknightsthespire.power.OrderPower;

public class SupportOrder extends CardSPBase {
    public static final String ID = "ats:Support Order";
    public static final String IMG_PATH = "img/cards/SupportOrder.png";
    public static final PositionType POSITION = PositionType.VANGUARD;
    private static final int COST = 0;
    private static final int SP = 10;
    private static final int ORDER = 2;
    private static final int UP_ORDER = 1;

    public SupportOrder() {
        super(ID, IMG_PATH, COST,
                CardType.SKILL, CardColors.AbstractCardEnum.DOCTOR_COLOR,
                CardRarity.UNCOMMON, CardTarget.SELF, true, POSITION, true, 0, 0, ORDER, SP);
    }

    @Override
    public void useCard(AbstractPlayer p, AbstractMonster m, boolean isSpJustUsed) {
        if(isSpJustUsed) addToBot(new ApplyPowerAction(p, p, new OrderPower(p, p, this.magicNumber), this.magicNumber));
    }

    @Override
    public AbstractCard makeCopy() {
        return new SupportOrder();
    }

    @Override
    public void upgradeCard() {
        this.upgradeMagicNumber(UP_ORDER);
    }

}
