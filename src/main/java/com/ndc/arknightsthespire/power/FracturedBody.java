package com.ndc.arknightsthespire.power;

import basemod.interfaces.CloneablePowerInterface;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.actions.common.LoseHPAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.ndc.arknightsthespire.util.TextureLoader;

//Gain 1 dex for the turn for each card played.

public class FracturedBody extends AbstractPower implements CloneablePowerInterface {
    public AbstractCreature source;

    public static final String POWER_ID = "Fractured Body";
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    // We create 2 new textures *Using This Specific Texture Loader* - an 84x84 image and a 32x32 one.
    // There's a fallback "missing texture" image, so the game shouldn't crash if you accidentally put a non-existent file.
    private static final Texture tex84 = TextureLoader.getTexture("img/power/FracturedBody_84.png");
    private static final Texture tex32 = TextureLoader.getTexture("img/power/FracturedBody_32.png");

    public FracturedBody(final AbstractCreature owner, final AbstractCreature source, int amount) {
        name = NAME;
        ID = POWER_ID;

        this.owner = owner;
        this.source = source;
        this.amount = amount;

        type = PowerType.BUFF;
        isTurnBased = true;

        // We load those txtures here.
        this.region128 = new TextureAtlas.AtlasRegion(tex84, 0, 0, 84, 84);
        this.region48 = new TextureAtlas.AtlasRegion(tex32, 0, 0, 32, 32);

        updateDescription();
    }
/*
    @Override
    public int onAttacked(DamageInfo info, int damage) {
        if(damage >= AbstractDungeon.player.currentHealth && AbstractDungeon.player.currentHealth != 1) {
            flash();
            return AbstractDungeon.player.currentHealth - 1;
        }
        else if(damage >= AbstractDungeon.player.currentHealth && AbstractDungeon.player.currentHealth == 1) {
            flash();
            return 0;
        }

        return damage;
    }
*/
    @Override
    public int onAttackedToChangeDamage(DamageInfo info, int damageAmount) {
        if(damageAmount >= AbstractDungeon.player.currentHealth && AbstractDungeon.player.currentHealth != 1) {
            flash();
            return AbstractDungeon.player.currentHealth - 1;
        }
        else if(damageAmount >= AbstractDungeon.player.currentHealth && AbstractDungeon.player.currentHealth == 1) {
            flash();
            return 0;
        }

        return damageAmount;
    }

    @Override
    public void atStartOfTurn() {
        AbstractPlayer p = AbstractDungeon.player;
        if (this.amount > 1)
            addToBot(new ReducePowerAction(p, p, "Fractured Body", 1));
        else if (this.amount == 1) {
            addToBot(new LoseHPAction(p, p, p.currentHealth - 1));
            addToBot(new RemoveSpecificPowerAction(p, p, "Fractured Body"));
        }
    }

    // Update the description when you apply this power. (i.e. add or remove an "s" in keyword(s))
    @Override
    public void updateDescription() {
        description = DESCRIPTIONS[0];
    }

    @Override
    public AbstractPower makeCopy() {
        return new FracturedBody(owner, source, amount);
    }
}
