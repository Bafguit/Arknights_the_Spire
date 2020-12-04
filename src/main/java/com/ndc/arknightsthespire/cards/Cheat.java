package com.ndc.arknightsthespire.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.ndc.arknightsthespire.CardColors;
import com.ndc.arknightsthespire.cards.base.CardSPBase;
import com.ndc.arknightsthespire.cards.base.PositionType;

public class Cheat extends CardSPBase {
    public static final String ID = "ats:Cheat";
    public static final String IMG_PATH = "img/cards/attack_beta.png";
    public static final PositionType POSITION = PositionType.CASTER;
    private static final int COST = 0;

    public Cheat() {
        super(ID, IMG_PATH, COST,
                CardType.ATTACK, CardColors.AbstractCardEnum.DOCTOR_COLOR,
                CardRarity.BASIC, CardTarget.ALL_ENEMY, false, POSITION, false);

        this.setBackgroundTexture("img/512/beta.png", "img/1024/beta.png");

        this.setOrbTexture("img/orbs/cost.png", "img/orbs/cost_small.png");

    }

    @Override
    public void useCard(AbstractPlayer p, AbstractMonster m, boolean isSpJustUsed) {
        AbstractDungeon.actionManager.addToBottom(new com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction(m,
                DamageInfo.createDamageMatrix(20000, true), this.damageTypeForTurn,
                AbstractGameAction.AttackEffect.NONE, true));
    }

    @Override
    public AbstractCard makeCopy() {
        return new Cheat();
    }

    @Override
    public void upgradeCard() {}

}
