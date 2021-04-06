
package com.ndc.arknightsthespire;

import basemod.BaseMod;
import basemod.ModLabeledToggleButton;
import basemod.ModPanel;
import basemod.devcommands.ConsoleCommand;
import basemod.interfaces.*;
import basemod.patches.com.megacrit.cardcrawl.unlock.UnlockTracker.PostRefresh;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.evacipated.cardcrawl.mod.stslib.Keyword;
import com.evacipated.cardcrawl.modthespire.lib.SpireConfig;
import com.evacipated.cardcrawl.modthespire.lib.SpireInitializer;
import com.google.gson.Gson;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.dungeons.Exordium;
import com.megacrit.cardcrawl.dungeons.TheBeyond;
import com.megacrit.cardcrawl.dungeons.TheCity;
import com.megacrit.cardcrawl.helpers.CardHelper;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.helpers.input.InputAction;
import com.megacrit.cardcrawl.localization.*;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.monsters.MonsterGroup;
import com.megacrit.cardcrawl.monsters.MonsterInfo;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import com.megacrit.cardcrawl.rooms.MonsterRoomElite;
import com.ndc.arknightsthespire.cards.caster.*;
import com.ndc.arknightsthespire.cards.defender.*;
import com.ndc.arknightsthespire.cards.defender.Defend;
import com.ndc.arknightsthespire.cards.guard.*;
import com.ndc.arknightsthespire.cards.medic.*;
import com.ndc.arknightsthespire.cards.sniper.*;
import com.ndc.arknightsthespire.cards.supporter.*;
import com.ndc.arknightsthespire.cards.specialist.*;
import com.ndc.arknightsthespire.cards.vanguard.*;
import com.ndc.arknightsthespire.cards.xCurse.SealedFloor;
import com.ndc.arknightsthespire.character.CharacterChen;
import com.ndc.arknightsthespire.character.CharacterDoctor;
import com.ndc.arknightsthespire.character.CharacterW;
import com.ndc.arknightsthespire.commands.SPCommandHandler;
import com.ndc.arknightsthespire.monsters.act1.*;
import com.ndc.arknightsthespire.monsters.act1.boss.Crown;
import com.ndc.arknightsthespire.monsters.act1.boss.Skull;
import com.ndc.arknightsthespire.monsters.act2.boss.Faust;
import com.ndc.arknightsthespire.monsters.act2.boss.Frost;
import com.ndc.arknightsthespire.monsters.act3.boss.FrostWS;
import com.ndc.arknightsthespire.monsters.act2.boss.Mephi;
import com.ndc.arknightsthespire.monsters.act3.boss.Patirot;
import com.ndc.arknightsthespire.potions.*;
import com.ndc.arknightsthespire.relics.*;
import com.ndc.arknightsthespire.ui.SpUI;
import com.ndc.arknightsthespire.ui.ToggleSpButton;
import com.ndc.arknightsthespire.util.MessageCaller;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Properties;

import static com.ndc.arknightsthespire.CardColors.AbstractCardEnum.*;
import static com.ndc.arknightsthespire.character.AtsEnum.*;


@SpireInitializer
public class ArknightsTheSpire extends PostRefresh implements EditCardsSubscriber, PostInitializeSubscriber, EditCharactersSubscriber, EditRelicsSubscriber, PreRoomRenderSubscriber, EditKeywordsSubscriber, EditStringsSubscriber, OnStartBattleSubscriber, PostBattleSubscriber, PostCreateStartingRelicsSubscriber {

    private static ArknightsTheSpire INSTANCE;
    public static boolean[] activeTutorials = new boolean[]{true};
    public static Properties AtSDS = new Properties();

    public static InputAction enableSPButton;

    public static boolean isBattle = false;

    public ArknightsTheSpire(){
        //Use this for when you subscribe to any hooks offered by BaseMod.
        BaseMod.subscribe(this);
        BaseMod.subscribe(new SPHandler());
    }

    //Used by @SpireInitializer
    public static void initialize(){
        BaseMod.addDynamicVariable(new SPDynamicVariable());
        BaseMod.addDynamicVariable(new ArmDynamicVariable());
        BaseMod.addDynamicVariable(new ResDynamicVariable());
        BaseMod.addDynamicVariable(new EtDynamicVariable());
        //This creates an instance of our classes and gets our code going after BaseMod and ModTheSpire initialize.
        INSTANCE = new ArknightsTheSpire();

        //Look at the BaseMod wiki for getting started. (Skip the decompiling part. It's not needed right now)
        CardColors.initialize();

        try {
            for(int i = 0; i < activeTutorials.length; ++i) {
                AtSDS.setProperty("activeTutorials" + i, "true");
            }

            SpireConfig config = new SpireConfig("ats", "AtsConfig", AtSDS);

            for(int j = 0; j < activeTutorials.length; ++j) {
                activeTutorials[j] = config.getBool("activeTutorials" + j);
            }
        } catch (IOException var3) {
            var3.printStackTrace();
        }
    }

