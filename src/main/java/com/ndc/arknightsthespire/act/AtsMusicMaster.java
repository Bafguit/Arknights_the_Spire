//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.ndc.arknightsthespire.act;

import com.megacrit.cardcrawl.audio.MainMusic;
import com.megacrit.cardcrawl.core.Settings;
import java.util.ArrayList;
import java.util.Iterator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class AtsMusicMaster {
    private static final Logger logger = LogManager.getLogger(AtsMusicMaster.class.getName());
    private ArrayList<MainMusic> mainTrack = new ArrayList();
    private ArrayList<AtsTempMusic> tempTrack = new ArrayList();

    public AtsMusicMaster() {
        Settings.MASTER_VOLUME = Settings.soundPref.getFloat("Master Volume", 0.5F);
        Settings.MUSIC_VOLUME = Settings.soundPref.getFloat("Music Volume", 0.5F);
        logger.info("Music Volume: " + Settings.MUSIC_VOLUME);
    }

    public void update() {
        this.updateBGM();
        this.updateTempBGM();
    }

    public void updateVolume() {
        Iterator var1 = this.mainTrack.iterator();

        while(var1.hasNext()) {
            MainMusic m = (MainMusic)var1.next();
            m.updateVolume();
        }

        var1 = this.tempTrack.iterator();

        while(var1.hasNext()) {
            AtsTempMusic m = (AtsTempMusic)var1.next();
            m.updateVolume();
        }

    }

    private void updateBGM() {
        Iterator i = this.mainTrack.iterator();

        while(i.hasNext()) {
            MainMusic e = (MainMusic)i.next();
            e.update();
            if (e.isDone) {
                i.remove();
            }
        }

    }

    private void updateTempBGM() {
        Iterator i = this.tempTrack.iterator();

        while(i.hasNext()) {
            AtsTempMusic e = (AtsTempMusic)i.next();
            e.update();
            if (e.isDone) {
                i.remove();
            }
        }

    }

    public void fadeOutTempBGM() {
        Iterator var1 = this.tempTrack.iterator();

        while(var1.hasNext()) {
            AtsTempMusic m = (AtsTempMusic)var1.next();
            if (!m.isFadingOut) {
                m.fadeOut();
            }
        }

        var1 = this.mainTrack.iterator();

        while(var1.hasNext()) {
            MainMusic m = (MainMusic)var1.next();
            m.unsilence();
        }

    }

    public void justFadeOutTempBGM() {
        Iterator var1 = this.tempTrack.iterator();

        while(var1.hasNext()) {
            AtsTempMusic m = (AtsTempMusic)var1.next();
            if (!m.isFadingOut) {
                m.fadeOut();
            }
        }

    }

    public void playTempBGM(String key) {
        if (key != null) {
            logger.info("Playing " + key);
            this.tempTrack.add(new AtsTempMusic(key, false));
            Iterator var2 = this.mainTrack.iterator();

            while(var2.hasNext()) {
                MainMusic m = (MainMusic)var2.next();
                m.silence();
            }
        }

    }

    public void playTempBgmInstantly(String key) {
        if (key != null) {
            logger.info("Playing " + key);
            this.tempTrack.add(new AtsTempMusic(key, true));
            Iterator var2 = this.mainTrack.iterator();

            while(var2.hasNext()) {
                MainMusic m = (MainMusic)var2.next();
                m.silenceInstantly();
            }
        }

    }

    public void precacheTempBgm(String key) {
        if (key != null) {
            logger.info("Pre-caching " + key);
            this.tempTrack.add(new AtsTempMusic(key, true, true, true));
        }

    }

    public void playPrecachedTempBgm() {
        if (!this.tempTrack.isEmpty()) {
            ((AtsTempMusic)this.tempTrack.get(0)).playPrecached();
            Iterator var1 = this.mainTrack.iterator();

            while(var1.hasNext()) {
                MainMusic m = (MainMusic)var1.next();
                m.silenceInstantly();
            }
        }

    }

    public void playTempBgmInstantly(String key, boolean loop) {
        if (key != null) {
            logger.info("Playing " + key);
            this.tempTrack.add(new AtsTempMusic(key, true, loop));
            Iterator var3 = this.mainTrack.iterator();

            while(var3.hasNext()) {
                MainMusic m = (MainMusic)var3.next();
                m.silenceInstantly();
            }
        }

    }

    public void changeBGM(String key) {
        this.mainTrack.add(new MainMusic(key));
    }

    public void fadeOutBGM() {
        Iterator var1 = this.mainTrack.iterator();

        while(var1.hasNext()) {
            MainMusic m = (MainMusic)var1.next();
            if (!m.isFadingOut) {
                m.fadeOut();
            }
        }

    }

    public void silenceBGM() {
        Iterator var1 = this.mainTrack.iterator();

        while(var1.hasNext()) {
            MainMusic m = (MainMusic)var1.next();
            m.silence();
        }

    }

    public void silenceBGMInstantly() {
        Iterator var1 = this.mainTrack.iterator();

        while(var1.hasNext()) {
            MainMusic m = (MainMusic)var1.next();
            m.silenceInstantly();
        }

    }

    public void unsilenceBGM() {
        Iterator var1 = this.mainTrack.iterator();

        while(var1.hasNext()) {
            MainMusic m = (MainMusic)var1.next();
            m.unsilence();
        }

    }

    public void silenceTempBgmInstantly() {
        AtsTempMusic m;
        for(Iterator var1 = this.tempTrack.iterator(); var1.hasNext(); m.isFadingOut = true) {
            m = (AtsTempMusic)var1.next();
            m.silenceInstantly();
        }

    }

    public void dispose() {
        Iterator var1 = this.mainTrack.iterator();

        while(var1.hasNext()) {
            MainMusic m = (MainMusic)var1.next();
            m.kill();
        }

        var1 = this.tempTrack.iterator();

        while(var1.hasNext()) {
            AtsTempMusic m = (AtsTempMusic)var1.next();
            m.kill();
        }

    }

    public void fadeAll() {
        Iterator var1 = this.mainTrack.iterator();

        while(var1.hasNext()) {
            MainMusic m = (MainMusic)var1.next();
            m.fadeOut();
        }

        var1 = this.tempTrack.iterator();

        while(var1.hasNext()) {
            AtsTempMusic m = (AtsTempMusic)var1.next();
            m.fadeOut();
        }

    }
}
