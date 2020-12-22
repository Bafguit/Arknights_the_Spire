package com.ndc.arknightsthespire.patcher;

import basemod.ReflectionHacks;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.evacipated.cardcrawl.modthespire.lib.ByRef;
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
import com.ndc.arknightsthespire.ui.ToggleSpButton;
import sun.reflect.Reflection;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.WeakHashMap;

public class EndTurnButtonPatcher {

    public static final float SHOW_X = 1640.0F * Settings.scale;
    public static final float HIDE_X = SHOW_X + 500.0F * Settings.scale;
    public static final float SHOW_Y = 229.0F * Settings.scale;

    private static final WeakHashMap<EndTurnButton, Float> currentXMap = new WeakHashMap<>();

    public static ToggleSpButton toggleSpButton;

    static {
        ReflectionHacks.setPrivateStaticFinal(EndTurnButton.class, "SHOW_X", SHOW_X + ToggleSpButton.OFFSET_X * Settings.scale);
        ReflectionHacks.setPrivateStaticFinal(EndTurnButton.class, "HIDE_X", HIDE_X + ToggleSpButton.OFFSET_X * Settings.scale);
        ReflectionHacks.setPrivateStaticFinal(EndTurnButton.class, "SHOW_Y", SHOW_Y);
    }
    @SpirePatch(
            clz= EndTurnButton.class,
            method=SpirePatch.CONSTRUCTOR
    )
    public static class ConstructorPatcher {
        public static void Postfix(EndTurnButton __instance, @ByRef(type="helpers.Hitbox") Object[] ___hb) {
            toggleSpButton = new ToggleSpButton(__instance);
            ___hb[0] = new Hitbox(0.0F, 0.0F, ToggleSpButton.HITBOX_WIDTH * Settings.scale, ToggleSpButton.HITBOX_HEIGHT * Settings.scale);
        }
    }

    @SpirePatch(
            clz= EndTurnButton.class,
            method="update"
    )
    public static class UpdatePatcher {
        public static void Postfix(EndTurnButton __instance) {
            toggleSpButton.update();
            System.out.println("UPDATE");
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
        }
    }

    @SpirePatch(
            clz= EndTurnButton.class,
            method="hide"
    )
    public static class HidePatcher {
        public static void Postfix(EndTurnButton __instance) {
            toggleSpButton.hide();
        }
    }

    @SpirePatch(
            clz= EndTurnButton.class,
            method="render",
            paramtypez = { SpriteBatch.class }
    )
    public static class RenderInsertPatcher1 {
        @SpireInsertPatch(
                rloc = 149,
                localvars = {"tmpY"}
        )
        public static void Insert(EndTurnButton __instance, SpriteBatch sb, @ByRef float[] tmpY) {
            float valueX = ReflectionHacks.getPrivate(__instance, EndTurnButton.class, "current_x");
            currentXMap.put(__instance, valueX);
            System.out.println("A " + valueX + " " + tmpY[0]);
            ReflectionHacks.setPrivate(__instance, EndTurnButton.class, "current_x", valueX + ToggleSpButton.RIGHT_TEXT_OFFSET_X * Settings.scale);
            tmpY[0] += (3.0F + ToggleSpButton.TEXT_OFFSET_Y) * Settings.scale;
            System.out.println("B " + ReflectionHacks.getPrivate(__instance, EndTurnButton.class, "current_x") + " " + tmpY[0]);
        }
    }
    @SpirePatch(
            clz= EndTurnButton.class,
            method="render",
            paramtypez = { SpriteBatch.class }
    )
    public static class RenderInsertPatcher2 {
        @SpireInsertPatch(
                rloc = 157
        )
        public static void Insert(EndTurnButton __instance, SpriteBatch sb) {
            if(currentXMap.get(__instance) != null) {
                System.out.println("C " + ReflectionHacks.getPrivate(__instance, EndTurnButton.class, "current_x"));
                ReflectionHacks.setPrivate(__instance, EndTurnButton.class, "current_x", currentXMap.get(__instance));
                System.out.println("D " + ReflectionHacks.getPrivate(__instance, EndTurnButton.class, "current_x"));
            }
        }
    }
/*
    @SpirePatch(
            clz= EndTurnButton.class,
            method="render",
            paramtypez = { SpriteBatch.class }
    )
    public static class RenderInsertPatcher1 {
        @SpireInsertPatch(
                rloc = 148,
                localvars = {"tmpY"}
        )
        public static void Insert(EndTurnButton __instance, SpriteBatch sb, @ByRef float[] ___current_x, @ByRef float[] tmpY) {
            float valueX = ___current_x[0];
            currentXMap.put(__instance, valueX);
            ___current_x[0] = 0;
            //___current_x[0] = valueX + ToggleSpButton.RIGHT_TEXT_OFFSET_X * Settings.scale;
            tmpY[0] += (3.0F + ToggleSpButton.TEXT_OFFSET_Y) * Settings.scale;
        }
    }
    @SpirePatch(
            clz= EndTurnButton.class,
            method="render",
            paramtypez = { SpriteBatch.class }
    )
    public static class RenderInsertPatcher2 {
        @SpireInsertPatch(
                rloc = 149
        )
        public static void Insert(EndTurnButton __instance, SpriteBatch sb, @ByRef float[] ___current_x) {
            if(currentXMap.get(__instance) != null) {
                ___current_x[0] = currentXMap.get(__instance);
            }
        }
    }*/

    @SpirePatch(
            clz= EndTurnButton.class,
            method="render",
            paramtypez = { SpriteBatch.class }
    )
    public static class RenderPatcher {
        public static void Postfix(EndTurnButton __instance, SpriteBatch sb) {
            toggleSpButton.render(sb);
        }
    }


}