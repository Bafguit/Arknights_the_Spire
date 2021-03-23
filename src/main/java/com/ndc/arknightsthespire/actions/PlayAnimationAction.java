package com.ndc.arknightsthespire.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.ndc.arknightsthespire.monsters.act3.boss.Patirot;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class PlayAnimationAction extends AbstractGameAction {
    private static final Logger logger = LogManager.getLogger(PlayAnimationAction.class);
    private AbstractCreature owner;
    private String key;
    private String sKey;
    private String idle;
    private String sfxKey;
    public float sfxTiming;
    public boolean isImmediate = false;
    public AtsSound atsS = new AtsSound();

    public PlayAnimationAction(AbstractCreature owner, String key, float time, String sKey, String sfxKey) {
        this.duration = this.startDuration = DEFAULT_DURATION;
        this.sfxTiming = Math.max(this.duration - time, 0.0F);
        this.owner = owner;
        this.key = key;
        this.sKey = sKey;
        this.sfxKey = sfxKey;
        if(this.owner instanceof Patirot) {
            this.idle = ((Patirot) this.owner).curKey;
        } else {
            this.idle = "Idle";
        }
    }

    public PlayAnimationAction(AbstractCreature owner, String key) {
        this(owner, key, 0.5F, null,  null);
    }

    public PlayAnimationAction(AbstractCreature owner, String key, boolean isFast) {
        this(owner, key, 0.5F, null,  null);
        this.isImmediate = isFast;
    }

    public PlayAnimationAction(AbstractCreature owner, String key, float time, String sfxKey) {
        this(owner, key, time, null,  sfxKey);
    }

    public PlayAnimationAction(AbstractCreature owner, String key, boolean isFast, String sfxKey) {
        this(owner, key, 0.0F, null,  sfxKey);
        this.isImmediate = isFast;
    }

    @Override
    public void update() {
        if (AbstractDungeon.getMonsters().areMonstersBasicallyDead()) {
            this.isDone = true;
        } else {
            if (this.duration == this.startDuration) {
                owner.state.setAnimation(0, key, false);
                if(this.sKey != null) {
                    owner.state.addAnimation(0, sKey, false, 0.0F);
                }
                owner.state.addAnimation(0, this.idle, true, 0.0F);
                if(this.sfxKey != null && this.duration == this.sfxTiming) {
                    atsS.update();
                    atsS.play(this.sfxKey, 0.0F);
                }


            }

            if(this.duration == this.startDuration && this.isImmediate) {
                this.isDone = true;
            } else {
                this.tickDuration();
            }
        }
    }
}