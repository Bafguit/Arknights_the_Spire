package com.ndc.arknightsthespire.relics;

import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.evacipated.cardcrawl.mod.stslib.actions.common.StunMonsterAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.ndc.arknightsthespire.power.LoseAtkPower;
import com.ndc.arknightsthespire.util.TextureLoader;

public class HeartsKp extends CustomRelic {
    public static final String ID = "ats:Hearts K Plus";
    private static final Texture IMG = TextureLoader.getTexture("atsImg/relics/HeartsKp.png");

    public HeartsKp() {
        super(ID, IMG, RelicTier.BOSS, LandingSound.HEAVY); // this relic is uncommon and sounds magic when you click it
    }

    @Override
    public void obtain() {
        // Replace the starter relic, or just give the relic if starter isn't found
        if (AbstractDungeon.player.hasRelic(HeartsK.ID)) {
            for (int i=0; i<AbstractDungeon.player.relics.size(); ++i) {
                if (AbstractDungeon.player.relics.get(i).relicId.equals(HeartsK.ID)) {
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
    public void atBattleStart() {
        this.flash();
        for(AbstractMonster m : AbstractDungeon.getCurrRoom().monsters.monsters) {
            this.addToTop(new RelicAboveCreatureAction(m, this));
            this.addToBot(new StunMonsterAction(m, null, 1));
        }
    }

    @Override
    public boolean canSpawn() {
        return AbstractDungeon.player.hasRelic(HeartsK.ID);
    }

    @Override
    public AbstractRelic makeCopy() { // always override this method to return a new instance of your relic
        return new HeartsKp();
    }

}