//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.ndc.arknightsthespire.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.AbstractGameAction.ActionType;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.UIStrings;
import com.megacrit.cardcrawl.powers.NightmarePower;
import com.ndc.arknightsthespire.cards.base.CardSPBase;

import java.util.AbstractList;
import java.util.Iterator;

public class SelectCardFromHandAction extends AbstractGameAction {
    private static final UIStrings uiStrings;
    public static final String[] TEXT;
    private AbstractPlayer p;
    public static int numDiscarded;
    private static final float DURATION;

    public SelectCardFromHandAction(int amount) {
        this.setValues(target, source, amount);
        this.actionType = ActionType.CARD_MANIPULATION;
        this.duration = DURATION;
        this.p = AbstractDungeon.player;
        this.amount = amount;
    }

    public void update() {
        if (this.duration == DURATION) {
            if (this.p.hand.isEmpty()) {
                this.isDone = true;
            } else if (this.p.hand.size() == 1) {
                this.addToTop(new ClassFromDeckToHandAction(this.p.hand.getBottomCard()));
                this.isDone = true;
            } else if(this.amount == 2 && this.p.hand.size() == 2) {
                Iterator var4 = this.p.hand.group.iterator();
                while (var4.hasNext()) {
                    this.addToTop(new ClassFromDeckToHandAction((AbstractCard) var4.next()));
                }
                this.isDone = true;
            } else {
                AbstractDungeon.handCardSelectScreen.open(TEXT[0], this.amount, false, false);
                this.tickDuration();
            }
        } else {
            if (!AbstractDungeon.handCardSelectScreen.wereCardsRetrieved) {
                Iterator var5 = AbstractDungeon.handCardSelectScreen.selectedCards.group.iterator();
                while (var5.hasNext()) {
                    AbstractCard card = (AbstractCard) var5.next();
                    this.addToTop(new ClassFromDeckToHandAction(card));
                    AbstractDungeon.player.hand.addToHand(card);
                }
                AbstractDungeon.handCardSelectScreen.selectedCards.clear();
                AbstractDungeon.handCardSelectScreen.wereCardsRetrieved = true;
            }

            this.tickDuration();
        }
    }

    static {
        uiStrings = CardCrawlGame.languagePack.getUIString("SelectCard");
        TEXT = uiStrings.TEXT;
        DURATION = Settings.ACTION_DUR_XFAST;
    }
}
