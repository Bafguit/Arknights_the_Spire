
package com.ndc.arknightsthespire;

import basemod.BaseMod;
import basemod.ModLabeledToggleButton;
import basemod.ModPanel;
import basemod.interfaces.*;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.evacipated.cardcrawl.mod.stslib.Keyword;
import com.evacipated.cardcrawl.modthespire.lib.SpireConfig;
import com.evacipated.cardcrawl.modthespire.lib.SpireInitializer;
import com.google.gson.Gson;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.*;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import com.ndc.arknightsthespire.cards.basic.*;
import com.ndc.arknightsthespire.cards.common.*;
import com.ndc.arknightsthespire.cards.rare.*;
import com.ndc.arknightsthespire.cards.uncommon.*;
import com.ndc.arknightsthespire.character.CharacterDoctor;
import com.ndc.arknightsthespire.events.NightField;
import com.ndc.arknightsthespire.relics.*;
import com.ndc.arknightsthespire.ui.ToggleSpButton;
import com.ndc.arknightsthespire.util.MessageCaller;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Properties;

import static com.ndc.arknightsthespire.CardColors.AbstractCardEnum.*;
import static com.ndc.arknightsthespire.character.ATSCharacterEnum.DOCTOR_CLASS;


@SpireInitializer
public class ArknightsTheSpire implements EditCardsSubscriber, PostInitializeSubscriber, EditCharactersSubscriber, EditRelicsSubscriber, PreRoomRenderSubscriber, EditKeywordsSubscriber, EditStringsSubscriber, OnStartBattleSubscriber {

