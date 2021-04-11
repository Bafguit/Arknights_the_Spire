package com.ndc.arknightsthespire.cards.xCurse;

import com.megacrit.cardcrawl.actions.GameActionManager;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.DiscardAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.curses.Injury;
import com.megacrit.cardcrawl.cards.curses.Necronomicurse;
import com.megacrit.cardcrawl.cards.purple.DeusExMachina;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.ndc.arknightsthespire.CardColors;
import com.ndc.arknightsthespire.actions.AtsSFX;
import com.ndc.arknightsthespire.cards.base.CardSPBase;
import com.ndc.arknightsthespire.cards.base.PositionType;

import java.util.ArrayList;
import java.util.Iterator;

import static com.megacrit.cardcrawl.actions.AbstractGameAction.AttackEffect;
import static com.megacrit.cardcrawl.dungeons.AbstractDungeon.*;

public class SealedFloor extends CardSPBase {
    public static final String ID = "ats:Sealed Floor";
    public static final String IMG_PATH = "atsImg/cards/Sealed_Floor.png";
    public static final PositionType POSITION = PositionType.NONE;
    private static final int COST = -2;
    private static final int DAMAGE = 6;
    private static final int UP_DAMAGE = 3;

    public SealedFloor() {
        super(ID, IMG_PATH, COST,
                CardType.CURSE, CardColor.CURSE,
                CardRarity.CURSE, CardTarget.NONE, POSITION);
        this.selfRetain = true;
    }

    @Override
    public boolean canUse(AbstractPlayer p, AbstractMonster m) {
        return false;
    }

    @Override
    public void triggerOnManualDiscard() {
        if(player.hand.size() == 10) {
            ArrayList<AbstractCard> cards = new ArrayList<>();

            for(AbstractCard c : player.hand.group) {
                if(c.cardID != SealedFloor.ID) {
                    cards.add(c);
                }
            }

            if(cards.size() > 1) {
                AbstractCard c = cards.get(cards.size() - 1);
                player.hand.moveToDiscardPile(c);
                c.triggerOnManualDiscard();
                GameActionManager.incrementDiscard(false);
            }
        }
        this.addToBot(new MakeTempCardInHandAction(this.makeCopy()));
        player.discardPile.removeCard(this);
    }
/*
    @Override
    public void didDiscard() {
        if(player.hand.size() == 10) {
            ArrayList<AbstractCard> cards = new ArrayList<>();

            for(AbstractCard c : player.hand.group) {
                if(c.cardID != this.cardID) {
                    cards.add(c);
                }
            }

            if(cards.size() > 1) {
                AbstractCard c = cards.get(cards.size() - 1);
                player.hand.moveToDiscardPile(c);
                c.triggerOnManualDiscard();
                GameActionManager.incrementDiscard(false);
            }
        }
        this.addToBot(new MakeTempCardInHandAction(this.makeCopy()));
        player.discardPile.removeCard(this);
    }*/

    @Override
    public void triggerOnExhaust() {
        if(player.hand.size() == 10) {
            ArrayList<AbstractCard> cards = new ArrayList<>();

            for(AbstractCard c : player.hand.group) {
                if(c.cardID != SealedFloor.ID) {
                    cards.add(c);
                }
            }

            if(cards.size() > 1) {
                AbstractCard c = cards.get(cards.size() - 1);
                player.hand.moveToDiscardPile(c);
                c.triggerOnManualDiscard();
                GameActionManager.incrementDiscard(false);
            }
        }
        this.addToBot(new MakeTempCardInHandAction(this.makeCopy()));
    }

    @Override
    public void useCard(AbstractPlayer p, AbstractMonster m, boolean isSpJustUsed) { }

    @Override
    public AbstractCard makeCopy() {
        return new SealedFloor();
    }

    @Override
    public void upgradeCard() {
    }

}
