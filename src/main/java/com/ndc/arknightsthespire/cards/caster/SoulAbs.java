package com.ndc.arknightsthespire.cards.caster;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.ndc.arknightsthespire.CardColors;
import com.ndc.arknightsthespire.cards.base.CardSPBase;
import com.ndc.arknightsthespire.cards.base.PositionType;
import com.ndc.arknightsthespire.power.SoulAbsPower;

public class SoulAbs extends CardSPBase {
    public static final String ID = "ats:Soul Absorption";
    public static final String IMG_PATH = "atsImg/cards/SoulAbs.png";
    public static final PositionType POSITION = PositionType.CASTER;
    private static final int COST = 1;
    private static final int UP_COST = 0;

    public SoulAbs() {
        super(ID, IMG_PATH, COST,
                CardType.SKILL, CardColors.AbstractCardEnum.DOCTOR_COLOR,
                CardRarity.UNCOMMON, CardTarget.SELF, POSITION);
    }

    @Override
    public void useCard(AbstractPlayer p, AbstractMonster m, boolean isSpJustUsed) {
        addToBot(new ApplyPowerAction(p, p, new SoulAbsPower(p, p)));
    }

    @Override
    public AbstractCard makeCopy() {
        return new SoulAbs();
    }

    @Override
    public void upgradeCard() {
        this.upgradeBaseCost(UP_COST);
    }

}
