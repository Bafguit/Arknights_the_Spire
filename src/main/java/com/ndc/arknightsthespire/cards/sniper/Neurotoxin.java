package com.ndc.arknightsthespire.cards.sniper;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.ndc.arknightsthespire.CardColors;
import com.ndc.arknightsthespire.cards.base.CardSPBase;
import com.ndc.arknightsthespire.cards.base.PositionType;
import com.ndc.arknightsthespire.power.NeurotoxinPower;

public class Neurotoxin extends CardSPBase {
    public static final String ID = "ats:Neurotoxin";
    public static final String IMG_PATH = "atsImg/cards/Neurotoxin.png";
    public static final PositionType POSITION = PositionType.SNIPER;
    private static final int COST = 1;
    private static final int POISON = 2;
    private static final int UP_POISON = 1;

    public Neurotoxin() {
        super(ID, IMG_PATH, COST,
                CardType.POWER, CardColors.AbstractCardEnum.DOCTOR_COLOR,
                CardRarity.UNCOMMON, CardTarget.SELF, POSITION, 0, 0, POISON, 0);
    }

    @Override
    public void useCard(AbstractPlayer p, AbstractMonster m, boolean isSpJustUsed) {
        addToBot(new ApplyPowerAction(p, p, new NeurotoxinPower(p, p, this.magicNumber), this.magicNumber));
    }

    @Override
    public AbstractCard makeCopy() {
        return new Neurotoxin();
    }

    @Override
    public void upgradeCard() {
        this.upgradeMagicNumber(UP_POISON);
    }

}
