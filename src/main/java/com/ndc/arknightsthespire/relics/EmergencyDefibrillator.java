package com.ndc.arknightsthespire.relics;

import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.HealAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.cards.red.Whirlwind;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.relics.ChemicalX;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import com.ndc.arknightsthespire.util.TextureLoader;

public class EmergencyDefibrillator extends CustomRelic {
    public static final String ID = "ats:Emergency Defibrillator";
    private static final Texture IMG = TextureLoader.getTexture("atsImg/relics/EmergencyDef.png");
    public boolean isUsed = false;

    public EmergencyDefibrillator() {
        super(ID, IMG, RelicTier.RARE, LandingSound.HEAVY);
    }

    @Override
    public void setCounter(int setCounter) {
        if (setCounter == -2) {
            this.usedUp();
            this.counter = -2;
            this.stopPulse();
        } else if(setCounter == -3) {
            this.counter = -3;
            this.beginPulse();
            this.pulse = true;
        } else if(setCounter == -1) {
            this.counter = -1;
            this.stopPulse();
        }
        this.getUpdatedDescription();
    }

    @Override
    public String getUpdatedDescription() {
        return this.pulse ? DESCRIPTIONS[1] : DESCRIPTIONS[0]; // DESCRIPTIONS pulls from your localization file
    }

    @Override
    public int onAttackedToChangeDamage(DamageInfo info, int damageAmount) {
        if(AbstractDungeon.getCurrRoom().phase == AbstractRoom.RoomPhase.COMBAT && !this.usedUp) {
            AbstractPlayer p = AbstractDungeon.player;

            if(this.pulse) {
                this.addToTop(new RelicAboveCreatureAction(AbstractDungeon.player, this));
                return 0;
            }

            if (!isUsed && p.currentHealth - damageAmount < Math.round(p.maxHealth / 4)) {
                this.setCounter(-3);
                if(p.currentHealth <= Math.round(p.maxHealth / 4)) {
                    return 0;
                } else {
                    return p.currentHealth - Math.round(p.maxHealth / 4);
                }
            }
        }

        return damageAmount;
    }

    @Override
    public void atTurnStart() {
        if(this.pulse) {
            AbstractPlayer p = AbstractDungeon.player;
            this.addToBot(new HealAction(p, p, Math.round(AbstractDungeon.player.maxHealth/2)));
            this.addToTop(new RelicAboveCreatureAction(p, this));
            this.setCounter(-2);
        }
    }

    @Override
    public void atPreBattle() {
        if(this.counter != -2) this.setCounter(-1);
    }

    @Override
    public AbstractRelic makeCopy() { // always override this method to return a new instance of your relic
        return new EmergencyDefibrillator();
    }

}