//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.ndc.arknightsthespire.act;

import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.UIStrings;
import com.megacrit.cardcrawl.map.MapRoomNode;
import com.megacrit.cardcrawl.monsters.MonsterInfo;
import com.megacrit.cardcrawl.random.Random;
import com.megacrit.cardcrawl.rooms.EmptyRoom;
import com.megacrit.cardcrawl.saveAndContinue.SaveFile;
import com.megacrit.cardcrawl.scenes.TheCityScene;
import com.megacrit.cardcrawl.unlock.UnlockTracker;
import java.util.ArrayList;
import java.util.Collections;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class TheCity extends AbstractDungeon {
    private static final Logger logger = LogManager.getLogger(TheCity.class.getName());
    private static final UIStrings uiStrings;
    public static final String[] TEXT;
    public static final String NAME;
    public static final String ID = "act:Chernobog";

    public TheCity(AbstractPlayer p, ArrayList<String> theList) {
        super(NAME, "Chernobog", p, theList);
        if (scene != null) {
            scene.dispose();
        }

        scene = new ArknightsScene();
        fadeColor = Color.valueOf("0a1e1eff");
        sourceFadeColor = Color.valueOf("0a1e1eff");
        this.initializeLevelSpecificChances();
        mapRng = new Random(Settings.seed + (long)(AbstractDungeon.actNum * 100));
        generateMap();
        CardCrawlGame.music.changeBGM(id);
        AbstractDungeon.currMapNode = new MapRoomNode(0, -1);
        AbstractDungeon.currMapNode.room = new EmptyRoom();
    }

    public TheCity(AbstractPlayer p, SaveFile saveFile) {
        super(NAME, p, saveFile);
        if (scene != null) {
            scene.dispose();
        }

        scene = new TheCityScene();
        fadeColor = Color.valueOf("0a1e1eff");
        sourceFadeColor = Color.valueOf("0a1e1eff");
        this.initializeLevelSpecificChances();
        miscRng = new Random(Settings.seed + (long)saveFile.floor_num);
        CardCrawlGame.music.changeBGM(id);
        mapRng = new Random(Settings.seed + (long)(saveFile.act_num * 100));
        generateMap();
        firstRoomChosen = true;
        this.populatePathTaken(saveFile);
    }

    protected void initializeLevelSpecificChances() {
        shopRoomChance = 0.05F;
        restRoomChance = 0.12F;
        treasureRoomChance = 0.0F;
        eventRoomChance = 0.22F;
        eliteRoomChance = 0.08F;
        smallChestChance = 50;
        mediumChestChance = 33;
        largeChestChance = 17;
        commonRelicChance = 50;
        uncommonRelicChance = 33;
        rareRelicChance = 17;
        colorlessRareChance = 0.3F;
        if (AbstractDungeon.ascensionLevel >= 12) {
            cardUpgradedChance = 0.125F;
        } else {
            cardUpgradedChance = 0.25F;
        }

    }

    protected void generateMonsters() {
        this.generateWeakEnemies(2);
        this.generateStrongEnemies(12);
        this.generateElites(10);
    }

    protected void generateWeakEnemies(int count) {
        ArrayList<MonsterInfo> monsters = new ArrayList();
        monsters.add(new MonsterInfo("Spheric Guardian", 2.0F));
        monsters.add(new MonsterInfo("Chosen", 2.0F));
        monsters.add(new MonsterInfo("Shell Parasite", 2.0F));
        monsters.add(new MonsterInfo("3 Byrds", 2.0F));
        monsters.add(new MonsterInfo("2 Thieves", 2.0F));
        MonsterInfo.normalizeWeights(monsters);
        this.populateMonsterList(monsters, count, false);
    }

    protected void generateStrongEnemies(int count) {
        ArrayList<MonsterInfo> monsters = new ArrayList();
        monsters.add(new MonsterInfo("Chosen and Byrds", 2.0F));
        monsters.add(new MonsterInfo("Sentry and Sphere", 2.0F));
        monsters.add(new MonsterInfo("Snake Plant", 6.0F));
        monsters.add(new MonsterInfo("Snecko", 4.0F));
        monsters.add(new MonsterInfo("Centurion and Healer", 6.0F));
        monsters.add(new MonsterInfo("Cultist and Chosen", 3.0F));
        monsters.add(new MonsterInfo("3 Cultists", 3.0F));
        monsters.add(new MonsterInfo("Shelled Parasite and Fungi", 3.0F));
        MonsterInfo.normalizeWeights(monsters);
        this.populateFirstStrongEnemy(monsters, this.generateExclusions());
        this.populateMonsterList(monsters, count, false);
    }

    protected void generateElites(int count) {
        ArrayList<MonsterInfo> monsters = new ArrayList();
        monsters.add(new MonsterInfo("Gremlin Leader", 1.0F));
        monsters.add(new MonsterInfo("Slavers", 1.0F));
        monsters.add(new MonsterInfo("Book of Stabbing", 1.0F));
        MonsterInfo.normalizeWeights(monsters);
        this.populateMonsterList(monsters, count, true);
    }

    protected ArrayList<String> generateExclusions() {
        ArrayList<String> retVal = new ArrayList();
        String var2 = (String)monsterList.get(monsterList.size() - 1);
        byte var3 = -1;
        switch(var2.hashCode()) {
        case -1001149827:
            if (var2.equals("3 Byrds")) {
                var3 = 1;
            }
            break;
        case 1989842815:
            if (var2.equals("Spheric Guardian")) {
                var3 = 0;
            }
            break;
        case 2017619858:
            if (var2.equals("Chosen")) {
                var3 = 2;
            }
        }

        switch(var3) {
        case 0:
            retVal.add("Sentry and Sphere");
            break;
        case 1:
            retVal.add("Chosen and Byrds");
            break;
        case 2:
            retVal.add("Chosen and Byrds");
            retVal.add("Cultist and Chosen");
        }

        return retVal;
    }

    protected void initializeBoss() {
        bossList.clear();
        if (Settings.isDailyRun) {
            bossList.add("Automaton");
            bossList.add("Collector");
            bossList.add("Champ");
            Collections.shuffle(bossList, new java.util.Random(monsterRng.randomLong()));
        } else if (!UnlockTracker.isBossSeen("CHAMP")) {
            bossList.add("Champ");
        } else if (!UnlockTracker.isBossSeen("AUTOMATON")) {
            bossList.add("Automaton");
        } else if (!UnlockTracker.isBossSeen("COLLECTOR")) {
            bossList.add("Collector");
        } else {
            bossList.add("Automaton");
            bossList.add("Collector");
            bossList.add("Champ");
            Collections.shuffle(bossList, new java.util.Random(monsterRng.randomLong()));
        }

        if (bossList.size() == 1) {
            bossList.add(bossList.get(0));
        } else if (bossList.isEmpty()) {
            logger.warn("Boss list was empty. How?");
            bossList.add("Automaton");
            bossList.add("Collector");
            bossList.add("Champ");
            Collections.shuffle(bossList, new java.util.Random(monsterRng.randomLong()));
        }

    }

    protected void initializeEventList() {
        eventList.add("Addict");
        eventList.add("Back to Basics");
        eventList.add("Beggar");
        eventList.add("Colosseum");
        eventList.add("Cursed Tome");
        eventList.add("Drug Dealer");
        eventList.add("Forgotten Altar");
        eventList.add("Ghosts");
        eventList.add("Masked Bandits");
        eventList.add("Nest");
        eventList.add("The Library");
        eventList.add("The Mausoleum");
        eventList.add("Vampires");
    }

    protected void initializeEventImg() {
        if (eventBackgroundImg != null) {
            eventBackgroundImg.dispose();
            eventBackgroundImg = null;
        }

        eventBackgroundImg = ImageMaster.loadImage("images/ui/event/panel.png");
    }

    protected void initializeShrineList() {
        shrineList.add("Match and Keep!");
        shrineList.add("Wheel of Change");
        shrineList.add("Golden Shrine");
        shrineList.add("Transmorgrifier");
        shrineList.add("Purifier");
        shrineList.add("Upgrade Shrine");
    }

    static {
        uiStrings = CardCrawlGame.languagePack.getUIString(ID);
        TEXT = uiStrings.TEXT;
        NAME = TEXT[0];
    }
}
