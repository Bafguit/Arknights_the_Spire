
package com.ndc.arknightsthespire;

import basemod.BaseMod;
import basemod.ModLabeledToggleButton;
import basemod.ModPanel;
import basemod.devcommands.ConsoleCommand;
import basemod.interfaces.*;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.evacipated.cardcrawl.mod.stslib.Keyword;
import com.evacipated.cardcrawl.modthespire.lib.SpireConfig;
import com.evacipated.cardcrawl.modthespire.lib.SpireInitializer;
import com.google.gson.Gson;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.dungeons.Exordium;
import com.megacrit.cardcrawl.helpers.CardHelper;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.helpers.input.InputAction;
import com.megacrit.cardcrawl.localization.*;
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
import com.ndc.arknightsthespire.character.CharacterDoctor;
import com.ndc.arknightsthespire.commands.SPCommandHandler;
import com.ndc.arknightsthespire.monsters.Genji;
import com.ndc.arknightsthespire.potions.*;
import com.ndc.arknightsthespire.relics.*;
import com.ndc.arknightsthespire.ui.SpUI;
import com.ndc.arknightsthespire.ui.ToggleSpButton;
import com.ndc.arknightsthespire.util.MessageCaller;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Properties;

import static com.ndc.arknightsthespire.CardColors.AbstractCardEnum.*;
import static com.ndc.arknightsthespire.character.ATSCharacterEnum.ATS_DOCTOR;


@SpireInitializer
public class ArknightsTheSpire implements EditCardsSubscriber, PostInitializeSubscriber, EditCharactersSubscriber, EditRelicsSubscriber, PreRoomRenderSubscriber, EditKeywordsSubscriber, EditStringsSubscriber, OnStartBattleSubscriber, PostBattleSubscriber {

    private static ArknightsTheSpire INSTANCE;
    public static boolean[] activeTutorials = new boolean[]{true};
    public static Properties AtSDS = new Properties();

    public static InputAction enableSPButton;

    public ArknightsTheSpire(){
        //Use this for when you subscribe to any hooks offered by BaseMod.
        BaseMod.subscribe(this);
        BaseMod.subscribe(new SPHandler());
    }

    //Used by @SpireInitializer
    public static void initialize(){
        BaseMod.addDynamicVariable(new SPDynamicVariable());
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
        BaseMod.addCard(new Apocalypse());
        BaseMod.addCard(new CurseDoll());
        BaseMod.addCard(new LiteraryStorm());
        BaseMod.addCard(new ShellDef());
        BaseMod.addCard(new Ignite());
        BaseMod.addCard(new VeryHotBlade());
        BaseMod.addCard(new CatScratch());
        BaseMod.addCard(new BloodOath());
        BaseMod.addCard(new LeapingHammer());
        BaseMod.addCard(new HammerOn());
        BaseMod.addCard(new CBBF());
        BaseMod.addCard(new AssaultOrder());
        BaseMod.addCard(new AbsoluteFocus());
        //Uncommon
        BaseMod.addCard(new Overload());
        BaseMod.addCard(new RapidMagazine());
        BaseMod.addCard(new Neurotoxin());
        BaseMod.addCard(new AutoCover());
        BaseMod.addCard(new SporePro());
        BaseMod.addCard(new EncForest());
        BaseMod.addCard(new ComMedShell());
        BaseMod.addCard(new DirDiagnosis());
        BaseMod.addCard(new SongOfBattle());
        BaseMod.addCard(new PartTimeJob());
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
        BaseMod.addCard(new SteamPump());
        BaseMod.addCard(new CollapsingStrike());
        BaseMod.addCard(new LN2Cannon());
        BaseMod.addCard(new RedShift());
        BaseMod.addCard(new ThermiteBlade());
        BaseMod.addCard(new DemonStrength());
        BaseMod.addCard(new SupportOrder());
        BaseMod.addCard(new DefendOrder());
        BaseMod.addCard(new SwordRain());
        BaseMod.addCard(new SeriousMode());
        BaseMod.addCard(new PhantomMirror());
        BaseMod.addCard(new SashimiPlatter());
        BaseMod.addCard(new LiteraryStorm());
        BaseMod.addCard(new GuardMode());
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
        BaseMod.addPotion(SpSmallPotion.class, CardHelper.getColor(53, 72, 76), CardHelper.getColor(250, 145, 73), CardHelper.getColor(250, 145, 73), SpSmallPotion.ID, ATS_DOCTOR);
        BaseMod.addPotion(SpBigPotion.class, CardHelper.getColor(53, 72, 76), CardHelper.getColor(250, 145, 73), CardHelper.getColor(250, 145, 73), SpBigPotion.ID, ATS_DOCTOR);
        BaseMod.addPotion(BurnSmallPotion.class, Color.RED, Color.RED, Color.ORANGE, BurnSmallPotion.ID, ATS_DOCTOR);
        BaseMod.addPotion(BurnBigPotion.class, Color.RED, Color.RED, Color.ORANGE, BurnBigPotion.ID, ATS_DOCTOR);
        BaseMod.addPotion(FlameArtsPotion.class, Color.RED, Color.RED, Color.ORANGE, FlameArtsPotion.ID, ATS_DOCTOR);
        ConsoleCommand.addCommand("sp", SPCommandHandler.class);
        addCustomMonster();
    }

