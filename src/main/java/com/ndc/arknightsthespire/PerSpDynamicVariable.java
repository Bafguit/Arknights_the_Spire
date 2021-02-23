package com.ndc.arknightsthespire;

import basemod.abstracts.DynamicVariable;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.ndc.arknightsthespire.cards.base.CardSPBase;
import com.ndc.arknightsthespire.cards.base.CardSpPreview;

public class PerSpDynamicVariable extends DynamicVariable {
    @Override
    public String key() {
        return "PS";
    }

    @Override
    public boolean isModified(AbstractCard abstractCard) {
        if(abstractCard instanceof CardSpPreview) {
            CardSpPreview card = (CardSpPreview) abstractCard;
            return card.isPerModified;
        }
        return false;
    }

    @Override
    public int value(AbstractCard abstractCard) {
        if(abstractCard instanceof CardSpPreview) {
            CardSpPreview card = (CardSpPreview) abstractCard;
            return (int) Math.floor(card.perBase * 100);
        } else if(abstractCard instanceof CardSPBase) {
            CardSPBase card = (CardSPBase) abstractCard;
            return (int) Math.floor(card.spCard.perBase * 100);
        }
        return 0;
    }

    @Override
    public int baseValue(AbstractCard abstractCard) {
        if(abstractCard instanceof CardSpPreview) {
            CardSpPreview card = (CardSpPreview) abstractCard;
            return (int) Math.floor(card.perTurn * 100);
        } else if(abstractCard instanceof CardSPBase) {
            CardSPBase card = (CardSPBase) abstractCard;
            return (int) Math.floor(card.spCard.perTurn * 100);
        }
        return 0;
    }

    @Override
    public boolean upgraded(AbstractCard abstractCard) {
        if(abstractCard instanceof CardSpPreview) {
            CardSpPreview card = (CardSpPreview) abstractCard;
            return card.upgradedPer;
        }
        return false;
    }
}