    public void receiveEditCards() {

        System.out.println("ADDING CARDS");
        //Basic
        BaseMod.addCard(new StrikeS());
        BaseMod.addCard(new StrikeC());
        BaseMod.addCard(new StrikeG());
        BaseMod.addCard(new StrikeV());
        BaseMod.addCard(new ArmCrushShot());
        BaseMod.addCard(new Defend());
        BaseMod.addCard(new EmotionAbs());
        //Common
        BaseMod.addCard(new PowerfulStrike());
        BaseMod.addCard(new ConShotAuto());
        BaseMod.addCard(new ExpAreaStr());
        BaseMod.addCard(new FlexCamouflage());
        BaseMod.addCard(new PoisonSpread());
        BaseMod.addCard(new Revitalization());
        BaseMod.addCard(new CurseDoll());
        BaseMod.addCard(new LiteraryStorm());
        BaseMod.addCard(new ShellDef());
        BaseMod.addCard(new Ignite());
        BaseMod.addCard(new VeryHotBlade());
        BaseMod.addCard(new CatScratch());
        BaseMod.addCard(new BloodOath());
        BaseMod.addCard(new HammerOn());
        BaseMod.addCard(new CBBF());
        BaseMod.addCard(new AssaultOrder());
        BaseMod.addCard(new AbsoluteFocus());
        BaseMod.addCard(new HealingMist());
        //Uncommon
        BaseMod.addCard(new Overload());
        BaseMod.addCard(new Neurotoxin());
        BaseMod.addCard(new AutoCover());
        BaseMod.addCard(new SporePro());
        BaseMod.addCard(new EncForest());
        BaseMod.addCard(new ComMedShell());
        BaseMod.addCard(new DirDiagnosis());
        BaseMod.addCard(new SongOfBattle());
        BaseMod.addCard(new HealingDrone());
        BaseMod.addCard(new HealingStr());
        BaseMod.addCard(new ChargingDef());
        BaseMod.addCard(new MagHammer());
        BaseMod.addCard(new Thorns());
        BaseMod.addCard(new CntHealMod());
        BaseMod.addCard(new BeatenUp());
        BaseMod.addCard(new MentalBurst());
        BaseMod.addCard(new SoulAbs());
        BaseMod.addCard(new Sunburst());
        BaseMod.addCard(new BurningGround());
        BaseMod.addCard(new GuardianObelisk());
        BaseMod.addCard(new FlameOfHeaven());
        BaseMod.addCard(new ChainHook());
        BaseMod.addCard(new HookShot());
        BaseMod.addCard(new ShadowRaid());
        BaseMod.addCard(new CollapsingStrike());
        BaseMod.addCard(new LN2Cannon());
        BaseMod.addCard(new RedShift());
        BaseMod.addCard(new ThermiteBlade());
        BaseMod.addCard(new DemonStrength());
        BaseMod.addCard(new SupportOrder());
        BaseMod.addCard(new SwordRain());
        BaseMod.addCard(new SeriousMode());
        BaseMod.addCard(new SashimiPlatter());
        BaseMod.addCard(new LiteraryStorm());
        BaseMod.addCard(new GuardMode());
        BaseMod.addCard(new ElectricNet());
        BaseMod.addCard(new SkullCrusher());
        //Rare
        BaseMod.addCard(new Ambush());
        BaseMod.addCard(new DogmaticField());
        BaseMod.addCard(new Foxfire());
        BaseMod.addCard(new EchoReverb());
        BaseMod.addCard(new Calcification());
        BaseMod.addCard(new Volcano());
        BaseMod.addCard(new TrueSilverSlash());
        BaseMod.addCard(new SoulRend());
        BaseMod.addCard(new FracturedBody());
        BaseMod.addCard(new ShadowAssault());
        BaseMod.addCard(new RatPack());
        BaseMod.addCard(new WolfPack());
        BaseMod.addCard(new DisruptionKick());
        BaseMod.addCard(new Durian());
        BaseMod.addCard(new RoarOfUrsus());
        BaseMod.addCard(new FierceGlare());
        //Curse
        BaseMod.addCard(new SealedFloor());

        System.out.println("DONE");
    }

