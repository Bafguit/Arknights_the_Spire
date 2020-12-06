package com.ndc.arknightsthespire.cards.guard;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.ndc.arknightsthespire.CardColors;
import com.ndc.arknightsthespire.cards.base.CardSPBase;
import com.ndc.arknightsthespire.cards.base.PositionType;
import com.ndc.arknightsthespire.power.SoulRendPower;

public class SoulRend extends CardSPBase {
    public static final String ID = "ats:Soul Rend";
    public static final String IMG_PATH = "img/cards/SoulRend.png";
    public static final PositionType POSITION = PositionType.GUARD;
    private static final int COST = 1;
    private static final int HEAL_AMOUNT = 1;
    private static final int UPGRADE_HEAL = 1;

    public SoulRend() {
        super(ID, IMG_PATH, COST,
                CardType.POWER, CardColors.AbstractCardEnum.DOCTOR_COLOR,
                CardRarity.RARE, CardTarget.SELF, POSITION, 0, 0, HEAL_AMOUNT, 0);
    }

    @Override
    public void useCard(AbstractPlayer p, AbstractMonster m, boolean isSpJustUsed) {
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new SoulRendPower(p, p, magicNumber)));
    }

    @Override
    public AbstractCard makeCopy() {
        return new SoulRend();
    }

    @Override
    public void upgradeCard() {
        this.upgradeMagicNumber(UPGRADE_HEAL);
    }



}
