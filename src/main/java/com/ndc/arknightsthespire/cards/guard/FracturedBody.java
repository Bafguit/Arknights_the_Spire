package com.ndc.arknightsthespire.cards.guard;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.DoubleDamagePower;
import com.ndc.arknightsthespire.CardColors;
import com.ndc.arknightsthespire.cards.base.CardSPBase;
import com.ndc.arknightsthespire.cards.base.PositionType;
import com.ndc.arknightsthespire.power.FracturedBodyPower;

public class FracturedBody extends CardSPBase {
    public static final String ID = "ats:Fractured Body";
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    // Get object containing the strings that are displayed in the game.
    public static final String NAME = cardStrings.NAME;
    public static final String IMG_PATH = "atsImg/cards/FracturedBody.png";
    public static final PositionType POSITION = PositionType.GUARD;
    private static final int COST = 1;
    private static final int UP_COST = 0;
    private static final int DEFAULT_SP = 10;

    public FracturedBody() {
        super(ID, IMG_PATH, COST,
                CardType.SKILL, CardColors.AbstractCardEnum.DOCTOR_COLOR,
                CardRarity.RARE, CardTarget.SELF, true, POSITION, true, 0, 0, 0, DEFAULT_SP);
        this.exhaust = true;
        this.selfRetain = true;
    }

    @Override
    public void useCard(AbstractPlayer p, AbstractMonster m, boolean isSpJustUsed) {
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p,
                new FracturedBodyPower(p, p, (isSpJustUsed ? 2 : 1)), (isSpJustUsed ? 2 : 1)));
        addToBot(new ApplyPowerAction(p, p, new DoubleDamagePower(p, (isSpJustUsed ? 2 : 1), false)));
    }

    @Override
    public AbstractCard makeCopy() {
        return new FracturedBody();
    }

    @Override
    public void upgradeCard() {
        this.upgradeBaseCost(UP_COST);
    }

}
