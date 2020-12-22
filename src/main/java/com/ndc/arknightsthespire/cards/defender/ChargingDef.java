package com.ndc.arknightsthespire.cards.defender;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.BufferPower;
import com.ndc.arknightsthespire.CardColors;
import com.ndc.arknightsthespire.cards.base.CardSPBase;
import com.ndc.arknightsthespire.cards.base.PositionType;

public class ChargingDef extends CardSPBase {
    public static final String ID = "ats:Charging Defense";
    public static final String IMG_PATH = "img/cards/cdef.png";
    public static final PositionType POSITION = PositionType.DEFENDER;
    private static final int COST = 1;
    private static final int BLOCK_AMT = 7;
    private static final int UPGRADE_BLOCK = 3;
    private static final int DEFAULT_SP = 10;

    public ChargingDef() {
        super(ID, IMG_PATH, COST,
                CardType.SKILL, CardColors.AbstractCardEnum.DOCTOR_COLOR,
                CardRarity.UNCOMMON, CardTarget.SELF, true, POSITION, true, 0, BLOCK_AMT, 0, DEFAULT_SP);
    }

    @Override
    public void useCard(AbstractPlayer p, AbstractMonster m, boolean isSpJustUsed) {
/*        if(!DogmaticField.checkGainBlock()) {
            AbstractDungeon.actionManager.addToBottom(new GainBlockAction(p, p, block));
        }
        else {
            AbstractDungeon.actionManager.addToBottom(new HealAction(p, p, block));
        }*/

        addToBot(new GainBlockAction(p, p, this.block));

        //SP Effect
        if(isSpJustUsed) {
            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, m, new BufferPower(p, 1), 1));
        }
    }

    @Override
    public AbstractCard makeCopy() {
        return new ChargingDef();
    }

    @Override
    public void upgradeCard() {
        this.upgradeBlock(UPGRADE_BLOCK);
    }

}
