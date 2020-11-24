package com.ndc.arknightsthespire.cards.rare;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.ndc.arknightsthespire.CardColors;
import com.ndc.arknightsthespire.cards.CardSPBase;
import com.ndc.arknightsthespire.cards.PositionType;
import com.ndc.arknightsthespire.power.SoulRend;

public class CardGuardSoulRend extends CardSPBase {
    public static final String ID = "Soul Rend";
    public static final String IMG_PATH = "img/cards/SoulRend.png";
    public static final PositionType POSITION = PositionType.GUARD;
    private static final int COST = 1;
    private static final int HEAL_AMOUNT = 1;
    private static final int UPGRADE_HEAL = 1;

    public CardGuardSoulRend() {
        super(ID, IMG_PATH, COST,
                CardType.POWER, CardColors.AbstractCardEnum.DOCTOR_COLOR,
                CardRarity.RARE, CardTarget.SELF, false, POSITION, false);
        this.magicNumber = this.baseMagicNumber = HEAL_AMOUNT;

        this.setBackgroundTexture("img/512/guard_512.png", "img/1024/guard.png");

        this.setOrbTexture("img/orbs/cost.png", "img/orbs/cost_small.png");

    }

    @Override
    public void useCard(AbstractPlayer p, AbstractMonster m, boolean isSpJustUsed) {
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new SoulRend(p, p, magicNumber)));
    }

    @Override
    public AbstractCard makeCopy() {
        return new CardGuardSoulRend();
    }

    @Override
    public void upgradeCard() {
        this.upgradeMagicNumber(UPGRADE_HEAL);
    }



}
