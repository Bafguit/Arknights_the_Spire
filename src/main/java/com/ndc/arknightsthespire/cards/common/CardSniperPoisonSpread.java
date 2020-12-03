package com.ndc.arknightsthespire.cards.common;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.PoisonPower;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import com.ndc.arknightsthespire.CardColors;
import com.ndc.arknightsthespire.cards.CardSPBase;
import com.ndc.arknightsthespire.cards.PositionType;

public class CardSniperPoisonSpread extends CardSPBase {
    public static final String ID = "ats:Poison Spread";
    public static final String IMG_PATH = "img/cards/PoisonSpread.png";
    public static final PositionType POSITION = PositionType.SNIPER;
    private static final int COST = 1;
    private static final int ATTACK_DMG = 7;
    private static final int UP_DMG = 2;
    private static final int DEFAULT_SP = 3;
    private static final int UP_SP = 2;
    private static final int POS = 2;
    private static final int UP_POS = 3;

    public CardSniperPoisonSpread() {
        super(ID, IMG_PATH, COST,
                CardType.ATTACK, CardColors.AbstractCardEnum.DOCTOR_COLOR,
                CardRarity.COMMON, CardTarget.ENEMY, true, POSITION, true);
        this.damage = this.baseDamage = ATTACK_DMG;
        this.magicNumber = this.baseMagicNumber = POS;
        this.sp = DEFAULT_SP;
        this.baseSP = DEFAULT_SP;

        this.setBackgroundTexture("img/512/sniper_512.png", "img/1024/sniper.png");

        this.setOrbTexture("img/orbs/cost.png", "img/orbs/cost_small.png");

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
        return new CardSniperPoisonSpread();
    }

    @Override
    public void upgradeCard() {
        this.upgradeMagicNumber(UP_POS);
        this.upgradeDamage(UP_DMG);
        this.upgradeSP(UP_SP);
    }

}
