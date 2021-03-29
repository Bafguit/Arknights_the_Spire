package com.ndc.arknightsthespire.relics;

import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.ndc.arknightsthespire.SPHandler;
import com.ndc.arknightsthespire.power.BurnPower;
import com.ndc.arknightsthespire.util.TextureLoader;

public class RhineCircuitry extends CustomRelic {
    public static final String ID = "ats:Rhine Circuitry";
    private static final Texture IMG = TextureLoader.getTexture("atsImg/relics/RhineCircuity.png");

    public RhineCircuitry() {
        super(ID, IMG, RelicTier.RARE, LandingSound.HEAVY);
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0] + 1 + DESCRIPTIONS[1]; // DESCRIPTIONS pulls from your localization file
    }

    @Override
    public void onUseCard(AbstractCard card, UseCardAction action) {
        for(AbstractMonster m : AbstractDungeon.getMonsters().monsters) {
            flash();
            this.addToBot(new ApplyPowerAction(m, null, new BurnPower(m, null, 1), 1, true));
        }
    }

    @Override
    public AbstractRelic makeCopy() { // always override this method to return a new instance of your relic
        return new RhineCircuitry();
    }

}