package com.ndc.arknightsthespire.power;

import basemod.interfaces.CloneablePowerInterface;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.cards.DamageInfo.DamageType;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.ndc.arknightsthespire.util.TextureLoader;

//Gain 1 dex for the turn for each card played.

public class WeakeningPower extends AbstractPower implements CloneablePowerInterface {
    public AbstractCreature source;
    private boolean isLower;
    private int number = 20;

    public static final String POWER_ID = "ats:Weakening";
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    // We create 2 new textures *Using This Specific Texture Loader* - an 84x84 image and a 32x32 one.
    // There's a fallback "missing texture" image, so the game shouldn't crash if you accidentally put a non-existent file.
    private static final Texture tex84 = TextureLoader.getTexture("img/power/Weakening_84.png");
    private static final Texture tex32 = TextureLoader.getTexture("img/power/Weakening_32.png");

    public WeakeningPower(final AbstractCreature owner, final AbstractCreature source, int num) {
        name = NAME;
        ID = POWER_ID;

        this.owner = owner;
        this.source = source;
        this.number = num;

        type = PowerType.BUFF;
        isTurnBased = true;

        // We load those txtures here.
        this.region128 = new TextureAtlas.AtlasRegion(tex84, 0, 0, 84, 84);
        this.region48 = new TextureAtlas.AtlasRegion(tex32, 0, 0, 32, 32);

        updateDescription();
    }

    @Override
    public float atDamageGive(float damage, DamageType type) {
        for (final AbstractMonster mo : AbstractDungeon.getCurrRoom().monsters.monsters) {
            if(mo.currentHealth < Math.round(mo.maxHealth / 10 * 4)) {
                this.isLower = true;
            }
        }

        if(isLower) {
            this.isLower = false;
            return damage * (1 + this.number / 100);
        }
        else {
            this.isLower = false;
            return damage;
        }
    }

    // Update the description when you apply this power. (i.e. add or remove an "s" in keyword(s))
    @Override
    public void updateDescription() {
        description = DESCRIPTIONS[0] + this.number + DESCRIPTIONS[1];
    }

    @Override
    public AbstractPower makeCopy() {
        return new WeakeningPower(owner, source, number);
    }
}
