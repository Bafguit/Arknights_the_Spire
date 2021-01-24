package com.ndc.arknightsthespire.patcher;

import com.evacipated.cardcrawl.modthespire.lib.*;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.localization.UIStrings;
import com.megacrit.cardcrawl.screens.options.InputSettingsScreen;
import com.megacrit.cardcrawl.screens.options.RemapInputElement;
import com.ndc.arknightsthespire.ArknightsTheSpire;
import com.ndc.arknightsthespire.patcher.InputActionSetPatcher;
import javassist.CtBehavior;

import java.util.ArrayList;

public class InputSettingScreenPatcher {

    @SpirePatch(
            clz=InputSettingsScreen.class,
            method="refreshData"
    )
    public static class RefreshDataPatcher
    {
        private static class Strings
        {
            private static final UIStrings uiStrings = CardCrawlGame.languagePack.getUIString("EnableSPKeyName");
        }

        @SpireInsertPatch(
                locator=Locator.class,
                localvars={"elements"}
        )
        public static void Insert(InputSettingsScreen __instance, ArrayList<RemapInputElement> elements)
        {
            if (!Settings.isControllerMode) {
                elements.add(new RemapInputElement(__instance, Strings.uiStrings.TEXT[0], InputActionSetPatcher.enableSPButton));
            }
        }

        private static class Locator extends SpireInsertLocator
        {
            @Override
            public int[] Locate(CtBehavior ctMethodToPatch) throws Exception
            {
                Matcher finalMatcher = new Matcher.FieldAccessMatcher(InputSettingsScreen.class, "maxScrollAmount");
                return LineFinder.findInOrder(ctMethodToPatch, finalMatcher);
            }
        }
    }
}
