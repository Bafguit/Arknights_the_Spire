package com.ndc.arknightsthespire.cards.uncommon;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.ndc.arknightsthespire.CardColors;
import com.ndc.arknightsthespire.cards.CardSPBase;
import com.ndc.arknightsthespire.cards.PositionType;

public class CardDefenderMagHammer extends CardSPBase {
    public static final String ID = "Magnetic Hammer";
    public static final String IMG_PATH = "img/cards/attack_beta.png";
    public static final PositionType POSITION = PositionType.DEFENDER;
    private static final int COST = 2;
    private static final int DAMAGE = 7;
    private static final int DEFAULT_SP = 15;

    public CardDefenderMagHammer() {
        super(ID, IMG_PATH, COST,
                CardType.ATTACK, CardColors.AbstractCardEnum.DOCTOR_COLOR,
                CardRarity.UNCOMMON, CardTarget.ALL_ENEMY, false, POSITION, true);
        this.damage = this.baseDamage = DAMAGE;
        this.sp = this.baseSP = DEFAULT_SP;

        this.setBackgroundTexture("img/512/defender_512.png", "img/1024/defender.png");

        this.setOrbTexture("img/orbs/cost.png", "img/orbs/cost_small.png");

    }

    @Override
    public void useCard(AbstractPlayer p, AbstractMonster m, boolean isSpJustUsed) {
        for (final AbstractMonster mo : AbstractDungeon.getCurrRoom().monsters.monsters) {
            //addToBot(new StunMonsterAction(mo, c));
        }

        //SP
    }

    @Override
    public AbstractCard makeCopy() {
        return new CardDefenderMagHammer();
    }

    @Override
    public void upgradeCard() {
    }



}
