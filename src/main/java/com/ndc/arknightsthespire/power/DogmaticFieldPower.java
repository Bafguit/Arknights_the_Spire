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
import com.ndc.arknightsthespire.util.TextureLoader;

//Gain 1 dex for the turn for each card played.

public class DogmaticFieldPower extends AbstractPower implements CloneablePowerInterface {
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
/*
    public void onGainedBlock(float blockAmount) {
        if(blockAmount > 0.0F) {
            flash();
            int overHeal = Math.round(p.currentHealth + blockAmount);

            System.out.println("XXXX" + blockAmount);
            if (overHeal > p.maxHealth) {
                int b = Math.round(blockAmount - (overHeal - p.maxHealth));
                addToBot(new LoseBlockAction(p, p, Math.round(b + (b/2))));
                AbstractDungeon.actionManager.currentAction.isDone = true;
            } else {
                addToBot(new LoseBlockAction(p, p, Math.round(blockAmount)));
                AbstractDungeon.actionManager.currentAction.isDone = true;
            }
            addToBot(new HealAction(p, p, Math.round(blockAmount)));
        }
    }*/
/*
    @Override
    public float modifyBlock(float blockAmount) {
        if(blockAmount > 0.0F) {
            flash();
            int overHeal = Math.round(p.currentHealth + blockAmount);

            System.out.println("YYYY" + blockAmount);
            if (overHeal > p.maxHealth) {
                int b = Math.round(blockAmount - (overHeal - p.maxHealth));
                addToBot(new HealAction(p, p, Math.round(blockAmount)));
                return b/2;
            } else {
                addToBot(new HealAction(p, p, Math.round(blockAmount)));
                return 0;
            }
        }
        return blockAmount;
    }*/

    @Override
    public int onHeal(int blockAmount) {
        int overHeal = Math.round(p.currentHealth + blockAmount);
        if (overHeal > p.maxHealth) {
            int b = Math.round(blockAmount - (overHeal - p.maxHealth));
            addToBot(new GainBlockAction(p, p, b/2));
            return b/2;
        }
        return blockAmount;
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
}
