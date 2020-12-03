package com.ndc.arknightsthespire.cards.uncommon;

import com.evacipated.cardcrawl.mod.stslib.actions.common.StunMonsterAction;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.ndc.arknightsthespire.CardColors;
import com.ndc.arknightsthespire.cards.CardSPBase;
import com.ndc.arknightsthespire.cards.PositionType;

public class CardDefenderMagHammer extends CardSPBase {
    public static final String ID = "ats:Magnetic Hammer";
    public static final String IMG_PATH = "img/cards/MagneticHammer.png";
    public static final PositionType POSITION = PositionType.DEFENDER;
    private static final int COST = 2;
    private static final int BLOCK = 7;
    private static final int UP_BLOCK = 3;
    private static final int DAMAGE = 7;
    private static final int UP_DAMAGE = 3;
    private static final int SP = 20;

    public CardDefenderMagHammer() {
        super(ID, IMG_PATH, COST,
                CardType.ATTACK, CardColors.AbstractCardEnum.DOCTOR_COLOR,
                CardRarity.UNCOMMON, CardTarget.ALL_ENEMY, false, POSITION, true);
        this.damage = this.baseDamage = DAMAGE;
        this.block = this.baseBlock = BLOCK;
        this.sp = this.baseSP = SP;
        this.isMultiDamage = true;

        this.setBackgroundTexture("img/512/defender_512.png", "img/1024/defender.png");

        this.setOrbTexture("img/orbs/cost.png", "img/orbs/cost_small.png");

    }

    @Override
    public void useCard(AbstractPlayer p, AbstractMonster m, boolean isSpJustUsed) {
        addToBot(new DamageAllEnemiesAction(m,
                this.multiDamage, this.damageTypeForTurn,
                AbstractGameAction.AttackEffect.BLUNT_HEAVY, false));
        if(isSpJustUsed) {
            for (final AbstractMonster mo : AbstractDungeon.getCurrRoom().monsters.monsters) {
                addToBot(new StunMonsterAction(mo, p, 1));
            }
        } else addToBot(new GainBlockAction(p, this.block));
        //SP
    }

    @Override
    public AbstractCard makeCopy() {
        return new CardDefenderMagHammer();
    }

    @Override
    public void upgradeCard() {
        this.upgradeDamage(UP_DAMAGE);
        this.upgradeBlock(UP_BLOCK);
    }



}
