package com.ndc.arknightsthespire;

import basemod.abstracts.DynamicVariable;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.ndc.arknightsthespire.cards.base.CardSPBase;

public class EtDynamicVariable extends DynamicVariable {
    @Override
    public String key() {
        return "ats:E";
    }

    @Override
    public boolean isModified(AbstractCard abstractCard) {
        if(abstractCard instanceof CardSPBase) {
            CardSPBase card = (CardSPBase) abstractCard;
            return card.isEtModified;
        }
        return false;
    }

    @Override
    public int value(AbstractCard abstractCard) {
        if(abstractCard instanceof CardSPBase) {
            CardSPBase card = (CardSPBase) abstractCard;
            return card.baseEt;
        }
        return 0;
    }

    @Override
    public int baseValue(AbstractCard abstractCard) {
        if(abstractCard instanceof CardSPBase) {
            CardSPBase card = (CardSPBase) abstractCard;
            return card.et;
        }
        return 0;
    }

    @Override
    public boolean upgraded(AbstractCard abstractCard) {
        if(abstractCard instanceof CardSPBase) {
            CardSPBase card = (CardSPBase) abstractCard;
            return card.upgradedEt;
        }
        return false;
    }
}