    public void receivePostInitialize() {
        try {
            this.CreatePanel();
        } catch (IOException var2) {
            var2.printStackTrace();
        }
        System.out.println("POSTINIT");
        ImageMaster.END_TURN_BUTTON = ToggleSpButton.UI_BUTTON_RIGHT;
        ImageMaster.END_TURN_BUTTON_GLOW = ToggleSpButton.UI_BUTTON_RIGHT_GLOW;
        ImageMaster.END_TURN_HOVER = ToggleSpButton.UI_BUTTON_RIGHT_HOVER;
        BaseMod.addPotion(SpSmallPotion.class, CardHelper.getColor(53, 72, 76), CardHelper.getColor(250, 145, 73), CardHelper.getColor(250, 145, 73), SpSmallPotion.ID, DOCTOR_CLASS);
        BaseMod.addPotion(SpBigPotion.class, CardHelper.getColor(53, 72, 76), CardHelper.getColor(250, 145, 73), CardHelper.getColor(250, 145, 73), SpBigPotion.ID, DOCTOR_CLASS);
        BaseMod.addPotion(BurnSmallPotion.class, Color.RED, Color.RED, Color.ORANGE, BurnSmallPotion.ID, DOCTOR_CLASS);
        BaseMod.addPotion(BurnBigPotion.class, Color.RED, Color.RED, Color.ORANGE, BurnBigPotion.ID, DOCTOR_CLASS);
        BaseMod.addPotion(FlameArtsPotion.class, Color.RED, Color.RED, Color.ORANGE, FlameArtsPotion.ID, DOCTOR_CLASS);
        ConsoleCommand.addCommand("sp", SPCommandHandler.class);
        addCustomMonster();
        /*Chernobog chernobog = new Chernobog();
        chernobog.addAct(Exordium.ID);*/
        /*CustomIntent.add(new ArtsAttackIntent());
        CustomIntent.add(new ArtsBuffAttackIntent());
        CustomIntent.add(new ArtsDebuffAttackIntent());*/
    }

    public void log(String s) {
        System.out.println(s);
    }
/*
    public void addCustomAct() {
        //Act 1
        Chernobog chernobog = new Chernobog();
        chernobog.addMonster(Genji.ID, () -> new Genji());
        chernobog.addStrongMonster(Shield.ID, () -> new Shield());
        chernobog.addMonster("ats:Slugs", () -> new MonsterGroup(new AbstractMonster[] {
                new SlugA(),
                new SlugB(),
                new SlugC()  }));
        chernobog.addBoss(Exordium.ID, () -> new Skull(), "atsImg/monsters/act_1/boss/skull.png", "atsImg/monsters/act_1/boss/skull_out.png");
        chernobog.addBoss(Exordium.ID, () -> new Crown(), "atsImg/monsters/act_1/boss/crown.png", "atsImg/monsters/act_1/boss/crown_out.png");


    }*/

    public void addCustomMonster() {
        System.out.println("GENJI");
        BaseMod.addMonster(Genji.ID, () -> new Genji());
        BaseMod.addMonster(Shield.ID, () -> new Shield());
        System.out.println("SLUG");
        //BaseMod.addMonster(SlugA.ID, () -> new SlugA());
        BaseMod.addMonster("Slugs", () -> new MonsterGroup(new AbstractMonster[] {
                 new SlugA(),
                 new SlugB(),
                 new SlugC()  }));
        log("Act 1 Boss");
        BaseMod.addMonster(Skull.ID, () -> new Skull());
        BaseMod.addMonster(Crown.ID, () -> new Crown());
        log("Act 2 Boss");
        BaseMod.addMonster("MephiFaust", () -> new MonsterGroup(new AbstractMonster[] { new Faust(), new Mephi() }));
        BaseMod.addMonster(Frost.ID, () -> new Frost());
        BaseMod.addMonster(FrostWS.ID, () -> new FrostWS());
        BaseMod.addMonster(Patirot.ID, () -> new Patirot());

        BaseMod.addBoss(Exordium.ID, Skull.ID, "atsImg/monsters/act_1/boss/skull.png", "atsImg/monsters/act_1/boss/skull_out.png");
        BaseMod.addBoss(Exordium.ID, Crown.ID, "atsImg/monsters/act_1/boss/crown.png", "atsImg/monsters/act_1/boss/crown_out.png");
        BaseMod.addBoss(TheCity.ID, "MephiFaust", "atsImg/monsters/act_2/boss/mephi.png", "atsImg/monsters/act_2/boss/mephi_out.png");
        BaseMod.addBoss(TheCity.ID, Frost.ID, "atsImg/monsters/act_2/boss/frost.png", "atsImg/monsters/act_2/boss/frost_out.png");
        BaseMod.addBoss(TheBeyond.ID, FrostWS.ID, "atsImg/monsters/act_3/boss/frost.png", "atsImg/monsters/act_3/boss/frost_out.png");
        BaseMod.addBoss(TheBeyond.ID, Patirot.ID, "atsImg/monsters/act_3/boss/patriot.png", "atsImg/monsters/act_3/boss/patriot_out.png");
        BaseMod.addStrongMonsterEncounter(Exordium.ID, new MonsterInfo(Shield.ID, 1.0F));
        BaseMod.addMonsterEncounter(Exordium.ID, new MonsterInfo(Genji.ID, 1.0F));
        BaseMod.addMonsterEncounter(Exordium.ID, new MonsterInfo("Slugs", 1.0F));
    }

