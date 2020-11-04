package com.ndc.arknightsthespire;

import basemod.BaseMod;
import basemod.interfaces.EditCardsSubscriber;
import basemod.interfaces.PostDrawSubscriber;
import com.evacipated.cardcrawl.modthespire.lib.SpireInitializer;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.ndc.arknightsthespire.cards.CardSniperOverload;


@SpireInitializer
public class ModInitializer implements PostDrawSubscriber, EditCardsSubscriber {

    public ModInitializer(){
        //Use this for when you subscribe to any hooks offered by BaseMod.
        BaseMod.subscribe(this);
    }



    //Used by @SpireInitializer
    public static void initialize(){

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
    }
}

