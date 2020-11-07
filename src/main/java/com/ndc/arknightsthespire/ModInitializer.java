package com.ndc.arknightsthespire;

import basemod.BaseMod;
import basemod.interfaces.EditCardsSubscriber;
import basemod.interfaces.PostDrawSubscriber;
import basemod.interfaces.PostInitializeSubscriber;
import com.evacipated.cardcrawl.modthespire.lib.SpireInitializer;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.ndc.arknightsthespire.buttons.ToggleSpButton;
import com.ndc.arknightsthespire.cards.CardSniperOverload;
import com.ndc.arknightsthespire.cards.CardSniperPowerfulStrike;


@SpireInitializer
public class ModInitializer implements PostDrawSubscriber, EditCardsSubscriber, PostInitializeSubscriber {

    public ModInitializer(){
        //Use this for when you subscribe to any hooks offered by BaseMod.
        BaseMod.subscribe(this);
    }



    //Used by @SpireInitializer
    public static void initialize(){
        BaseMod.addDynamicVariable(new SPDynamicVariable());
        //This creates an instance of our classes and gets our code going after BaseMod and ModTheSpire initialize.
        ModInitializer modInitializer = new ModInitializer();

        //Look at the BaseMod wiki for getting started. (Skip the decompiling part. It's not needed right now)

    }



    @Override
    public void receivePostDraw(AbstractCard card) {
        System.out.println(card.name + " 이(가) 뽑혔다!");
    }

    @Override
    public void receiveEditCards() {
        BaseMod.addCard(new CardSniperOverload());
        BaseMod.addCard(new CardSniperPowerfulStrike());
    }

    @Override
    public void receivePostInitialize() {
        ImageMaster.END_TURN_BUTTON = ToggleSpButton.UI_BUTTON_RIGHT;
        ImageMaster.END_TURN_BUTTON_GLOW = ToggleSpButton.UI_BUTTON_RIGHT_GLOW;
        ImageMaster.END_TURN_HOVER = ToggleSpButton.UI_BUTTON_RIGHT_HOVER;
    }
}

