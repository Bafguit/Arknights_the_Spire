package com.ndc.arknightsthespire.cards.sniper;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.LoseStrengthPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.ndc.arknightsthespire.CardColors;
import com.ndc.arknightsthespire.actions.ApplyAtkAction;
import com.ndc.arknightsthespire.cards.base.CardSPBase;
import com.ndc.arknightsthespire.cards.base.PositionType;

public class FlexCamouflage extends CardSPBase {
    public static final String ID = "ats:Flexible Camouflage";
    public static final String IMG_PATH = "img/cards/FlexibleCamouflage.png";
    public static final PositionType POSITION = PositionType.SNIPER;
    private static final int COST = 0;
    private static final int FLEX = 2;
    private static final int UP_FLEX = 2;

    public FlexCamouflage() {
        super(ID, IMG_PATH, COST,
                CardType.SKILL, CardColors.AbstractCardEnum.DOCTOR_COLOR,
                CardRarity.COMMON, CardTarget.SELF, POSITION, 0, 0, FLEX, 0);
    }

    @Override
    public void useCard(AbstractPlayer p, AbstractMonster m, boolean isSpJustUsed) {
        ApplyAtkAction.applyTurn(this.magicNumber);
    }

    @Override
    public AbstractCard makeCopy() {
        return new FlexCamouflage();
    }

    @Override
    public void upgradeCard() {
        this.upgradeMagicNumber(UP_FLEX);
    }

}
