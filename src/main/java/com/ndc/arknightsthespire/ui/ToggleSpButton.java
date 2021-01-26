package com.ndc.arknightsthespire.ui;

import basemod.ReflectionHacks;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.*;
import com.megacrit.cardcrawl.helpers.ShaderHelper.Shader;
import com.megacrit.cardcrawl.helpers.controller.CInputActionSet;
import com.megacrit.cardcrawl.helpers.input.InputActionSet;
import com.megacrit.cardcrawl.helpers.input.InputHelper;
import com.megacrit.cardcrawl.localization.TutorialStrings;
import com.megacrit.cardcrawl.localization.UIStrings;
import com.megacrit.cardcrawl.ui.buttons.EndTurnButton;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import com.megacrit.cardcrawl.vfx.EndTurnGlowEffect;
import com.ndc.arknightsthespire.SPHandler;
import com.ndc.arknightsthespire.cards.base.CardSPBase;
import com.ndc.arknightsthespire.patcher.EndTurnButtonPatcher;
import com.ndc.arknightsthespire.patcher.InputActionSetPatcher;

import java.util.ArrayList;
import java.util.Iterator;

public class ToggleSpButton {
    private static final TutorialStrings tutorialStrings;
    public static final String[] MSG;
    public static final String[] LABEL;
    private static final UIStrings uiStrings;
    public static final String[] TEXT;
    private String label;
    public static final String TURN_ON_MSG;
    public static final String TURN_OFF_MSG;
    private static final Color DISABLED_COLOR;
    private static final float SHOW_X;
    private static final float SHOW_Y;
    private static final float HIDE_X;
    private float current_x;
    private float current_y;
    private float target_x;
    private boolean isHidden;
    public boolean enabled;
    private boolean isDisabled;
    private Color textColor;
    private ArrayList<EndTurnGlowEffect> glowList;
    private float glowTimer;
    public boolean isGlowing;
    private Hitbox hb;
    private float holdProgress;
    private Color holdBarColor;
    private boolean wasKeyPressed = false; //The last state when keypress is processed.

    public static final float OFFSET_X = 93;
    public static final float LEFT_TEXT_OFFSET_X = 7.0F;
    public static final float RIGHT_TEXT_OFFSET_X = -7.0F;
    public static final float TEXT_OFFSET_Y = -1.5F;
    public static final float HITBOX_WIDTH = 185.0F;
    public static final float HITBOX_HEIGHT = 100.0F;
    EndTurnButton endTurnButton;

    public static Texture UI_BUTTON_LEFT = ImageMaster.loadImage("img/ui/uiButtonLeft.png");
    public static Texture UI_BUTTON_LEFT_HOVER = ImageMaster.loadImage("img/ui/uiButtonLeftHover.png");
    public static Texture UI_BUTTON_RIGHT = ImageMaster.loadImage("img/ui/uiButtonRight.png");
    public static Texture UI_BUTTON_RIGHT_GLOW = ImageMaster.loadImage("img/ui/uiButtonRightGlow.png");
    public static Texture UI_BUTTON_RIGHT_HOVER = ImageMaster.loadImage("img/ui/uiButtonRightHover.png");

    public ToggleSpButton(EndTurnButton endTurnButton) {
        this.label = TURN_ON_MSG;
        this.current_x = HIDE_X;
        this.current_y = SHOW_Y;
        this.target_x = this.current_x;
        this.isHidden = true;
        this.enabled = false;
        this.isDisabled = false;
        this.glowList = new ArrayList();
        this.glowTimer = 0.0F;
        this.isGlowing = false;
        this.hb = new Hitbox(0.0F, 0.0F, HITBOX_WIDTH * Settings.scale, HITBOX_HEIGHT * Settings.scale);
        this.holdProgress = 0.0F;
        this.holdBarColor = new Color(1.0F, 1.0F, 1.0F, 0.0F);

        this.endTurnButton = endTurnButton;
    }