    private static ArknightsTheSpire INSTANCE;
    public static boolean[] activeTutorials = new boolean[]{true};
    public static Properties AtSDS = new Properties();



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
        BaseMod.addCard(new CardCheat());
        //Sniper
        BaseMod.addCard(new CardSniperOverload());
        BaseMod.addCard(new CardSniperPowerfulStrike());
        BaseMod.addCard(new CardSniperArmCrushShot());
        BaseMod.addCard(new CardSniperRapidMagazine());
        BaseMod.addCard(new CardSniperConShotAuto());
        BaseMod.addCard(new CardSniperExpAreaStr());
        BaseMod.addCard(new CardSniperFlexCamouflage());
        BaseMod.addCard(new CardSniperPoisonSpread());
        BaseMod.addCard(new CardSniperNeurotoxin());
        BaseMod.addCard(new CardSniperAmbush());
        //Medic
        BaseMod.addCard(new CardMedicDogmaticField());
        BaseMod.addCard(new CardMedicRevitalization());
        BaseMod.addCard(new CardMedicAutoCover());
        BaseMod.addCard(new CardMedicApocalypse());
        BaseMod.addCard(new CardMedicComMedShell());
        //Supporter
        BaseMod.addCard(new CardSupporterFoxfire());
        BaseMod.addCard(new CardSupporterSporePro());
        BaseMod.addCard(new CardSupporterEncForest());
        BaseMod.addCard(new CardSupporterCurseDoll());
        //Defender
        BaseMod.addCard(new CardDefenderDefendUp());
        BaseMod.addCard(new CardDefenderChargingDef());
        BaseMod.addCard(new CardDefenderShellDef());
        BaseMod.addCard(new CardDefenderMagHammer());
        BaseMod.addCard(new CardDefenderThorns());
        BaseMod.addCard(new CardDefenderCntHealMod());
        //Caster
        BaseMod.addCard(new CardCasterEmotionAbs());
        BaseMod.addCard(new CardCasterMentalBurst());
        BaseMod.addCard(new CardCasterSoulAbs());
        BaseMod.addCard(new CardCasterFate());
        BaseMod.addCard(new CardCasterGuardianOb());
        BaseMod.addCard(new CardCasterVeryHotBlade());
        //Specialist
        BaseMod.addCard(new CardSpecialistRatPack());
        BaseMod.addCard(new CardSpecialistChainHook());
        BaseMod.addCard(new CardSpecialistHookShot());
        BaseMod.addCard(new CardSpecialistShadowRaid());
        BaseMod.addCard(new CardSpecialistWolfPack());
        BaseMod.addCard(new CardSpecialistDisruptionKick());
        BaseMod.addCard(new CardSpecialistDurian());
        //Guard
        BaseMod.addCard(new CardGuardCatScratch());
        BaseMod.addCard(new CardGuardRedShift());
        BaseMod.addCard(new CardGuardTrueSilverSlash());
        BaseMod.addCard(new CardGuardBloodOath());
        BaseMod.addCard(new CardGuardShadowAssault());
        BaseMod.addCard(new CardGuardThermiteBlade());
        BaseMod.addCard(new CardGuardSoulRend());
        BaseMod.addCard(new CardGuardFracturedBody());
        //Vanguard
        BaseMod.addCard(new CardVanguardAssaultOrder());

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
        //BaseMod.addEvent(NightField.ID, NightField.class);
    }

    public void receiveEditCharacters() {
        System.out.println("ADDING CHARACTER");
        BaseMod.addCharacter(new CharacterDoctor(CardCrawlGame.playerName),
                "img/char/CharSelectButtonDoctor.png",
                "img/char/PortraitBG.png",
                DOCTOR_CLASS);
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
        BaseMod.addRelicToCustomPool(new Defend(), DOCTOR_COLOR);
        BaseMod.addRelicToCustomPool(new Cure(), DOCTOR_COLOR);
        BaseMod.addRelicToCustomPool(new Snipe(), DOCTOR_COLOR);
        BaseMod.addRelicToCustomPool(new Special(), DOCTOR_COLOR);
        BaseMod.addRelicToCustomPool(new Support(), DOCTOR_COLOR);
        BaseMod.addRelicToCustomPool(new MaxSp1(), DOCTOR_COLOR);
        BaseMod.addRelicToCustomPool(new MaxSp2(), DOCTOR_COLOR);
        BaseMod.addRelicToCustomPool(new MaxSp3(), DOCTOR_COLOR);
        BaseMod.addRelicToCustomPool(new MaxSp4(), DOCTOR_COLOR);
        BaseMod.addRelicToCustomPool(new OldCoin(), DOCTOR_COLOR);
        BaseMod.addRelicToCustomPool(new Gyao(), DOCTOR_COLOR);
        BaseMod.addRelicToCustomPool(new OriginiumAdd(), DOCTOR_COLOR);

        System.out.println("DONE");
    }

    public void receivePreRoomRender(SpriteBatch sb) {
    }


    public void receiveEditStrings() {
        String lang = getLangString();
        BaseMod.loadCustomStringsFile(CardStrings.class, "localization/" + lang + "/AtS_Cards.json");
        BaseMod.loadCustomStringsFile(PowerStrings.class, "localization/" + lang + "/AtS_Powers.json");
        BaseMod.loadCustomStringsFile(RelicStrings.class, "localization/" + lang + "/AtS_Relics.json");
        BaseMod.loadCustomStringsFile(CharacterStrings.class, "localization/" + lang + "/AtS_Doctor.json");
        BaseMod.loadCustomStringsFile(UIStrings.class, "localization/" + lang + "/AtS_UI.json");
        BaseMod.loadCustomStringsFile(EventStrings.class, "localization/" + lang + "/AtS_Events.json");
        BaseMod.loadCustomStringsFile(TutorialStrings.class, "localization/" + lang + "/AtS_tutorials.json");
    }


    private String getLangString() {
        return Settings.language.name().toLowerCase();
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
        /*
        Texture badgeTexture = TextureLoader.getTexture("CapriCoreResources/images/MokouMod/Badge.png");
        BaseMod.registerModBadge(badgeTexture, "ats", "Fastcat", "Arknights the Spire", settingsPanel);*/
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
}
