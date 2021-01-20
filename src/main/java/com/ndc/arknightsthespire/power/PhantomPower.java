package com.ndc.arknightsthespire.power;

import basemod.interfaces.CloneablePowerInterface;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardQueueItem;
import com.megacrit.cardcrawl.cards.DamageInfo.DamageType;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.ndc.arknightsthespire.cards.base.CardSPBase;
import com.ndc.arknightsthespire.cards.base.PositionType;
import com.ndc.arknightsthespire.util.TextureLoader;

import java.util.ArrayList;
import java.util.Iterator;

//Gain 1 dex for the turn for each card played.

public class PhantomPower extends AbstractPower implements CloneablePowerInterface {

    public static final String POWER_ID = "ats:Phantom";
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    // We create 2 new textures *Using This Specific Texture Loader* - an 84x84 image and a 32x32 one.
    // There's a fallback "missing texture" image, so the game shouldn't crash if you accidentally put a non-existent file.
    private static final Texture tex84 = TextureLoader.getTexture("img/power/Phantom_84.png");
    private static final Texture tex32 = TextureLoader.getTexture("img/power/Phantom_32.png");

    public PhantomPower() {
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
    public void onUseCard(AbstractCard card, UseCardAction action) {
        if(card instanceof CardSPBase) {
            CardSPBase cSP = (CardSPBase) card;
            if (cSP.position == PositionType.SPECIALIST) {
                this.flash();
                ArrayList<AbstractCard> groupCopy = new ArrayList();
                Iterator var4 = AbstractDungeon.player.hand.group.iterator();

                while (true) {
                    while (var4.hasNext()) {
                        AbstractCard c = (AbstractCard) var4.next();
                        if (c.cost > 0 && c.costForTurn > 0 && !c.freeToPlayOnce) {
                            groupCopy.add(c);
                        }
                    }

                    var4 = AbstractDungeon.actionManager.cardQueue.iterator();

                    while (var4.hasNext()) {
                        CardQueueItem i = (CardQueueItem) var4.next();
                        if (i.card != null) {
                            groupCopy.remove(i.card);
                        }
                    }

                    AbstractCard c = null;
                    if (!groupCopy.isEmpty()) {
                        Iterator var9 = groupCopy.iterator();

                        while (var9.hasNext()) {
                            AbstractCard cc = (AbstractCard) var9.next();
                        }

                        c = (AbstractCard) groupCopy.get(AbstractDungeon.cardRandomRng.random(0, groupCopy.size() - 1));
                    }

                    if (c != null) {
                        c.freeToPlayOnce = true;
                    }
                    break;
                }
            }
        }

    }

    @Override
    public void updateDescription() {
        description = DESCRIPTIONS[0];
    }

    @Override
    public AbstractPower makeCopy() {
        return new PhantomPower();
    }
}
