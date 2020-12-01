//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.ndc.arknightsthespire.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Interpolation;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon.CurrentScreen;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.helpers.controller.CInputActionSet;
import com.megacrit.cardcrawl.helpers.input.InputHelper;
import com.megacrit.cardcrawl.localization.TutorialStrings;
import com.megacrit.cardcrawl.ui.FtueTip;

public class AtsTutorial extends FtueTip {
    private static final TutorialStrings tutorialStrings;
    public static final String[] txt;
    public static final String[] LABEL;
    private Texture img1 = ImageMaster.loadImage("img/ui/t1.png");
    private Color screen = Color.valueOf("1c262a00");
    private float x;
    private float x1;
    private float targetX;
    private float startX;
    private float scrollTimer = 0.0F;
    private static String txt1;

    public AtsTutorial() {
        txt1 = txt[0];
        AbstractDungeon.player.releaseCard();
        if (AbstractDungeon.isScreenUp) {
            AbstractDungeon.dynamicBanner.hide();
            AbstractDungeon.previousScreen = AbstractDungeon.screen;
        }

        AbstractDungeon.isScreenUp = true;
        AbstractDungeon.screen = CurrentScreen.FTUE;
        AbstractDungeon.overlayMenu.showBlackScreen();
        this.x = 0.0F;
        this.x1 = 567.0F * Settings.scale;
        AbstractDungeon.overlayMenu.proceedButton.show();
        AbstractDungeon.overlayMenu.proceedButton.setLabel(LABEL[0]);
    }

    public void update() {
        AbstractDungeon.overlayMenu.proceedButton.setLabel(LABEL[0]);

        if (this.screen.a != 0.8F) {
            Color var10000 = this.screen;
            var10000.a += Gdx.graphics.getDeltaTime();
            if (this.screen.a > 0.8F) {
                this.screen.a = 0.8F;
            }
        }

        if (AbstractDungeon.overlayMenu.proceedButton.isHovered && InputHelper.justClickedLeft || CInputActionSet.proceed.isJustPressed()) {
            CInputActionSet.proceed.unpress();
            CardCrawlGame.sound.play("DECK_CLOSE");
            AbstractDungeon.closeCurrentScreen();
            AbstractDungeon.overlayMenu.proceedButton.hide();
            AbstractDungeon.effectList.clear();
            this.startX = this.x;
            this.targetX = 0; //(float)(this.currentSlot * Settings.WIDTH)
            this.scrollTimer = 0.3F;
            AbstractDungeon.overlayMenu.proceedButton.setLabel(LABEL[0]);
        }

        if (this.scrollTimer != 0.0F) {
            this.scrollTimer -= Gdx.graphics.getDeltaTime();
            if (this.scrollTimer < 0.0F) {
                this.scrollTimer = 0.0F;
            }
        }

        this.x = Interpolation.fade.apply(this.targetX, this.startX, this.scrollTimer / 0.3F);
    }

    public void render(SpriteBatch sb) {
        sb.setColor(this.screen);
        sb.draw(ImageMaster.WHITE_SQUARE_IMG, 0.0F, 0.0F, (float)Settings.WIDTH, (float)Settings.HEIGHT);
        sb.setColor(Color.WHITE);
        sb.draw(this.img1, this.x + this.x1 - 380.0F, (float)Settings.HEIGHT / 2.0F - 290.0F, 380.0F, 290.0F, 760.0F, 580.0F, Settings.scale, Settings.scale, 0.0F, 0, 0, 760, 580, false, false);
        FontHelper.renderSmartText(sb, FontHelper.panelNameFont, txt1, this.x + this.x1 + 400.0F * Settings.scale, (float)Settings.HEIGHT / 2.0F - FontHelper.getSmartHeight(FontHelper.panelNameFont, txt1, 700.0F * Settings.scale, 40.0F * Settings.scale) / 2.0F, 700.0F * Settings.scale, 40.0F * Settings.scale, Settings.CREAM_COLOR);
        FontHelper.renderFontCenteredWidth(sb, FontHelper.panelNameFont, LABEL[1], (float)Settings.WIDTH / 2.0F, (float)Settings.HEIGHT / 2.0F - 360.0F * Settings.scale, Settings.GOLD_COLOR);
        AbstractDungeon.overlayMenu.proceedButton.render(sb);
    }

    static {
        tutorialStrings = CardCrawlGame.languagePack.getTutorialString("ArknightsDoctor");
        txt = tutorialStrings.TEXT;
        LABEL = tutorialStrings.LABEL;
        txt1 = txt[0];
    }
}
