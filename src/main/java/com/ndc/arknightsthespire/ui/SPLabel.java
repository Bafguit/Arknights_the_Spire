package com.ndc.arknightsthespire.ui;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.ndc.arknightsthespire.SPHandler;

public class SPLabel {

    public final int TEXT_X = 100;
    public final int TEXT_Y = 200;
    public final Color TEXT_COLOR = Color.CYAN;

    public void render(SpriteBatch sb) {
        FontHelper.renderFontCentered(sb, FontHelper.panelEndTurnFont, String.valueOf(SPHandler.getSp()), TEXT_X * Settings.scale, TEXT_Y * Settings.scale, TEXT_COLOR);
    }
}
