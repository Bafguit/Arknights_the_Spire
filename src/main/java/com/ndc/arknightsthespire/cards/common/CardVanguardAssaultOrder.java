package com.ndc.arknightsthespire.cards.common;

import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.ndc.arknightsthespire.CardColors;
import com.ndc.arknightsthespire.cards.CardSPBase;
import com.ndc.arknightsthespire.cards.PositionType;

public class CardVanguardAssaultOrder extends CardSPBase {
    public static final String ID = "ats:Assault Order";
    public static final String IMG_PATH = "img/cards/AssaultOrder.png";
    public static final PositionType POSITION = PositionType.VANGUARD;
    private static final int COST = 0;
    private static final int SP = 10;
    private static final int UP_SP = 7;
    private static final int ENERGY = 2;

    public CardVanguardAssaultOrder() {
        super(ID, IMG_PATH, COST,
                CardType.SKILL, CardColors.AbstractCardEnum.DOCTOR_COLOR,
                CardRarity.COMMON, CardTarget.SELF, true, POSITION, true);
        this.sp = this.baseSP = SP;
        this.magicNumber = this.baseMagicNumber = ENERGY;

        this.setBackgroundTexture("img/512/vanguard_512.png", "img/1024/vanguard.png");

        this.setOrbTexture("img/orbs/cost.png", "img/orbs/cost_small.png");

    }

    @Override
    public void useCard(AbstractPlayer p, AbstractMonster m, boolean isSpJustUsed) {
        if(isSpJustUsed) addToBot(new GainEnergyAction(this.magicNumber));
    }

    @Override
    public AbstractCard makeCopy() {
        return new CardVanguardAssaultOrder();
    }

    @Override
    public void upgradeCard() {
        this.upgradeSP(UP_SP);
    }

}