    public void receiveEditCharacters() {
        BaseMod.addCharacter(new CharacterDoctor(CardCrawlGame.playerName),
                "atsImg/char/CharSelectButtonDoctor.png",
                "atsImg/char/PortraitBG.png",
                DOCTOR_CLASS);
        System.out.println("ADDING CHARACTER");
        BaseMod.addCharacter(new CharacterW(CardCrawlGame.playerName),
                "atsImg/char/CharSelectButtonW.png",
                "atsImg/char/PortraitBG_w.png",
                W_CLASS);
        BaseMod.addCharacter(new CharacterChen(CardCrawlGame.playerName),
                "atsImg/char/CharSelectButtonChen.png",
                "atsImg/char/PortraitBG_chen.png",
                CHEN_CLASS);
        System.out.println("DONE");
    }

    public void receiveEditRelics() {
        System.out.println("ADDING RELICS");

        BaseMod.addRelicToCustomPool(new SpiritualRecovery(), DOCTOR_COLOR);
        BaseMod.addRelicToCustomPool(new RhineCircuitry(), DOCTOR_COLOR);
        BaseMod.addRelicToCustomPool(new MilitaryTradition(), DOCTOR_COLOR);
        BaseMod.addRelicToCustomPool(new AbilityAura(), DOCTOR_COLOR);
        BaseMod.addRelicToCustomPool(new Lavender(), DOCTOR_COLOR);
        BaseMod.addRelicToCustomPool(new Omniscience(), DOCTOR_COLOR);
        BaseMod.addRelicToCustomPool(new Sanity(), DOCTOR_COLOR);
        BaseMod.addRelicToCustomPool(new SanityP(), DOCTOR_COLOR);
        BaseMod.addRelicToCustomPool(new Berate(), DOCTOR_COLOR);
        BaseMod.addRelicToCustomPool(new IndustrialWaterCannon(), DOCTOR_COLOR);
        BaseMod.addRelicToCustomPool(new Bonebreaker(), DOCTOR_COLOR);
        BaseMod.addRelicToCustomPool(new EmergencyDefibrillator(), DOCTOR_COLOR);
        BaseMod.addRelicToCustomPool(new HiddenKiller(), DOCTOR_COLOR);
        BaseMod.addRelicToCustomPool(new Arts(), DOCTOR_COLOR);
        BaseMod.addRelicToCustomPool(new Assault(), DOCTOR_COLOR);
        BaseMod.addRelicToCustomPool(new Attack(), DOCTOR_COLOR);
        BaseMod.addRelicToCustomPool(new com.ndc.arknightsthespire.relics.Defend(), DOCTOR_COLOR);
        BaseMod.addRelicToCustomPool(new Cure(), DOCTOR_COLOR);
        BaseMod.addRelicToCustomPool(new Snipe(), DOCTOR_COLOR);
        BaseMod.addRelicToCustomPool(new Special(), DOCTOR_COLOR);
        BaseMod.addRelicToCustomPool(new Support(), DOCTOR_COLOR);
        BaseMod.addRelicToCustomPool(new Gyao(), DOCTOR_COLOR);
        BaseMod.addRelicToCustomPool(new OriginiumAdd(), DOCTOR_COLOR);
        BaseMod.addRelicToCustomPool(new TacticalDelivery(), DOCTOR_COLOR);
        BaseMod.addRelicToCustomPool(new RemnantAsh(), DOCTOR_COLOR);
        BaseMod.addRelicToCustomPool(new HeartsK(), DOCTOR_COLOR);
        BaseMod.addRelicToCustomPool(new HeartsKp(), DOCTOR_COLOR);
        BaseMod.addRelicToCustomPool(new Crimson(), DOCTOR_COLOR);
        BaseMod.addRelicToCustomPool(new CrimsonP(), DOCTOR_COLOR);

        System.out.println("DONE");
    }

