package com.ndc.arknightsthespire.cards.common;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.ndc.arknightsthespire.CardColors;
import com.ndc.arknightsthespire.RandomAttack;
import com.ndc.arknightsthespire.cards.CardSPBase;
import com.ndc.arknightsthespire.cards.PositionType;

public class CardCasterVeryHotBlade extends CardSPBase {
    public static final String ID = "ats:Very Hot Balde";
    public static final String IMG_PATH = "img/cards/VeryHotBlade.png";
    public static final PositionType POSITION = PositionType.CASTER;
    private static final int COST = 0;
    private static final int DAMAGE = 3;
    private static final int UP_DAMAGE = 1;
    private static final int SP = 5;
    private static final int HIT = 3;

    public CardCasterVeryHotBlade() {
        super(ID, IMG_PATH, COST,
                CardType.ATTACK, CardColors.AbstractCardEnum.DOCTOR_COLOR,
                CardRarity.COMMON, CardTarget.ENEMY, true, POSITION, true);
        this.damage = this.baseDamage = DAMAGE;
        this.magicNumber = this.baseMagicNumber = HIT;
        this.sp = this.baseSP = SP;

        this.setBackgroundTexture("img/512/caster_512.png", "img/1024/caster.png");

        this.setOrbTexture("img/orbs/cost.png", "img/orbs/cost_small.png");

    }

    @Override
    public void useCard(AbstractPlayer p, AbstractMonster m, boolean isSpJustUsed) {
        if(isSpJustUsed) {
            for (int forI = 0; forI < this.magicNumber; forI++) {
                AbstractDungeon.actionManager.addToBottom(new com.megacrit.cardcrawl.actions.common.DamageAction(m,
                        new DamageInfo(p, (m.currentBlock > 0 ? this.damage + 2 : this.damage), this.damageTypeForTurn),
                        AbstractGameAction.AttackEffect.BLUNT_LIGHT, true));
            }
        }
    }

    @Override
    public AbstractCard makeCopy() {
        return new CardCasterVeryHotBlade();
    }

    @Override
    public void upgradeCard() {
        this.upgradeDamage(UP_DAMAGE);
    }

}
