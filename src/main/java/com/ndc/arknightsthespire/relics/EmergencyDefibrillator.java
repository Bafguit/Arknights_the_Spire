package com.ndc.arknightsthespire.relics;

import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import com.ndc.arknightsthespire.power.EmergencyPower;
import com.ndc.arknightsthespire.util.TextureLoader;

public class EmergencyDefibrillator extends CustomRelic {
    public static final String ID = "ats:Emergency Defibrillator";
    private static final Texture IMG = TextureLoader.getTexture("img/relics/EmergencyDef.png");
    public boolean isUsed = false;

    public EmergencyDefibrillator() {
        super(ID, IMG, RelicTier.RARE, LandingSound.HEAVY); // this relic is uncommon and sounds magic when you click it
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0]; // DESCRIPTIONS pulls from your localization file
    }

    @Override
    public int onAttackedToChangeDamage(DamageInfo info, int damageAmount) {
        if(AbstractDungeon.getCurrRoom().phase == AbstractRoom.RoomPhase.COMBAT) {
            AbstractPlayer p = AbstractDungeon.player;
            if (!isUsed && !this.usedUp && info.owner.isPlayer && p.currentHealth - damageAmount < Math.round(p.maxHealth / 4)) {
                addToBot(new ApplyPowerAction(p, p, new EmergencyPower(p, p)));
                isUsed = true;
                return p.currentHealth - Math.round(p.maxHealth / 4);
            }
        }

        if(isUsed) {
            return 0;
        }
        else return damageAmount;
    }

    @Override
    public void atTurnStart() {
        if(isUsed) {
            this.usedUp();
            isUsed = false;
        }
    }

    @Override
    public AbstractRelic makeCopy() { // always override this method to return a new instance of your relic
        return new EmergencyDefibrillator();
    }

}