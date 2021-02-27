package com.ndc.arknightsthespire;

import basemod.abstracts.DynamicVariable;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.ndc.arknightsthespire.cards.base.CardSPBase;

public class ArmDynamicVariable extends DynamicVariable {
    @Override
    public String key() {
        return "ats:A";
    }

    @Override
    public boolean isModified(AbstractCard abstractCard) {
        if(abstractCard instanceof CardSPBase) {
            CardSPBase card = (CardSPBase) abstractCard;
            return card.isArmModified;
        }
        return false;
    }

    @Override
    public int value(AbstractCard abstractCard) {
        if(abstractCard instanceof CardSPBase) {
            CardSPBase card = (CardSPBase) abstractCard;
            return card.baseArm;
        }
        return 0;
    }

    @Override
    public int baseValue(AbstractCard abstractCard) {
        if(abstractCard instanceof CardSPBase) {
            CardSPBase card = (CardSPBase) abstractCard;
            return card.arm;
        }
        return 0;
    }

    @Override
    public boolean upgraded(AbstractCard abstractCard) {
        if(abstractCard instanceof CardSPBase) {
            CardSPBase card = (CardSPBase) abstractCard;
            return card.upgradedArm;
        }
        return false;
    }
}
