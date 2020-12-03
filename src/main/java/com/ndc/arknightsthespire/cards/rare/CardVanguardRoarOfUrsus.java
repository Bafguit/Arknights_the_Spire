package com.ndc.arknightsthespire.cards.rare;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.ndc.arknightsthespire.CardColors;
import com.ndc.arknightsthespire.cards.CardSPBase;
import com.ndc.arknightsthespire.cards.PositionType;
import com.ndc.arknightsthespire.power.DefendOrder;
import com.ndc.arknightsthespire.power.RoarOfUrsus;

public class CardVanguardRoarOfUrsus extends CardSPBase {
    public static final String ID = "ats:Roar of Ursus";
    public static final String IMG_PATH = "img/cards/RoarOfUrsus.png";
    public static final PositionType POSITION = PositionType.VANGUARD;
    private static final int COST = 1;

    public CardVanguardRoarOfUrsus() {
        super(ID, IMG_PATH, COST,
                CardType.POWER, CardColors.AbstractCardEnum.DOCTOR_COLOR,
                CardRarity.RARE, CardTarget.SELF, false, POSITION, false);

        this.setBackgroundTexture("img/512/vanguard_512.png", "img/1024/vanguard.png");

        this.setOrbTexture("img/orbs/cost.png", "img/orbs/cost_small.png");

    }

    @Override
    public void useCard(AbstractPlayer p, AbstractMonster m, boolean isSpJustUsed) {
        addToBot(new ApplyPowerAction(p, p, new RoarOfUrsus(p, p)));
    }

    @Override
    public AbstractCard makeCopy() {
        return new CardVanguardRoarOfUrsus();
    }

    @Override
    public void upgradeCard() {
        this.isInnate = true;
    }

}
