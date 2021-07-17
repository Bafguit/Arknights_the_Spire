package com.ndc.arknightsthespire.power;

import basemod.interfaces.CloneablePowerInterface;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo.DamageType;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.FrailPower;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import com.ndc.arknightsthespire.util.TextureLoader;

//Gain 1 dex for the turn for each card played.

public class SeriousPower extends AbstractPower implements CloneablePowerInterface {

    public static final String POWER_ID = "ats:Serious Mode";
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    // We create 2 new textures *Using This Specific Texture Loader* - an 84x84 image and a 32x32 one.
    // There's a fallback "missing texture" image, so the game shouldn't crash if you accidentally put a non-existent file.
    private static final Texture tex84 = TextureLoader.getTexture("atsImg/power/Serious_84.png");
    private static final Texture tex32 = TextureLoader.getTexture("atsImg/power/Serious_32.png");

    public SeriousPower() {
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
        if(this.amount != getHpPercent()) {
            this.amount = getHpPercent();
        }
        updateDescription();
    }

    @Override
    public float modifyBlock(float blockAmount) {
        return blockAmount / 2;
    }

    @Override
    public float atDamageGive(float damage, DamageType type) {
        return damage * (2.0F - calcHealth());
    }

    private float calcHealth() {

        float ch = this.owner.currentHealth;
        float maxh = this.owner.maxHealth / 10 * 7;
        float minh = this.owner.maxHealth - maxh;

        if(ch > minh) return (ch - minh) / maxh;
        else return 0;
    }

    private int getHpPercent() {
        return (int)(100.0F - calcHealth() * 100);
    }

    @Override
    public void updateDescription() {
        description = DESCRIPTIONS[0] + getHpPercent() + DESCRIPTIONS[1];
    }

    @Override
    public AbstractPower makeCopy() {
        return new SeriousPower();
    }
}
