
package com.ndc.arknightsthespire;

import basemod.BaseMod;
import basemod.interfaces.*;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.evacipated.cardcrawl.modthespire.lib.SpireInitializer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.ndc.arknightsthespire.cards.basic.CardCasterEmotionAbs;
import com.ndc.arknightsthespire.cards.basic.CardDefenderDefendUp;
import com.ndc.arknightsthespire.cards.basic.CardSniperArmCrushShot;
import com.ndc.arknightsthespire.cards.basic.CardSniperPowerfulStrike;
import com.ndc.arknightsthespire.cards.common.*;
import com.ndc.arknightsthespire.cards.rare.*;
import com.ndc.arknightsthespire.cards.uncommon.*;
import com.ndc.arknightsthespire.character.CharacterDoctor;
import com.ndc.arknightsthespire.relics.*;
import com.ndc.arknightsthespire.ui.ToggleSpButton;

import static com.ndc.arknightsthespire.character.ATSCharacterEnum.DOCTOR_CLASS;


@SpireInitializer
public class ArknightsTheSpire implements EditCardsSubscriber, PostInitializeSubscriber, EditCharactersSubscriber, EditRelicsSubscriber, PreRoomRenderSubscriber {

    private static ArknightsTheSpire INSTANCE;

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
    }


    @Override
    public void receiveEditCards() {

        System.out.println("ADDING CARDS");
        //Sniper
        BaseMod.addCard(new CardSniperOverload());
        BaseMod.addCard(new CardSniperPowerfulStrike());
        BaseMod.addCard(new CardSniperArmCrushShot());
        BaseMod.addCard(new CardSniperRapidMagazine());
        BaseMod.addCard(new CardSniperConShotAuto());
        BaseMod.addCard(new CardSniperExpAreaStr());
        //Medic
        BaseMod.addCard(new CardMedicDogmaticField());
        BaseMod.addCard(new CardMedicRevitalization());
        //Supporter
        BaseMod.addCard(new CardSupporterFoxfire());
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
        //Specialist
        BaseMod.addCard(new CardSpecialistRatPack());
        //Guard
        BaseMod.addCard(new CardGuardCatScratch());
        BaseMod.addCard(new CardGuardRedShift());
        BaseMod.addCard(new CardGuardTrueSilverSlash());
        BaseMod.addCard(new CardGuardBloodOath());
        BaseMod.addCard(new CardGuardShadowAssault());
        BaseMod.addCard(new CardGuardThermiteBlade());
        //Vanguard
        BaseMod.addCard(new CardVanguardAssaultOrder());

        System.out.println("DONE");
    }

    @Override
    public void receivePostInitialize() {
        System.out.println("POSTINIT");
        ImageMaster.END_TURN_BUTTON = ToggleSpButton.UI_BUTTON_RIGHT;
        ImageMaster.END_TURN_BUTTON_GLOW = ToggleSpButton.UI_BUTTON_RIGHT_GLOW;
        ImageMaster.END_TURN_HOVER = ToggleSpButton.UI_BUTTON_RIGHT_HOVER;
    }

    @Override
    public void receiveEditCharacters() {
        System.out.println("ADDING CHARACTER");
        BaseMod.addCharacter(new CharacterDoctor(CardCrawlGame.playerName),
                "img/char/CharacterDoctorButton.png",
                "img/char/PortraitBG.png",
                DOCTOR_CLASS);
        System.out.println("DONE");
    }

    @Override
    public void receiveEditRelics() {
        System.out.println("ADDING RELICS");

        BaseMod.addRelicToCustomPool(new SpiritualRecovery(), CardColors.AbstractCardEnum.DOCTOR_COLOR);
        BaseMod.addRelicToCustomPool(new RhineCircuitry(), CardColors.AbstractCardEnum.DOCTOR_COLOR);
        BaseMod.addRelicToCustomPool(new MilitaryTradition(), CardColors.AbstractCardEnum.DOCTOR_COLOR);
        BaseMod.addRelicToCustomPool(new AbilityAura(), CardColors.AbstractCardEnum.DOCTOR_COLOR);
        BaseMod.addRelicToCustomPool(new Lavender(), CardColors.AbstractCardEnum.DOCTOR_COLOR);
        BaseMod.addRelicToCustomPool(new Omniscience(), CardColors.AbstractCardEnum.DOCTOR_COLOR);

        System.out.println("DONE");
    }

    public void receivePreRoomRender(SpriteBatch sb) {
    }
}
