package com.ndc.arknightsthespire.power;

import basemod.interfaces.CloneablePowerInterface;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import com.megacrit.cardcrawl.powers.WeakPower;
import com.ndc.arknightsthespire.util.TextureLoader;

//Gain 1 dex for the turn for each card played.

public class BurnPower extends AbstractPower implements CloneablePowerInterface {
    public AbstractCreature source;

    public static final String POWER_ID = "ats:Burn";
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    // We create 2 new textures *Using This Specific Texture Loader* - an 84x84 image and a 32x32 one.
    // There's a fallback "missing texture" image, so the game shouldn't crash if you accidentally put a non-existent file.
    private static final Texture tex84 = TextureLoader.getTexture("img/power/Burn_84.png");
    private static final Texture tex32 = TextureLoader.getTexture("img/power/Burn_32.png");

    public BurnPower(final AbstractCreature owner, final AbstractCreature source, int amount) {
        name = NAME;
        ID = POWER_ID;

        this.owner = owner;
        this.source = source;
        this.amount = amount;

        type = PowerType.DEBUFF;
        isTurnBased = true;

        // We load those txtures here.
        this.region128 = new TextureAtlas.AtlasRegion(tex84, 0, 0, 84, 84);
        this.region48 = new TextureAtlas.AtlasRegion(tex32, 0, 0, 32, 32);

        updateDescription();
    }

    @Override
    public void atStartOfTurn() {
        flash();
        for (int forI = 0; forI < this.amount; forI++) {
            addToBot(new DamageAction(owner, new DamageInfo(owner, 1, DamageInfo.DamageType.HP_LOSS),
                    AbstractGameAction.AttackEffect.FIRE, true));
        }
    }

    // Update the description when you apply this power. (i.e. add or remove an "s" in keyword(s))
    @Override
    public void updateDescription() {
        description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];
    }

    @Override
    public void atEndOfTurn(boolean isPlayer) {
        if(!isPlayer) {
            flash();
            for (int forI = 0; forI < this.amount; forI++) {
                addToBot(new DamageAction(owner, new DamageInfo(owner, 1, DamageInfo.DamageType.HP_LOSS),
                        AbstractGameAction.AttackEffect.FIRE, true));
            }
            if(this.owner.hasPower("ats:Guardian Obelisk") && this.amount > 1) {
                this.owner.getPower("ats:Guardian Obelisk").flash();
                addToBot(new ReducePowerAction(this.owner, this.source, "ats:Burn", Math.round(this.amount / 2)));
                addToBot(new RemoveSpecificPowerAction(this.owner, this.source, "ats:Guardian Obelisk"));
            } else {
                addToBot(new RemoveSpecificPowerAction(owner, owner, "ats:Burn"));
            }
        }
    }

    @Override
    public AbstractPower makeCopy() {
        return new BurnPower(owner, source, amount);
    }
}
