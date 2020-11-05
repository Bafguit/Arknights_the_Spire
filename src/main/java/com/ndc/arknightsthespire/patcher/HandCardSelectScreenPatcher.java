package com.ndc.arknightsthespire.patcher;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.evacipated.cardcrawl.modthespire.lib.SpireInsertPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.core.OverlayMenu;
import com.ndc.arknightsthespire.buttons.ToggleSpButton;

@SpirePatch(
        clz= OverlayMenu.class,
        method="render"
)
public class HandCardSelectScreenPatcher {

    private static ToggleSpButton toggleSpButton;

    @SpirePatch(
            clz= OverlayMenu.class,
            method=SpirePatch.CONSTRUCTOR
    )
    public static class ConstructorPatcher {
        public static void PostFix(OverlayMenu __instance) {
            toggleSpButton = new ToggleSpButton(__instance.endTurnButton);
        }
    }

    @SpirePatch(
            clz= OverlayMenu.class,
            method="update"
    )
    public static class UpdatePatcher {
        @SpireInsertPatch(
                loc=67
        )
        public static void Insert(OverlayMenu __instance) {
            toggleSpButton.update();
        }
    }

    @SpirePatch(
            clz= OverlayMenu.class,
            method="showCombatPanels"
    )
    public static class ShowCombatPanelsPatcher {
        @SpireInsertPatch(
                loc=110
        )
        public static void Insert(OverlayMenu __instance) {
            toggleSpButton.show();
        }
    }

    @SpirePatch(
            clz= OverlayMenu.class,
            method="hideCombatPanels"
    )
    public static class HideCombatPanelsPatcher {
        @SpireInsertPatch(
                loc=126
        )
        public static void Insert(OverlayMenu __instance) {
            toggleSpButton.hide();
        }
    }

    @SpirePatch(
            clz= OverlayMenu.class,
            method="render"
    )
    public static class RenderPatcher {
        @SpireInsertPatch(
                loc=167,
                localvars = {"sb"}
        )
        public static void Insert(OverlayMenu __instance, SpriteBatch sb) {
            toggleSpButton.render(sb);
        }
    }
}