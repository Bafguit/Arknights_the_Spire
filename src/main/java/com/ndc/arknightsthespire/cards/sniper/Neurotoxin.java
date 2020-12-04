package com.ndc.arknightsthespire.cards.sniper;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.ndc.arknightsthespire.CardColors;
import com.ndc.arknightsthespire.cards.base.CardSPBase;
import com.ndc.arknightsthespire.cards.base.PositionType;
import com.ndc.arknightsthespire.power.NeurotoxinPower;

public class Neurotoxin extends CardSPBase {
    public static final String ID = "ats:Neurotoxin";
    public static final String IMG_PATH = "img/cards/Neurotoxin.png";
    public static final PositionType POSITION = PositionType.SNIPER;
    private static final int COST = 1;

    public Neurotoxin() {
        super(ID, IMG_PATH, COST,
                CardType.POWER, CardColors.AbstractCardEnum.DOCTOR_COLOR,
                CardRarity.UNCOMMON, CardTarget.SELF, false, POSITION, false);

        this.setBackgroundTexture("img/512/sniper_512.png", "img/1024/sniper.png");

        this.setOrbTexture("img/orbs/cost.png", "img/orbs/cost_small.png");

    }

    @Override
    public void useCard(AbstractPlayer p, AbstractMonster m, boolean isSpJustUsed) {
        addToBot(new ApplyPowerAction(p, p, new NeurotoxinPower(p, p, 1), 1));
    }

    @Override
    public AbstractCard makeCopy() {
        return new Neurotoxin();
    }

    @Override
    public void upgradeCard() {
        this.isInnate = true;
    }

}
