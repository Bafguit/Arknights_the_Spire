package com.ndc.arknightsthespire.cards.uncommon;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.ndc.arknightsthespire.CardColors;
import com.ndc.arknightsthespire.RandomAttack;
import com.ndc.arknightsthespire.cards.CardSPBase;
import com.ndc.arknightsthespire.cards.PositionType;

public class CardGuardRedShift extends CardSPBase {
    public static final String ID = "Red Shift";
    public static final String IMG_PATH = "img/cards/RedShift.png";
    public static final PositionType POSITION = PositionType.GUARD;
    private static final int COST = 1;
    private static final int ATTACK_DMG = 2;
    private static final int UP_DMG = 1;

    public CardGuardRedShift() {
        super(ID, IMG_PATH, COST,
                CardType.ATTACK, CardColors.AbstractCardEnum.DOCTOR_COLOR,
                CardRarity.UNCOMMON, CardTarget.ALL_ENEMY, false, POSITION, false);
        this.damage = this.baseDamage = ATTACK_DMG;

        this.setBackgroundTexture("img/512/guard_512.png", "img/1024/guard.png");

        this.setOrbTexture("img/orbs/cost.png", "img/orbs/cost_small.png");

    }

    @Override
    public void useCard(AbstractPlayer p, AbstractMonster m, boolean isSpJustUsed) {
        for(int for_i = 0; for_i < 5; for_i++) {
            this.addToBot(new RandomAttack(this, AbstractGameAction.AttackEffect.BLUNT_LIGHT, true));
        }
    }

    @Override
    public AbstractCard makeCopy() {
        return new CardGuardRedShift();
    }

    @Override
    public void upgradeCard() {
        this.upgradeDamage(UP_DMG);
    }

}
