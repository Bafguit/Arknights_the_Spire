package com.ndc.arknightsthespire.relics;

import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.ndc.arknightsthespire.SPHandler;
import com.ndc.arknightsthespire.util.TextureLoader;

public class MilitaryTradition extends CustomRelic {
    public static final String ID = "Military Tradition";
    private static final Texture IMG = TextureLoader.getTexture("img/relics/beta.png");
    private static final Texture OUTLINE = TextureLoader.getTexture("img/relics/outline/beta.png");

    public MilitaryTradition() {
        super(ID, IMG, OUTLINE, RelicTier.UNCOMMON, LandingSound.CLINK); // this relic is uncommon and sounds magic when you click it
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0] + 6 + DESCRIPTIONS[1]; // DESCRIPTIONS pulls from your localization file
    }

    @Override
    public void atBattleStart() {
        flash();
        SPHandler.addSp(6);
    }

    @Override
    public AbstractRelic makeCopy() { // always override this method to return a new instance of your relic
        return new MilitaryTradition();
    }

}