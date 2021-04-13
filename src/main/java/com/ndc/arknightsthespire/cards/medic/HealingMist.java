package com.ndc.arknightsthespire.cards.medic;

import com.megacrit.cardcrawl.actions.common.ExhaustSpecificCardAction;
import com.megacrit.cardcrawl.actions.common.HealAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.purple.DeusExMachina;
import com.megacrit.cardcrawl.cards.tempCards.Miracle;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.ndc.arknightsthespire.CardColors;
import com.ndc.arknightsthespire.SPHandler;
import com.ndc.arknightsthespire.cards.base.CardSPBase;
import com.ndc.arknightsthespire.cards.base.PositionType;

public class HealingMist extends CardSPBase {
    public static final String ID = "ats:Lancet";
    public static final String IMG_PATH = "atsImg/cards/Lancet.png";
    public static final PositionType POSITION = PositionType.MEDIC;
    private static final int COST = -2;
    private static final int SP = 6;
    private static final int UP_SP = 2;

    public HealingMist() {
        super(ID, IMG_PATH, COST,
                CardType.SKILL, CardColors.AbstractCardEnum.DOCTOR_COLOR,
                CardRarity.COMMON, CardTarget.NONE, POSITION, 0, 0, SP, 0);
        this.exhaust = true;
    }

    @Override
    public void triggerWhenDrawn() {
        addToTop(new HealAction(AbstractDungeon.player, AbstractDungeon.player, this.magicNumber));
        addToTop(new ExhaustSpecificCardAction(this, AbstractDungeon.player.hand));
    }

    @Override
    public boolean canUse(AbstractPlayer p, AbstractMonster m) {
        return false;
    }

    @Override
    public void useCard(AbstractPlayer p, AbstractMonster m, boolean isSpJustUsed) {
    }

    @Override
    public AbstractCard makeCopy() {
        return new HealingMist();
    }

    @Override
    public void upgradeCard() {
        this.upgradeMagicNumber(UP_SP);
    }

}
