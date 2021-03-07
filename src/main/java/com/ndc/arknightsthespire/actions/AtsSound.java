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
    private static final String SFX_DIR = "sfx/";

    public AtsSound() {
        long startTime = System.currentTimeMillis();
        Settings.SOUND_VOLUME = Settings.soundPref.getFloat("Sound Volume", 0.35F);

        this.map.put("JUEYING_1", this.load("p_skill_jueying_1"));
        this.map.put("JUEYING_2", this.load("p_skill_jueying_2"));
        this.map.put("BADAO", this.load("p_skill_chixiaobadao"));
        this.map.put("DURIAN", this.load("p_skill_fulminatingpotionY"));
        this.map.put("TIGER", this.load("p_skill_tigerdrop_2"));
        this.map.put("WOLF", this.load("p_skill_wolfroar"));
        this.map.put("SPIRIT", this.load("p_skill_spiritexplo"));
        this.map.put("FLAME", this.load("p_skill_flameexplo_3"));
        this.map.put("BURN", this.load("p_skill_flameexplo_4"));
        this.map.put("FIRE", this.load("p_skill_firebuff"));
        this.map.put("METEOR", this.load("p_skill_aeroliteexplo"));
        this.map.put("SKY", this.load("p_skill_skyeyeopen"));
        this.map.put("PUMP", this.load("p_skill_hydraulic_n"));
        this.map.put("HYDPUMP", this.load("p_skill_hydraulic_h"));
        this.map.put("BELL", this.load("p_skill_recallbell"));
        this.map.put("CLING", this.load("p_skill_soulwolf"));
        this.map.put("JUMP", this.load("p_skill_jumphammer"));
        this.map.put("SWORDRAIN", this.load("p_skill_swordrain"));
        this.map.put("BATTLESONG", this.load("p_skill_battlesong"));
        this.map.put("MAGNETIC_HAMMER", this.load("p_skill_magnetichammer"));
        this.map.put("SILVER", this.load("p_atk_silver_n"));
        this.map.put("BOTTLE", this.load("p_atk_bottle_n"));
        this.map.put("PISTOL", this.load("p_atk_pistol_n"));
        this.map.put("PISTOL_H", this.load("p_atk_pistol_h"));
        this.map.put("HAWK", this.load("p_atk_hawk_n"));
        this.map.put("HAWK_H", this.load("p_atk_hawk_h"));
        this.map.put("SMG", this.load("p_atk_smg_n"));
        this.map.put("SMG_H", this.load("p_atk_smg_h"));
        this.map.put("PUNCH", this.load("p_atk_punch_h"));
        this.map.put("ARROW", this.load("p_atk_arrow_n"));
        this.map.put("ARROW_H", this.load("p_atk_arrow_h"));
        this.map.put("CROSSBOW", this.load("p_atk_crossbow_n"));
        this.map.put("GRENADE", this.load("p_atk_grenade_n"));
        this.map.put("ROPE", this.load("p_atk_cowboy_h"));
        this.map.put("PAW", this.load("p_atk_paw_n"));
        this.map.put("PAW_H", this.load("p_atk_paw_h"));
        this.map.put("DAGGER", this.load("p_atk_firedagger_s"));
        this.map.put("BLADE", this.load("p_atk_blade_n"));
        this.map.put("POWER_HEAL", this.load("p_atk_heal_h"));
        this.map.put("VOLCANO", this.load("p_aoe_flamethrower_pre"));
        this.map.put("REDSHIFT", this.load("p_imp_knifethrow_h"));
        this.map.put("KNIFE", this.load("p_imp_knifethrow_n"));
        this.map.put("CASTER", this.load("p_imp_darkmag_n"));
        this.map.put("MILK", this.load("p_imp_milkcannon_n"));
        this.map.put("SPEAR", this.load("p_imp_spear_n"));
        this.map.put("SPEAR_H", this.load("p_imp_spear_h"));
        this.map.put("BAGPIPE", this.load("p_imp_gunlance_s"));
        this.map.put("SKULL_R", this.load("e_skill_skulsrshot"));
        this.map.put("SKULL_A", this.load("e_skill_skulsrsword"));
        this.map.put("CROWN", this.load("e_skill_crownsflash"));
        this.map.put("MEPHI", this.load("e_skill_aoeheal"));
        this.map.put("FAUST_N", this.load("e_atk_faust_n"));
        this.map.put("FAUST_H", this.load("e_atk_faust_h"));
        this.map.put("FAUST_S", this.load("e_imp_faust_h2"));
        this.map.put("BALLISTA", this.load("e_imp_faust_n"));
        this.map.put("SKULL_INFO", this.load("act1_skull_intro"));
        this.map.put("SKULL_LOOP", this.load("act1_skull_loop"));
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
    /*
    public long playIntroLoop(String key, float volume) {
        if (this.map.containsKey(key)) {
            logger.info("Preloading: " + key);
            long id = ((Sfx)this.map.get(key)).play(0.0F);
            ((Sfx)this.map.get(key)).stop(id);
        } else {
            logger.info("Missing: " + key);
        }
    }*/

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
