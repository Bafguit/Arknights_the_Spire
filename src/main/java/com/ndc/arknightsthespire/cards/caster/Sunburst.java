package com.ndc.arknightsthespire.cards.caster;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.FrailPower;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import com.megacrit.cardcrawl.powers.WeakPower;
import com.ndc.arknightsthespire.CardColors;
import com.ndc.arknightsthespire.actions.RandomAttack;
import com.ndc.arknightsthespire.cards.base.CardSPBase;
import com.ndc.arknightsthespire.cards.base.PositionType;
import com.ndc.arknightsthespire.power.BurnPower;

public class Sunburst extends CardSPBase {
    public static final String ID = "ats:Sunburst";
    public static final String IMG_PATH = "img/cards/Sunburst.png";
    public static final PositionType POSITION = PositionType.CASTER;
    private static final int COST = 0;
    private static final int DAMAGE = 10;
    private static final int SP = 9;
    private static final int UP_SP = 7;
    private static final int BURN = 2;
    private static final int UP_BURN = 1;

    public Sunburst() {
        super(ID, IMG_PATH, COST,
                CardType.ATTACK, CardColors.AbstractCardEnum.DOCTOR_COLOR,
                CardRarity.UNCOMMON, CardTarget.ALL_ENEMY, true, POSITION, true);
        this.damage = this.baseDamage = DAMAGE;
        this.magicNumber = this.baseMagicNumber = BURN;
        this.sp = this.baseSP = SP;
        this.isMultiDamage = true;

        this.setBackgroundTexture("img/512/caster_512.png", "img/1024/caster.png");

        this.setOrbTexture("img/orbs/cost.png", "img/orbs/cost_small.png");

    }

    @Override
    public void useCard(AbstractPlayer p, AbstractMonster m, boolean isSpJustUsed) {
        if(isSpJustUsed) {
            addToBot(new DamageAllEnemiesAction(m,
                    this.multiDamage, this.damageTypeForTurn,
                    AbstractGameAction.AttackEffect.FIRE, false));
            for (final AbstractMonster mo : AbstractDungeon.getCurrRoom().monsters.monsters) {
                addToBot(new ApplyPowerAction(mo, p,
                        new FrailPower(mo, 2, false), 2, true));
                addToBot(new ApplyPowerAction(mo, p,
                        new BurnPower(mo, p, 2), 2, true));
            }
        }
    }

    @Override
    public AbstractCard makeCopy() {
        return new Sunburst();
    }

    @Override
    public void upgradeCard() {
        this.upgradeMagicNumber(UP_BURN);
        this.upgradeSP(UP_SP);
    }

}
