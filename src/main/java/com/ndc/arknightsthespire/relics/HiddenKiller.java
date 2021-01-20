//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.ndc.arknightsthespire.relics;

import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.AbstractCard.CardType;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.characters.AbstractPlayer.PlayerClass;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.PowerTip;
import com.megacrit.cardcrawl.powers.LoseStrengthPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.ndc.arknightsthespire.util.TextureLoader;

public class HiddenKiller extends CustomRelic {
    public static final String ID = "ats:Hidden Killer";
    private static final Texture IMG = TextureLoader.getTexture("img/relics/HiddenKiller.png");
    private boolean gainStrNext = false;
    private boolean firstTurn = false;

    public HiddenKiller() {
        super(ID, IMG, RelicTier.COMMON, LandingSound.CLINK);
        this.pulse = false;
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }

    @Override
    public void updateDescription(PlayerClass c) {
        this.tips.clear();
        this.tips.add(new PowerTip(this.name, this.description));
        this.initializeTips();
    }

    @Override
    public void atPreBattle() {
        this.flash();
        this.firstTurn = true;
        this.gainStrNext = true;
        if (!this.pulse) {
            this.beginPulse();
            this.pulse = true;
        }

    }

    @Override
    public void atTurnStart() {
        this.beginPulse();
        this.pulse = true;
        if (this.gainStrNext && !this.firstTurn) {
            this.flash();
            AbstractPlayer p = AbstractDungeon.player;
            addToBot(new ApplyPowerAction(p, p, new StrengthPower(p, 2), 2));
            addToBot(new ApplyPowerAction(p, p, new LoseStrengthPower(p, 2), 2));
        }

        this.firstTurn = false;
        this.gainStrNext = true;
    }

    @Override
    public void onUseCard(AbstractCard card, UseCardAction action) {
        if (card.type == CardType.ATTACK) {
            this.gainStrNext = false;
            this.pulse = false;
        }

    }

    @Override
    public void onVictory() {
        this.pulse = false;
    }

    @Override
    public AbstractRelic makeCopy() {
        return new HiddenKiller();
    }
}
