package com.ndc.arknightsthespire.cards.rare;

import com.evacipated.cardcrawl.mod.stslib.actions.common.StunMonsterAction;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.unique.RemoveAllPowersAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.ndc.arknightsthespire.CardColors;
import com.ndc.arknightsthespire.cards.CardSPBase;
import com.ndc.arknightsthespire.cards.PositionType;

public class CardSpecialistDisruptionKick extends CardSPBase {
    public static final String ID = "ats:Disruption Kick";
    public static final String IMG_PATH = "img/cards/DisruptionKick.png";
    public static final PositionType POSITION = PositionType.SPECIALIST;
    private static final int COST = 2;
    private static final int UP_COST = 1;

    public CardSpecialistDisruptionKick() {
        super(ID, IMG_PATH, COST,
                CardType.ATTACK, CardColors.AbstractCardEnum.DOCTOR_COLOR,
                CardRarity.RARE, CardTarget.ALL_ENEMY, false, POSITION, false);
        this.damage = this.baseDamage = 1;

        this.setBackgroundTexture("img/512/specialist_512.png", "img/1024/specialist.png");

        this.setOrbTexture("img/orbs/cost.png", "img/orbs/cost_small.png");

        this.exhaust = true;

    }

    @Override
    public void useCard(AbstractPlayer p, AbstractMonster m, boolean isSpJustUsed) {
        AbstractDungeon.actionManager.addToBottom(new com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction(m,
                DamageInfo.createDamageMatrix(this.damage, true), this.damageTypeForTurn,
                AbstractGameAction.AttackEffect.LIGHTNING, true));
        for (final AbstractMonster mo : AbstractDungeon.getCurrRoom().monsters.monsters) {
            addToBot(new RemoveAllPowersAction(mo, false));
        }
    }

    @Override
    public AbstractCard makeCopy() {
        return new CardSpecialistDisruptionKick();
    }

    @Override
    public void upgradeCard() {
        this.upgradeBaseCost(UP_COST);
    }

}
