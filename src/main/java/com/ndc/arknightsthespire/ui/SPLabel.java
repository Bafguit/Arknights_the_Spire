package com.ndc.arknightsthespire.ui;

import basemod.ReflectionHacks;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeType;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.ndc.arknightsthespire.SPHandler;
import org.lwjgl.Sys;

import java.awt.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class SPLabel {

    public static final int TEXT_X = Settings.M_H/2;
    public static final int TEXT_Y = 200;
    public static final Color TEXT_COLOR = Color.CYAN;
    private static BitmapFont SP_LABEL_FONT;

    private static final float HIDE_X = TEXT_X - 650.0F * Settings.scale;
    private static final float HIDE_Y = TEXT_Y;

    private float current_x;
    private float current_y;
    private float target_x;

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

    public void hide() {
        this.target_x = HIDE_X;
    }

    public void show() {
        this.target_x = TEXT_X;
    }

    public void render(SpriteBatch sb) {
        if (this.current_x != this.target_x) {
            this.current_x = MathUtils.lerp(this.current_x, this.target_x, Gdx.graphics.getDeltaTime() * 9.0F);
            if (Math.abs(this.current_x - this.target_x) < Settings.UI_SNAP_THRESHOLD) {
                this.current_x = this.target_x;
            }
        }
        FontHelper.renderFontCentered(sb, SP_LABEL_FONT, SPHandler.getSp()+"/"+SPHandler.getMaxSp(), current_x * Settings.scale, current_y * Settings.scale, TEXT_COLOR);
    }

    public SPLabel() {
        current_x = HIDE_X;
        current_y = TEXT_Y;
    }
}
