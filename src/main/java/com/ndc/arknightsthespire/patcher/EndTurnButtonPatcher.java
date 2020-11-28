package com.ndc.arknightsthespire.patcher;

import basemod.ReflectionHacks;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.evacipated.cardcrawl.modthespire.lib.SpireInsertPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.core.OverlayMenu;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.*;
import com.megacrit.cardcrawl.helpers.controller.CInputActionSet;
import com.megacrit.cardcrawl.helpers.input.InputActionSet;
import com.megacrit.cardcrawl.ui.buttons.EndTurnButton;
import com.ndc.arknightsthespire.SPHandler;
import com.ndc.arknightsthespire.ui.SPLabel;
import com.ndc.arknightsthespire.ui.ToggleSpButton;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class EndTurnButtonPatcher {

    public static final float SHOW_X = 1640.0F * Settings.scale + ToggleSpButton.RIGHT_OFFSET_X;
    public static final float HIDE_X = SHOW_X + 500.0F * Settings.scale;
    public static final float SHOW_Y = 210.0F * Settings.scale + ToggleSpButton.BUTTON_OFFSET_Y;

    public static ToggleSpButton toggleSpButton;
    public static SPLabel spLabel;

    static {
        ReflectionHacks.setPrivateStaticFinal(EndTurnButton.class, "SHOW_X", SHOW_X);
        ReflectionHacks.setPrivateStaticFinal(EndTurnButton.class, "HIDE_X", HIDE_X);
        ReflectionHacks.setPrivateStaticFinal(EndTurnButton.class, "SHOW_Y", SHOW_Y);
    }

    @SpirePatch(
            clz= EndTurnButton.class,
            method=SpirePatch.CONSTRUCTOR
    )
    public static class ConstructorPatcher {
        public static void Postfix(EndTurnButton __instance) {
            toggleSpButton = new ToggleSpButton(__instance);
            spLabel = new SPLabel();
        }
    }

    @SpirePatch(
            clz= EndTurnButton.class,
            method="update"
    )
    public static class UpdatePatcher {
        public static void Postfix(EndTurnButton __instance) {
            toggleSpButton.update();
        }
    }

    @SpirePatch(
            clz= EndTurnButton.class,
            method="enable"
    )
    public static class EnablePatcher {
        public static void Postfix(EndTurnButton __instance) {
            toggleSpButton.enable();
        }
    }

    @SpirePatch(
            clz= EndTurnButton.class,
            method="disable",
            paramtypez = { boolean.class }
    )
    public static class DisablePatcher1 {
        public static void Postfix(EndTurnButton __instance, boolean isEnemyTurn) {
            toggleSpButton.disable();
        }
    }

    @SpirePatch(
            clz= EndTurnButton.class,
            method="disable",
            paramtypez = {}
    )
    public static class DisablePatcher2 {
        public static void Postfix(EndTurnButton __instance) {
            toggleSpButton.disable();
        }
    }

    @SpirePatch(
            clz= EndTurnButton.class,
            method="show"
    )
    public static class ShowPatcher {
        public static void Postfix(EndTurnButton __instance) {
            toggleSpButton.show();
            spLabel.show();
        }
    }

    @SpirePatch(
            clz= EndTurnButton.class,
            method="hide"
    )
    public static class HidePatcher {
        public static void Postfix(EndTurnButton __instance) {
            toggleSpButton.hide();
            spLabel.hide();
        }
    }

    @SpirePatch(
            clz= EndTurnButton.class,
            method="render",
            paramtypez = { SpriteBatch.class }
    )
    public static class RenderPatcher {
        public static void Postfix(EndTurnButton __instance, SpriteBatch sb) {
            toggleSpButton.render(sb);
            spLabel.render(sb);
        }
    }


}