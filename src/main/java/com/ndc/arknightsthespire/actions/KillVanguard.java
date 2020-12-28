//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.ndc.arknightsthespire.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.FlashAtkImgEffect;

public class KillVanguard extends AbstractGameAction {
    private int energyGainAmt;
    private int repeatAmt;
    private boolean isFast;
    private boolean hasSfx;
    private DamageInfo info;
    private AttackEffect effect;

    public KillVanguard(AbstractCreature target, DamageInfo info, AttackEffect effect, int energyAmt, boolean isFast, boolean isMuted, int repeat) {
        this.info = info;
        this.setValues(target, info);
        this.energyGainAmt = energyAmt;
        this.actionType = ActionType.DAMAGE;
        this.effect = effect;
        this.duration = Settings.ACTION_DUR_FASTER;
        this.isFast = isFast;
        this.hasSfx = isMuted;
        this.repeatAmt = repeat;
    }

    public KillVanguard(AbstractCreature target, DamageInfo info, AttackEffect effect, int energyAmt, boolean isFast, boolean isMuted) {
        this(target, info, effect, energyAmt, isFast, isMuted, 1);
    }

    public KillVanguard(AbstractCreature target, DamageInfo info, AttackEffect effect, int energyAmt) {
        this(target, info, effect, energyAmt, false, false, 1);
    }

    public KillVanguard(AbstractCreature target, DamageInfo info, AttackEffect effect, int energyAmt, int repeat) {
        this(target, info, effect, energyAmt, false, false, repeat);
    }

    public void update() {
        if (this.duration == Settings.ACTION_DUR_FASTER && this.target != null) {
            for(int for_i = 0; for_i < this.repeatAmt; for_i++) {
                AbstractDungeon.effectList.add(new FlashAtkImgEffect(this.target.hb.cX, this.target.hb.cY, this.effect, this.hasSfx));
                this.target.damage(this.info);
            }
            if (((AbstractMonster)this.target).isDying || this.target.currentHealth <= 0) {
                addToBot(new GainEnergyAction(this.energyGainAmt));
            }

            if (AbstractDungeon.getCurrRoom().monsters.areMonstersBasicallyDead()) {
                AbstractDungeon.actionManager.clearPostCombatActions();
            }
        }

        this.tickDuration();
    }
}
