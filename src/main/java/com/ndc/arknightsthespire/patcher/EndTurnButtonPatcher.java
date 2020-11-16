package com.ndc.arknightsthespire.patcher;

import basemod.ReflectionHacks;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.*;
import com.megacrit.cardcrawl.helpers.controller.CInputActionSet;
import com.megacrit.cardcrawl.helpers.input.InputActionSet;
import com.megacrit.cardcrawl.ui.buttons.EndTurnButton;
import com.ndc.arknightsthespire.SPHandler;
import com.ndc.arknightsthespire.ui.ToggleSpButton;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class EndTurnButtonPatcher {

    public static final float SHOW_X = 1640.0F * Settings.scale + ToggleSpButton.RIGHT_OFFSET_X;
    public static final float HIDE_X = SHOW_X + 500.0F * Settings.scale;
    public static final float SHOW_Y = 210.0F * Settings.scale + ToggleSpButton.BUTTON_OFFSET_Y;

    public static Method renderHoldEndTurn;
    public static Method renderGlowEffect;

    static {
        try {
            renderHoldEndTurn = EndTurnButton.class.getDeclaredMethod("renderHoldEndTurn", SpriteBatch.class);
            renderGlowEffect = EndTurnButton.class.getDeclaredMethod("renderGlowEffect", SpriteBatch.class, float.class, float.class);

            renderHoldEndTurn.setAccessible(true);
            renderGlowEffect.setAccessible(true);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }

    @SpirePatch(
            clz= EndTurnButton.class,
            method=SpirePatch.CONSTRUCTOR
    )
    public static class ConstructorPatcher {
        public static void Postfix(EndTurnButton __instance) {
            setPrivate(__instance, "current_y", SHOW_Y);
        }
    }

    @SpirePatch(
            clz= EndTurnButton.class,
            method="show"
    )
    public static class ShowPatcher {
        public static void Replace(EndTurnButton __instance) {
            if ((boolean) getPrivate(__instance, "isHidden")) {
                setPrivate(__instance, "target_x", SHOW_X);
                setPrivate(__instance, "isHidden", false);
                if (__instance.isGlowing) {
                    setPrivate(__instance, "glowTimer", -1.0F);
                }
            }
        }
    }

    @SpirePatch(
            clz= EndTurnButton.class,
            method="hide"
    )
    public static class HidePatcher {
        public static void Replace(EndTurnButton __instance) {
            if (!(boolean) getPrivate(__instance, "isHidden")) {
                setPrivate(__instance, "target_x", HIDE_X);
                setPrivate(__instance, "isHidden", true);
            }

        }
    }

    @SpirePatch(
            clz= EndTurnButton.class,
            method="render"
    )
    public static class RenderPatcher {
        public static void Replace(EndTurnButton __instance, SpriteBatch sb) throws InvocationTargetException, IllegalAccessException {
            float current_x = (float) getPrivate(__instance, "current_x");
            float current_y = (float) getPrivate(__instance, "current_y");
            boolean isDisabled = (boolean) getPrivate(__instance, "isDisabled");
            boolean isHidden = (boolean) getPrivate(__instance, "isHidden");
            Hitbox hb = (Hitbox) getPrivate(__instance, "hb");
            String label = (String) getPrivate(__instance, "label");
            float holdProgress = (float) getPrivate(__instance, "holdProgress");
            Color DISABLED_COLOR = (Color) getPrivate(__instance, "DISABLED_COLOR");
            String ENEMY_TURN_MSG = (String) getPrivate(__instance, "ENEMY_TURN_MSG");
            String[] LABEL = (String[]) getPrivate(__instance, "LABEL");
            String[] MSG = (String[]) getPrivate(__instance, "MSG");

            if (!Settings.hideEndTurn) {
                float tmpY = current_y;
                renderHoldEndTurn.invoke(__instance, sb);
                if (!isDisabled && __instance.enabled) {
                    if (hb.hovered) {
                        if (__instance.isWarning) {
                            setPrivate(__instance, "textColor", Settings.RED_TEXT_COLOR);
                        } else {
                            setPrivate(__instance, "textColor", Color.CYAN);
                        }
                    } else if (__instance.isGlowing) {
                        setPrivate(__instance, "textColor", Settings.GOLD_COLOR);
                    } else {
                        setPrivate(__instance, "textColor", Settings.CREAM_COLOR);
                    }
                    //System.out.println("SANS1");
                    if (hb.hovered && !AbstractDungeon.isScreenUp && !Settings.isTouchScreen) {
                        //System.out.println("SANS2");
                        TipHelper.renderGenericTip(current_x - 90.0F * Settings.scale, current_y + 300.0F * Settings.scale, LABEL[0] + " (" + InputActionSet.endTurn.getKeyString() + ")", MSG[0] + AbstractDungeon.player.gameHandSize + MSG[1]);

                        //System.out.println("SANS3");
                    }
                } else if (label.equals(ENEMY_TURN_MSG)) {
                    setPrivate(__instance, "textColor", Settings.CREAM_COLOR);
                } else {
                    setPrivate(__instance, "textColor", Color.LIGHT_GRAY);
                }
                //System.out.println("SANS");
                if (hb.clickStarted && !AbstractDungeon.isScreenUp) {
                    tmpY -= 2.0F * Settings.scale;
                } else if (hb.hovered && !AbstractDungeon.isScreenUp) {
                    tmpY += 2.0F * Settings.scale;
                }
                //System.out.println("SANS");
                if (!__instance.enabled) {
                    ShaderHelper.setShader(sb, ShaderHelper.Shader.GRAYSCALE);
                } else if (!isDisabled && (!hb.clickStarted || !hb.hovered)) {
                    sb.setColor(Color.WHITE);
                } else {
                    sb.setColor(DISABLED_COLOR);
                }
                //System.out.println("SANS");
                Texture buttonImg;
                if (__instance.isGlowing && !hb.clickStarted) {
                    buttonImg = ImageMaster.END_TURN_BUTTON_GLOW;
                } else {
                    buttonImg = ImageMaster.END_TURN_BUTTON;
                }
                //System.out.println("SANS");
                if (hb.hovered && !isDisabled && !AbstractDungeon.isScreenUp) {
                    sb.draw(ImageMaster.END_TURN_HOVER, current_x - 128.0F, tmpY - 128.0F, 128.0F, 128.0F, 256.0F, 256.0F, Settings.scale, Settings.scale, 0.0F, 0, 0, 256, 256, false, false);
                }
                //System.out.println("SANS");
                sb.draw(buttonImg, current_x - 128.0F, tmpY - 128.0F, 128.0F, 128.0F, 256.0F, 256.0F, Settings.scale, Settings.scale, 0.0F, 0, 0, 256, 256, false, false);
                if (!__instance.enabled) {
                    ShaderHelper.setShader(sb, ShaderHelper.Shader.DEFAULT);
                }
                //System.out.println("SANS");
                renderGlowEffect.invoke(__instance, sb, current_x, current_y);
                if ((hb.hovered || holdProgress > 0.0F) && !isDisabled && !AbstractDungeon.isScreenUp) {
                    sb.setBlendFunction(770, 1);
                    sb.setColor(Settings.HALF_TRANSPARENT_WHITE_COLOR);
                    sb.draw(buttonImg, current_x - 128.0F, tmpY - 128.0F, 128.0F, 128.0F, 256.0F, 256.0F, Settings.scale, Settings.scale, 0.0F, 0, 0, 256, 256, false, false);
                    sb.setBlendFunction(770, 771);
                }
                //System.out.println("SANS");
                if (Settings.isControllerMode && __instance.enabled) {
                    sb.setColor(Color.WHITE);
                    sb.draw(CInputActionSet.proceed.getKeyImg(), current_x - 32.0F - 42.0F * Settings.scale - FontHelper.getSmartWidth(FontHelper.panelEndTurnFont, label, 99999.0F, 0.0F) / 2.0F, tmpY - 32.0F, 32.0F, 32.0F, 64.0F, 64.0F, Settings.scale, Settings.scale, 0.0F, 0, 0, 64, 64, false, false);
                }
                //System.out.println("SANS");
                FontHelper.renderFontCentered(sb, FontHelper.panelEndTurnFont, label, current_x + ToggleSpButton.RIGHT_TEXT_OFFSET_X * Settings.scale, tmpY + ToggleSpButton.TEXT_OFFSET_Y * Settings.scale, (Color) getPrivate(__instance, "textColor"));
                if (!isHidden) {
                    hb.render(sb);
                }
            }

        }
    }

    public static Object getPrivate(EndTurnButton __instance, String name) {
        return ReflectionHacks.getPrivate(__instance, EndTurnButton.class, name);
    }

    public static void setPrivate(EndTurnButton __instance, String name, Object value) {
        ReflectionHacks.setPrivate(__instance, EndTurnButton.class, name, value);
    }
}
