//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.ndc.arknightsthespire.events;

import basemod.interfaces.PreRoomRenderSubscriber;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.helpers.MathHelper;
import com.megacrit.cardcrawl.helpers.controller.CInputActionSet;
import com.megacrit.cardcrawl.helpers.input.InputHelper;
import com.megacrit.cardcrawl.localization.UIStrings;
import com.megacrit.cardcrawl.rooms.CampfireUI;
import com.megacrit.cardcrawl.rooms.RestRoom;
import com.megacrit.cardcrawl.ui.campfire.AbstractCampfireOption;
import com.megacrit.cardcrawl.vfx.campfire.CampfireLiftEffect;
import com.ndc.arknightsthespire.SPHandler;
import com.ndc.arknightsthespire.util.TextureLoader;

public class MaxSpOption extends AbstractCampfireOption {
    private static final UIStrings uiStrings;
    public static final String[] TEXT;

    public MaxSpOption(boolean isMaxYet) {
        this.label = TEXT[0];
        this.usable = isMaxYet;
        this.description = TEXT[1] + TEXT[2] + SPHandler.getMaxSp() + TEXT[3];
        this.img = TextureLoader.getTexture("img/ui/maximizeSp.png");
    }
    @Override
    public void useOption() {
        if (this.usable) {
            AbstractDungeon.effectList.add(new CampfireLiftEffect());
            SPHandler.addMaxSp(10);
        }
    }

    static {
        uiStrings = CardCrawlGame.languagePack.getUIString("MaxSpOption");
        TEXT = uiStrings.TEXT;
    }

}
