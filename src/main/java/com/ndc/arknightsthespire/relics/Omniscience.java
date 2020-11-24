package com.ndc.arknightsthespire.relics;

import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.HealAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.ndc.arknightsthespire.SPHandler;
import com.ndc.arknightsthespire.power.ChargingDefense;
import com.ndc.arknightsthespire.util.TextureLoader;

public class Omniscience extends CustomRelic {
    public static final String ID = "Omniscience";
    private static final Texture IMG = TextureLoader.getTexture("img/relics/beta.png");
    private static final Texture OUTLINE = TextureLoader.getTexture("img/relics/outline/beta.png");

    public Omniscience() {
        super(ID, IMG, OUTLINE, RelicTier.BOSS, LandingSound.HEAVY); // this relic is uncommon and sounds magic when you click it
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0]; // DESCRIPTIONS pulls from your localization file
    }

    @Override
    public void atBattleStart() {
        AbstractPlayer p = AbstractDungeon.player;
        flash();
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new ChargingDefense(p, p, 3), 3));
    }

    @Override
    public AbstractRelic makeCopy() { // always override this method to return a new instance of your relic
        return new Omniscience();
    }

}