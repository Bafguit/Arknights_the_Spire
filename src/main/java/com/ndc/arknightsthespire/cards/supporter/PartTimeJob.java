package com.ndc.arknightsthespire.cards.supporter;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.RegenPower;
import com.ndc.arknightsthespire.CardColors;
import com.ndc.arknightsthespire.cards.base.CardSPBase;
import com.ndc.arknightsthespire.cards.base.PositionType;
import com.ndc.arknightsthespire.power.PartTimeJobPower;

public class PartTimeJob extends CardSPBase {
    public static final String ID = "ats:Part Time Job";
    public static final String IMG_PATH = "atsImg/cards/PartTimeJob.png";
    public static final PositionType POSITION = PositionType.SUPPORTER;
    private static final int COST = 1;

    public PartTimeJob() {
        super(ID, IMG_PATH, COST,
                CardType.POWER, CardColors.AbstractCardEnum.DOCTOR_COLOR,
                CardRarity.UNCOMMON, CardTarget.SELF, POSITION);
    }

    @Override
    public void useCard(AbstractPlayer p, AbstractMonster m, boolean isSpJustUsed) {
        if(this.upgraded) {
            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new PartTimeJobPower(p, p, 1), 1));
        } else {
            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new RegenPower(p, 3), 3));
        }
    }

    @Override
    public AbstractCard makeCopy() {
        return new PartTimeJob();
    }

    @Override
    public void upgradeCard() {
    }

}
