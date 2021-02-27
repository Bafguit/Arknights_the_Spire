package com.ndc.arknightsthespire;

import basemod.abstracts.DynamicVariable;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.ndc.arknightsthespire.cards.base.CardSPBase;

public class ResDynamicVariable extends DynamicVariable {
    @Override
    public String key() {
        return "ats:R";
    }

    @Override
    public boolean isModified(AbstractCard abstractCard) {
        if(abstractCard instanceof CardSPBase) {
            CardSPBase card = (CardSPBase) abstractCard;
            return card.isResModified;
        }
        return false;
    }

    @Override
    public int value(AbstractCard abstractCard) {
        if(abstractCard instanceof CardSPBase) {
            CardSPBase card = (CardSPBase) abstractCard;
            return card.baseRes;
        }
        return 0;
    }

    @Override
    public int baseValue(AbstractCard abstractCard) {
        if(abstractCard instanceof CardSPBase) {
            CardSPBase card = (CardSPBase) abstractCard;
            return card.res;
        }
        return 0;
    }

    @Override
    public boolean upgraded(AbstractCard abstractCard) {
        if(abstractCard instanceof CardSPBase) {
            CardSPBase card = (CardSPBase) abstractCard;
            return card.upgradedRes;
        }
        return false;
    }
}
