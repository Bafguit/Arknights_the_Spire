package com.ndc.arknightsthespire.cards.common;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.LoseStrengthPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.ndc.arknightsthespire.CardColors;
import com.ndc.arknightsthespire.cards.CardSPBase;
import com.ndc.arknightsthespire.cards.PositionType;

public class CardSniperFlexCamouflage extends CardSPBase {
    public static final String ID = "Flexible Camouflage";
    public static final String IMG_PATH = "img/cards/FlexibleCamouflage.png";
    public static final PositionType POSITION = PositionType.SNIPER;
    private static final int COST = 0;
    private static final int FLEX = 1;
    private static final int UP_FLEX = 1;

    public CardSniperFlexCamouflage() {
        super(ID, IMG_PATH, COST,
                CardType.SKILL, CardColors.AbstractCardEnum.DOCTOR_COLOR,
                CardRarity.COMMON, CardTarget.SELF, false, POSITION, false);

        this.setBackgroundTexture("img/512/sniper_512.png", "img/1024/sniper.png");

        this.setOrbTexture("img/orbs/cost.png", "img/orbs/cost_small.png");

        this.magicNumber = this.baseMagicNumber = FLEX;

    }

    @Override
    public void useCard(AbstractPlayer p, AbstractMonster m, boolean isSpJustUsed) {
        addToBot(new ApplyPowerAction(p, p, new StrengthPower(p, this.magicNumber), this.magicNumber));
        addToBot(new ApplyPowerAction(p, p, new LoseStrengthPower(p, this.magicNumber), this.magicNumber));
    }

    @Override
    public AbstractCard makeCopy() {
        return new CardSniperFlexCamouflage();
    }

    @Override
    public void upgradeCard() {
        this.upgradeMagicNumber(UP_FLEX);
    }

}
