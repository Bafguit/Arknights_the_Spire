package com.ndc.arknightsthespire.relics;

import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.watcher.VigorPower;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.ndc.arknightsthespire.util.TextureLoader;

public class CrimsonP extends CustomRelic {
    public static final String ID = "ats:Crimson Plus";
    private static final Texture IMG = TextureLoader.getTexture("atsImg/relics/ShadAP.png");

    public CrimsonP() {
        super(ID, IMG, RelicTier.BOSS, LandingSound.HEAVY); // this relic is uncommon and sounds magic when you click it
    }

    @Override
    public void obtain() {
        // Replace the starter relic, or just give the relic if starter isn't found
        if (AbstractDungeon.player.hasRelic(Crimson.ID)) {
            for (int i=0; i<AbstractDungeon.player.relics.size(); ++i) {
                if (AbstractDungeon.player.relics.get(i).relicId.equals(Crimson.ID)) {
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
        return DESCRIPTIONS[0] + 3 + DESCRIPTIONS[1]; // DESCRIPTIONS pulls from your localization file
    }

    @Override
    public void atTurnStart() {
        if(!AbstractDungeon.player.hasPower(VigorPower.POWER_ID)) {
            this.flash();
            this.addToTop(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new VigorPower(AbstractDungeon.player, 3), 3));
            this.addToTop(new RelicAboveCreatureAction(AbstractDungeon.player, this));
        }
    }


    @Override
    public boolean canSpawn() {
        return AbstractDungeon.player.hasRelic(Crimson.ID);
    }

    @Override
    public AbstractRelic makeCopy() { // always override this method to return a new instance of your relic
        return new CrimsonP();
    }

}