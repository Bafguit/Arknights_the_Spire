package com.ndc.arknightsthespire;

import basemod.BaseMod;
import com.evacipated.cardcrawl.modthespire.lib.SpireEnum;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.helpers.CardLibrary;

import com.badlogic.gdx.graphics.Color;

public class CardColors {

    public static void initialize() {
        BaseMod.addColor(CardColors.AbstractCardEnum.ATS_SNIPER,
                Color.WHITE, Color.WHITE, Color.WHITE, Color.WHITE, Color.WHITE, Color.WHITE, Color.WHITE,
                "img/512/atk_sniper.png", "img/512/skill_sniper.png", "img/512/pwr_sniper.png",
                "img/orbs/cost.png", "img/1024/atk_sniper.png", "img/1024/skill_sniper.png",
                "img/1024/pwr_sniper.png", "img/orbs/cost.png", "img/orbs/cost_small.png");

        /*BaseMod.addColor(CardColors.AbstractCardEnum.ATS_DEFENDER,
                Color.WHITE, Color.WHITE, Color.WHITE, Color.WHITE, Color.WHITE, Color.WHITE, Color.WHITE,
                "img/512/atk_defender.png", "img/512/skill_defender.png", "img/512/pwr_defender.png",
                "img/orbs/cost.png", "img/1024/atk_defender.png", "img/1024/skill_defender.png",
                "img/1024/pwr_defender.png", "img/orbs/cost.png", "img/orbs/cost_small.png");*/

    }

    public static class AbstractCardEnum {
        @SpireEnum
        public static AbstractCard.CardColor ATS_SNIPER;
        //public static AbstractCard.CardColor ATS_DEFENDER;
    }

    public static class CardLibraryEnum {
        @SpireEnum
        public static CardLibrary.LibraryType ATS_SNIPER;
        //public static CardLibrary.LibraryType ATS_DEFENDER;
    }
}
