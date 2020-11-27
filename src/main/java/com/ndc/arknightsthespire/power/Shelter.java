package com.ndc.arknightsthespire.power;

import basemod.devcommands.power.Power;
import basemod.interfaces.CloneablePowerInterface;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.ndc.arknightsthespire.util.TextureLoader;

//Gain 1 dex for the turn for each card played.

public class Shelter extends AbstractPower implements CloneablePowerInterface {
    public AbstractCreature source;
    public int toMulti = 1;

    public static final String POWER_ID = "ats:Shelter";
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    // We create 2 new textures *Using This Specific Texture Loader* - an 84x84 image and a 32x32 one.
    // There's a fallback "missing texture" image, so the game shouldn't crash if you accidentally put a non-existent file.
    private static final Texture tex84 = TextureLoader.getTexture("img/power/Shelter_84.png");
    private static final Texture tex32 = TextureLoader.getTexture("img/power/Shelter_32.png");

    public Shelter(final AbstractCreature owner, final AbstractCreature source, int amount, int multiple) {
        name = NAME;
        ID = POWER_ID;
        toMulti = multiple;
        this.amount = amount;

        this.owner = owner;
        this.source = source;

        type = PowerType.BUFF;
        isTurnBased = true;

        // We load those txtures here.
        this.region128 = new TextureAtlas.AtlasRegion(tex84, 0, 0, 84, 84);
        this.region48 = new TextureAtlas.AtlasRegion(tex32, 0, 0, 32, 32);

        updateDescription();
    }

    @Override
    public int onAttacked (DamageInfo info, int damageAmount) {
        flash();
        if(toMulti == 2) return Math.round(damageAmount / 2);
        return Math.round(damageAmount * 3 / 4);
    }

    @Override
    public void atStartOfTurn() {
        if (this.amount > 1)
            addToBot(new ReducePowerAction(AbstractDungeon.player, AbstractDungeon.player, "ats:Shelter", 1));
        else if (this.amount == 1) {
            addToBot(new RemoveSpecificPowerAction(AbstractDungeon.player, AbstractDungeon.player, "ats:Shelter"));
        }
    }

    // Update the description when you apply this power. (i.e. add or remove an "s" in keyword(s))
    @Override
    public void updateDescription() {
        description = DESCRIPTIONS[0] + (25 * toMulti) + DESCRIPTIONS[1];
    }

    @Override
    public AbstractPower makeCopy() {
        return new Shelter(owner, source, amount, toMulti);
    }
}
