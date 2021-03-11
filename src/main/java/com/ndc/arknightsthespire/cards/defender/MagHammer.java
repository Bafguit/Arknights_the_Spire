package com.ndc.arknightsthespire.cards.defender;

import com.evacipated.cardcrawl.mod.stslib.actions.common.StunMonsterAction;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.LoseStrengthPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.ndc.arknightsthespire.CardColors;
import com.ndc.arknightsthespire.actions.ApplyDefAction;
import com.ndc.arknightsthespire.actions.AtsSFX;
import com.ndc.arknightsthespire.actions.DamageAllMute;
import com.ndc.arknightsthespire.cards.base.CardSPBase;
import com.ndc.arknightsthespire.cards.base.PositionType;

public class MagHammer extends CardSPBase {
    public static final String ID = "ats:Magnetic Hammer";
    public static final String IMG_PATH = "atsImg/cards/MagneticHammer.png";
    public static final PositionType POSITION = PositionType.DEFENDER;
    private static final int COST = 2;
    private static final int BLOCK = 8;
    private static final int UP_BLOCK = 2;
    private static final int DAMAGE = 8;
    private static final int UP_DAMAGE = 2;
    private static final int SP = 10;
    private static final int WEAK = 3;
    private static final int UP_WEAK = 2;

    public MagHammer() {
        super(ID, IMG_PATH, COST,
                CardType.ATTACK, CardColors.AbstractCardEnum.DOCTOR_COLOR,
                CardRarity.UNCOMMON, CardTarget.ALL_ENEMY, false, POSITION, true, DAMAGE, BLOCK, WEAK, SP);
        this.isMultiDamage = true;
        this.setArm(40);
    }

    @Override
    public void useCard(AbstractPlayer p, AbstractMonster m, boolean isSpJustUsed) {
        addToBot(new AtsSFX("MAGNETIC_HAMMER"));
        addToBot(new DamageAllMute(this.multiDamage, this.damageTypeForTurn,
                AbstractGameAction.AttackEffect.BLUNT_HEAVY, false));
        addToBot(new GainBlockAction(p, this.block));
        if(isSpJustUsed) {
            for (final AbstractMonster mo : AbstractDungeon.getCurrRoom().monsters.monsters) {
                addToBot(new ApplyPowerAction(mo, p, new StrengthPower(mo, -this.magicNumber), -this.magicNumber));
                addToBot(new ApplyPowerAction(mo, p, new LoseStrengthPower(mo, -this.magicNumber), -this.magicNumber));
            }
        }
        //SP
    }

    @Override
    public AbstractCard makeCopy() {
        return new MagHammer();
    }

    @Override
    public void upgradeCard() {
        this.upgradeDamage(UP_DAMAGE);
        this.upgradeBlock(UP_BLOCK);
        this.upgradeMagicNumber(UP_WEAK);
    }



}
