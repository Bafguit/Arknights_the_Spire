package com.ndc.arknightsthespire.cards.sniper;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.PoisonPower;
import com.ndc.arknightsthespire.CardColors;
import com.ndc.arknightsthespire.cards.base.CardSPBase;
import com.ndc.arknightsthespire.cards.base.PositionType;

public class PoisonSpread extends CardSPBase {
    public static final String ID = "ats:Poison Spread";
    public static final String IMG_PATH = "img/cards/PoisonSpread.png";
    public static final PositionType POSITION = PositionType.SNIPER;
    private static final int COST = 1;
    private static final int ATTACK_DMG = 8;
    private static final int UP_DMG = 2;
    private static final int DEFAULT_SP = 3;
    private static final int UP_SP = 2;
    private static final int POS = 3;
    private static final int UP_POS = 1;

    public PoisonSpread() {
        super(ID, IMG_PATH, COST,
                CardType.ATTACK, CardColors.AbstractCardEnum.DOCTOR_COLOR,
                CardRarity.COMMON, CardTarget.ENEMY, true, POSITION, true, ATTACK_DMG, 0, POS, DEFAULT_SP);
    }

    @Override
    public void useCard(AbstractPlayer p, AbstractMonster m, boolean isSpJustUsed) {
        AbstractDungeon.actionManager.addToBottom(new com.megacrit.cardcrawl.actions.common.DamageAction(m,
                new DamageInfo(p, damage, this.damageTypeForTurn),
                AbstractGameAction.AttackEffect.POISON));
        if(isSpJustUsed) AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(m, p,
                new PoisonPower(m, p, this.magicNumber), this.magicNumber));
    }

    @Override
    public AbstractCard makeCopy() {
        return new PoisonSpread();
    }

    @Override
    public void upgradeCard() {
        this.upgradeMagicNumber(UP_POS);
        this.upgradeDamage(UP_DMG);
        this.upgradeSP(UP_SP);
    }

}
