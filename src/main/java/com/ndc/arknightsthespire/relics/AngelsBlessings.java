package com.ndc.arknightsthespire.relics;

import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.ndc.arknightsthespire.SPHandler;
import com.ndc.arknightsthespire.cards.CardSPBase;
import com.ndc.arknightsthespire.cards.PositionType;
import com.ndc.arknightsthespire.power.AngelBless;
import com.ndc.arknightsthespire.util.TextureLoader;

import static com.megacrit.cardcrawl.cards.AbstractCard.*;

public class AngelsBlessings extends CustomRelic {
    public static final String ID = "ats:Angels Blessings";
    private static final Texture IMG = TextureLoader.getTexture("img/relics/AngelsBlessings.png");
    public CardSPBase spC;

    public AngelsBlessings() {
        super(ID, IMG, RelicTier.COMMON, LandingSound.MAGICAL); // this relic is uncommon and sounds magic when you click it
        this.counter = 0;
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0]; // DESCRIPTIONS pulls from your localization file
    }

    @Override
    public void onUseCard(AbstractCard card, UseCardAction action) {
        AbstractCard c = card;
        if(c instanceof CardSPBase) {
            this.spC = (CardSPBase) c;
        }

        if(this.spC.position == PositionType.SNIPER && this.spC.type == CardType.ATTACK) {
            if(this.counter < 3) this.counter++;
            if(this.counter == 3) {
                flash();
                AbstractPlayer p = AbstractDungeon.player;
                addToBot(new ApplyPowerAction(p, p, new AngelBless(p, p)));
            }
        }
    }

    @Override
    public int onAttackedToChangeDamage(DamageInfo info, int damageAmount) {
        AbstractPlayer p = AbstractDungeon.player;
        if(p.hasPower("ats:Angel Bless")) {
            this.counter = 0;
            addToBot(new RemoveSpecificPowerAction(p, p, "ats:Angel Bless"));
            return damageAmount + 1;
        }
        return damageAmount;
    }

    @Override
    public void atTurnStart() {
        flash();
        SPHandler.addSp(1);
    }

    @Override
    public AbstractRelic makeCopy() { // always override this method to return a new instance of your relic
        return new AngelsBlessings();
    }

}