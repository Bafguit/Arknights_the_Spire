package com.ndc.arknightsthespire.power;

import basemod.interfaces.CloneablePowerInterface;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.TheBombPower;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndAddToHandEffect;
import com.ndc.arknightsthespire.SPHandler;
import com.ndc.arknightsthespire.cards.xCurse.Mine;
import com.ndc.arknightsthespire.util.TextureLoader;

import java.util.Iterator;

//Gain 1 dex for the turn for each card played.

public class MinePower extends AbstractPower implements CloneablePowerInterface {
    public AbstractCreature source;

    public static final String POWER_ID = "ats:Mine";
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    public boolean isUsed = true;

    private static final Texture tex84 = TextureLoader.getTexture("atsImg/power/Mine_84.png");
    private static final Texture tex32 = TextureLoader.getTexture("atsImg/power/Mine_32.png");

    public MinePower(int amount) {
        name = NAME;
        ID = POWER_ID;
        this.owner = AbstractDungeon.player;
        this.amount = amount;

        type = PowerType.BUFF;
        isTurnBased = true;

        this.region128 = new TextureAtlas.AtlasRegion(tex84, 0, 0, 84, 84);
        this.region48 = new TextureAtlas.AtlasRegion(tex32, 0, 0, 32, 32);

        updateDescription();
    }

    @Override
    public void atStartOfTurn() {
        if(this.isUsed) {
            flash();
            this.addToBot(new VFXAction(new ShowCardAndAddToHandEffect(new Mine())));
            if (this.amount > 1) {
                this.amount--;
                updateDescription();
            } else {
                this.addToBot(new RemoveSpecificPowerAction(AbstractDungeon.player, null, this));
            }
            this.isUsed = false;
        }
    }

    @Override
    public void onExhaust(AbstractCard card) {
        if(card.cardID == Mine.ID) {
            this.isUsed = true;
        }
    }

    // Update the description when you apply this power. (i.e. add or remove an "s" in keyword(s))
    @Override
    public void updateDescription() {
        description = DESCRIPTIONS[0] + this.amount;
    }

    @Override
    public AbstractPower makeCopy() {
        return new MinePower(amount);
    }
}
