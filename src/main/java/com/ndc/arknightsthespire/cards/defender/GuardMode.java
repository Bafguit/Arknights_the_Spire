package com.ndc.arknightsthespire.cards.defender;

import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.RegenPower;
import com.ndc.arknightsthespire.CardColors;
import com.ndc.arknightsthespire.actions.ApplyDefAction;
import com.ndc.arknightsthespire.cards.base.CardSPBase;
import com.ndc.arknightsthespire.cards.base.PositionType;

public class GuardMode extends CardSPBase {
    public static final String ID = "ats:Guard Mode";
    public static final String IMG_PATH = "atsImg/cards/GuardMode.png";
    public static final PositionType POSITION = PositionType.DEFENDER;
    private static final int COST = 1;
    private static final int BLOCK_AMT = 7;
    private static final int UPGRADE_BLOCK = 3;

    public GuardMode() {
        super(ID, IMG_PATH, COST,
                CardType.SKILL, CardColors.AbstractCardEnum.DOCTOR_COLOR,
                CardRarity.COMMON, CardTarget.SELF, POSITION, 0, BLOCK_AMT, 0, 0);
        this.setArm(70);
    }

    @Override
    public void useCard(AbstractPlayer p, AbstractMonster m, boolean isSpJustUsed) {
        addToBot(new GainBlockAction(p, this.block * (p.currentHealth <= p.maxHealth/2 ? 2 : 1)));
    }

    @Override
    public AbstractCard makeCopy() {
        return new GuardMode();
    }

    @Override
    public void upgradeCard() {
        this.upgradeBlock(UPGRADE_BLOCK);
    }



}
