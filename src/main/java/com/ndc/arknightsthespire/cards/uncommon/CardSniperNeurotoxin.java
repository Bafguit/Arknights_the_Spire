package com.ndc.arknightsthespire.cards.uncommon;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.ndc.arknightsthespire.CardColors;
import com.ndc.arknightsthespire.cards.CardSPBase;
import com.ndc.arknightsthespire.cards.PositionType;
import com.ndc.arknightsthespire.power.CompositeMedicalShells;
import com.ndc.arknightsthespire.power.Neurotoxin;

public class CardSniperNeurotoxin extends CardSPBase {
    public static final String ID = "ats:Neurotoxin";
    public static final String IMG_PATH = "img/cards/Neurotoxin.png";
    public static final PositionType POSITION = PositionType.SNIPER;
    private static final int COST = 1;

    public CardSniperNeurotoxin() {
        super(ID, IMG_PATH, COST,
                CardType.POWER, CardColors.AbstractCardEnum.DOCTOR_COLOR,
                CardRarity.UNCOMMON, CardTarget.SELF, false, POSITION, false);

        this.setBackgroundTexture("img/512/sniper_512.png", "img/1024/sniper.png");

        this.setOrbTexture("img/orbs/cost.png", "img/orbs/cost_small.png");

    }

    @Override
    public void useCard(AbstractPlayer p, AbstractMonster m, boolean isSpJustUsed) {
        addToBot(new ApplyPowerAction(p, p, new Neurotoxin(p, p, 1), 1));
    }

    @Override
    public AbstractCard makeCopy() {
        return new CardSniperNeurotoxin();
    }

    @Override
    public void upgradeCard() {
        this.isInnate = true;
    }

}
