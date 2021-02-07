package com.ndc.arknightsthespire.relics;

import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.evacipated.cardcrawl.mod.stslib.actions.tempHp.AddTemporaryHPAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.BufferPower;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.ndc.arknightsthespire.util.TextureLoader;

public class Omniscience extends CustomRelic {
    public static final String ID = "ats:Omniscience";
    private static final Texture IMG = TextureLoader.getTexture("img/relics/Omniscience.png");

    public Omniscience() {
        super(ID, IMG, RelicTier.RARE, LandingSound.HEAVY); // this relic is uncommon and sounds magic when you click it
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0]; // DESCRIPTIONS pulls from your localization file
    }

    @Override
    public void atBattleStartPreDraw() {
        AbstractPlayer p = AbstractDungeon.player;
        int temp = Math.round(p.maxHealth / 4);
        flash();
        addToBot(new AddTemporaryHPAction(p, p, temp));
    }

    @Override
    public AbstractRelic makeCopy() { // always override this method to return a new instance of your relic
        return new Omniscience();
    }

}