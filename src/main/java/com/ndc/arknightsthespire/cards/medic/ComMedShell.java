package com.ndc.arknightsthespire.cards.medic;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.ndc.arknightsthespire.CardColors;
import com.ndc.arknightsthespire.cards.base.CardSPBase;
import com.ndc.arknightsthespire.cards.base.PositionType;
import com.ndc.arknightsthespire.power.ComMedShellPower;

public class ComMedShell extends CardSPBase {
    public static final String ID = "ats:Composite Medical Shells";
    public static final String IMG_PATH = "atsImg/cards/ComMedShell.png";
    public static final PositionType POSITION = PositionType.MEDIC;
    private static final int COST = 1;

    public ComMedShell() {
        super(ID, IMG_PATH, COST,
                CardType.POWER, CardColors.AbstractCardEnum.DOCTOR_COLOR,
                CardRarity.UNCOMMON, CardTarget.SELF, POSITION, 0, 0, 10, 0);
    }

    @Override
    public void useCard(AbstractPlayer p, AbstractMonster m, boolean isSpJustUsed) {
        addToBot(new ApplyPowerAction(p, p, new ComMedShellPower(p, p, this.magicNumber)));
    }

    @Override
    public AbstractCard makeCopy() {
        return new ComMedShell();
    }

    @Override
    public void upgradeCard() {
        this.upgradeMagicNumber(5);
    }

}
