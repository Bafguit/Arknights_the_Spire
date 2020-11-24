package com.ndc.arknightsthespire.patcher;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.evacipated.cardcrawl.modthespire.lib.SpireInsertPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.ui.buttons.EndTurnButton;
import com.ndc.arknightsthespire.SPHandler;
import com.ndc.arknightsthespire.cards.CardSPBase;

import javax.smartcardio.Card;
import java.util.ArrayList;

public class AbstractCardPatcher {
    /*]]]=

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
                if(card.shouldUseSp()) {
                    card.description = card.spDescription;
                    card.rawDescription = card.spRawDescription;
                } else {
                    card.description = card.normalDescription;
                    card.rawDescription = card.normalRawDescription;
                }
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
            CardSPBase card = (CardSPBase) __instance;
            if(card.shouldUseSp()) {
                card.description = card.spDescription;
                card.rawDescription = card.spRawDescription;
            } else {
                card.description = card.normalDescription;
                card.rawDescription = card.normalRawDescription;
            }
            card.renderSp(sb);
        }
    }

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
                card.normalDescription = card.description;
                card.normalRawDescription = card.rawDescription;
                card.description = new ArrayList<>();
                card.rawDescription = card.spRawDescription;;
                card.initializeDescription();
                card.spDescription = card.description;

                card.rawDescription = card.normalRawDescription;
                card.description = card.normalDescription;
            }
        }
    }*/
}