    public void update() {
        /*if(!Settings.hideEndTurn) {
            this.current_y = SHOW_Y + BUTTON_OFFSET_Y;
        }*/

        if(endTurnButton.enabled) {
            this.enable();
        }
        if(!endTurnButton.enabled) {
            SPHandler.setSpMode(false);
            updateText(SPHandler.isSpModeEnabled() ? TURN_OFF_MSG : TURN_ON_MSG);
            this.disable();
        }

        //Check Key Press
        if(this.enabled) {
            if(InputActionSetPatcher.enableSPButton.isPressed()) {
                if(!wasKeyPressed) {
                    if (!SPHandler.isSpModeEnabled()) {
                        SPHandler.setSpMode(true);
                        updateText(SPHandler.isSpModeEnabled() ? TURN_OFF_MSG : TURN_ON_MSG);
                        CardSPBase.updateAllStateInHand(true);
                    }
                    wasKeyPressed = true;
                }
            } else {
                if(wasKeyPressed) {
                    if (SPHandler.isSpModeEnabled()) {
                        SPHandler.setSpMode(false);
                        updateText(SPHandler.isSpModeEnabled() ? TURN_OFF_MSG : TURN_ON_MSG);
                        CardSPBase.updateAllStateInHand(true);
                    }
                    wasKeyPressed = false;
                }
            }
        }

        this.glow();
        this.updateHoldProgress();
        if (this.current_x != this.target_x) {
            this.current_x = MathUtils.lerp(this.current_x, this.target_x, Gdx.graphics.getDeltaTime() * 9.0F);
            if (Math.abs(this.current_x - this.target_x) < Settings.UI_SNAP_THRESHOLD) {
                this.current_x = this.target_x;
            }
        }

        this.hb.move(this.current_x, this.current_y);
        if (this.enabled) {
            this.isDisabled = false;
            if (AbstractDungeon.isScreenUp || AbstractDungeon.player.isDraggingCard || AbstractDungeon.player.inSingleTargetMode) {
                this.isDisabled = true;
            }

            if (AbstractDungeon.player.hoveredCard == null) {
                this.hb.update();
            }

            if (!Settings.USE_LONG_PRESS && InputHelper.justClickedLeft && this.hb.hovered && !this.isDisabled && !AbstractDungeon.isScreenUp) {
                this.hb.clickStarted = true;
                CardCrawlGame.sound.play("UI_CLICK_1");
            }

            if (this.hb.hovered && !this.isDisabled && !AbstractDungeon.isScreenUp) {
                if (this.hb.justHovered && AbstractDungeon.player.hoveredCard == null) {
                    //When hovered
                    CardCrawlGame.sound.play("UI_HOVER");
                    Iterator var1 = AbstractDungeon.player.hand.group.iterator();
                    while(var1.hasNext()) {
                        AbstractCard c = (AbstractCard)var1.next();
                        if(c instanceof CardSPBase) {
                            CardSPBase card = (CardSPBase) c;
                            if (card.isGlowing && card.canAffordSP()) {
                                //c.superFlash(c.glowColor);
                            }
                        }
                    }

                }
            }
        }

        if (this.holdProgress == 0.4F && !this.isDisabled && !AbstractDungeon.isScreenUp) {
            this.disable();
            this.holdProgress = 0.0F;
        }

        if ((!Settings.USE_LONG_PRESS || !Settings.isControllerMode && !InputActionSet.endTurn.isPressed()) && (this.hb.clicked || (InputActionSet.endTurn.isJustPressed() || CInputActionSet.proceed.isJustPressed()) && !this.isDisabled && this.enabled)) {
            this.hb.clicked = false;
            if (!this.isDisabled && !AbstractDungeon.isScreenUp) {
                //When Clicked

                SPHandler.toggleSpMode();
                updateText(SPHandler.isSpModeEnabled() ? TURN_OFF_MSG : TURN_ON_MSG);
                CardSPBase.updateAllStateInHand(true);
            }
        }
    }

