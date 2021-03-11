package com.ndc.arknightsthespire.monsters.intent;

import actlikeit.RazIntent.CustomIntent;
import basemod.ReflectionHacks;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.UIStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.ndc.arknightsthespire.character.AtsEnum;

public class ArtsAttackIntent extends CustomIntent {
    public static final String ID = "ArtsAttack";
    private static final UIStrings uiStrings = CardCrawlGame.languagePack.getUIString(ID);
    private static final String[] TEXT = uiStrings.TEXT;

    public ArtsAttackIntent() {
        super(AtsEnum.ATTACK_ARTS, TEXT[0], "atsImg/ui/intent/art.png", "atsImg/ui/intent/arts_tip.png");
    }

    @Override
    public String description(AbstractMonster mo) {
        String result = TEXT[1];
        result += ReflectionHacks.getPrivate(mo, AbstractMonster.class, "intentDmg");
        if(ReflectionHacks.getPrivate(mo, AbstractMonster.class, "isMultiDmg")) {
            result += TEXT[2];
            result += ReflectionHacks.getPrivate(mo, AbstractMonster.class, "intentMultiAmt");
            result += TEXT[3];
        } else {
            result += TEXT[4];
        }

        return result;
    }

}