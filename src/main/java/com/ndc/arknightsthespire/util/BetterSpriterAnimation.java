//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.ndc.arknightsthespire.util;

import basemod.animations.AbstractAnimation;
import basemod.animations.AbstractAnimation.Type;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.brashmonkey.spriter.Data;
import com.brashmonkey.spriter.Drawer;
import com.brashmonkey.spriter.Loader;
import com.brashmonkey.spriter.Player;
import com.brashmonkey.spriter.Point;
import com.brashmonkey.spriter.SCMLReader;
import com.brashmonkey.spriter.LibGdx.LibGdxLoader;
import com.brashmonkey.spriter.Timeline.Key.Object;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class BetterSpriterAnimation extends AbstractAnimation {
    public static boolean drawBones = false;
    private static final float animFps = 0.016666668F;
    private float frameRegulator = 0.0F;
    LibGdxLoader loader;
    BetterSpriterAnimation.BetterLibGdxDrawer drawer;
    ShapeRenderer renderer = new ShapeRenderer();
    public Player myPlayer;
    private Color color;
    private Color targetColor;
    private static final float lerpSpeed = 3.0F;
    private boolean isDying = false;

    public BetterSpriterAnimation(String filepath) {
        FileHandle handle = Gdx.files.internal(filepath);
        Data data = (new SCMLReader(handle.read())).getData();
        this.loader = new LibGdxLoader(data);
        this.loader.load(handle.file());
        this.drawer = new BetterSpriterAnimation.BetterLibGdxDrawer(this.loader, this.renderer);
        this.myPlayer = new Player(data.getEntity(0));
        this.myPlayer.setScale(Settings.scale);
        this.color = this.renderer.getColor();
        this.targetColor = new Color(this.color.r, this.color.g, this.color.b, 0.0F);
    }

    public Type type() {
        return Type.SPRITE;
    }

    public void setFlip(boolean horizontal, boolean vertical) {
        if (horizontal && this.myPlayer.flippedX() > 0 || !horizontal && this.myPlayer.flippedX() < 0) {
            this.myPlayer.flipX();
        }

        if (vertical && this.myPlayer.flippedY() > 0 || !vertical && this.myPlayer.flippedY() < 0) {
            this.myPlayer.flipY();
        }

    }

    public boolean isFastMode() {
        return Settings.FAST_MODE;
    }

    public void renderSprite(SpriteBatch batch, float x, float y) {
        this.drawer.batch = batch;

        for(this.frameRegulator += (Gdx.graphics.getDeltaTime() * (isFastMode() ? 1.0F : 0.6F)); this.frameRegulator - 0.016666668F >= 0.0F; this.frameRegulator -= 0.016666668F) {
            this.myPlayer.update();
        }

        if (this.isDying) {
            this.color.lerp(this.targetColor, Gdx.graphics.getDeltaTime() * 2.0F);
            this.drawer.setColor(this.color.r, this.color.g, this.color.b, this.color.a);
        }

        AbstractPlayer player = AbstractDungeon.player;
        if (player != null) {
            this.myPlayer.setPosition(new Point(x, y));
            this.drawer.draw(this.myPlayer);
            if (drawBones) {
                batch.end();
                this.renderer.setAutoShapeType(true);
                this.renderer.begin();
                this.drawer.drawBoxes(this.myPlayer);
                this.drawer.drawBones(this.myPlayer);
                this.renderer.end();
                batch.begin();
            }
        }

    }

    public void startDying() {
        this.isDying = true;
    }

    private static class BetterLibGdxDrawer extends Drawer<Sprite> {
        public SpriteBatch batch;
        ShapeRenderer renderer;

        public BetterLibGdxDrawer(Loader<Sprite> loader, ShapeRenderer renderer) {
            super(loader);
            this.renderer = renderer;
        }

        public void setColor(float r, float g, float b, float a) {
            this.renderer.setColor(r, g, b, a);
        }

        public void rectangle(float x, float y, float width, float height) {
            this.renderer.rect(x, y, width, height);
        }

        public void line(float x1, float y1, float x2, float y2) {
            this.renderer.line(x1, y1, x2, y2);
        }

        public void circle(float x, float y, float radius) {
            this.renderer.circle(x, y, radius);
        }

        public void draw(Object object) {
            Sprite sprite = (Sprite)this.loader.get(object.ref);
            float newPivotX = sprite.getWidth() * object.pivot.x;
            float newX = object.position.x - newPivotX;
            float newPivotY = sprite.getHeight() * object.pivot.y;
            float newY = object.position.y - newPivotY;
            sprite.setX(newX);
            sprite.setY(newY);
            sprite.setOrigin(newPivotX, newPivotY);
            sprite.setRotation(object.angle);
            sprite.setColor(this.renderer.getColor());
            sprite.setScale(object.scale.x, object.scale.y);
            sprite.draw(this.batch);
        }
    }
}
