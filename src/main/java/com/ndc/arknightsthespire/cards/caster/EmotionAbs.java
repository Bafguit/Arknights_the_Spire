package com.ndc.arknightsthespire.cards.caster;

import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.ndc.arknightsthespire.CardColors;
import com.ndc.arknightsthespire.SPHandler;
import com.ndc.arknightsthespire.cards.base.CardSPBase;
import com.ndc.arknightsthespire.cards.base.PositionType;

public class EmotionAbs extends CardSPBase {
    public static final String ID = "ats:Emotion Absorption";
    public static final String IMG_PATH = "img/cards/EmotionAbs.png";
    public static final PositionType POSITION = PositionType.CASTER;
    private static final int COST = 0;
    private static final int SP = 3;

    public EmotionAbs() {
        super(ID, IMG_PATH, COST,
                CardType.SKILL, CardColors.AbstractCardEnum.DOCTOR_COLOR,
                CardRarity.BASIC, CardTarget.SELF, false, POSITION, false);
        this.magicNumber = this.baseMagicNumber = SP;

        this.setBackgroundTexture("img/512/caster_512.png", "img/1024/caster.png");

        this.setOrbTexture("img/orbs/cost.png", "img/orbs/cost_small.png");

    }

    @Override
    public void useCard(AbstractPlayer p, AbstractMonster m, boolean isSpJustUsed) {
        if(this.upgraded) {
            SPHandler.addSp(this.magicNumber);
            addToBot(new DrawCardAction(1));
        }
        else SPHandler.addSp(this.magicNumber);
    }

    @Override
    public AbstractCard makeCopy() {
        return new EmotionAbs();
    }

    @Override
    public void upgradeCard() {}

}
