package com.ndc.arknightsthespire.ui;

import basemod.ReflectionHacks;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeType;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.ndc.arknightsthespire.SPHandler;
import org.lwjgl.Sys;

import java.awt.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class SPLabel {

    public final int TEXT_X = Settings.M_H/2;
    public final int TEXT_Y = 200;
    public final Color TEXT_COLOR = Color.CYAN;
    private static BitmapFont SP_LABEL_FONT;

    static {
        SP_LABEL_FONT = FontHelper.panelEndTurnFont;
        try {
            Method prepFont = FontHelper.class.getDeclaredMethod("prepFont", float.class, boolean.class);
            prepFont.setAccessible(true);
            SP_LABEL_FONT = (BitmapFont) prepFont.invoke(null, 50.0f, false);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    public void render(SpriteBatch sb) {
        FontHelper.renderFontCentered(sb, SP_LABEL_FONT, String.valueOf(SPHandler.getSp()), TEXT_X * Settings.scale, TEXT_Y * Settings.scale, TEXT_COLOR);
    }
}
