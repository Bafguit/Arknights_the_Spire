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
import com.ndc.arknightsthespire.actions.AtsSFX;
import com.ndc.arknightsthespire.actions.DamageAllMute;
import com.ndc.arknightsthespire.actions.RandomAttack;
import com.ndc.arknightsthespire.cards.base.CardSPBase;
import com.ndc.arknightsthespire.cards.base.PositionType;
import com.ndc.arknightsthespire.character.AtsEnum;
import com.ndc.arknightsthespire.power.BurnPower;

public class Sunburst extends CardSPBase {
    public static final String ID = "ats:Sunburst";
    public static final String IMG_PATH = "atsImg/cards/Sunburst.png";
    public static final PositionType POSITION = PositionType.CASTER;
    private static final int COST = 2;
    private static final int DAMAGE = 10;
    private static final int UP_DAMAGE = 2;
    private static final int SP = 7;
    private static final int BURN = 2;
    private static final int UP_BURN = 1;

    public Sunburst() {
        super(ID, IMG_PATH, COST,
                CardType.ATTACK, CardColors.AbstractCardEnum.DOCTOR_COLOR,
                CardRarity.UNCOMMON, CardTarget.ALL_ENEMY, true, POSITION, true, DAMAGE, 0, BURN, SP);
        this.isMultiDamage = true;
    }

    @Override
    public void useCard(AbstractPlayer p, AbstractMonster m, boolean isSpJustUsed) {
        addToBot(new AtsSFX("FLAME"));
        addToBot(new DamageAllMute(AbstractDungeon.player,
                this.multiDamage, DamageInfo.DamageType.NORMAL,
                AbstractGameAction.AttackEffect.FIRE, false, true, this.magicNumber, isSpJustUsed ? this.magicNumber : 0));
    }

    @Override
    public AbstractCard makeCopy() {
        return new Sunburst();
    }

    @Override
    public void upgradeCard() {
        this.upgradeMagicNumber(UP_BURN);
        this.upgradeDamage(UP_DAMAGE);
    }

}
