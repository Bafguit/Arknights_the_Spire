package com.ndc.arknightsthespire.cards.uncommon;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.ndc.arknightsthespire.CardColors;
import com.ndc.arknightsthespire.RandomAttack;
import com.ndc.arknightsthespire.SPHandler;
import com.ndc.arknightsthespire.cards.CardSPBase;
import com.ndc.arknightsthespire.cards.PositionType;

public class CardCasterMentalBurst extends CardSPBase {
    public static final String ID = "Mental Burst";
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    // Get object containing the strings that are displayed in the game.
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/cards/attack_beta.png";
    public static final PositionType POSITION = PositionType.CASTER;
    private static final int COST = 0;
    private static final int DAMAGE = 5;
    private static final int SP = 20;
    private static final int HIT = 6;
    private static final int UP_HIT = 2;

    public CardCasterMentalBurst() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION,
                CardType.ATTACK, CardColors.AbstractCardEnum.DOCTOR_COLOR,
                CardRarity.COMMON, CardTarget.ALL_ENEMY, true, POSITION, false);
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
        return new CardCasterMentalBurst();
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeMagicNumber(UP_HIT);
        }
    }

}
