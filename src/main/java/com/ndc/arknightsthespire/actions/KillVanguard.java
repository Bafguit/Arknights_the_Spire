//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.ndc.arknightsthespire.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class KillVanguard extends AbstractGameAction {
    private int energyGainAmt;
    private boolean isFast;
    private boolean hasSfx;
    private DamageInfo info;
    private AttackEffect effect;

    public KillVanguard(AbstractCreature target, DamageInfo info, AttackEffect effect, int energyAmt, boolean isFast, boolean isMuted) {
        this.info = info;
        this.setValues(target, info);
        this.energyGainAmt = energyAmt;
        this.actionType = ActionType.DAMAGE;
        this.effect = effect;
        this.duration = Settings.ACTION_DUR_FASTER;
        this.isFast = isFast;
        this.hasSfx = isMuted;
    }

    public KillVanguard(AbstractCreature target, DamageInfo info, AttackEffect effect, int energyAmt) {
        this(target, info, effect, energyAmt, false, false);
    }

    public void update() {
        if (this.duration == Settings.ACTION_DUR_FASTER && this.target != null) {
            addToBot(new DamageAction(target,
                    this.info, this.effect, this.isFast, this.hasSfx));
            if (((AbstractMonster)this.target).isDying || this.target.currentHealth <= 0) {
                this.addToBot(new GainEnergyAction(this.energyGainAmt));
            }

            if (AbstractDungeon.getCurrRoom().monsters.areMonstersBasicallyDead()) {
                AbstractDungeon.actionManager.clearPostCombatActions();
            }
        }

        this.tickDuration();
    }

    public boolean hasWeakened() {
        return true;
    }
}
