
package com.ndc.arknightsthespire;

import basemod.BaseMod;
import basemod.interfaces.EditCardsSubscriber;
import basemod.interfaces.EditCharactersSubscriber;
import basemod.interfaces.PostDrawSubscriber;
import basemod.interfaces.PostInitializeSubscriber;
import com.evacipated.cardcrawl.modthespire.lib.SpireInitializer;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.ndc.arknightsthespire.buttons.ToggleSpButton;
import com.ndc.arknightsthespire.cards.CardDefenderDefendUp;
import com.ndc.arknightsthespire.cards.CardSniperOverload;
import com.ndc.arknightsthespire.cards.CardSniperPowerfulStrike;
import com.ndc.arknightsthespire.character.CharacterDoctor;

import static com.ndc.arknightsthespire.character.ATSCharacterEnum.DOCTOR_CLASS;


@SpireInitializer
public class ArknightsTheSpire implements PostDrawSubscriber, EditCardsSubscriber, PostInitializeSubscriber, EditCharactersSubscriber {

    private static ArknightsTheSpire INSTANCE;

    public ArknightsTheSpire(){
        //Use this for when you subscribe to any hooks offered by BaseMod.
        BaseMod.subscribe(this);
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
    public void receivePostDraw(AbstractCard card) {
        System.out.println(card.name + " 이(가) 뽑혔다!");
    }

    @Override
    public void receiveEditCards() {
        System.out.println("ADDING CARDS");
        BaseMod.addCard(new CardSniperOverload());
        BaseMod.addCard(new CardSniperPowerfulStrike());
        BaseMod.addCard(new CardDefenderDefendUp());
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
}
