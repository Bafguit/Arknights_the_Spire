package com.ndc.arknightsthespire.patcher;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.evacipated.cardcrawl.modthespire.lib.SpireInsertPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.helpers.input.InputAction;
import com.megacrit.cardcrawl.helpers.input.InputActionSet;
import com.ndc.arknightsthespire.cards.base.CardSPBase;

public class InputActionSetPatcher {

    public static InputAction enableSPButton;
    private static final String SP_BUTTON_KEY = "ENABLE_SP_BUTTON";
    private static final int SP_BUTTON_KEYCODE = 59; //CTRL_LEFT

    @SpirePatch(
            clz= InputActionSet.class,
            method="load"
    )
    public static class LoadPatcher {
        public static void Prefix() {
            enableSPButton = new InputAction(InputActionSet.prefs.getInteger(SP_BUTTON_KEY, SP_BUTTON_KEYCODE));
        }
    }

    @SpirePatch(
            clz= InputActionSet.class,
            method="save"
    )
    public static class SavePatcher {
        public static void Prefix() {
            InputActionSet.prefs.putInteger(SP_BUTTON_KEY, enableSPButton.getKey());
        }
    }

    @SpirePatch(
            clz= InputActionSet.class,
            method="resetToDefaults"
    )
    public static class ResetToDefaultsPatcher {
        public static void Prefix() {
            enableSPButton.remap(SP_BUTTON_KEYCODE);
        }
    }
}
