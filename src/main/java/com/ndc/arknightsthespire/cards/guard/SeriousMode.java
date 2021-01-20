package com.ndc.arknightsthespire.cards.guard;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.ndc.arknightsthespire.CardColors;
import com.ndc.arknightsthespire.cards.base.CardSPBase;
import com.ndc.arknightsthespire.cards.base.PositionType;
import com.ndc.arknightsthespire.power.SeriousPower;
import com.ndc.arknightsthespire.power.SoulRendPower;

public class SeriousMode extends CardSPBase {
    public static final String ID = "ats:Serious Mode";
    public static final String IMG_PATH = "img/cards/SeriousMode.png";
    public static final PositionType POSITION = PositionType.GUARD;
    private static final int COST = 1;

    public SeriousMode() {
        super(ID, IMG_PATH, COST,
                CardType.POWER, CardColors.AbstractCardEnum.DOCTOR_COLOR,
                CardRarity.UNCOMMON, CardTarget.SELF, POSITION);
    }

    @Override
    public void useCard(AbstractPlayer p, AbstractMonster m, boolean isSpJustUsed) {
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new SeriousPower()));
    }

    @Override
    public AbstractCard makeCopy() {
        return new SeriousMode();
    }

    @Override
    public void upgradeCard() {
        this.isInnate = true;
    }



}
