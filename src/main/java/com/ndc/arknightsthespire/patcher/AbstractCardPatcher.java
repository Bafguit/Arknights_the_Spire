package com.ndc.arknightsthespire.patcher;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.evacipated.cardcrawl.modthespire.lib.SpireInsertPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.ndc.arknightsthespire.cards.CardSPBase;

public class AbstractCardPatcher {

    @SpirePatch(
            clz= AbstractCard.class,
            method="renderInLibrary"
    )
    public static class RenderInLibraryPatcher {
        @SpireInsertPatch(
                rloc = 27,
                localvars = {"sb"}
        )
        public static void Insert(AbstractCard __instance, SpriteBatch sb) {
            System.out.println("HEYYYY");
            if(__instance instanceof CardSPBase) {
                System.out.println("KAYYYY");
                ((CardSPBase) __instance).renderSp(sb);
            }
        }
    }
}
