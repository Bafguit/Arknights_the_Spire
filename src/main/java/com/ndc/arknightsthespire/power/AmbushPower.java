package com.ndc.arknightsthespire.power;

import basemod.interfaces.CloneablePowerInterface;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.cards.DamageInfo.DamageType;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.ndc.arknightsthespire.cards.base.CardSPBase;
import com.ndc.arknightsthespire.cards.base.PositionType;
import com.ndc.arknightsthespire.util.TextureLoader;

//Gain 1 dex for the turn for each card played.

public class AmbushPower extends AbstractPower implements CloneablePowerInterface {
    public AbstractCreature source;

    public static final String POWER_ID = "ats:Ambush";
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    public int evade = 40;
    public boolean canUse = false;

    // We create 2 new textures *Using This Specific Texture Loader* - an 84x84 image and a 32x32 one.
    // There's a fallback "missing texture" image, so the game shouldn't crash if you accidentally put a non-existent file.
    private static final Texture tex84 = TextureLoader.getTexture("img/power/Ambush_84.png");
    private static final Texture tex32 = TextureLoader.getTexture("img/power/Ambush_32.png");

    public AmbushPower(final AbstractCreature owner, final AbstractCreature source, int eva) {
        name = NAME;
        ID = POWER_ID;

        this.owner = owner;
        this.source = source;
        evade = eva;

        type = PowerType.BUFF;
        isTurnBased = true;

        // We load those txtures here.
        this.region128 = new TextureAtlas.AtlasRegion(tex84, 0, 0, 84, 84);
        this.region48 = new TextureAtlas.AtlasRegion(tex32, 0, 0, 32, 32);

        updateDescription();
    }

    @Override
    public void onUseCard(AbstractCard card, UseCardAction action) {
        if(card instanceof CardSPBase) {
            CardSPBase c = (CardSPBase) card;
            if(c.position == PositionType.SNIPER && c.type == AbstractCard.CardType.ATTACK) {
                canUse = true;
            } else canUse = false;
        }
    }

    @Override
    public int onAttacked(DamageInfo info, int damage) {
        if(canUse && info.type == DamageType.NORMAL && damage > 0) {
            int r = (int) Math.random() * 100;
            if (r < evade) {
                flash();
                return 0;
            }
            else return damage;
        } else return damage;
    }

    @Override
    public void atStartOfTurn() {
        canUse = false;
    }

    // Update the description when you apply this power. (i.e. add or remove an "s" in keyword(s))
    @Override
    public void updateDescription() {
        description = DESCRIPTIONS[0] + evade + DESCRIPTIONS[1];
    }

    @Override
    public AbstractPower makeCopy() {
        return new AmbushPower(owner, source, evade);
    }
}
