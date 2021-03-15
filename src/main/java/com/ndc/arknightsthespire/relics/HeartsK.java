package com.ndc.arknightsthespire.relics;

import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.monsters.beyond.Transient;
import com.megacrit.cardcrawl.powers.GainStrengthPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.relics.RedMask;
import com.ndc.arknightsthespire.SPHandler;
import com.ndc.arknightsthespire.cards.base.CardSPBase;
import com.ndc.arknightsthespire.power.LoseAtkPower;
import com.ndc.arknightsthespire.util.TextureLoader;

public class HeartsK extends CustomRelic {
    public static final String ID = "ats:Hearts K";
    private static final Texture IMG = TextureLoader.getTexture("atsImg/relics/HeartsK.png");

    public HeartsK() {
        super(ID, IMG, RelicTier.STARTER, LandingSound.HEAVY); // this relic is uncommon and sounds magic when you click it
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
            this.addToBot(new ApplyPowerAction(m, null, new StrengthPower(m, -4), -4, true));
            if (!m.hasPower("Artifact")) {
                this.addToTop(new ApplyPowerAction(m, null, new GainStrengthPower(m, 4), 4, true));
            }
        }
    }

    @Override
    public AbstractRelic makeCopy() { // always override this method to return a new instance of your relic
        return new HeartsK();
    }

}