    private void updateHoldProgress() {
        if (!Settings.USE_LONG_PRESS || !Settings.isControllerMode && !InputActionSet.endTurn.isPressed() && !InputHelper.isMouseDown) {
            this.holdProgress -= Gdx.graphics.getDeltaTime();
            if (this.holdProgress < 0.0F) {
                this.holdProgress = 0.0F;
            }

        } else {
            if ((this.hb.hovered && InputHelper.isMouseDown || CInputActionSet.proceed.isPressed() || InputActionSet.endTurn.isPressed()) && !this.isDisabled && this.enabled) {
                this.holdProgress += Gdx.graphics.getDeltaTime();
                if (this.holdProgress > 0.4F) {
                    this.holdProgress = 0.4F;
                }
            } else {
                this.holdProgress -= Gdx.graphics.getDeltaTime();
                if (this.holdProgress < 0.0F) {
                    this.holdProgress = 0.0F;
                }
            }

        }
    }

    public void enable() {
        this.enabled = true;
    }

    public void disable() {
        this.enabled = false;
        this.hb.hovered = false;
        this.isGlowing = false;
    }

    public void updateText(String msg) {
        this.label = msg;
    }

    private void glow() {
        if (this.isGlowing && !this.isHidden) {
            if (this.glowTimer < 0.0F) {
                this.glowList.add(new EndTurnGlowEffect());
                this.glowTimer = 1.2F;
            } else {
                this.glowTimer -= Gdx.graphics.getDeltaTime();
            }
        }

        Iterator i = this.glowList.iterator();

        while(i.hasNext()) {
            AbstractGameEffect e = (AbstractGameEffect)i.next();
            e.update();
            if (e.isDone) {
                i.remove();
            }
        }

    }

    public void hide() {
        this.target_x = HIDE_X;
        this.isHidden = true;

    }

    public void show() {
        this.target_x = SHOW_X;
        this.isHidden = false;
        if (this.isGlowing) {
            this.glowTimer = -1.0F;
        }

    }

    public void render(SpriteBatch sb) {
        if(!Settings.hideEndTurn) {
            float tmpY = this.current_y;
            this.renderHoldEndTurn(sb);
            //System.out.println(ReflectionHacks.getPrivate(endTurnButton, EndTurnButton.class, "label"));
            if (!this.isDisabled && this.enabled) {
                if (this.hb.hovered) {
                    if (SPHandler.isSpModeEnabled()) {
                        this.textColor = Color.MAGENTA;
                    } else {
                        this.textColor = Color.CYAN;
                    }
                } else if (this.isGlowing) {
                    this.textColor = Settings.GOLD_COLOR;
                } else {
                    this.textColor = Settings.CREAM_COLOR;
                }

                if (this.hb.hovered && !AbstractDungeon.isScreenUp && !Settings.isTouchScreen) {
                    TipHelper.renderGenericTip(this.current_x - 90.0F * Settings.scale, this.current_y + 300.0F * Settings.scale, LABEL[0], MSG[0]);
                }
            } else if (ReflectionHacks.getPrivate(endTurnButton, EndTurnButton.class, "label").equals(EndTurnButton.ENEMY_TURN_MSG)) {
                this.textColor = Settings.CREAM_COLOR;
            } else {
                this.textColor = Color.LIGHT_GRAY;
            }

            if (this.hb.clickStarted && !AbstractDungeon.isScreenUp) {
                tmpY -= 2.0F * Settings.scale;
            } else if (this.hb.hovered && !AbstractDungeon.isScreenUp) {
                tmpY += 2.0F * Settings.scale;
            }

            if (!this.enabled) {
                ShaderHelper.setShader(sb, Shader.GRAYSCALE);
            } else if (!this.isDisabled && (!this.hb.clickStarted || !this.hb.hovered)) {
                sb.setColor(Color.WHITE);
            } else {
                sb.setColor(DISABLED_COLOR);
            }

            Texture buttonImg;
            if (this.isGlowing && !this.hb.clickStarted) {
                buttonImg = UI_BUTTON_LEFT_HOVER;
            } else {
                buttonImg = UI_BUTTON_LEFT;
            }

            if (this.hb.hovered && !this.isDisabled && !AbstractDungeon.isScreenUp) {
                sb.draw(UI_BUTTON_LEFT_HOVER, this.current_x - 128.0F, tmpY - 128.0F, 128.0F, 128.0F, 256.0F, 256.0F, Settings.scale, Settings.scale, 0.0F, 0, 0, 256, 256, false, false);
            }

            sb.draw(buttonImg, this.current_x - 128.0F, tmpY - 128.0F, 128.0F, 128.0F, 256.0F, 256.0F, Settings.scale, Settings.scale, 0.0F, 0, 0, 256, 256, false, false);
            if (!this.enabled) {
                ShaderHelper.setShader(sb, Shader.DEFAULT);
            }

            this.renderGlowEffect(sb, this.current_x, this.current_y);
            if ((this.hb.hovered || this.holdProgress > 0.0F) && !this.isDisabled && !AbstractDungeon.isScreenUp) {
                sb.setBlendFunction(770, 1);
                sb.setColor(Settings.HALF_TRANSPARENT_WHITE_COLOR);
                sb.draw(buttonImg, this.current_x - 128.0F, tmpY - 128.0F, 128.0F, 128.0F, 256.0F, 256.0F, Settings.scale, Settings.scale, 0.0F, 0, 0, 256, 256, false, false);
                sb.setBlendFunction(770, 771);
            }

            if (Settings.isControllerMode && this.enabled) {
                sb.setColor(Color.WHITE);
                sb.draw(CInputActionSet.proceed.getKeyImg(), this.current_x - 32.0F - 42.0F * Settings.scale - FontHelper.getSmartWidth(FontHelper.panelEndTurnFont, this.label, 99999.0F, 0.0F) / 2.0F, tmpY - 32.0F, 32.0F, 32.0F, 64.0F, 64.0F, Settings.scale, Settings.scale, 0.0F, 0, 0, 64, 64, false, false);
            }

            FontHelper.renderFontCentered(sb, FontHelper.panelEndTurnFont, this.label, this.current_x + LEFT_TEXT_OFFSET_X * Settings.scale, tmpY + TEXT_OFFSET_Y * Settings.scale, this.textColor);
            if (!this.isHidden) {
                this.hb.render(sb);
            }
        }
    }

