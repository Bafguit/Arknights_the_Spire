package com.ndc.arknightsthespire.cards.caster;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.ndc.arknightsthespire.cards.base.CardSPBase;
import com.ndc.arknightsthespire.power.BurnGroundPower;

import static com.megacrit.cardcrawl.cards.AbstractCard.CardRarity.UNCOMMON;
import static com.megacrit.cardcrawl.cards.AbstractCard.CardTarget.SELF;
import static com.megacrit.cardcrawl.cards.AbstractCard.CardType.POWER;
import static com.ndc.arknightsthespire.CardColors.AbstractCardEnum.DOCTOR_COLOR;
import static com.ndc.arknightsthespire.cards.base.PositionType.CASTER;

public class BurningGround extends CardSPBase {
    public static final String ID = "ats:Burning Ground";
    public static final String IMG_PATH = "img/cards/BurningGround.png";
    private static final int COST = 1;
    private static final int BURN = 2;
    private static final int UP_BURN = 1;

    public BurningGround() {
        super(ID, IMG_PATH, COST, POWER, DOCTOR_COLOR,
                UNCOMMON, SELF, CASTER, 0, 0, BURN, 0);
    }

    @Override
    public void useCard(AbstractPlayer p, AbstractMonster m, boolean isSpJustUsed) {
        addToBot(new ApplyPowerAction(p, p, new BurnGroundPower(p, p, this.magicNumber)));
    }

    @Override
    public AbstractCard makeCopy() {
        return new BurningGround();
    }

    @Override
    public void upgradeCard() {
        this.upgradeMagicNumber(UP_BURN);
    }

}
