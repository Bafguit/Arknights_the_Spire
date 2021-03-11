package com.ndc.arknightsthespire.power;

import basemod.interfaces.CloneablePowerInterface;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo.DamageType;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.FrailPower;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import com.ndc.arknightsthespire.util.TextureLoader;

//Gain 1 dex for the turn for each card played.

public class DurianPower extends AbstractPower implements CloneablePowerInterface {

    public static final String POWER_ID = "ats:Durian";
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    // We create 2 new textures *Using This Specific Texture Loader* - an 84x84 image and a 32x32 one.
    // There's a fallback "missing texture" image, so the game shouldn't crash if you accidentally put a non-existent file.
    private static final Texture tex84 = TextureLoader.getTexture("atsImg/power/Durian_84.png");
    private static final Texture tex32 = TextureLoader.getTexture("atsImg/power/Durian_32.png");

    public DurianPower() {
        name = NAME;
        ID = POWER_ID;

        this.owner = AbstractDungeon.player;

        type = PowerType.BUFF;

        // We load those txtures here.
        this.region128 = new TextureAtlas.AtlasRegion(tex84, 0, 0, 84, 84);
        this.region48 = new TextureAtlas.AtlasRegion(tex32, 0, 0, 32, 32);

        updateDescription();
    }

    @Override
    public void update(int slot) {
        updateDescription();
    }

    @Override
    public float atDamageGive(float damage, DamageType type) {
        return damage * 2.0F;
    }
    
    @Override
    public void atEndOfTurn(boolean isPlayer) {
      addToBot(new RemoveSpecificPowerAction(this.owner, this.owner, DurianPower.POWER_ID));
    }

    @Override
    public void updateDescription() {
        description = DESCRIPTIONS[0];
    }

    @Override
    public AbstractPower makeCopy() {
        return new DurianPower();
    }
}
