package com.ndc.arknightsthespire.relics;

import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import com.ndc.arknightsthespire.SPHandler;
import com.ndc.arknightsthespire.util.TextureLoader;

public class SpiritualRecovery extends CustomRelic {
    public static final String ID = "ats:Spiritual Recovery";
    private static final Texture IMG = TextureLoader.getTexture("atsImg/relics/SpiritualRecovery.png");
    private static final int SP_RECOVER = 1;

    public SpiritualRecovery() {
        super(ID, IMG, RelicTier.COMMON, LandingSound.MAGICAL); // this relic is uncommon and sounds magic when you click it
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0] + SP_RECOVER + DESCRIPTIONS[1]; // DESCRIPTIONS pulls from your localization file
    }

    @Override
    public int onPlayerHeal(int healAmount) {
        if(AbstractDungeon.getCurrRoom().phase == AbstractRoom.RoomPhase.COMBAT) {
            flash();
            SPHandler.addSp(SP_RECOVER);
        }

        return healAmount;
    }

    @Override
    public AbstractRelic makeCopy() { // always override this method to return a new instance of your relic
        return new SpiritualRecovery();
    }

}