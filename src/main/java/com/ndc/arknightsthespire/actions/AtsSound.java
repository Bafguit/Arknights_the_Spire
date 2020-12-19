//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.ndc.arknightsthespire.actions;

import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.audio.Sfx;
import com.megacrit.cardcrawl.audio.SoundInfo;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class AtsSound {
    private static final Logger logger = LogManager.getLogger(AtsSound.class.getName());
    private HashMap<String, Sfx> map = new HashMap();
    private ArrayList<SoundInfo> fadeOutList = new ArrayList();
    private static final String SFX_DIR = "sfx/p_";

    public AtsSound() {
        long startTime = System.currentTimeMillis();
        Settings.SOUND_VOLUME = Settings.soundPref.getFloat("Sound Volume", 0.35F);

        this.map.put("JUEYING_1", this.load("skill_jueying_1"));
        this.map.put("JUEYING_2", this.load("skill_jueying_2"));
        this.map.put("BADAO", this.load("skill_chixiaobadao"));
        this.map.put("DURIAN", this.load("skill_fulminatingpotionY"));
        this.map.put("TIGER", this.load("skill_tigerdrop_2"));
        this.map.put("WOLF", this.load("skill_wolfroar"));
        this.map.put("SPIRIT", this.load("skill_spiritexplo"));
        this.map.put("FLAME", this.load("skill_flameexplo_3"));
        this.map.put("BURN", this.load("skill_flameexplo_4"));
        this.map.put("FIRE", this.load("skill_firebuff"));
        this.map.put("METEOR", this.load("skill_aeroliteexplo"));
        this.map.put("SKY", this.load("skill_skyeyeopen"));
        this.map.put("PUMP", this.load("skill_hydraulic_n"));
        this.map.put("HYDPUMP", this.load("skill_hydraulic_h"));
        this.map.put("BELL", this.load("skill_recallbell"));
        this.map.put("CLING", this.load("skill_soulwolf"));
        this.map.put("JUMP", this.load("skill_jumphammer"));
        this.map.put("BATTLESONG", this.load("skill_battlesong"));
        this.map.put("MAGNETIC_HAMMER", this.load("skill_magnetichammer"));
        this.map.put("SILVER", this.load("atk_silver_n"));
        this.map.put("BOTTLE", this.load("atk_bottle_n"));
        this.map.put("PISTOL", this.load("atk_pistol_n"));
        this.map.put("PISTOL_H", this.load("atk_pistol_h"));
        this.map.put("HAWK", this.load("atk_hawk_n"));
        this.map.put("HAWK_H", this.load("atk_hawk_h"));
        this.map.put("SMG", this.load("atk_smg_n"));
        this.map.put("SMG_H", this.load("atk_smg_h"));
        this.map.put("PUNCH", this.load("atk_punch_h"));
        this.map.put("ARROW", this.load("atk_arrow_n"));
        this.map.put("ARROW_H", this.load("atk_arrow_h"));
        this.map.put("CROSSBOW", this.load("atk_crossbow_n"));
        this.map.put("GRENADE", this.load("atk_grenade_n"));
        this.map.put("ROPE", this.load("atk_cowboy_h"));
        this.map.put("PAW", this.load("atk_paw_n"));
        this.map.put("PAW_H", this.load("atk_paw_h"));
        this.map.put("DAGGER", this.load("atk_firedagger_s"));
        this.map.put("SPEAR", this.load("atk_spear_n"));
        this.map.put("BLADE", this.load("atk_blade_n"));
        this.map.put("VOLCANO", this.load("aoe_flamethrower_pre"));
        this.map.put("REDSHIFT", this.load("imp_knifethrow_h"));
        this.map.put("KNIFE", this.load("imp_knifethrow_n"));
        this.map.put("CASTER", this.load("imp_darkmag_n"));
        this.map.put("MILK", this.load("imp_milkcannon_n"));
        logger.info("Sound Effect Volume: " + Settings.SOUND_VOLUME);
        logger.info("Loaded " + this.map.size() + " Sound Effects");
        logger.info("SFX load time: " + (System.currentTimeMillis() - startTime) + "ms");
    }

    private Sfx load(String filename) {
        return this.load(filename, false);
    }

    private Sfx load(String filename, boolean preload) {
        return new Sfx(SFX_DIR + filename + ".ogg", preload);
    }

    public void update() {
        Iterator i = this.fadeOutList.iterator();

        while(i.hasNext()) {
            SoundInfo e = (SoundInfo)i.next();
            e.update();
            Sfx sfx = (Sfx)this.map.get(e.name);
            if (sfx != null) {
                if (e.isDone) {
                    sfx.stop(e.id);
                    i.remove();
                } else {
                    sfx.setVolume(e.id, Settings.SOUND_VOLUME * Settings.MASTER_VOLUME * e.volumeMultiplier);
                }
            }
        }

    }

    public void preload(String key) {
        if (this.map.containsKey(key)) {
            logger.info("Preloading: " + key);
            long id = ((Sfx)this.map.get(key)).play(0.0F);
            ((Sfx)this.map.get(key)).stop(id);
        } else {
            logger.info("Missing: " + key);
        }

    }

    public long play(String key, boolean useBgmVolume) {
        if (CardCrawlGame.MUTE_IF_BG && Settings.isBackgrounded) {
            return 0L;
        } else if (this.map.containsKey(key)) {
            return useBgmVolume ? ((Sfx)this.map.get(key)).play(Settings.MUSIC_VOLUME * Settings.MASTER_VOLUME) : ((Sfx)this.map.get(key)).play(Settings.SOUND_VOLUME * Settings.MASTER_VOLUME);
        } else {
            logger.info("Missing: " + key);
            return 0L;
        }
    }

    public long play(String key) {
        return CardCrawlGame.MUTE_IF_BG && Settings.isBackgrounded ? 0L : this.play(key, false);
    }

    public long play(String key, float pitchVariation) {
        if (CardCrawlGame.MUTE_IF_BG && Settings.isBackgrounded) {
            return 0L;
        } else if (this.map.containsKey(key)) {
            return ((Sfx)this.map.get(key)).play(Settings.SOUND_VOLUME * Settings.MASTER_VOLUME, 1.0F + MathUtils.random(-pitchVariation, pitchVariation), 0.0F);
        } else {
            logger.info("Missing: " + key);
            return 0L;
        }
    }

    public long playA(String key, float pitchAdjust) {
        if (CardCrawlGame.MUTE_IF_BG && Settings.isBackgrounded) {
            return 0L;
        } else if (this.map.containsKey(key)) {
            return ((Sfx)this.map.get(key)).play(Settings.SOUND_VOLUME * Settings.MASTER_VOLUME, 1.0F + pitchAdjust, 0.0F);
        } else {
            logger.info("Missing: " + key);
            return 0L;
        }
    }

    public long playV(String key, float volumeMod) {
        if (CardCrawlGame.MUTE_IF_BG && Settings.isBackgrounded) {
            return 0L;
        } else if (this.map.containsKey(key)) {
            return ((Sfx)this.map.get(key)).play(Settings.SOUND_VOLUME * Settings.MASTER_VOLUME * volumeMod, 1.0F, 0.0F);
        } else {
            logger.info("Missing: " + key);
            return 0L;
        }
    }

    public long playAV(String key, float pitchAdjust, float volumeMod) {
        if (CardCrawlGame.MUTE_IF_BG && Settings.isBackgrounded) {
            return 0L;
        } else if (this.map.containsKey(key)) {
            return ((Sfx)this.map.get(key)).play(Settings.SOUND_VOLUME * Settings.MASTER_VOLUME * volumeMod, 1.0F + pitchAdjust, 0.0F);
        } else {
            logger.info("Missing: " + key);
            return 0L;
        }
    }

    public long playAndLoop(String key) {
        if (this.map.containsKey(key)) {
            return ((Sfx)this.map.get(key)).loop(Settings.SOUND_VOLUME * Settings.MASTER_VOLUME);
        } else {
            logger.info("Missing: " + key);
            return 0L;
        }
    }

    public long playAndLoop(String key, float volume) {
        if (this.map.containsKey(key)) {
            return ((Sfx)this.map.get(key)).loop(volume);
        } else {
            logger.info("Missing: " + key);
            return 0L;
        }
    }

    public void adjustVolume(String key, long id, float volume) {
        ((Sfx)this.map.get(key)).setVolume(id, volume);
    }

    public void adjustVolume(String key, long id) {
        ((Sfx)this.map.get(key)).setVolume(id, Settings.SOUND_VOLUME * Settings.MASTER_VOLUME);
    }

    public void fadeOut(String key, long id) {
        this.fadeOutList.add(new SoundInfo(key, id));
    }

    public void stop(String key, long id) {
        ((Sfx)this.map.get(key)).stop(id);
    }

    public void stop(String key) {
        if (this.map.get(key) != null) {
            ((Sfx)this.map.get(key)).stop();
        }

    }
}
