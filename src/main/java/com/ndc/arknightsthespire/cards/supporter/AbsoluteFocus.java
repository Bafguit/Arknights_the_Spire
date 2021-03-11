package com.ndc.arknightsthespire.cards.supporter;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import com.megacrit.cardcrawl.powers.WeakPower;
import com.ndc.arknightsthespire.CardColors;
import com.ndc.arknightsthespire.actions.SelectCardFromHandAction;
import com.ndc.arknightsthespire.cards.base.CardSPBase;
import com.ndc.arknightsthespire.cards.base.PositionType;

public class AbsoluteFocus extends CardSPBase {
    public static final String ID = "ats:Absolute Focus";
    public static final String IMG_PATH = "atsImg/cards/AbsoluteFocus.png";
    public static final PositionType POSITION = PositionType.SUPPORTER;
    private static final int COST = 0;
    private static final int NUM = 1;
    private static final int UP_NUM = 1;

    public AbsoluteFocus() {
        super(ID, IMG_PATH, COST,
                CardType.SKILL, CardColors.AbstractCardEnum.DOCTOR_COLOR,
                CardRarity.COMMON, CardTarget.SELF, POSITION, 0, 0, NUM, 0);
    }

    @Override
    public void useCard(AbstractPlayer p, AbstractMonster m, boolean isSpJustUsed) {
        addToBot(new SelectCardFromHandAction(this.upgraded ? 2 : 1));
    }

    @Override
    public AbstractCard makeCopy() {
        return new AbsoluteFocus();
    }

    @Override
    public void upgradeCard() {
        this.upgradeMagicNumber(UP_NUM);
    }



}
