package com.ndc.arknightsthespire.ui;
/*
public class ToggleSpButton1 {

    EndTurnButton endTurnButton;

    public ToggleSpButton1(EndTurnButton endTurnButton) {
        this.endTurnButton = endTurnButton;
    }

    public void update() {
    }

    public void show() {
    }

    public void hide() {
    }

    public void render(SpirePatch sb) {
        float tmpY = (float) ReflectionHacks.getPrivate(endTurnButton, ToggleSpButton.class, "current_y");
        if (!Settings.hideEndTurn) {
            tmpY -= 128.0F;
        }
        this.renderHoldEndTurn(sb);
        if (!this.isDisabled && this.enabled) {
            if (this.hb.hovered) {
                if (this.isWarning) {
                    this.textColor = Settings.RED_TEXT_COLOR;
                } else {
                    this.textColor = Color.CYAN;
                }
            } else if (this.isGlowing) {
                this.textColor = Settings.GOLD_COLOR;
            } else {
                this.textColor = Settings.CREAM_COLOR;
            }

            if (this.hb.hovered && !AbstractDungeon.isScreenUp && !Settings.isTouchScreen) {
                TipHelper.renderGenericTip(this.current_x - 90.0F * Settings.scale, this.current_y + 300.0F * Settings.scale, LABEL[0] + " (" + InputActionSet.endTurn.getKeyString() + ")", MSG[0] + AbstractDungeon.player.gameHandSize + MSG[1]);
            }
        } else if (this.label.equals(ENEMY_TURN_MSG)) {
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
            buttonImg = ImageMaster.END_TURN_BUTTON_GLOW;
        } else {
            buttonImg = ImageMaster.END_TURN_BUTTON;
        }

        if (this.hb.hovered && !this.isDisabled && !AbstractDungeon.isScreenUp) {
            sb.draw(ImageMaster.END_TURN_HOVER, this.current_x - 128.0F, tmpY - 128.0F, 128.0F, 128.0F, 256.0F, 256.0F, Settings.scale, Settings.scale, 0.0F, 0, 0, 256, 256, false, false);
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

        FontHelper.renderFontCentered(sb, FontHelper.panelEndTurnFont, this.label, this.current_x - 0.0F * Settings.scale, tmpY - 3.0F * Settings.scale, this.textColor);
        if (!this.isHidden) {
            this.hb.render(sb);
        }
    }
}*/
