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

    public PlayAnimationAction(AbstractCreature owner, String key) {
        this.duration = DEFAULT_DURATION;
        this.owner = owner;
        this.key = key;
    }

    @Override
    public void update() {
        if (AbstractDungeon.getMonsters().areMonstersBasicallyDead()) {
            this.isDone = true;
        } else {
            if (this.duration == DEFAULT_DURATION) {
                owner.state.setAnimation(0, key, false);
                owner.state.addAnimation(0, "Idle", true, 0.0F);
            }
            this.tickDuration();
        }
    }
}