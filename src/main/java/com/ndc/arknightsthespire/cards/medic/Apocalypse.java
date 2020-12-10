package com.ndc.arknightsthespire.cards.medic;

import com.megacrit.cardcrawl.actions.common.HealAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.ndc.arknightsthespire.CardColors;
import com.ndc.arknightsthespire.SPHandler;
import com.ndc.arknightsthespire.cards.base.CardSPBase;
import com.ndc.arknightsthespire.cards.base.PositionType;

public class Apocalypse extends CardSPBase {
    public static final String ID = "ats:Just Like an Apocalypse";
    public static final String IMG_PATH = "img/cards/JLAA.png";
    public static final PositionType POSITION = PositionType.MEDIC;
    private static final int COST = 1;
    private static final int SP = 2;
    private static final int UP_SP = 1;

    public Apocalypse() {
        super(ID, IMG_PATH, COST,
                CardType.SKILL, CardColors.AbstractCardEnum.DOCTOR_COLOR,
                CardRarity.COMMON, CardTarget.SELF, POSITION, 0, 0, SP, 0);
    }

    @Override
    public void useCard(AbstractPlayer p, AbstractMonster m, boolean isSpJustUsed) {
        SPHandler.addSp(this.magicNumber);
        addToBot(new HealAction(p, p, this.magicNumber));
    }

    @Override
    public AbstractCard makeCopy() {
        return new Apocalypse();
    }

    @Override
    public void upgradeCard() {
        this.upgradeMagicNumber(UP_SP);
    }

}
