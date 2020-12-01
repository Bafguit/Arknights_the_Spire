package com.ndc.arknightsthespire.events;

import com.megacrit.cardcrawl.actions.common.HealAction;
import com.megacrit.cardcrawl.actions.common.LoseHPAction;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.events.AbstractImageEvent;
import com.megacrit.cardcrawl.localization.EventStrings;

import static com.megacrit.cardcrawl.dungeons.AbstractDungeon.*;

public class NightField extends AbstractImageEvent {

    //This isn't technically needed but it becomes useful later
    public static final String ID = "ats:Night Field";
    private static final EventStrings eventStrings = CardCrawlGame.languagePack.getEventString(ID);
    public static final String NAME = eventStrings.NAME;
    public static final String[] TEXT = eventStrings.DESCRIPTIONS;
    public static final String[] OPTIONS = eventStrings.OPTIONS;
    public static CurScreen screen;

    public NightField(){
        super(NAME, TEXT[0], "img/events/NightField.png");

        //This is where you would create your dialog options
        this.imageEventText.setDialogOption(OPTIONS[0]);
        this.imageEventText.setDialogOption(OPTIONS[1]);
        this.imageEventText.setDialogOption(OPTIONS[3]);

        this.hasDialog = true;
        this.hasFocus = true;
        this.screen = CurScreen.INTRO;
    }

    @Override
    protected void buttonEffect(int buttonPressed) {
        switch(this.screen) {
            case INTRO:
                if(buttonPressed == 0) {
                    this.imageEventText.clearRemainingOptions();
                    actionManager.addToBottom(new HealAction(player, player, 10));
                    this.imageEventText.updateBodyText(TEXT[1]);
                    this.imageEventText.setDialogOption(OPTIONS[8]);
                    this.screen = CurScreen.LEAVE;
                } else if(buttonPressed == 1) {
                    this.imageEventText.clearRemainingOptions();
                    this.imageEventText.updateBodyText(TEXT[2]);
                    this.imageEventText.setDialogOption(OPTIONS[4]);
                    this.imageEventText.setDialogOption(OPTIONS[9]);
                    this.screen = CurScreen.SELECT_1;
                } else {
                    this.imageEventText.clearRemainingOptions();
                    this.imageEventText.updateBodyText(TEXT[3]);
                    if(player.gold >= 75) {
                        this.imageEventText.setDialogOption(OPTIONS[5], player.gold < 75);
                    } else {
                        this.imageEventText.setDialogOption(OPTIONS[4], player.gold < 75);
                    }
                    this.imageEventText.setDialogOption(OPTIONS[9]);
                    this.screen = CurScreen.SELECT_2;
                }
                break;
            case SELECT_1:
                this.imageEventText.clearRemainingOptions();
                if(buttonPressed == 0) {
                    actionManager.addToBottom(new LoseHPAction(player, player, 6));
                    player.getRelic("ats:OldCoin");
                    this.imageEventText.updateBodyText(TEXT[1]);
                    this.imageEventText.setDialogOption(OPTIONS[8]);
                    this.screen = CurScreen.LEAVE;
                } else {
                    this.imageEventText.updateBodyText(TEXT[1]);
                    this.imageEventText.setDialogOption(OPTIONS[8]);
                    this.screen = CurScreen.LEAVE;
                }
                break;
            case SELECT_2:
                this.imageEventText.clearRemainingOptions();
                if(buttonPressed == 0) {
                    player.loseGold(75);
                    player.getRelic("ats:Gyao");
                    this.imageEventText.updateBodyText(TEXT[1]);
                    this.imageEventText.setDialogOption(OPTIONS[8]);
                    this.screen = CurScreen.LEAVE;
                } else {
                    this.imageEventText.updateBodyText(TEXT[1]);
                    this.imageEventText.setDialogOption(OPTIONS[8]);
                    this.screen = CurScreen.LEAVE;
                }
                break;
            case LEAVE:
                this.imageEventText.updateDialogOption(0, OPTIONS[8]);
                this.imageEventText.clearRemainingOptions();
                this.openMap();
                break;
        }

        }
}