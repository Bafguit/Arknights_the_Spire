package com.ndc.arknightsthespire.cards.rare;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.NoBlockPower;
import com.ndc.arknightsthespire.CardColors;
import com.ndc.arknightsthespire.cards.CardSPBase;
import com.ndc.arknightsthespire.cards.PositionType;

import java.util.Iterator;

public class CardGuardTrueSilverSlash extends CardSPBase {
    public static final String ID = "ats:True Silver Slash";
    public static final String IMG_PATH = "img/cards/TrueSilverSlash.png";
    public static final PositionType POSITION = PositionType.GUARD;
    private static final int COST = 3;
    private static final int ATTACK_DMG = 8;
    private static final int UP_DMG = 2;
    private static final int SP = 40;

    public CardGuardTrueSilverSlash() {
        super(ID, IMG_PATH, COST,
                CardType.ATTACK, CardColors.AbstractCardEnum.DOCTOR_COLOR,
                CardRarity.RARE, CardTarget.ALL_ENEMY, true, POSITION, true);
        this.damage = this.baseDamage = ATTACK_DMG;
        this.sp = this.baseSP = SP;

        this.setBackgroundTexture("img/512/guard_512.png", "img/1024/guard.png");

        this.setOrbTexture("img/orbs/cost.png", "img/orbs/cost_small.png");

    }

    public int getGuardDeck() {

        int guardCount = 0;
        Iterator var1 = AbstractDungeon.player.masterDeck.group.iterator();

        while (var1.hasNext()) {
            AbstractCard c = (AbstractCard) var1.next();
            if(c instanceof CardSPBase) {
                CardSPBase card = (CardSPBase) c;
                if (card.position == POSITION) guardCount++;
            }
        }

        return guardCount;
    }

    @Override
    public void useCard(AbstractPlayer p, AbstractMonster m, boolean isSpJustUsed) {
        int cardAmount = getGuardDeck();

        if(isSpJustUsed) this.exhaust = true;

        for(int for_i = 0; for_i < (isSpJustUsed ? cardAmount : 3); for_i++) {
            AbstractDungeon.actionManager.addToBottom(new com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction(m,
                    DamageInfo.createDamageMatrix(this.damage, true), this.damageTypeForTurn,
                    AbstractGameAction.AttackEffect.SLASH_HEAVY, false));
        }
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p,
                new NoBlockPower(p, 1, false), 1));
    }

    @Override
    public AbstractCard makeCopy() {
        return new CardGuardTrueSilverSlash();
    }

    @Override
    public void upgradeCard() {
        this.upgradeDamage(UP_DMG);
    }

}
