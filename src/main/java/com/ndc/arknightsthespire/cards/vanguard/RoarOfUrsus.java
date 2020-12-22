package com.ndc.arknightsthespire.cards.vanguard;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.ndc.arknightsthespire.CardColors;
import com.ndc.arknightsthespire.cards.base.CardSPBase;
import com.ndc.arknightsthespire.cards.base.PositionType;
import com.ndc.arknightsthespire.power.UrsusPower;

public class RoarOfUrsus extends CardSPBase {
    public static final String ID = "ats:Roar Of Ursus";
    public static final String IMG_PATH = "img/cards/RoarOfUrsus.png";
    public static final PositionType POSITION = PositionType.VANGUARD;
    private static final int COST = 1;
    private static final int UP_COST = 0;

    public RoarOfUrsus() {
        super(ID, IMG_PATH, COST,
                CardType.POWER, CardColors.AbstractCardEnum.DOCTOR_COLOR,
                CardRarity.RARE, CardTarget.SELF, POSITION);
    }

    @Override
    public void useCard(AbstractPlayer p, AbstractMonster m, boolean isSpJustUsed) {
        addToBot(new ApplyPowerAction(p, p, new UrsusPower(p, p)));
    }

    @Override
    public AbstractCard makeCopy() {
        return new RoarOfUrsus();
    }

    @Override
    public void upgradeCard() {
        this.upgradeBaseCost(UP_COST);
        this.isInnate = true;
    }

}