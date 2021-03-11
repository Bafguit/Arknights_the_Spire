package com.ndc.arknightsthespire.cards.supporter;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.ndc.arknightsthespire.CardColors;
import com.ndc.arknightsthespire.cards.base.CardSPBase;
import com.ndc.arknightsthespire.cards.base.PositionType;
import com.ndc.arknightsthespire.power.ShelterPower;

public class EncForest extends CardSPBase {
    public static final String ID = "ats:Encircling Forest";
    public static final String IMG_PATH = "atsImg/cards/EncirclingForest.png";
    public static final PositionType POSITION = PositionType.SUPPORTER;
    private static final int COST = 1;
    private static final int SHELTER = 1;
    private static final int UP_SHELTER = 1;
    private static final int SP = 10;

    public EncForest() {
        super(ID, IMG_PATH, COST,
                CardType.SKILL, CardColors.AbstractCardEnum.DOCTOR_COLOR,
                CardRarity.UNCOMMON, CardTarget.SELF, false, POSITION, true, 0, 0, SHELTER, SP);
    }

    @Override
    public void useCard(AbstractPlayer p, AbstractMonster m, boolean isSpJustUsed) {
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, m, new ShelterPower(p, p,
                this.magicNumber, (isSpJustUsed ? 2 : 1)), this.magicNumber));
    }

    @Override
    public AbstractCard makeCopy() {
        return new EncForest();
    }

    @Override
    public void upgradeCard() {
        this.upgradeMagicNumber(UP_SHELTER);
    }

}
