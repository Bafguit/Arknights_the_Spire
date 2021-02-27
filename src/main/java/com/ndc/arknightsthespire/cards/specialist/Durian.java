package com.ndc.arknightsthespire.cards.specialist;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.PenNibPower;
import com.ndc.arknightsthespire.CardColors;
import com.ndc.arknightsthespire.actions.AtsSFX;
import com.ndc.arknightsthespire.cards.base.CardSPBase;
import com.ndc.arknightsthespire.cards.base.PositionType;

import static com.megacrit.cardcrawl.actions.AbstractGameAction.*;

public class Durian extends CardSPBase {
    public static final String ID = "ats:Durian";
    public static final String IMG_PATH = "img/cards/Durian.png";
    public static final PositionType POSITION = PositionType.SPECIALIST;
    private static final int COST = 1;
    private static final int UP_COST = 0;

    public Durian() {
        super(ID, IMG_PATH, COST,
                CardType.SKILL, CardColors.AbstractCardEnum.DOCTOR_COLOR,
                CardRarity.RARE, CardTarget.SELF, POSITION, 0, 5, 0, 0);
        this.exhaust = true;
        this.selfRetain = true;
    }

    @Override
    public void useCard(AbstractPlayer p, AbstractMonster m, boolean isSpJustUsed) {


        addToBot(new AtsSFX("DURIAN"));
        for(int forI = 0; forI < 5; forI++) {
            addToBot(new DamageAction(p, this.getInfo(), AttackEffect.FIRE, true, true));
        }

        addToBot(new ApplyPowerAction(p, p, new PenNibPower(p, 1), 1));
    }

    @Override
    public AbstractCard makeCopy() {
        return new Durian();
    }

    @Override
    public void upgradeCard() {
        this.upgradeBaseCost(UP_COST);
    }

}
