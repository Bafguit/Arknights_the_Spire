package com.ndc.arknightsthespire.cards.defender;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.RegenPower;
import com.ndc.arknightsthespire.CardColors;
import com.ndc.arknightsthespire.cards.base.CardSPBase;
import com.ndc.arknightsthespire.cards.base.PositionType;

public class ShellDef extends CardSPBase {
    public static final String ID = "ats:Shell Defense";
    public static final String IMG_PATH = "img/cards/sdef.png";
    public static final PositionType POSITION = PositionType.DEFENDER;
    private static final int COST = 1;
    private static final int BLOCK_AMT = 8;
    private static final int UPGRADE_BLOCK = 5;
    private static final int DEFAULT_SP = 6;

    public ShellDef() {
        super(ID, IMG_PATH, COST,
                CardType.SKILL, CardColors.AbstractCardEnum.DOCTOR_COLOR,
                CardRarity.COMMON, CardTarget.SELF, false, POSITION, true, 0, BLOCK_AMT, 0, DEFAULT_SP);
    }

    @Override
    public void useCard(AbstractPlayer p, AbstractMonster m, boolean isSpJustUsed) {
        if(!isSpJustUsed) checkGainBlock(block);
        else {
            checkGainBlock(block);
            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new RegenPower(p, 2), 2));
        }
    }

    @Override
    public AbstractCard makeCopy() {
        return new ShellDef();
    }

    @Override
    public void upgradeCard() {
        this.upgradeBlock(UPGRADE_BLOCK);
    }



}
