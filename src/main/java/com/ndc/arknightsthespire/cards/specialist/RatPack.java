package com.ndc.arknightsthespire.cards.specialist;

import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.ndc.arknightsthespire.CardColors;
import com.ndc.arknightsthespire.cards.base.CardSPBase;
import com.ndc.arknightsthespire.cards.base.PositionType;

public class RatPack extends CardSPBase {
    public static final String ID = "ats:Rat Pack";
    public static final String IMG_PATH = "img/cards/RatPack.png";
    public static final PositionType POSITION = PositionType.SPECIALIST;
    private static final int COST = 3;
    private static final int UP_COST = 2;

    public RatPack() {
        super(ID, IMG_PATH, COST,
                CardType.SKILL, CardColors.AbstractCardEnum.DOCTOR_COLOR,
                CardRarity.RARE, CardTarget.SELF, POSITION);
        this.exhaust = true;
        this.selfRetain = true;
    }

    @Override
    public void useCard(AbstractPlayer p, AbstractMonster m, boolean isSpJustUsed) {
        addToBot(new GainBlockAction(p, p, AbstractDungeon.player.maxHealth));
    }

    @Override
    public AbstractCard makeCopy() {
        return new RatPack();
    }

    @Override
    public void upgradeCard() {
        this.upgradeBaseCost(UP_COST);
    }

}
