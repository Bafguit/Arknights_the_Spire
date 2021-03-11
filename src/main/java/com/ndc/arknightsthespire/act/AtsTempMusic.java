//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.ndc.arknightsthespire.act;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.math.Interpolation;
import com.megacrit.cardcrawl.audio.MainMusic;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.MathHelper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class AtsTempMusic {
    private static final Logger logger = LogManager.getLogger(AtsTempMusic.class.getName());
    private Music music;
    public String key;
    private static final String DIR = "audio/music/";
    private static final String SHOP_BGM = "STS_Merchant_NewMix_v1.ogg";
    private static final String SHRINE_BGM = "STS_Shrine_NewMix_v1.ogg";
    private static final String MINDBLOOM_BGM = "STS_Boss1MindBloom_v1.ogg";
    private static final String LEVEL_1_BOSS_BGM = "STS_Boss1_NewMix_v1.ogg";
    private static final String LEVEL_2_BOSS_BGM = "STS_Boss2_NewMix_v1.ogg";
    private static final String LEVEL_3_BOSS_BGM = "STS_Boss3_NewMix_v1.ogg";
    private static final String LEVEL_4_BOSS_BGM = "STS_Boss4_v6.ogg";
    private static final String ELITE_BGM = "STS_EliteBoss_NewMix_v1.ogg";
    private static final String CREDITS = "STS_Credits_v5.ogg";
    public boolean isSilenced;
    private float silenceTimer;
    private float silenceTime;
    private static final float FAST_SILENCE_TIME = 0.25F;
    private float silenceStartVolume;
    private static final float FADE_IN_TIME = 4.0F;
    private static final float FAST_FADE_IN_TIME = 0.25F;
    private static final float FADE_OUT_TIME = 4.0F;
    private float fadeTimer;
    private float fadeTime;
    public boolean isFadingOut;
    private float fadeOutStartVolume;
    public boolean isDone;

    public AtsTempMusic(String key, boolean isFast) {
        this(key, isFast, true);
    }

    public AtsTempMusic(String key, boolean isFast, boolean loop) {
        this.isSilenced = false;
        this.silenceTimer = 0.0F;
        this.silenceTime = 0.0F;
        this.isFadingOut = false;
        this.isDone = false;
        this.key = key;
        this.music = this.getSong(key);
        if (isFast) {
            this.fadeTimer = 0.25F;
            this.fadeTime = 0.25F;
        } else {
            this.fadeTimer = 4.0F;
            this.fadeTime = 4.0F;
        }

        this.music.setLooping(loop);
        this.music.play();
        this.music.setVolume(0.0F);
    }

    public AtsTempMusic(String key, boolean isFast, boolean loop, boolean precache) {
        this.isSilenced = false;
        this.silenceTimer = 0.0F;
        this.silenceTime = 0.0F;
        this.isFadingOut = false;
        this.isDone = false;
        this.key = key;
        this.music = this.getSong(key);
        if (isFast) {
            this.fadeTimer = 0.25F;
            this.fadeTime = 0.25F;
        } else {
            this.fadeTimer = 4.0F;
            this.fadeTime = 4.0F;
        }

        this.music.setLooping(loop);
        this.music.setVolume(0.0F);
    }

    public void playPrecached() {
        if (!this.music.isPlaying()) {
            this.music.play();
        } else {
            logger.info("[WARNING] Attempted to play music that is already playing.");
        }

    }

    private Music getSong(String key) {
        byte var3 = -1;
        switch(key.hashCode()) {
        case -1849738749:
            if (key.equals("SHRINE")) {
                var3 = 1;
            }
            break;
        case -1680385247:
            if (key.equals("BOSS_BEYOND")) {
                var3 = 5;
            }
            break;
        case -1671294147:
            if (key.equals("BOSS_BOTTOM")) {
                var3 = 3;
            }
            break;
        case -1586817479:
            if (key.equals("BOSS_ENDING")) {
                var3 = 6;
            }
            break;
        case -843017007:
            if (key.equals("MINDBLOOM")) {
                var3 = 2;
            }
            break;
        case 2544374:
            if (key.equals("SHOP")) {
                var3 = 0;
            }
            break;
        case 29569789:
            if (key.equals("BOSS_CITY")) {
                var3 = 4;
            }
            break;
        case 66059891:
            if (key.equals("ELITE")) {
                var3 = 7;
            }
            break;
        case 1746616442:
            if (key.equals("CREDITS")) {
                var3 = 8;
            }
        }

        switch(var3) {
        case 0:
            return MainMusic.newMusic("audio/music/STS_Merchant_NewMix_v1.ogg");
        case 1:
            return MainMusic.newMusic("audio/music/STS_Shrine_NewMix_v1.ogg");
        case 2:
            return MainMusic.newMusic("audio/music/STS_Boss1MindBloom_v1.ogg");
        case 3:
            return MainMusic.newMusic("audio/music/STS_Boss1_NewMix_v1.ogg");
        case 4:
            return MainMusic.newMusic("audio/music/STS_Boss2_NewMix_v1.ogg");
        case 5:
            return MainMusic.newMusic("audio/music/STS_Boss3_NewMix_v1.ogg");
        case 6:
            return MainMusic.newMusic("audio/music/STS_Boss4_v6.ogg");
        case 7:
            return MainMusic.newMusic("audio/music/STS_EliteBoss_NewMix_v1.ogg");
        case 8:
            return MainMusic.newMusic("audio/music/STS_Credits_v5.ogg");
        default:
            return MainMusic.newMusic("atsAudio/music/" + key);
        }
    }

    public void fadeOut() {
        this.isFadingOut = true;
        this.fadeOutStartVolume = this.music.getVolume();
        this.fadeTimer = 4.0F;
    }

    public void silenceInstantly() {
        this.isSilenced = true;
        this.silenceTimer = 0.25F;
        this.silenceTime = 0.25F;
        this.silenceStartVolume = this.music.getVolume();
    }

    public void kill() {
        logger.info("Disposing TempMusic: " + this.key);
        this.music.dispose();
        this.isDone = true;
    }

    public void update() {
        if (this.music.isPlaying()) {
            if (!this.isFadingOut) {
                this.updateFadeIn();
            } else {
                this.updateFadeOut();
            }
        } else if (this.isFadingOut) {
            this.kill();
        }

    }

    private void updateFadeIn() {
        if (!this.isSilenced) {
            this.fadeTimer -= Gdx.graphics.getDeltaTime();
            if (this.fadeTimer < 0.0F) {
                this.fadeTimer = 0.0F;
                if (!Settings.isBackgrounded) {
                    this.music.setVolume(Interpolation.fade.apply(0.0F, 1.0F, 1.0F - this.fadeTimer / this.fadeTime) * Settings.MUSIC_VOLUME * Settings.MASTER_VOLUME);
                } else {
                    this.music.setVolume(MathHelper.slowColorLerpSnap(this.music.getVolume(), 0.0F));
                }
            } else if (!Settings.isBackgrounded) {
                this.music.setVolume(Interpolation.fade.apply(0.0F, 1.0F, 1.0F - this.fadeTimer / this.fadeTime) * Settings.MUSIC_VOLUME * Settings.MASTER_VOLUME);
            } else {
                this.music.setVolume(MathHelper.slowColorLerpSnap(this.music.getVolume(), 0.0F));
            }
        } else {
            this.silenceTimer -= Gdx.graphics.getDeltaTime();
            if (this.silenceTimer < 0.0F) {
                this.silenceTimer = 0.0F;
            }

            if (!Settings.isBackgrounded) {
                this.music.setVolume(Interpolation.fade.apply(this.silenceStartVolume, 0.0F, 1.0F - this.silenceTimer / this.silenceTime));
            } else {
                this.music.setVolume(MathHelper.slowColorLerpSnap(this.music.getVolume(), 0.0F));
            }
        }

    }

    private void updateFadeOut() {
        this.fadeTimer -= Gdx.graphics.getDeltaTime();
        if (this.fadeTimer < 0.0F) {
            this.fadeTimer = 0.0F;
            this.isDone = true;
            logger.info("Disposing TempMusic: " + this.key);
            this.music.dispose();
        } else {
            this.music.setVolume(Interpolation.fade.apply(this.fadeOutStartVolume, 0.0F, 1.0F - this.fadeTimer / 4.0F));
        }

    }

    public void updateVolume() {
        if (!this.isFadingOut && !this.isSilenced) {
            this.music.setVolume(Settings.MUSIC_VOLUME * Settings.MASTER_VOLUME);
        }

    }
}
