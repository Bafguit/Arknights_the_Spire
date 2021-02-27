package com.ndc.arknightsthespire.actions;

import basemod.ReflectionHacks;
import com.evacipated.cardcrawl.mod.stslib.actions.common.StunMonsterAction;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
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

public class RandomAttack extends AbstractGameAction {

    private boolean skipWait;
    private boolean isMuted;
    private boolean hasBurn;
    private boolean hasStun;
    private int burnAmount;
    private DamageInfo info;

    public RandomAttack(DamageInfo info, AttackEffect effect, boolean superFast, boolean isMuted, boolean hasStun, boolean hasBurn, int burnAmount) {
        this.info = info;
        this.attackEffect = effect;
        this.skipWait = superFast;
        this.isMuted = isMuted;
        this.hasStun = hasStun;
        this.hasBurn = hasBurn;
        this.burnAmount = burnAmount;
    }

    public RandomAttack(DamageInfo info, AttackEffect effect) {
        this(info, effect, false, false);
    }

    public RandomAttack(DamageInfo info, AttackEffect effect, boolean superFast, boolean isMuted) {
        this(info, effect, superFast, isMuted, false, false, 0);
    }

    public RandomAttack(DamageInfo info, AttackEffect effect, boolean superFast, boolean isMuted, boolean hasBurn, int burnAmount) {
        this(info, effect, superFast, isMuted, false, hasBurn, burnAmount);
    }

    public RandomAttack(DamageInfo info, AttackEffect effect, boolean superFast, boolean isMuted, boolean hasStun) {
        this(info, effect, superFast, isMuted, hasStun, false, 0);
    }

    public RandomAttack(DamageInfo info) {
        this(info, false);
    }

    public RandomAttack(DamageInfo info, boolean superFast) {
        this(info, AttackEffect.NONE, superFast, false, false, false, 0);
    }

    @Override
    public void update() {
        this.target = AbstractDungeon.getMonsters().getRandomMonster((AbstractMonster)null, true, AbstractDungeon.cardRandomRng);
        if (this.target != null) {
            //AbstractCard card = (AbstractCard) ReflectionHacks.getPrivate(this, AttackDamageRandomEnemyAction.class, "card");
            //AttackEffect effect = (AttackEffect) ReflectionHacks.getPrivate(this, AttackDamageRandomEnemyAction.class, "effect");
            //card.calculateCardDamage((AbstractMonster)this.target);
            if (this.attackEffect == AttackEffect.LIGHTNING) {
                this.addToTop(new DamageAction(this.target, this.info, AttackEffect.NONE, this.skipWait, isMuted));
                this.addToTop(new SFXAction("ORB_LIGHTNING_EVOKE", 0.1F));
                this.addToTop(new VFXAction(new LightningEffect(this.target.hb.cX, this.target.hb.cY)));
            } else {
                this.addToTop(new DamageAction(this.target, this.info, this.attackEffect, this.skipWait, isMuted));
            }
            if(this.hasBurn) {
                this.addToBot(new ApplyPowerAction(this.target, AbstractDungeon.player, new BurnPower(this.target, AbstractDungeon.player, this.burnAmount), this.burnAmount, true));
            }
            if(this.hasStun) {
                this.addToBot(new StunMonsterAction((AbstractMonster) this.target, AbstractDungeon.player));
            }
        }

        this.isDone = true;
    }

}
