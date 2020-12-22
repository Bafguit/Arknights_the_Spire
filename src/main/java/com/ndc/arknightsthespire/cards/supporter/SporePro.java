package com.ndc.arknightsthespire.cards.supporter;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.WeakPower;
import com.ndc.arknightsthespire.CardColors;
import com.ndc.arknightsthespire.actions.AtsSFX;
import com.ndc.arknightsthespire.cards.base.CardSPBase;
import com.ndc.arknightsthespire.cards.base.PositionType;
import com.ndc.arknightsthespire.power.AtsSlowPower;

public class SporePro extends CardSPBase {
    public static final String ID = "ats:Spore Proliferation";
    public static final String IMG_PATH = "img/cards/SporeProliferation.png";
    public static final PositionType POSITION = PositionType.SUPPORTER;
    private static final int COST = 1;
    private static final int WEAK = 2;
    private static final int SP = 15;
    private static final int UP_SP = 10;

    public SporePro() {
        super(ID, IMG_PATH, COST,
                CardType.SKILL, CardColors.AbstractCardEnum.DOCTOR_COLOR,
                CardRarity.UNCOMMON, CardTarget.ENEMY, false, POSITION, true, 0, 0, WEAK, SP);
    }

    @Override
    public void useCard(AbstractPlayer p, AbstractMonster m, boolean isSpJustUsed) {
        addToBot(new AtsSFX("BOTTLE"));
        if(isSpJustUsed) {
            if(m.powers.size() > 0) {
                int powerLength = m.powers.size();
                int rand = (int) (Math.random() * powerLength);
                Object[] powers = m.powers.toArray();
                addToBot(new RemoveSpecificPowerAction(m, p, (AbstractPower) powers[rand]));
            }
        }
        addToBot(new ApplyPowerAction(m, p,
                    new WeakPower(m, this.magicNumber, false), this.magicNumber, true));
    }

    @Override
    public AbstractCard makeCopy() {
        return new SporePro();
    }

    @Override
    public void upgradeCard() {
        this.upgradeSP(UP_SP);
    }



}