    public void addCustomMonster() {
        System.out.println("GENJI");
        BaseMod.addMonster(Genji.ID, () -> new Genji());
        //System.out.println("SLUG S");
        /*BaseMod.addMonster("Slugs", () -> new MonsterGroup(new AbstractMonster[] {
                new SlugA(),
                new SlugB(),
                new SlugC()
        }));*/
        BaseMod.addMonsterEncounter(Exordium.ID, new MonsterInfo(Genji.ID, 5));
        //BaseMod.addMonsterEncounter(Exordium.ID, new MonsterInfo("Slugs", 0));
    }

    public void receiveEditCharacters() {
        System.out.println("ADDING CHARACTER");
        BaseMod.addCharacter(new CharacterDoctor(CardCrawlGame.playerName),
                "img/char/CharSelectButtonDoctor.png",
                "img/char/PortraitBG.png",
                ATS_DOCTOR);
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

        System.out.println("DONE");
    }

    public void receivePreRoomRender(SpriteBatch sb) {
    }


    public void receiveEditStrings() {
        String lang = getLangString();
        BaseMod.loadCustomStringsFile(UIStrings.class, "localization/" + lang + "/AtS_UI.json");
        BaseMod.loadCustomStringsFile(CardStrings.class, "localization/" + lang + "/AtS_Cards.json");
        BaseMod.loadCustomStringsFile(PowerStrings.class, "localization/" + lang + "/AtS_Powers.json");
        BaseMod.loadCustomStringsFile(RelicStrings.class, "localization/" + lang + "/AtS_Relics.json");
        BaseMod.loadCustomStringsFile(PotionStrings.class, "localization/" + lang + "/AtS_Potions.json");
        BaseMod.loadCustomStringsFile(CharacterStrings.class, "localization/" + lang + "/AtS_Doctor.json");
        BaseMod.loadCustomStringsFile(TutorialStrings.class, "localization/" + lang + "/AtS_tutorials.json");
        BaseMod.loadCustomStringsFile(MonsterStrings.class, "localization/" + lang + "/AtS_Monsters.json");
    }


    private String getLangString() {
        String language = Settings.language.name().toLowerCase();

        switch (language) {
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
        String json = Gdx.files.internal("localization/" + getLangString() + "/AtS_Keywords.json").readString(String.valueOf(StandardCharsets.UTF_8));
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
        if (AbstractDungeon.player instanceof CharacterDoctor) {
            if (activeTutorials[0]) {
                AbstractDungeon.actionManager.addToBottom(new MessageCaller(0));
            }
        }
    }

    @Override
    public void receivePostBattle(AbstractRoom abstractRoom) {
        if(abstractRoom instanceof MonsterRoomElite && SPHandler.getUpToMaxSp() && SpUI.isDoctor()) {
            abstractRoom.addRelicToRewards(new OriginiumAdd());
        }
    }
}
