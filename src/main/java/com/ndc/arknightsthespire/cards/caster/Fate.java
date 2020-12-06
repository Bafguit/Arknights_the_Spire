package com.ndc.arknightsthespire.cards.caster;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.ndc.arknightsthespire.CardColors;
import com.ndc.arknightsthespire.cards.base.CardSPBase;
import com.ndc.arknightsthespire.cards.base.PositionType;
import com.ndc.arknightsthespire.power.FatePower;

public class Fate extends CardSPBase {
    public static final String ID = "ats:Fate";
    public static final String IMG_PATH = "img/cards/Fate.png";
    public static final PositionType POSITION = PositionType.CASTER;
    private static final int COST = 3;
    private static final int UP_COST = 2;

    public Fate() {
        super(ID, IMG_PATH, COST,
                CardType.POWER, CardColors.AbstractCardEnum.DOCTOR_COLOR,
                CardRarity.RARE, CardTarget.SELF, POSITION);
    }

    @Override
    public void useCard(AbstractPlayer p, AbstractMonster m, boolean isSpJustUsed) {
        addToBot(new ApplyPowerAction(p, p, new FatePower(p, p)));
    }

    @Override
    public AbstractCard makeCopy() {
        return new Fate();
    }

    @Override
    public void upgradeCard() {
        this.upgradeBaseCost(UP_COST);
    }

}