    private void renderHoldEndTurn(SpriteBatch sb) {
        if (Settings.USE_LONG_PRESS) {
            this.holdBarColor.r = 0.0F;
            this.holdBarColor.g = 0.0F;
            this.holdBarColor.b = 0.0F;
            this.holdBarColor.a = this.holdProgress * 1.5F;
            sb.setColor(this.holdBarColor);
            sb.draw(ImageMaster.WHITE_SQUARE_IMG, this.current_x - 107.0F * Settings.scale, this.current_y + 53.0F * Settings.scale - 7.0F * Settings.scale, 525.0F * Settings.scale * this.holdProgress + 14.0F * Settings.scale, 20.0F * Settings.scale);
            this.holdBarColor.r = this.holdProgress * 2.5F;
            this.holdBarColor.g = 0.6F + this.holdProgress;
            this.holdBarColor.b = 0.6F;
            this.holdBarColor.a = 1.0F;
            sb.setColor(this.holdBarColor);
            sb.draw(ImageMaster.WHITE_SQUARE_IMG, this.current_x - 100.0F * Settings.scale, this.current_y + 53.0F * Settings.scale, 525.0F * Settings.scale * this.holdProgress, 6.0F * Settings.scale);
        }
    }

    private void renderGlowEffect(SpriteBatch sb, float x, float y) {
        Iterator var4 = this.glowList.iterator();

        while(var4.hasNext()) {
            EndTurnGlowEffect e = (EndTurnGlowEffect)var4.next();
            e.render(sb, x, y);
        }

    }

    static {
        tutorialStrings = CardCrawlGame.languagePack.getTutorialString("SpToggleButton Tip");//TODO
        MSG = tutorialStrings.TEXT;
        LABEL = tutorialStrings.LABEL;
        uiStrings = CardCrawlGame.languagePack.getUIString("SpToggleButton");
        TEXT = uiStrings.TEXT;
        TURN_ON_MSG = TEXT[0];
        TURN_OFF_MSG = TEXT[1];
        DISABLED_COLOR = new Color(0.7F, 0.7F, 0.7F, 1.0F);
        SHOW_X = EndTurnButtonPatcher.SHOW_X - OFFSET_X * Settings.scale;
        SHOW_Y = EndTurnButtonPatcher.SHOW_Y;
        HIDE_X = EndTurnButtonPatcher.HIDE_X - OFFSET_X * Settings.scale;
    }
}
