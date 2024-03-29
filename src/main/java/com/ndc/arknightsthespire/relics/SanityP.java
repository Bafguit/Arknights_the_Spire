package com.ndc.arknightsthespire.relics;

import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.ui.campfire.AbstractCampfireOption;
import com.ndc.arknightsthespire.SPHandler;
import com.ndc.arknightsthespire.cards.base.CardSPBase;
import com.ndc.arknightsthespire.ui.MaxSpOption;
import com.ndc.arknightsthespire.util.TextureLoader;

import java.util.ArrayList;

public class SanityP extends CustomRelic {
    public static final String ID = "ats:Sanity Plus";
    private static final Texture IMG = TextureLoader.getTexture("atsImg/relics/SanityP.png");

    public SanityP() {
        super(ID, IMG, RelicTier.BOSS, LandingSound.FLAT); // this relic is uncommon and sounds magic when you click it
    }

    @Override
    public void obtain() {
        // Replace the starter relic, or just give the relic if starter isn't found
        if (AbstractDungeon.player.hasRelic("ats:Sanity")) {
            for (int i=0; i<AbstractDungeon.player.relics.size(); ++i) {
                if (AbstractDungeon.player.relics.get(i).relicId.equals("ats:Sanity")) {
                    instantObtain(AbstractDungeon.player, i, true);
                    break;
                }
            }
        } else {
            super.obtain();
        }
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0]; // DESCRIPTIONS pulls from your localization file
    }

    @Override
    public void onUseCard(AbstractCard c, UseCardAction useCardAction) {
        if(c instanceof CardSPBase) {
            CardSPBase card = (CardSPBase) c;
            if (card.canUseSP && card.isSpJustUsed && this.pulse && card.sp > 0) {
                int temp = Math.round(card.baseSP / 2);
                addToTop(new RelicAboveCreatureAction(AbstractDungeon.player, this));
                SPHandler.addSp(temp);
                addToBot(new GainBlockAction(AbstractDungeon.player, temp));
                this.pulse = false;
            }
        }
    }

    @Override
    public void atTurnStart() {
        this.beginPulse();
        this.pulse = true;
    }

    @Override
    public void onVictory() {
        this.pulse = false;
    }

    @Override
    public boolean canSpawn() {
        return AbstractDungeon.player.hasRelic("ats:Sanity");
    }
/*
    @Override
    public void addCampfireOption(ArrayList<AbstractCampfireOption> options) {
        options.add(new MaxSpOption(SPHandler.getUpToMaxSp()));
    }*/

    @Override
    public AbstractRelic makeCopy() { // always override this method to return a new instance of your relic
        return new SanityP();
    }

}