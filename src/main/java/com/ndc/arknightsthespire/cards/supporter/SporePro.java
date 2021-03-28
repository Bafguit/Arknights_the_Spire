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
import com.ndc.arknightsthespire.actions.DisruptionKickAction;
import com.ndc.arknightsthespire.cards.base.CardSPBase;
import com.ndc.arknightsthespire.cards.base.PositionType;
import com.ndc.arknightsthespire.power.ArmPerTurnPower;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

public class SporePro extends CardSPBase {
    public static final String ID = "ats:Spore Proliferation";
    public static final String IMG_PATH = "atsImg/cards/SporeProliferation.png";
    public static final PositionType POSITION = PositionType.SUPPORTER;
    private static final int COST = 1;
    private static final int WEAK = 2;
    private static final int UP_WEAK = 1;

    public SporePro() {
        super(ID, IMG_PATH, COST,
                CardType.SKILL, CardColors.AbstractCardEnum.DOCTOR_COLOR,
                CardRarity.UNCOMMON, CardTarget.ENEMY, POSITION, 0, 0, WEAK, 0);
    }

    @Override
    public void useCard(AbstractPlayer p, AbstractMonster m, boolean isSpJustUsed) {
        addToBot(new AtsSFX("BOTTLE"));
            if(m.powers.size() > 0) {
                ArrayList<AbstractPower> buffs = new ArrayList<>();
                for(AbstractPower pow : m.powers) {
                    if(pow.type == AbstractPower.PowerType.BUFF && !DisruptionKickAction.unRemoval.contains(pow.ID)) {
                        buffs.add(pow);
                    }
                }

                if(buffs.size() > 0) {
                    Random random = new Random();
                    addToBot(new RemoveSpecificPowerAction(m, p, buffs.get(random.nextInt(buffs.size()))));
                }
            }
        addToBot(new ApplyPowerAction(m, p, new WeakPower(m, this.magicNumber, false), this.magicNumber, true));
    }

    @Override
    public AbstractCard makeCopy() {
        return new SporePro();
    }

    @Override
    public void upgradeCard() {
        this.upgradeMagicNumber(UP_WEAK);
    }



}
