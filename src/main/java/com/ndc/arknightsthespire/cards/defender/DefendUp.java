package com.ndc.arknightsthespire.cards.defender;

import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.ndc.arknightsthespire.CardColors;
import com.ndc.arknightsthespire.cards.base.CardSPBase;
import com.ndc.arknightsthespire.cards.base.PositionType;

public class DefendUp extends CardSPBase {
    public static final String ID = "ats:Defend Up";
    public static final String IMG_PATH = "img/cards/defend.png";
    public static final PositionType POSITION = PositionType.DEFENDER;
    private static final int COST = 1;
    private static final int BLOCK_AMT = 5;
    private static final int UPGRADE_BLOCK = 3;
    private static final int DEFAULT_SP = 5;

    public DefendUp() {
        super(ID, IMG_PATH, COST,
                CardType.SKILL, CardColors.AbstractCardEnum.DOCTOR_COLOR,
                CardRarity.BASIC, CardTarget.SELF, false, POSITION, true, 0, BLOCK_AMT, 0, DEFAULT_SP);
    }

    @Override
    public void useCard(AbstractPlayer p, AbstractMonster m, boolean isSpJustUsed) {

        checkGainBlock(this.block + (isSpJustUsed ? 5 : 0));
    }

    @Override
    public AbstractCard makeCopy() {
        return new DefendUp();
    }

    @Override
    public void upgradeCard() {
        this.upgradeBlock(UPGRADE_BLOCK);
    }



}
