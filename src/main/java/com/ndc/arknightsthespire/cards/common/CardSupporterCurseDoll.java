package com.ndc.arknightsthespire.cards.common;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.unique.RemoveAllPowersAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import com.megacrit.cardcrawl.powers.WeakPower;
import com.ndc.arknightsthespire.CardColors;
import com.ndc.arknightsthespire.cards.CardSPBase;
import com.ndc.arknightsthespire.cards.PositionType;

public class CardSupporterCurseDoll extends CardSPBase {
    public static final String ID = "ats:Curse Doll";
    public static final String IMG_PATH = "img/cards/CurseDoll.png";
    public static final PositionType POSITION = PositionType.SUPPORT;
    private static final int COST = 1;
    private static final int NUM = 1;
    private static final int UP_NUM = 1;

    public CardSupporterCurseDoll() {
        super(ID, IMG_PATH, COST,
                CardType.SKILL, CardColors.AbstractCardEnum.DOCTOR_COLOR,
                CardRarity.COMMON, CardTarget.ENEMY, false, POSITION, false);
        this.magicNumber = this.baseMagicNumber = NUM;

        this.setBackgroundTexture("img/512/supporter_512.png", "img/1024/supporter.png");

        this.setOrbTexture("img/orbs/cost.png", "img/orbs/cost_small.png");

    }

    @Override
    public void useCard(AbstractPlayer p, AbstractMonster m, boolean isSpJustUsed) {
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(m, p,
                    new WeakPower(m, this.magicNumber, false), this.magicNumber));
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(m, p,
                new VulnerablePower(m, this.magicNumber, false), this.magicNumber));
    }

    @Override
    public AbstractCard makeCopy() {
        return new CardSupporterCurseDoll();
    }

    @Override
    public void upgradeCard() {
        this.upgradeMagicNumber(UP_NUM);
    }



}
