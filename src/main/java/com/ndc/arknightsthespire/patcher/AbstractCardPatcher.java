package com.ndc.arknightsthespire.patcher;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.evacipated.cardcrawl.modthespire.lib.SpireInsertPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.ndc.arknightsthespire.cards.base.CardSPBase;

public class AbstractCardPatcher {

    @SpirePatch(
            clz= AbstractCard.class,
            method="renderInLibrary"
    )
    public static class RenderInLibraryPatcher {
        @SpireInsertPatch(
                rloc = 19,
                localvars = {"sb"}
        )
        public static void Insert(AbstractCard __instance, SpriteBatch sb) {
            if(__instance instanceof CardSPBase) {
                CardSPBase card = (CardSPBase) __instance;
                card.updateState(false);
                card.renderSp(sb);
            }
        }
    }

    @SpirePatch(
            clz= AbstractCard.class,
            method="renderCard"
    )
    public static class RenderCardPatcher {
        @SpireInsertPatch(
                rloc = 10,
                localvars = {"sb"}
        )
        public static void Insert(AbstractCard __instance, SpriteBatch sb) {
            if(__instance instanceof CardSPBase) {
                CardSPBase card = (CardSPBase) __instance;
                card.updateState(true);
                card.renderSp(sb);
            }
        }
    }
/*
    @SpirePatch(
            clz= AbstractCard.class,
            method=SpirePatch.CONSTRUCTOR,
            paramtypez = {String.class, String.class, String.class, int.class, String.class,
                    AbstractCard.CardType.class, AbstractCard.CardColor.class, AbstractCard.CardRarity.class,
                    AbstractCard.CardTarget.class, DamageInfo.DamageType.class}
    )
    public static class ConstructorPatcher {
        public static void Postfix(AbstractCard __instance) {
            if(__instance instanceof CardSPBase) {
                CardSPBase card = (CardSPBase) __instance;
                card.rawDescription = card.spDescription;;
                card.initializeDescription();
            }
        }
    }*/
}
