package com.ndc.arknightsthespire.actions;

import basemod.ReflectionHacks;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.AttackDamageRandomEnemyAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.LightningEffect;
import com.ndc.arknightsthespire.power.BurnGroundPower;
import com.ndc.arknightsthespire.power.BurnPower;

public class RandomAttack extends AttackDamageRandomEnemyAction {

    private boolean skipWait;
    private boolean isMuted;
    private boolean hasBurn;
    private int burnAmount;

    public RandomAttack(AbstractCard card, AttackEffect effect) {
        this(card, effect, false, false);
    }

    public RandomAttack(AbstractCard card, AttackEffect effect, boolean superFast, boolean isMuted) {
        super(card, effect);
        this.skipWait = superFast;
        this.isMuted = isMuted;
    }

    public RandomAttack(AbstractCard card, AttackEffect effect, boolean superFast, boolean isMuted, boolean hasBurn, int burnAmount) {
        super(card, effect);
        this.skipWait = superFast;
        this.isMuted = isMuted;
        this.hasBurn = hasBurn;
        this.burnAmount = burnAmount;
    }

    public RandomAttack(AbstractCard card) {
        this(card, false);
    }

    public RandomAttack(AbstractCard card, boolean superFast) {
        this(card, AttackEffect.NONE, superFast, false);
    }

    @Override
    public void update() {
        this.target = AbstractDungeon.getMonsters().getRandomMonster((AbstractMonster)null, true, AbstractDungeon.cardRandomRng);
        if (this.target != null) {
            AbstractCard card = (AbstractCard) ReflectionHacks.getPrivate(this, AttackDamageRandomEnemyAction.class, "card");
            AttackEffect effect = (AttackEffect) ReflectionHacks.getPrivate(this, AttackDamageRandomEnemyAction.class, "effect");
            card.calculateCardDamage((AbstractMonster)this.target);
            if (AttackEffect.LIGHTNING == effect) {
                this.addToTop(new DamageAction(this.target, new DamageInfo(AbstractDungeon.player, card.damage, card.damageTypeForTurn), AttackEffect.NONE, this.skipWait, isMuted));
                this.addToTop(new SFXAction("ORB_LIGHTNING_EVOKE", 0.1F));
                this.addToTop(new VFXAction(new LightningEffect(this.target.hb.cX, this.target.hb.cY)));
            } else {
                this.addToTop(new DamageAction(this.target, new DamageInfo(AbstractDungeon.player, card.damage, card.damageTypeForTurn), effect, this.skipWait, isMuted));
            }
            if(hasBurn) {
                this.addToBot(new ApplyPowerAction(this.target, AbstractDungeon.player, new BurnPower(this.target, AbstractDungeon.player, this.burnAmount), this.burnAmount, true));
            }
        }

        this.isDone = true;
    }

}