    public void receivePreRoomRender(SpriteBatch sb) {
    }


    public void receiveEditStrings() {
        String lang = getLangString();
        BaseMod.loadCustomStringsFile(UIStrings.class, "atsLocalization/" + lang + "/AtS_UI.json");
        BaseMod.loadCustomStringsFile(CardStrings.class, "atsLocalization/" + lang + "/AtS_Cards.json");
        BaseMod.loadCustomStringsFile(PowerStrings.class, "atsLocalization/" + lang + "/AtS_Powers.json");
        BaseMod.loadCustomStringsFile(RelicStrings.class, "atsLocalization/" + lang + "/AtS_Relics.json");
        BaseMod.loadCustomStringsFile(PotionStrings.class, "atsLocalization/" + lang + "/AtS_Potions.json");
        BaseMod.loadCustomStringsFile(CharacterStrings.class, "atsLocalization/" + lang + "/AtS_Doctor.json");
        BaseMod.loadCustomStringsFile(TutorialStrings.class, "atsLocalization/" + lang + "/AtS_tutorials.json");
        BaseMod.loadCustomStringsFile(MonsterStrings.class, "atsLocalization/" + lang + "/AtS_Monster.json");
    }


    private String getLangString() {
        String language = Settings.language.name().toLowerCase();

        switch (language) {
            case "rus":
                return "rus";

            case "zhs":
                return "zhs";

            case "kor":
                return "kor";

            default:
                return "eng";
        }
    }

    public void receiveEditKeywords() {
        Gson gson = new Gson();
        String json = Gdx.files.internal("atsLocalization/" + getLangString() + "/AtS_Keywords.json").readString(String.valueOf(StandardCharsets.UTF_8));
        Keyword[] keywords = (Keyword[])gson.fromJson(json, Keyword[].class);
        if (keywords != null) {
            int var7 = keywords.length;
            for(int var8 = 0; var8 < var7; ++var8) {
                Keyword keyword = keywords[var8];
                BaseMod.addKeyword("ats", keyword.PROPER_NAME, keyword.NAMES, keyword.DESCRIPTION);

            }
        }
        System.out.println();
    }

    private void CreatePanel() throws IOException {
        SpireConfig spireConfig = new SpireConfig("ats", "AtsConfig");
        ModPanel settingsPanel = new ModPanel();
        UIStrings uiStrings = CardCrawlGame.languagePack.getUIString("ats:Sans");
        String[] TEXT = uiStrings.TEXT;
        ModLabeledToggleButton tutorialOpen = new ModLabeledToggleButton(TEXT[0], 500.0F, 550.0F, Settings.CREAM_COLOR, FontHelper.charDescFont, activeTutorials[0], settingsPanel, (label) -> {
        }, (button) -> {
            for(int i = 0; i < activeTutorials.length; ++i) {
                spireConfig.setBool("activeTutorials", activeTutorials[i] = button.enabled);
            }

            CardCrawlGame.mainMenuScreen.optionPanel.effects.clear();

            try {
                spireConfig.save();
            } catch (IOException var3) {
                var3.printStackTrace();
            }

        });
        settingsPanel.addUIElement(tutorialOpen);
    }

    public static void saveData() throws IOException {
        SpireConfig config = new SpireConfig("ats", "AtsConfig");

        for(int i = 0; i < activeTutorials.length; ++i) {
            config.setBool("activeTutorials" + i, activeTutorials[i]);
        }

        config.save();
    }

    @Override
    public void receiveOnBattleStart(AbstractRoom abstractRoom) {
        if (SpUI.isDoctor()) {
            if (activeTutorials[0]) {
                AbstractDungeon.actionManager.addToBottom(new MessageCaller(0));
            }
            isBattle = true;
            //ApplyDefAction.applyDef(AbstractDungeon.player, CharacterDoctor.defaultArm, CharacterDoctor.defaultRes);
        }
    }

    @Override
    public void receivePostBattle(AbstractRoom abstractRoom) {
        if(SpUI.isDoctor()) {
            isBattle = false;
            if (abstractRoom instanceof MonsterRoomElite && SPHandler.getUpToMaxSp()) {
                abstractRoom.addRelicToRewards(new OriginiumAdd());
            }
        }
    }

    public static boolean getBattle() {
        return isBattle;
    }

    @Override
    public void receivePostCreateStartingRelics(AbstractPlayer.PlayerClass playerClass, ArrayList<String> arrayList) {
        isBattle = false;
    }
}
