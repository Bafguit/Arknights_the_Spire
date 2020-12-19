package com.ndc.arknightsthespire.cards.medic;

import com.megacrit.cardcrawl.actions.common.HealAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.actions.unique.RemoveAllPowersAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.ndc.arknightsthespire.CardColors;
import com.ndc.arknightsthespire.SPHandler;
import com.ndc.arknightsthespire.cards.base.CardSPBase;
import com.ndc.arknightsthespire.cards.base.PositionType;

public class DirDiagnosis extends CardSPBase {
    public static final String ID = "ats:Directed Diagnosis";
    public static final String IMG_PATH = "img/cards/DirDiagnosis.png";
    public static final PositionType POSITION = PositionType.MEDIC;
    private static final int COST = 1;
    private static final int HEAL = 2;
    private static final int UP_HEAL = 1;
    private static final int SP = 15;
    private static final int UP_SP = 10;

    public DirDiagnosis() {
        super(ID, IMG_PATH, COST,
                CardType.SKILL, CardColors.AbstractCardEnum.DOCTOR_COLOR,
                CardRarity.UNCOMMON, CardTarget.SELF, true, POSITION, true, 0, 0, HEAL, SP);
    }

    @Override
    public void useCard(AbstractPlayer p, AbstractMonster m, boolean isSpJustUsed) {
        if(p.hasPower("Weakened") && p.getPower("Weakened").amount >= 2)
            addToBot(new ReducePowerAction(p, p, "Weakened", Math.round(p.getPower("Weakened").amount / 2)));
        if(p.hasPower("Vulnerable") && p.getPower("Vulnerable").amount >= 2)
            addToBot(new ReducePowerAction(p, p, "Vulnerable", Math.round(p.getPower("Vulnerable").amount / 2)));
        if(p.hasPower("Frail") && p.getPower("Frail").amount >= 2)
            addToBot(new ReducePowerAction(p, p, "Frail", Math.round(p.getPower("Frail").amount / 2)));
        addToBot(new HealAction(p, p, this.magicNumber));

        if(isSpJustUsed) {
            if(p.powers.size() > 0) {
                int powerLength = p.powers.size();
                Object[] powers = p.powers.toArray();
                AbstractPower power;
                for(int forI = 0; forI < powerLength; forI++) {
                    power = (AbstractPower) powers[forI];
                    if(power.type != AbstractPower.PowerType.DEBUFF) {
                        powers[forI] = null;
                    }
                }
                int rand;
                do{
                    rand = (int) (Math.random() * powerLength);
                } while(powers[rand] == null);
                power = (AbstractPower) powers[rand];
                addToBot(new RemoveSpecificPowerAction(p, p, power));
            }
        }
    }

    @Override
    public AbstractCard makeCopy() {
        return new DirDiagnosis();
    }

    @Override
    public void upgradeCard() {
        this.upgradeMagicNumber(UP_HEAL);
        this.upgradeSP(UP_SP);
    }

}
