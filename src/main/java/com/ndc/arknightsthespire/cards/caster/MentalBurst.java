package com.ndc.arknightsthespire.cards.caster;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.ndc.arknightsthespire.CardColors;
import com.ndc.arknightsthespire.actions.AtsSFX;
import com.ndc.arknightsthespire.actions.RandomAttack;
import com.ndc.arknightsthespire.cards.base.CardSPBase;
import com.ndc.arknightsthespire.cards.base.PositionType;

public class MentalBurst extends CardSPBase {
    public static final String ID = "ats:Mental Burst";
    public static final String IMG_PATH = "img/cards/MentalBurst.png";
    public static final PositionType POSITION = PositionType.CASTER;
    private static final int COST = 2;
    private static final int DAMAGE = 2;
    private static final int UP_DAMAGE = 1;
    private static final int SP = 7;
    private static final int HIT = 6;
    private static final int SP_HIT = 7;
    private static final int UP_SP_HIT = 1;

    public MentalBurst() {
        super(ID, IMG_PATH, COST,
                CardType.ATTACK, CardColors.AbstractCardEnum.DOCTOR_COLOR,
                CardRarity.UNCOMMON, CardTarget.ALL_ENEMY, true, POSITION, true, DAMAGE, 0, SP_HIT, SP);

    }

    @Override
    public void useCard(AbstractPlayer p, AbstractMonster m, boolean isSpJustUsed) {
        addToBot(new AtsSFX("SPIRIT"));
        for (int forI = 0; forI < (isSpJustUsed ? this.magicNumber : HIT); forI++) {
            this.addToBot(new RandomAttack(this, AbstractGameAction.AttackEffect.BLUNT_LIGHT, true, true, true, 1));
        }
    }

    @Override
    public AbstractCard makeCopy() {
        return new MentalBurst();
    }

    @Override
    public void upgradeCard() {
        this.upgradeDamage(UP_DAMAGE);
        this.upgradeMagicNumber(UP_SP_HIT);
    }

}
