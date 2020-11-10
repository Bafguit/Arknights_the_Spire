package com.ndc.arknightsthespire.buttons;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Interpolation;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.helpers.Hitbox;
import com.megacrit.cardcrawl.helpers.MathHelper;
import com.megacrit.cardcrawl.helpers.TipHelper;
import com.megacrit.cardcrawl.helpers.input.InputHelper;
import com.megacrit.cardcrawl.localization.TutorialStrings;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import com.megacrit.cardcrawl.ui.panels.AbstractPanel;
import com.megacrit.cardcrawl.unlock.UnlockTracker;
import com.megacrit.cardcrawl.vfx.RefreshEnergyEffect;

public class SPPanel extends AbstractPanel {
    private static final TutorialStrings tutorialStrings;
    public static final String[] MSG;
    public static final String[] LABEL;
    private static final int RAW_W = 256;
    private static final Color ENERGY_TEXT_COLOR;
    public static float fontScale;
    public static final float FONT_POP_SCALE = 2.0F;
    public static int totalCount;
    private Hitbox tipHitbox;
    private Texture gainEnergyImg;
    private float energyVfxAngle;
    private float energyVfxScale;
    private Color energyVfxColor;
    public static float energyVfxTimer;
    public static final float ENERGY_VFX_TIME = 2.0F;
    private static final float VFX_ROTATE_SPEED = -30.0F;

    public SPPanel() {
        super(198.0F * Settings.scale, 190.0F * Settings.scale, -480.0F * Settings.scale, 200.0F * Settings.scale, 12.0F * Settings.scale, -12.0F * Settings.scale, (Texture)null, true);
        this.tipHitbox = new Hitbox(0.0F, 0.0F, 120.0F * Settings.scale, 120.0F * Settings.scale);
        this.energyVfxAngle = 0.0F;
        this.energyVfxScale = Settings.scale;
        this.energyVfxColor = Color.WHITE.cpy();
        this.gainEnergyImg = AbstractDungeon.player.getEnergyImage();
    }

    public static void setEnergy(int energy) {
        totalCount = energy;
        AbstractDungeon.effectsQueue.add(new RefreshEnergyEffect());
        energyVfxTimer = 2.0F;
        fontScale = 2.0F;
    }

    public static void addEnergy(int e) {
        totalCount += e;
        if (totalCount >= 9) {
            UnlockTracker.unlockAchievement("ADRENALINE");
        }

        if (totalCount > 999) {
            totalCount = 999;
        }

        AbstractDungeon.effectsQueue.add(new RefreshEnergyEffect());
        fontScale = 2.0F;
        spVfxTimer = 2.0F;
    }

    public static void useSp(int e) {
        totalCount -= e;
        if (totalCount < 0) {
            totalCount = 0;
        }

        if (e != 0) {
            fontScale = 2.0F;
        }

    }

    public void update() {
        AbstractDungeon.player.updateOrb(totalCount);
        this.updateVfx();
        if (fontScale != 1.0F) {
            fontScale = MathHelper.scaleLerpSnap(fontScale, 1.0F);
        }

        this.tipHitbox.update();
        if (this.tipHitbox.hovered && !AbstractDungeon.isScreenUp) {
            AbstractDungeon.overlayMenu.hoveredTip = true;
        }

        if (Settings.isDebug) {
            if (InputHelper.scrolledDown) {
                addSp(1);
            } else if (InputHelper.scrolledUp && totalCount > 0) {
                useSp(1);
            }
        }

    }

    private void updateVfx() {
        if (spVfxTimer != 0.0F) {
            this.spVfxColor.a = Interpolation.exp10In.apply(0.5F, 0.0F, 1.0F - spVfxTimer / 2.0F);
            this.spVfxAngle += Gdx.graphics.getDeltaTime() * -30.0F;
            this.spVfxScale = Settings.scale * Interpolation.exp10In.apply(1.0F, 0.1F, 1.0F - energyVfxTimer / 2.0F);
            spVfxTimer -= Gdx.graphics.getDeltaTime();
            if (spVfxTimer < 0.0F) {
                spVfxTimer = 0.0F;
                this.spVfxColor.a = 0.0F;
            }
        }

    }

    public void render(SpriteBatch sb) {
        this.tipHitbox.move(this.current_x, this.current_y);
        this.renderOrb(sb);
        this.renderVfx(sb);
        String spMsg = totalCount + "/" + AbstractDungeon.player.energy.energy;
        AbstractDungeon.player.getEnergyNumFont().getData().setScale(fontScale);
        FontHelper.renderFontCentered(sb, AbstractDungeon.player.getEnergyNumFont(), energyMsg, this.current_x, this.current_y, ENERGY_TEXT_COLOR);
        this.tipHitbox.render(sb);
        if (this.tipHitbox.hovered && AbstractDungeon.getCurrRoom().phase == AbstractRoom.RoomPhase.COMBAT && !AbstractDungeon.isScreenUp) {
            TipHelper.renderGenericTip(50.0F * Settings.scale, 380.0F * Settings.scale, LABEL[0], MSG[0]);
        }

    }

    private void renderOrb(SpriteBatch sb) {
        if (totalCount == 0) {
            AbstractDungeon.player.renderOrb(sb, false, this.current_x, this.current_y);
        } else {
            AbstractDungeon.player.renderOrb(sb, true, this.current_x, this.current_y);
        }

    }

    private void renderVfx(SpriteBatch sb) {
        if (energyVfxTimer != 0.0F) {
            sb.setBlendFunction(770, 1);
            sb.setColor(this.energyVfxColor);
            sb.draw(this.gainEnergyImg, this.current_x - 128.0F, this.current_y - 128.0F, 128.0F, 128.0F, 256.0F, 256.0F, this.energyVfxScale, this.energyVfxScale, -this.energyVfxAngle + 50.0F, 0, 0, 256, 256, true, false);
            sb.draw(this.gainEnergyImg, this.current_x - 128.0F, this.current_y - 128.0F, 128.0F, 128.0F, 256.0F, 256.0F, this.energyVfxScale, this.energyVfxScale, this.energyVfxAngle, 0, 0, 256, 256, false, false);
            sb.setBlendFunction(770, 771);
        }

    }

    public static int getCurrentEnergy() {
        return AbstractDungeon.player == null ? 0 : totalCount;
    }

    static {
        tutorialStrings = CardCrawlGame.languagePack.getTutorialString("SP Panel Tip");
        MSG = tutorialStrings.TEXT;
        LABEL = tutorialStrings.LABEL;
        ENERGY_TEXT_COLOR = new Color(1.0F, 1.0F, 0.86F, 1.0F);
        fontScale = 1.0F;
        totalCount = 0;
        energyVfxTimer = 0.0F;
    }
}