package com.ndc.arknightsthespire.relics;

import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.ndc.arknightsthespire.util.TextureLoader;

public class OldCoin extends CustomRelic {
    public static final String ID = "ats:Old Coin";
    private static final Texture IMG = TextureLoader.getTexture("img/relics/OldCoin.png");

    public OldCoin() {
        super(ID, IMG, RelicTier.UNCOMMON, LandingSound.CLINK); // this relic is uncommon and sounds magic when you click it
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0]; // DESCRIPTIONS pulls from your localization file
    }

    public void onEquip() {
        CardCrawlGame.sound.play("GOLD_GAIN");
        AbstractDungeon.player.gainGold(AbstractDungeon.player.gold);
    }

    @Override
    public AbstractRelic makeCopy() { // always override this method to return a new instance of your relic
        return new OldCoin();
    }

}