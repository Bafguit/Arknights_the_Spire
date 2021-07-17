package com.ndc.arknightsthespire.cards.caster;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.ndc.arknightsthespire.CardColors;
import com.ndc.arknightsthespire.cards.base.CardSPBase;
import com.ndc.arknightsthespire.cards.base.PositionType;
import com.ndc.arknightsthespire.power.BurnPower;
import com.ndc.arknightsthespire.power.GuardObePower;

public class GuardianObelisk extends CardSPBase {
    public static final String ID = "ats:Guardian Obelisk";
    public static final String IMG_PATH = "atsImg/cards/GuardianObelisk.png";
    public static final PositionType POSITION = PositionType.CASTER;
    private static final int COST = 1;
    private static final int UP_COST = 0;

    public GuardianObelisk() {
        super(ID, IMG_PATH, COST,
                CardType.SKILL, CardColors.AbstractCardEnum.DOCTOR_COLOR,
                CardRarity.UNCOMMON, CardTarget.ENEMY, POSITION);
    }

    @Override
    public void useCard(AbstractPlayer p, AbstractMonster m, boolean isSpJustUsed) {
        if(m.hasPower("ats:Burn")) {
            addToBot(new GainBlockAction(p, p, m.getPower("ats:Burn").amount));
            addToBot(new ApplyPowerAction(m, p, new GuardObePower(m, p, 1)));
        }
    }

    @Override
    public AbstractCard makeCopy() {
        return new GuardianObelisk();
    }

    @Override
    public void upgradeCard() {
        this.upgradeBaseCost(UP_COST);
    }

}
