package com.ndc.arknightsthespire.cards.rare;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.PenNibPower;
import com.ndc.arknightsthespire.CardColors;
import com.ndc.arknightsthespire.actions.RandomAttack;
import com.ndc.arknightsthespire.cards.CardSPBase;
import com.ndc.arknightsthespire.cards.PositionType;

public class CardGuardShadowAssault extends CardSPBase {
    public static final String ID = "ats:Shadow Assault";
    public static final String IMG_PATH = "img/cards/ShadowAssault.png";
    public static final String SP_IMG_PATH = "img/cards/ShadowAssault_sp.png";
    public static final PositionType POSITION = PositionType.GUARD;
    private static final int COST = 3;
    private static final int ATTACK_DMG = 10;
    private static final int UP_DMG = 1;
    private static final int SP = 40;
    private static final int UP_SP = 30;

    public CardGuardShadowAssault() { //Not Using
        super(ID, IMG_PATH, COST,
                CardType.ATTACK, CardColors.AbstractCardEnum.DOCTOR_COLOR,
                CardRarity.RARE, CardTarget.ALL_ENEMY, false, POSITION, true);
        this.damage = this.baseDamage = ATTACK_DMG;
        this.sp = SP;
        this.baseSP = SP;
        this.spCardImage = SP_IMG_PATH;
        this.isMultiDamage = true;

        this.setBackgroundTexture("img/512/guard_512.png", "img/1024/guard.png");

        this.setOrbTexture("img/orbs/cost.png", "img/orbs/cost_small.png");

    }

    @Override
    public void useCard(AbstractPlayer p, AbstractMonster m, boolean isSpJustUsed) {

        if(isSpJustUsed) {
            for (int for_i = 0; for_i < 4; for_i++) {
                this.addToBot(new RandomAttack(this,
                        AbstractGameAction.AttackEffect.SLASH_HORIZONTAL, true));
                this.addToBot(new RandomAttack(this,
                        AbstractGameAction.AttackEffect.SLASH_VERTICAL, true));
            }
            this.addToBot(new RandomAttack(this,
                    AbstractGameAction.AttackEffect.SLASH_HORIZONTAL, false));

            this.addToBot(new RandomAttack(this,
                    AbstractGameAction.AttackEffect.SLASH_HEAVY, false));
        }
        else {
            AbstractDungeon.actionManager.addToBottom(new DamageAllEnemiesAction(m,
                    this.multiDamage, this.damageTypeForTurn,
                    AbstractGameAction.AttackEffect.SLASH_HORIZONTAL, true));
            AbstractDungeon.actionManager.addToBottom(new DamageAllEnemiesAction(m,
                    this.multiDamage, this.damageTypeForTurn,
                    AbstractGameAction.AttackEffect.SLASH_HORIZONTAL, true));
            this.damage = this.baseDamage;
        }
    }

    @Override
    public AbstractCard makeCopy() {
        return new CardGuardShadowAssault();
    }

    @Override
    public void upgradeCard() {
        this.upgradeDamage(UP_DMG);
        this.upgradeSP(UP_SP);
    }

}
