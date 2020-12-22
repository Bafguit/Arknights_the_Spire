package com.ndc.arknightsthespire.cards.specialist;

import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.ndc.arknightsthespire.CardColors.AbstractCardEnum;
import com.ndc.arknightsthespire.actions.AtsSFX;
import com.ndc.arknightsthespire.actions.CheckGainEnergy;
import com.ndc.arknightsthespire.cards.base.CardSPBase;
import com.ndc.arknightsthespire.cards.base.PositionType;

public class HookShot extends CardSPBase {
    public static final String ID = "ats:Hook Shot";
    public static final String IMG_PATH = "img/cards/HookShot.png";
    public static final PositionType POSITION = PositionType.SPECIALIST;
    private static final int COST = 1;
    private static final int BLOCK = 5;
    private static final int UP_BLOCK = 3;
    private static final int SP = 5;

    public HookShot() {
        super(ID, IMG_PATH, COST,
                CardType.SKILL, AbstractCardEnum.DOCTOR_COLOR,
                CardRarity.UNCOMMON, CardTarget.SELF, true, POSITION, true, 0, BLOCK, 0, SP);
    }

    @Override
    public void useCard(AbstractPlayer p, AbstractMonster m, boolean isSpJustUsed) {
        addToBot(new AtsSFX("ROPE"));
        addToBot(new GainBlockAction(p, this.block));
        addToBot(new DrawCardAction((isSpJustUsed ? 2 : 1)));
    }

    @Override
    public AbstractCard makeCopy() {
        return new HookShot();
    }

    @Override
    public void upgradeCard() {
        this.upgradeBlock(UP_BLOCK);
    }

}
