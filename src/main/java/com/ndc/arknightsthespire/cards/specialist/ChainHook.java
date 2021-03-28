package com.ndc.arknightsthespire.cards.specialist;

import com.megacrit.cardcrawl.actions.AbstractGameAction.AttackEffect;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.ndc.arknightsthespire.CardColors;
import com.ndc.arknightsthespire.actions.AtsSFX;
import com.ndc.arknightsthespire.cards.base.CardSPBase;
import com.ndc.arknightsthespire.cards.base.PositionType;

public class ChainHook extends CardSPBase {
    public static final String ID = "ats:Chain Hook";
    public static final String IMG_PATH = "atsImg/cards/ChainHook.png";
    public static final PositionType POSITION = PositionType.SPECIALIST;
    private static final int COST = 1;
    private static final int DAMAGE = 10;
    private static final int UP_DAMAGE = 5;
    private static final int SP = 5;

    public ChainHook() {
        super(ID, IMG_PATH, COST,
                CardType.ATTACK, CardColors.AbstractCardEnum.DOCTOR_COLOR,
                CardRarity.UNCOMMON, CardTarget.ENEMY, true, POSITION, true, DAMAGE, 0, 0, SP);
    }

    @Override
    public void useCard(AbstractPlayer p, AbstractMonster m, boolean isSpJustUsed) {
        addToBot(new AtsSFX("ROPE"));
        addToBot(new DamageAction(m, this.getInfo(), AttackEffect.SMASH, false, true));
        addToBot(new DrawCardAction((isSpJustUsed ? 2 : 1)));
    }

    @Override
    public AbstractCard makeCopy() {
        return new ChainHook();
    }

    @Override
    public void upgradeCard() {
        this.upgradeDamage(UP_DAMAGE);
    }

}
