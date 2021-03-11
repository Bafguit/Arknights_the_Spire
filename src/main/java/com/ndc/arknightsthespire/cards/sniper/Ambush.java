package com.ndc.arknightsthespire.cards.sniper;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.ndc.arknightsthespire.CardColors;
import com.ndc.arknightsthespire.cards.base.CardSPBase;
import com.ndc.arknightsthespire.cards.base.PositionType;
import com.ndc.arknightsthespire.power.AmbushPower;

public class Ambush extends CardSPBase {
    public static final String ID = "ats:Ambush";
    public static final String IMG_PATH = "atsImg/cards/Ambush.png";
    public static final PositionType POSITION = PositionType.SNIPER;
    private static final int COST = 1;
    private static final int EVADE = 40;
    private static final int UP_EVADE = 20;

    public Ambush() {
        super(ID, IMG_PATH, COST,
                CardType.POWER, CardColors.AbstractCardEnum.DOCTOR_COLOR,
                CardRarity.RARE, CardTarget.SELF, POSITION, 0, 0, EVADE, 0);
        this.isInnate = true;
    }

    @Override
    public void useCard(AbstractPlayer p, AbstractMonster m, boolean isSpJustUsed) {
        addToBot(new ApplyPowerAction(p, p, new AmbushPower(p, p, this.magicNumber)));
    }

    @Override
    public AbstractCard makeCopy() {
        return new Ambush();
    }

    @Override
    public void upgradeCard() {
        this.upgradeMagicNumber(UP_EVADE);
    }

}
