package com.ndc.arknightsthespire.cards.supporter;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.WeakPower;
import com.ndc.arknightsthespire.CardColors;
import com.ndc.arknightsthespire.actions.ClassFromDeckToHandAction;
import com.ndc.arknightsthespire.cards.base.CardSPBase;
import com.ndc.arknightsthespire.cards.base.PositionType;

public class LiteraryStorm extends CardSPBase {
    public static final String ID = "ats:Literary Storm";
    public static final String IMG_PATH = "img/cards/LiteraryStorm.png";
    public static final PositionType POSITION = PositionType.SUPPORTER;
    private static final int COST = 1;
    private static final int UP_COST = 0;

    public LiteraryStorm() {
        super(ID, IMG_PATH, COST,
                CardType.SKILL, CardColors.AbstractCardEnum.DOCTOR_COLOR,
                CardRarity.UNCOMMON, CardTarget.SELF, POSITION);
        this.exhaust = true;
    }

    @Override
    public void useCard(AbstractPlayer p, AbstractMonster m, boolean isSpJustUsed) {
        addToBot(new ClassFromDeckToHandAction(PositionType.CASTER));
        addToBot(new ClassFromDeckToHandAction(PositionType.SNIPER));
        addToBot(new ClassFromDeckToHandAction(PositionType.GUARD));
        addToBot(new ClassFromDeckToHandAction(PositionType.DEFENDER));
        addToBot(new ClassFromDeckToHandAction(PositionType.MEDIC));
        addToBot(new ClassFromDeckToHandAction(PositionType.SUPPORTER));
        addToBot(new ClassFromDeckToHandAction(PositionType.SPECIALIST));
        addToBot(new ClassFromDeckToHandAction(PositionType.VANGUARD));
    }

    @Override
    public AbstractCard makeCopy() {
        return new LiteraryStorm();
    }

    @Override
    public void upgradeCard() {
        this.upgradeBaseCost(UP_COST);
    }
}
