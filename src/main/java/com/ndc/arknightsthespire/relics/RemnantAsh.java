package com.ndc.arknightsthespire.relics;

import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.LoseHPAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import com.ndc.arknightsthespire.power.FracturedBodyPower;
import com.ndc.arknightsthespire.util.TextureLoader;

public class RemnantAsh extends CustomRelic {
    public static final String ID = "ats:Remnant Ash";
    private static final Texture IMG = TextureLoader.getTexture("atsImg/relics/Ash.png");

    public RemnantAsh() {
        super(ID, IMG, RelicTier.RARE, LandingSound.SOLID);
    }

    @Override
    public void setCounter(int setCounter) {
        if (setCounter == -2) {
            this.usedUp();
            this.counter = -2;
            this.stopPulse();
        } else if(setCounter == 2) {
            this.counter = setCounter;
            this.beginPulse();
            this.pulse = true;
        } else if(setCounter == -1) {
            this.counter = -1;
            this.stopPulse();
        }
        this.getUpdatedDescription();
    }

    @Override
    public void atTurnStart() {
        if(this.counter > 0) --this.counter;

        if (this.counter == 0) {
            this.addToTop(new RelicAboveCreatureAction(AbstractDungeon.player, this));
            AbstractPlayer p = AbstractDungeon.player;
            this.addToBot(new LoseHPAction(p, p, 9999));
            this.setCounter(-2);
        }
    }

    @Override
    public String getUpdatedDescription() {
        return this.pulse ? DESCRIPTIONS[1] + this.counter + DESCRIPTIONS[2] : DESCRIPTIONS[0];
    }

    @Override
    public int onAttackedToChangeDamage(DamageInfo info, int damageAmount) {
        if(AbstractDungeon.getCurrRoom().phase == AbstractRoom.RoomPhase.COMBAT && !this.usedUp) {
            AbstractPlayer p = AbstractDungeon.player;

            if (this.pulse) {
                this.addToTop(new RelicAboveCreatureAction(AbstractDungeon.player, this));
                return Math.min(p.currentHealth - 1, damageAmount);
            } else if (p.currentHealth - damageAmount < 1) {
                if(p.hasRelic(EmergencyDefibrillator.ID)) {
                    if(p.getRelic(EmergencyDefibrillator.ID).counter != -2) {
                        return damageAmount;
                    }
                }
                if(p.hasPower(FracturedBodyPower.POWER_ID)) {
                    return damageAmount;
                }
                this.addToTop(new RelicAboveCreatureAction(AbstractDungeon.player, this));
                this.setCounter(2);
                return p.currentHealth - 1;
            }
        }
        return damageAmount;
    }

    @Override
    public void atPreBattle() {
        if(this.counter != -2) this.setCounter(-1);
    }

    @Override
    public AbstractRelic makeCopy() { // always override this method to return a new instance of your relic
        return new RemnantAsh();
    }

}