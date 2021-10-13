//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.ndc.arknightsthespire.actions;

import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.AbstractGameAction.ActionType;
import com.megacrit.cardcrawl.actions.AbstractGameAction.AttackEffect;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.cards.DamageInfo.DamageType;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import com.megacrit.cardcrawl.vfx.combat.FlashAtkImgEffect;
import com.ndc.arknightsthespire.power.BurnPower;

import java.util.Iterator;

public class DamageAllMute extends AbstractGameAction {
    public int[] damage;
    private int baseDamage;
    private boolean firstFrame;
    private boolean utilizeBaseDamage;
    private boolean isMuted;
    private boolean hasBurn = false;
    private int burn = 0;
    private int vul = 0;

    public DamageAllMute(AbstractCreature source, int[] amount, DamageType type, AttackEffect effect, boolean isFast, boolean isMuted) {
        this.firstFrame = true;
        this.utilizeBaseDamage = false;
        this.source = source;
        this.damage = amount;
        this.actionType = ActionType.DAMAGE;
        this.damageType = type;
        this.attackEffect = effect;
        this.isMuted = isMuted;
        if (isFast) {
            this.duration = Settings.ACTION_DUR_XFAST;
        } else {
            this.duration = Settings.ACTION_DUR_FAST;
        }

    }

    public DamageAllMute(AbstractCreature source, int[] amount, DamageType type, AttackEffect effect, boolean isFast, boolean isMuted, int burn, int vul) {
        this(source, amount, type, effect, isFast, isMuted);
        this.hasBurn = true;
        this.burn = burn;
        this.vul = vul;
    }

    public DamageAllMute(int[] amount, DamageType type, AttackEffect effect, boolean isFast) {
        this(AbstractDungeon.player, amount, type, effect, isFast, true);
    }

    public DamageAllMute(int baseDamage, DamageType type, AttackEffect effect) {
        this(AbstractDungeon.player, (int[])null, type, effect, false, true);
        this.baseDamage = baseDamage;
        this.utilizeBaseDamage = true;
    }

    public void update() {
        int size;
        if (this.firstFrame) {
            size = AbstractDungeon.getCurrRoom().monsters.monsters.size();
            if (this.utilizeBaseDamage) {
                this.damage = DamageInfo.createDamageMatrix(this.baseDamage);
            }

            for(int i = 0; i < size; ++i) {
                if (!((AbstractMonster)AbstractDungeon.getCurrRoom().monsters.monsters.get(i)).isDying && ((AbstractMonster)AbstractDungeon.getCurrRoom().monsters.monsters.get(i)).currentHealth > 0 && !((AbstractMonster)AbstractDungeon.getCurrRoom().monsters.monsters.get(i)).isEscaping) {
                    if (this.isMuted) {
                        AbstractDungeon.effectList.add(new FlashAtkImgEffect(((AbstractMonster)AbstractDungeon.getCurrRoom().monsters.monsters.get(i)).hb.cX, ((AbstractMonster)AbstractDungeon.getCurrRoom().monsters.monsters.get(i)).hb.cY, this.attackEffect, true));
                    } else {
                        AbstractDungeon.effectList.add(new FlashAtkImgEffect(((AbstractMonster)AbstractDungeon.getCurrRoom().monsters.monsters.get(i)).hb.cX, ((AbstractMonster)AbstractDungeon.getCurrRoom().monsters.monsters.get(i)).hb.cY, this.attackEffect));
                    }
                }
            }

            this.firstFrame = false;
        }

        this.tickDuration();
        if (this.isDone) {
            Iterator var4 = AbstractDungeon.player.powers.iterator();

            while(var4.hasNext()) {
                AbstractPower p = (AbstractPower)var4.next();
                p.onDamageAllEnemies(this.damage);
            }

            int temp = AbstractDungeon.getCurrRoom().monsters.monsters.size();

            for(int i = 0; i < temp; ++i) {
                AbstractMonster m = (AbstractMonster)AbstractDungeon.getCurrRoom().monsters.monsters.get(i);
                if (!m.isDeadOrEscaped()) {
                    if (this.attackEffect == AttackEffect.POISON) {
                        m.tint.color.set(Color.CHARTREUSE);
                        m.tint.changeColor(Color.WHITE.cpy());
                    } else if (this.attackEffect == AttackEffect.FIRE) {
                        m.tint.color.set(Color.RED);
                        m.tint.changeColor(Color.WHITE.cpy());
                    }

                    m.damage(new DamageInfo(this.source, this.damage[i], this.damageType));

                    if(this.vul > 0) {
                        addToBot(new ApplyPowerAction(m, AbstractDungeon.player, new VulnerablePower(m, this.vul, false)));
                    }
                    if(this.hasBurn) {
                        addToBot(new ApplyPowerAction(m, AbstractDungeon.player, new BurnPower(m, this.source, this.burn)));
                    }
                }
            }

            if (AbstractDungeon.getCurrRoom().monsters.areMonstersBasicallyDead()) {
                AbstractDungeon.actionManager.clearPostCombatActions();
            }

            if (!Settings.FAST_MODE) {
                this.addToTop(new WaitAction(0.1F));
            }
        }

    }
}
