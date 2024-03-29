package com.ndc.arknightsthespire.cards.supporter;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import com.megacrit.cardcrawl.powers.WeakPower;
import com.ndc.arknightsthespire.CardColors;
import com.ndc.arknightsthespire.actions.ApplyDefAction;
import com.ndc.arknightsthespire.cards.base.CardSPBase;
import com.ndc.arknightsthespire.cards.base.PositionType;
import com.ndc.arknightsthespire.power.ArmourPower;

public class CurseDoll extends CardSPBase {
    public static final String ID = "ats:Curse Doll";
    public static final String IMG_PATH = "atsImg/cards/CurseDoll.png";
    public static final PositionType POSITION = PositionType.SUPPORTER;
    private static final int COST = 1;
    private static final int NUM = 3;
    private static final int UP_NUM = 1;

    public CurseDoll() {
        super(ID, IMG_PATH, COST,
                CardType.SKILL, CardColors.AbstractCardEnum.DOCTOR_COLOR,
                CardRarity.COMMON, CardTarget.ENEMY, POSITION, 0, 0, NUM, 0);
        this.setArm(NUM);
    }

    @Override
    public void useCard(AbstractPlayer p, AbstractMonster m, boolean isSpJustUsed) {
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(m, p,
                    new WeakPower(m, 1, false), 1));
        addToBot(new ApplyPowerAction(m, p, new VulnerablePower(m, this.magicNumber, false)));
    }

    @Override
    public AbstractCard makeCopy() {
        return new CurseDoll();
    }

    @Override
    public void upgradeCard() {
        this.upgradeMagicNumber(UP_NUM);
    }



}
