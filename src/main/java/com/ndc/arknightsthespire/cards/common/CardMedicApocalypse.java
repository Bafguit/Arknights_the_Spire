package com.ndc.arknightsthespire.cards.common;

import com.megacrit.cardcrawl.actions.common.HealAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.ndc.arknightsthespire.CardColors;
import com.ndc.arknightsthespire.SPHandler;
import com.ndc.arknightsthespire.cards.CardSPBase;
import com.ndc.arknightsthespire.cards.PositionType;

public class CardMedicApocalypse extends CardSPBase {
    public static final String ID = "ats:Just Like an Apocalypse";
    public static final String IMG_PATH = "img/cards/JLAA.png";
    public static final PositionType POSITION = PositionType.MEDIC;
    private static final int COST = 1;
    private static final int SP = 2;
    private static final int UP_SP = 1;

    public CardMedicApocalypse() {
        super(ID, IMG_PATH, COST,
                CardType.SKILL, CardColors.AbstractCardEnum.DOCTOR_COLOR,
                CardRarity.COMMON, CardTarget.SELF, false, POSITION, false);
        this.magicNumber = this.baseMagicNumber = SP;

        this.setBackgroundTexture("img/512/medic_512.png", "img/1024/medic.png");

        this.setOrbTexture("img/orbs/cost.png", "img/orbs/cost_small.png");

    }

    @Override
    public void useCard(AbstractPlayer p, AbstractMonster m, boolean isSpJustUsed) {
        SPHandler.addSp(this.magicNumber);
        addToBot(new HealAction(p, p, this.magicNumber));
    }

    @Override
    public AbstractCard makeCopy() {
        return new CardMedicApocalypse();
    }

    @Override
    public void upgradeCard() {
        this.upgradeMagicNumber(UP_SP);
    }

}
