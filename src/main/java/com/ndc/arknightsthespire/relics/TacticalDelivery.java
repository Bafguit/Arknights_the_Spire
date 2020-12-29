package com.ndc.arknightsthespire.relics;

import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.ndc.arknightsthespire.SPHandler;
import com.ndc.arknightsthespire.util.TextureLoader;

public class TacticalDelivery extends CustomRelic {
    public static final String ID = "ats:Tactical Delivery";
    private static final Texture IMG = TextureLoader.getTexture("img/relics/TacticalDelivery.png");

    public TacticalDelivery() {
        super(ID, IMG, RelicTier.BOSS, LandingSound.FLAT); // this relic is uncommon and sounds magic when you click it
    }

    @Override
    public void onEquip() {
        ++AbstractDungeon.player.energy.energyMaster;
    }

    @Override
    public void onUnequip() {
        --AbstractDungeon.player.energy.energyMaster;
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0]; // DESCRIPTIONS pulls from your localization file
    }
    @Override
    public AbstractRelic makeCopy() { // always override this method to return a new instance of your relic
        return new TacticalDelivery();
    }

}