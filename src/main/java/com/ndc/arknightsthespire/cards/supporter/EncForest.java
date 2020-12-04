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
    public static final String IMG_PATH = "img/cards/EncirclingForest.png";
    public static final PositionType POSITION = PositionType.SUPPORT;
    private static final int COST = 1;
    private static final int SHELTER = 1;
    private static final int UP_SHELTER = 1;
    private static final int SP = 13;

    public EncForest() {
        super(ID, IMG_PATH, COST,
                CardType.SKILL, CardColors.AbstractCardEnum.DOCTOR_COLOR,
                CardRarity.UNCOMMON, CardTarget.SELF, false, POSITION, true);
        this.magicNumber = this.baseMagicNumber = SHELTER;
        this.sp = this.baseSP = SP;

        this.setBackgroundTexture("img/512/supporter_512.png", "img/1024/supporter.png");

        this.setOrbTexture("img/orbs/cost.png", "img/orbs/cost_small.png");

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
