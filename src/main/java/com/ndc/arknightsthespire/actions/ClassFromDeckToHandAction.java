//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.ndc.arknightsthespire.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.AbstractGameAction.ActionType;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.cards.AbstractCard.CardType;
import com.megacrit.cardcrawl.cards.CardGroup.CardGroupType;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.UIStrings;
import com.ndc.arknightsthespire.cards.base.CardSPBase;
import com.ndc.arknightsthespire.cards.base.PositionType;

import java.util.Iterator;
import java.util.Random;

public class ClassFromDeckToHandAction extends AbstractGameAction {
    private AbstractPlayer p;
    private PositionType pos;

    public ClassFromDeckToHandAction(int amount, PositionType pos) {
        this.p = AbstractDungeon.player;
        this.setValues(this.p, AbstractDungeon.player, amount);
        this.actionType = ActionType.CARD_MANIPULATION;
        this.duration = Settings.ACTION_DUR_MED;
        this.pos = pos;
    }

    public ClassFromDeckToHandAction(PositionType pos) {
        this(1, pos);
    }

    public ClassFromDeckToHandAction(AbstractCard c) {
        this(1, c instanceof CardSPBase ? ((CardSPBase) c).position : PositionType.NONE);
    }

    public void update() {
        AbstractCard card;
            CardGroup tmp = new CardGroup(CardGroupType.UNSPECIFIED);
            Iterator var5 = this.p.drawPile.group.iterator();
            while(var5.hasNext()) {
                AbstractCard c = (AbstractCard)var5.next();
                if(c instanceof CardSPBase) {
                    CardSPBase spc = (CardSPBase) c;
                    if (spc.position == this.pos) {
                        tmp.group.add(c);
                    }
                }
            }

        if (tmp.group.size() != 0) {
            Random random = new Random();
            card = tmp.group.get(random.nextInt(tmp.group.size()));

            if (this.p.hand.size() == 10) {
                this.p.drawPile.moveToDiscardPile(card);
                this.p.createHandIsFullDialog();
            } else {
                this.p.drawPile.removeCard(card);
                this.p.hand.addToTop(card);
                card.triggerWhenDrawn();
            }

            this.p.hand.refreshHandLayout();
            this.p.hand.applyPowers();

        }
        this.isDone = true;

    }
}
