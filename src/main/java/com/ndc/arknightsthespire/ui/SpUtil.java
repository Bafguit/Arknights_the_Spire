//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.ndc.arknightsthespire.ui;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.evacipated.cardcrawl.modthespire.lib.LineFinder;
import com.evacipated.cardcrawl.modthespire.lib.Matcher;
import com.evacipated.cardcrawl.modthespire.lib.SpireField;
import com.evacipated.cardcrawl.modthespire.lib.SpireInsertLocator;
import com.evacipated.cardcrawl.modthespire.lib.SpireInsertPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePrefixPatch;
import com.evacipated.cardcrawl.modthespire.lib.Matcher.MethodCallMatcher;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.OverlayMenu;
import com.megacrit.cardcrawl.ui.panels.ExhaustPanel;
import javassist.CtBehavior;

import javax.swing.*;

public class SpUtil {
    public SpUtil() {
    }

    @SpirePatch(
            clz = OverlayMenu.class,
            method = "hideCombatPanels"
    )
    public static class HidePanel {
        public HidePanel() {
        }

        @SpirePrefixPatch
        public static void patch(OverlayMenu __instance) {
            SpManager.getSpUI().hide();
        }
    }

    @SpirePatch(
            clz = OverlayMenu.class,
            method = "showCombatPanels"
    )
    public static class ShowPanel {
        public ShowPanel() {
        }

        @SpirePrefixPatch
        public static void patch(OverlayMenu __instance) {
            SpManager.getSpUI().show();
        }
    }

    @SpirePatch(
            clz = OverlayMenu.class,
            method = "render"
    )
    public static class PanelRender {
        public PanelRender() {
        }

        @SpireInsertPatch(
                locator = com.ndc.arknightsthespire.ui.SpUtil.PanelRender.Locator.class
        )
        public static void patch(OverlayMenu __instance, SpriteBatch sb) {
            SpManager.getSpUI().render(sb);
        }

        private static class Locator extends SpireInsertLocator {
            private Locator() {
            }

            public int[] Locate(CtBehavior ctBehavior) throws Exception {
                Matcher finalMatcher = new MethodCallMatcher(ExhaustPanel.class, "render");
                return LineFinder.findInOrder(ctBehavior, finalMatcher);
            }
        }
    }

    @SpirePatch(
            clz = OverlayMenu.class,
            method = "update"
    )
    public static class PanelUpdate {
        public PanelUpdate() {
        }

        @SpireInsertPatch(
                locator = SpUtil.PanelUpdate.Locator.class
        )
        public static void patch(OverlayMenu __instance) {
            SpManager.getSpUI().update();
        }

        private static class Locator extends SpireInsertLocator {
            private Locator() {
            }

            public int[] Locate(CtBehavior ctBehavior) throws Exception {
                Matcher finalMatcher = new MethodCallMatcher(OverlayMenu.class, "updateBlackScreen");
                return LineFinder.findInOrder(ctBehavior, finalMatcher);
            }
        }
    }

    @SpirePatch(
            clz = AbstractPlayer.class,
            method = "<class>"
    )
    public static class SpFields {
        public static SpireField<SpUI> panel = new SpireField(SpUI::new);

        public SpFields() {
        }
    }
}
