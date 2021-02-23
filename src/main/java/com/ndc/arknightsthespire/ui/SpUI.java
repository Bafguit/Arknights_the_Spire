//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.ndc.arknightsthespire.ui;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.helpers.Hitbox;
import com.megacrit.cardcrawl.helpers.MathHelper;
import com.megacrit.cardcrawl.helpers.PowerTip;
import com.megacrit.cardcrawl.helpers.TipHelper;
import com.megacrit.cardcrawl.localization.UIStrings;
import com.megacrit.cardcrawl.rooms.AbstractRoom.RoomPhase;
import com.megacrit.cardcrawl.ui.panels.AbstractPanel;
import com.ndc.arknightsthespire.SPHandler;
import com.ndc.arknightsthespire.cards.base.CardSPBase;
import com.ndc.arknightsthespire.util.TextureLoader;

import java.util.ArrayList;
import java.util.Iterator;

import static com.megacrit.cardcrawl.dungeons.AbstractDungeon.*;
import static com.megacrit.cardcrawl.dungeons.AbstractDungeon.getCurrRoom;
import static com.ndc.arknightsthespire.character.AtsEnum.DOCTOR_CLASS;

public class SpUI extends AbstractPanel {
    private static final UIStrings uiStrings;
    private static final Color ENERGY_TEXT_COLOR;
    public static float fontScale;
    private Hitbox tipHitbox;
    private float energyVfxAngle;
    private float energyVfxScale;
    private Color energyVfxColor;
    private float cooldown = 1.0F;
    public static String image;
    public static Texture tex;
    private static ArrayList<PowerTip> tips;
    private static PowerTip tooltip;

    public SpUI() {
        super(96.0F * Settings.scale, 320.0F * Settings.scale, (float)Settings.WIDTH + 480.0F * Settings.scale, (float)Settings.HEIGHT / 2.0F, 12.0F * Settings.scale, -12.0F * Settings.scale, tex, false);
        this.tipHitbox = new Hitbox(0.0F, 0.0F, 120.0F * Settings.scale, 120.0F * Settings.scale);
        this.energyVfxAngle = 0.0F;
        this.energyVfxScale = Settings.scale;
        this.energyVfxColor = Color.WHITE.cpy();
        tips = new ArrayList();
        tooltip = new PowerTip(uiStrings.TEXT[0], uiStrings.TEXT[1]);
        tips.add(tooltip);
        updateTooltip();
    }

    public static Texture OrbVfx() {
        return TextureLoader.getTexture("img/char/orb/rvfx.png");
    }

    public void update() {
        this.updateVfx();
        if (fontScale != 1.0F) {
            fontScale = MathHelper.scaleLerpSnap(fontScale, 2.0F);
        }

        this.tipHitbox.update();
        if (this.tipHitbox.hovered && !isScreenUp) {
            overlayMenu.hoveredTip = true;
        }

    }

    public static boolean isDoctor() {

        if(player.chosenClass == DOCTOR_CLASS) {
            return true;
        } else {
            Iterator var1 = player.masterDeck.group.iterator();

            while (var1.hasNext()) {
                AbstractCard c = (AbstractCard) var1.next();
                if (c instanceof CardSPBase) {
                    return true;
                }
            }

            var1 = player.hand.group.iterator();

            while (var1.hasNext()) {
                AbstractCard c = (AbstractCard) var1.next();
                if (c instanceof CardSPBase) {
                    return true;
                }
            }

            var1 = player.exhaustPile.group.iterator();

            while (var1.hasNext()) {
                AbstractCard c = (AbstractCard) var1.next();
                if (c instanceof CardSPBase) {
                    return true;
                }
            }

            var1 = player.drawPile.group.iterator();

            while (var1.hasNext()) {
                AbstractCard c = (AbstractCard) var1.next();
                if (c instanceof CardSPBase) {
                    return true;
                }
            }
        }

        return false;
    }

    private void updateVfx() {
        tex = OrbVfx();
    }

    public void render(SpriteBatch sb) {
        if (!this.isHidden && CardCrawlGame.isInARun() && getCurrRoom() != null && getCurrRoom().phase == RoomPhase.COMBAT && isDoctor()) {
            this.tipHitbox.move(this.current_x, this.current_y);
            this.renderVfx(sb);
            String text = SPHandler.getSp() + "/" + SPHandler.getMaxSp();
            FontHelper.renderFontCentered(sb, FontHelper.energyNumFontBlue, text, this.current_x, this.current_y, ENERGY_TEXT_COLOR);
            this.tipHitbox.render(sb);
            if (this.tipHitbox.hovered && !isScreenUp) {
                TipHelper.renderGenericTip(this.current_x + (float)tex.getWidth() / 2.0F * Settings.scale, this.current_y + (float)tex.getHeight() / 2.0F * Settings.scale, uiStrings.TEXT[0], uiStrings.TEXT[1]);
            }
        }

    }

    private void renderVfx(SpriteBatch sb) {
            sb.setBlendFunction(770, 1);
            sb.setColor(this.energyVfxColor);
            sb.draw(tex, this.current_x - 128.0F, this.current_y - 128.0F, 128.0F, 128.0F, 256.0F, 256.0F, this.energyVfxScale, this.energyVfxScale, this.energyVfxAngle, 0, 0, 256, 256, false, false);
            sb.setBlendFunction(770, 771);

    }

    public static void updateTooltip() {
        if (player != null) {
            tooltip.body = uiStrings.TEXT[1];
            tips.clear();
            tips.add(tooltip);
        }

    }

    static {
        uiStrings = CardCrawlGame.languagePack.getUIString("SpToolTips");
        ENERGY_TEXT_COLOR = new Color(1.0F, 1.0F, 0.86F, 1.0F);
        fontScale = 1.0F;
        tex = OrbVfx();
    }
}
