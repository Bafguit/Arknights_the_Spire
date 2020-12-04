package com.ndc.arknightsthespire.cards.caster;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.ndc.arknightsthespire.CardColors;
import com.ndc.arknightsthespire.actions.RandomAttack;
import com.ndc.arknightsthespire.cards.base.CardSPBase;
import com.ndc.arknightsthespire.cards.base.PositionType;

public class MentalBurst extends CardSPBase {
    public static final String ID = "ats:Mental Burst";
    public static final String IMG_PATH = "img/cards/MentalBurst.png";
    public static final PositionType POSITION = PositionType.CASTER;
    private static final int COST = 0;
    private static final int DAMAGE = 5;
    private static final int SP = 20;
    private static final int HIT = 6;
    private static final int UP_HIT = 2;

    public MentalBurst() {
        super(ID, IMG_PATH, COST,
                CardType.ATTACK, CardColors.AbstractCardEnum.DOCTOR_COLOR,
                CardRarity.UNCOMMON, CardTarget.ALL_ENEMY, true, POSITION, true);
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
                this.addToBot(new RandomAttack(this, AbstractGameAction.AttackEffect.BLUNT_LIGHT, true));
            }
        }
    }

    @Override
    public AbstractCard makeCopy() {
        return new MentalBurst();
    }

    @Override
    public void upgradeCard() {
        this.upgradeMagicNumber(UP_HIT);
    }

}
