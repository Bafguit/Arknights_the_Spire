package com.ndc.arknightsthespire.cards.specialist;

import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.ndc.arknightsthespire.CardColors.AbstractCardEnum;
import com.ndc.arknightsthespire.actions.AtsSFX;
import com.ndc.arknightsthespire.cards.base.CardSPBase;
import com.ndc.arknightsthespire.cards.base.PositionType;

public class ElectricNet extends CardSPBase {
    public static final String ID = "ats:Electric Net";
    public static final String IMG_PATH = "atsImg/cards/ElectricNet.png";
    public static final PositionType POSITION = PositionType.SPECIALIST;
    private static final int COST = 1;
    private static final int DRAW = 3;
    private static final int UP_DRAW = 1;
    private static final int SP = 10;

    public ElectricNet() {
        super(ID, IMG_PATH, COST,
                CardType.SKILL, AbstractCardEnum.DOCTOR_COLOR,
                CardRarity.UNCOMMON, CardTarget.SELF, true, POSITION, true, 0, 0, DRAW, SP);
    }

    @Override
    public void useCard(AbstractPlayer p, AbstractMonster m, boolean isSpJustUsed) {
        addToBot(new AtsSFX("ROPE"));
        addToBot(new DrawCardAction(this.magicNumber));
        if(isSpJustUsed) {
            addToBot(new GainEnergyAction(1));
        }
    }

    @Override
    public AbstractCard makeCopy() {
        return new ElectricNet();
    }

    @Override
    public void upgradeCard() {
        this.upgradeMagicNumber(UP_DRAW);
    }

}
