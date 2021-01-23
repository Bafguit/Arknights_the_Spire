package com.ndc.arknightsthespire.cards.defender;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.ndc.arknightsthespire.CardColors;
import com.ndc.arknightsthespire.cards.base.CardSPBase;
import com.ndc.arknightsthespire.cards.base.PositionType;
import com.ndc.arknightsthespire.power.CntHealModPower;

public class CntHealMod extends CardSPBase {
    public static final String ID = "ats:Counter Healing Mode";
    public static final String IMG_PATH = "img/cards/CntHealMode.png";
    public static final PositionType POSITION = PositionType.DEFENDER;
    private static final int COST = 2;
    private static final int SP = 1;
    private static final int UP_SP = 1;

    public CntHealMod() {
        super(ID, IMG_PATH, COST,
                CardType.POWER, CardColors.AbstractCardEnum.DOCTOR_COLOR,
                CardRarity.UNCOMMON, CardTarget.SELF, POSITION, 0, 0, SP, 0);
    }

    @Override
    public void useCard(AbstractPlayer p, AbstractMonster m, boolean isSpJustUsed) {
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new CntHealModPower(p, p, this.magicNumber)));
    }

    @Override
    public AbstractCard makeCopy() {
        return new CntHealMod();
    }

    @Override
    public void upgradeCard() {
      this.upgradeMagicNumber(UP_SP);
    }



}
