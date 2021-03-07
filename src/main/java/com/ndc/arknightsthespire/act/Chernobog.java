package com.ndc.arknightsthespire.act;

import actlikeit.dungeons.CustomDungeon;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.dungeons.Exordium;
import com.megacrit.cardcrawl.localization.UIStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.monsters.MonsterGroup;
import com.megacrit.cardcrawl.monsters.MonsterInfo;
import com.megacrit.cardcrawl.saveAndContinue.SaveFile;
import com.megacrit.cardcrawl.scenes.AbstractScene;
import com.ndc.arknightsthespire.actions.AtsSound;
import com.ndc.arknightsthespire.monsters.act1.*;
import com.ndc.arknightsthespire.monsters.act1.boss.Crown;
import com.ndc.arknightsthespire.monsters.act1.boss.Skull;

import java.util.ArrayList;

public class Chernobog extends CustomDungeon {
    public static final String ID = "act:Chernobog"; //From the main mod file for best practices
    private static final UIStrings uiStrings = CardCrawlGame.languagePack.getUIString(ID);
    public static final String[] TEXT = uiStrings.TEXT;
    public static final String NAME = TEXT[0];

    public Chernobog() {
        super(NAME, ID);
        this.setMainMusic("sfx/act1_main.ogg");
        this.addTempMusic("SKULL_INTRO", "sfx/act1_skull_intro.ogg");
        this.addTempMusic("SKULL_LOOP", "sfx/act1_skull_loop.ogg");
        this.addTempMusic("CROWN_INTRO", "sfx/act1_crown_intro.ogg");
        this.addTempMusic("CROWN_LOOP", "sfx/act1_crown_loop.ogg");
        this.addMonster(Genji.ID, () -> new Genji());
        this.addStrongMonster(Shield.ID, () -> new Shield());
        this.addMonster("ats:Slugs", () -> new MonsterGroup(new AbstractMonster[] {
                new SlugA(),
                new SlugB(),
                new SlugC()  }));
        this.addBoss(Skull.ID, () -> new Skull(), "img/monsters/act_1/boss/skull.png", "img/monsters/act_1/boss/skull_out.png");
        this.addBoss(Crown.ID, () -> new Crown(), "img/monsters/act_1/boss/crown.png", "img/monsters/act_1/boss/crown_out.png");
    }

    public Chernobog(CustomDungeon cd, AbstractPlayer p, ArrayList<String> emptyList) {
        super(cd, p, emptyList);
    }
    public Chernobog(CustomDungeon cd, AbstractPlayer p, SaveFile sf) {
        super(cd, p, sf);
    }

    @Override
    public AbstractScene DungeonScene() {
        return new ArknightsScene();
    }

    public void bossSeen() {
        super.bossSeen();/*
        if(AbstractDungeon.bossKey == Skull.ID) {
            new AtsSound().
            playTempMusic("SKULL_LOOP", true);
        }*/
    }

    @Override
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
            cardUpgradedChance = 0.25F;
        } else {
            cardUpgradedChance = 0.5F;
        }

    }
}