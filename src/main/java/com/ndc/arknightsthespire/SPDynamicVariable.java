package com.ndc.arknightsthespire;

import basemod.abstracts.DynamicVariable;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.ndc.arknightsthespire.cards.CardSPBase;

public class SPDynamicVariable extends DynamicVariable {
    @Override
    public String key() {
        return "S";
    }

    @Override
    public boolean isModified(AbstractCard abstractCard) {
        if(abstractCard instanceof CardSPBase) {
            CardSPBase card = (CardSPBase) abstractCard;
            return card.isSpModified;
        }
        return false;
    }

    @Override
    public int value(AbstractCard abstractCard) {
        if(abstractCard instanceof CardSPBase) {
            CardSPBase card = (CardSPBase) abstractCard;
            return card.sp;
        }
        return 0;
    }

    @Override
    public int baseValue(AbstractCard abstractCard) {
        if(abstractCard instanceof CardSPBase) {
            CardSPBase card = (CardSPBase) abstractCard;
            return card.baseSp;
        }
        return 0;
    }

    @Override
    public boolean upgraded(AbstractCard abstractCard) {
        if(abstractCard instanceof CardSPBase) {
            CardSPBase card = (CardSPBase) abstractCard;
            return card.upgradedSp;
        }
        return false;
    }
}
