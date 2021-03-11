package com.ndc.arknightsthespire;

import basemod.BaseMod;
import com.evacipated.cardcrawl.modthespire.lib.SpireEnum;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.Ironclad;
import com.megacrit.cardcrawl.helpers.CardLibrary;

import com.badlogic.gdx.graphics.Color;

public class CardColors {

    public static void initialize() {
        BaseMod.addColor(CardColors.AbstractCardEnum.DOCTOR_COLOR,
                Color.WHITE, Color.WHITE, Color.WHITE, Color.WHITE, Color.WHITE, Color.WHITE, Color.WHITE,
                "atsImg/512/beta.png", "atsImg/512/beta.png", "atsImg/512/beta.png",
                "atsImg/orbs/cost.png", "atsImg/1024/beta.png", "atsImg/1024/beta.png",
                "atsImg/1024/beta.png", "atsImg/orbs/cost.png", "atsImg/orbs/cost_tip.png");

    }

    public static class AbstractCardEnum {
        @SpireEnum
        public static AbstractCard.CardColor DOCTOR_COLOR;
    }

    public static class CardLibraryEnum {
        @SpireEnum
        public static CardLibrary.LibraryType DOCTOR_COLOR;
    }
}
