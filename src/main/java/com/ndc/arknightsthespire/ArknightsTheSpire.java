
package com.ndc.arknightsthespire;

import basemod.BaseMod;
import basemod.helpers.RelicType;
import basemod.interfaces.EditCardsSubscriber;
import basemod.interfaces.EditCharactersSubscriber;
import basemod.interfaces.EditRelicsSubscriber;
import basemod.interfaces.PostInitializeSubscriber;
import com.evacipated.cardcrawl.modthespire.lib.SpireInitializer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.ndc.arknightsthespire.cards.basic.CardSniperArmCrushShot;
import com.ndc.arknightsthespire.cards.basic.CardTestSPGainer;
import com.ndc.arknightsthespire.relics.SpiritualRecovery;
import com.ndc.arknightsthespire.ui.ToggleSpButton;
import com.ndc.arknightsthespire.cards.basic.CardDefenderDefendUp;
import com.ndc.arknightsthespire.cards.basic.CardSniperPowerfulStrike;
import com.ndc.arknightsthespire.cards.common.CardDefenderShellDef;
import com.ndc.arknightsthespire.cards.rare.CardMedicDogmaticField;
import com.ndc.arknightsthespire.cards.uncommon.*;
import com.ndc.arknightsthespire.character.CharacterDoctor;

import static com.ndc.arknightsthespire.character.ATSCharacterEnum.DOCTOR_CLASS;


@SpireInitializer
public class ArknightsTheSpire implements EditCardsSubscriber, PostInitializeSubscriber, EditCharactersSubscriber, EditRelicsSubscriber {

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
        //Medic
        BaseMod.addCard(new CardMedicDogmaticField());
        //Supporter
        BaseMod.addCard(new CardSupporterFoxfire());
        //Defender
        BaseMod.addCard(new CardDefenderDefendUp());
        BaseMod.addCard(new CardDefenderChargingDef());
        BaseMod.addCard(new CardDefenderShellDef());
        BaseMod.addCard(new CardDefenderMagHammer());
        BaseMod.addCard(new CardDefenderThorns());
        BaseMod.addCard(new CardDefenderCntHealMod());
        //Test
        BaseMod.addCard(new CardTestSPGainer());

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

        BaseMod.addRelic(new SpiritualRecovery(), RelicType.SHARED);
        //TODO    RelicType에 박사 전용으로 하나 만들어야함. 이름은 대충 DOCTOR로 하면 될듯

        System.out.println("DONE");
    }
}
