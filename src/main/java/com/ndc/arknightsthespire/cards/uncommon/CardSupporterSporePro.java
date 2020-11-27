package com.ndc.arknightsthespire.cards.uncommon;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.unique.RemoveAllPowersAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.WeakPower;
import com.ndc.arknightsthespire.CardColors;
import com.ndc.arknightsthespire.cards.CardSPBase;
import com.ndc.arknightsthespire.cards.PositionType;

public class CardSupporterSporePro extends CardSPBase {
    public static final String ID = "ats:Spore Proliferation";
    public static final String IMG_PATH = "img/cards/SporeProliferation.png";
    public static final PositionType POSITION = PositionType.SUPPORT;
    private static final int COST = 1;
    private static final int WEAK = 2;
    private static final int SP = 17;
    private static final int UP_SP = 13;

    public CardSupporterSporePro() {
        super(ID, IMG_PATH, COST,
                CardType.SKILL, CardColors.AbstractCardEnum.DOCTOR_COLOR,
                CardRarity.UNCOMMON, CardTarget.ENEMY, false, POSITION, true);
        this.magicNumber = this.baseMagicNumber = WEAK;
        this.sp = this.baseSP = SP;

        this.setBackgroundTexture("img/512/supporter_512.png", "img/1024/supporter.png");

        this.setOrbTexture("img/orbs/cost.png", "img/orbs/cost_small.png");

    }

    @Override
    public void useCard(AbstractPlayer p, AbstractMonster m, boolean isSpJustUsed) {
        if(isSpJustUsed) AbstractDungeon.actionManager.addToBottom(new RemoveAllPowersAction(m, false));
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(m, p,
                    new WeakPower(m, this.magicNumber, false), this.magicNumber, true));
    }

    @Override
    public AbstractCard makeCopy() {
        return new CardSupporterSporePro();
    }

    @Override
    public void upgradeCard() {
        this.upgradeSP(UP_SP);
    }



}
