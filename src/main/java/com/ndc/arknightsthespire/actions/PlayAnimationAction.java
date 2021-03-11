package com.ndc.arknightsthespire.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class PlayAnimationAction extends AbstractGameAction {
    private static final Logger logger = LogManager.getLogger(PlayAnimationAction.class);
    private AbstractCreature owner;
    private String key;
    private String sKey;
    private String sfxKey;
    public float sfxTiming;
    public AtsSound atsS = new AtsSound();

    public PlayAnimationAction(AbstractCreature owner, String key, float time, String sKey, String sfxKey) {
        this.duration = DEFAULT_DURATION;
        this.sfxTiming = Math.max(this.duration - time, 0.0F);
        this.owner = owner;
        this.key = key;
        this.sKey = sKey;
        this.sfxKey = sfxKey;
    }

    public PlayAnimationAction(AbstractCreature owner, String key) {
        this(owner, key, 0.5F, null,  null);
    }

    public PlayAnimationAction(AbstractCreature owner, String key, float time, String sfxKey) {
        this(owner, key, time, null,  sfxKey);
    }

    @Override
    public void update() {
        if (AbstractDungeon.getMonsters().areMonstersBasicallyDead()) {
            this.isDone = true;
        } else {
            if (this.duration == DEFAULT_DURATION) {
                owner.state.setAnimation(0, key, false);
                if(this.sKey != null) {
                    owner.state.addAnimation(0, sKey, false, 0.0F);
                }
                owner.state.addAnimation(0, "Idle", true, 0.0F);
                if(this.sfxKey != null && this.duration == this.sfxTiming) {
                    atsS.update();
                    atsS.play(this.sfxKey, 0.0F);
                }
            }
            this.tickDuration();
        }
    }
}