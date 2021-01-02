package com.ndc.arknightsthespire.power;

import basemod.interfaces.CloneablePowerInterface;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.HealAction;
import com.megacrit.cardcrawl.actions.utility.LoseBlockAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.DexterityPower;
import com.megacrit.cardcrawl.powers.FrailPower;
import com.ndc.arknightsthespire.interfaces.OnGainBlockPower;
import com.ndc.arknightsthespire.interfaces.PreGainBlockPower;
import com.ndc.arknightsthespire.util.TextureLoader;

//Gain 1 dex for the turn for each card played.

public class DogmaticFieldPower extends AbstractPower implements CloneablePowerInterface, OnGainBlockPower {
    public AbstractCreature source;

    public static final String POWER_ID = "ats:Dogmatic Field";
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    public static final AbstractPlayer p = AbstractDungeon.player;

    // We create 2 new textures *Using This Specific Texture Loader* - an 84x84 image and a 32x32 one.
    // There's a fallback "missing texture" image, so the game shouldn't crash if you accidentally put a non-existent file.
    private static final Texture tex84 = TextureLoader.getTexture("img/power/DogmaticField_84.png");
    private static final Texture tex32 = TextureLoader.getTexture("img/power/DogmaticField_32.png");

    public DogmaticFieldPower(final AbstractCreature owner, final AbstractCreature source) {
        name = NAME;
        ID = POWER_ID;

        this.owner = owner;
        this.source = source;

        type = PowerType.BUFF;
        isTurnBased = false;

        // We load those txtures here.
        this.region128 = new TextureAtlas.AtlasRegion(tex84, 0, 0, 84, 84);
        this.region48 = new TextureAtlas.AtlasRegion(tex32, 0, 0, 32, 32);

        updateDescription();
    }

    // Update the description when you apply this power. (i.e. add or remove an "s" in keyword(s))
    @Override
    public void updateDescription() {
        description = DESCRIPTIONS[0];
    }

    @Override
    public AbstractPower makeCopy() {
        return new DogmaticFieldPower(owner, source);
    }

    @Override
    public void onGainBlock(AbstractCreature owner, AbstractCreature source, int blockAmount) {
        int overHeal = p.currentHealth + blockAmount - p.maxHealth;
        flash();
        p.loseBlock((overHeal > 0 ? Math.round(blockAmount - overHeal/2) : blockAmount), true);
        AbstractDungeon.actionManager.addToBottom(new HealAction(p, p, blockAmount, 0.15F));
    }
}
