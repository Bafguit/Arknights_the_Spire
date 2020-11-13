package com.ndc.arknightsthespire.cards.utill;

import com.megacrit.cardcrawl.localization.CardStrings;

public class CardLang extends CardStrings {

    public String SP_NAME;
    public String UPGRADE_NAME;
    public String SP_DESCRIPTION;

    public static CardLang getMockCardString() {
        CardLang retVal = new CardLang();
        retVal.NAME = "[MISSING_TITLE]";
        retVal.SP_NAME = "[MISSING_SP_TITLE]";
        retVal.UPGRADE_NAME = "[MISSING_TITLE+]";
        retVal.DESCRIPTION = "[MISSING_DESCRIPTION]";
        retVal.SP_DESCRIPTION = "[MISSING_SP_DESCRIPTION]";
        retVal.UPGRADE_DESCRIPTION = "[MISSING_DESCRIPTION+]";
        retVal.EXTENDED_DESCRIPTION = new String[]{"[MISSING_0]", "[MISSING_1]", "[MISSING_2]"};
        return retVal;
    }
}
