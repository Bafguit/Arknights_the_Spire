package com.ndc.arknightsthespire.cards.specialist;

import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.ndc.arknightsthespire.CardColors.AbstractCardEnum;
import com.ndc.arknightsthespire.actions.CheckGainEnergy;
import com.ndc.arknightsthespire.cards.base.CardSPBase;
import com.ndc.arknightsthespire.cards.base.PositionType;

public class HookShot extends CardSPBase {
    public static final String ID = "ats:Hook Shot";
    public static final String IMG_PATH = "img/cards/HookShot.png";
    public static final PositionType POSITION = PositionType.SPECIALIST;
    private static final int COST = 1;
    private static final int UP_COST = 0;
    private static final int SP = 3;

    public HookShot() {
        super(ID, IMG_PATH, COST,
                CardType.SKILL, AbstractCardEnum.DOCTOR_COLOR,
                CardRarity.UNCOMMON, CardTarget.SELF, true, POSITION, true, 0, 0, 0, SP);
    }

    @Override
    public void useCard(AbstractPlayer p, AbstractMonster m, boolean isSpJustUsed) {
        addToBot(new DrawCardAction(3));
        if(isSpJustUsed) new CheckGainEnergy(1);
    }

    @Override
    public AbstractCard makeCopy() {
        return new HookShot();
    }

    @Override
    public void upgradeCard() {
        this.upgradeBaseCost(UP_COST);
    }

}
