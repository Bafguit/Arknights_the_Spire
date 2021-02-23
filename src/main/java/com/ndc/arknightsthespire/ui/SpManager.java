//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.ndc.arknightsthespire.ui;

import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.rooms.AbstractRoom.RoomPhase;
import com.ndc.arknightsthespire.character.AtsEnum;
import com.ndc.arknightsthespire.ui.SpUtil.SpFields;

import static com.megacrit.cardcrawl.dungeons.AbstractDungeon.player;

public class SpManager {

    public SpManager() {
    }

    public static boolean isImmortal() {
        return player.chosenClass == AtsEnum.DOCTOR_CLASS;
    }

    public static SpUI getSpUI() {
        return (SpUI)SpFields.panel.get(player);
    }

    public static boolean shouldRenderSp() {
        return CardCrawlGame.isInARun() && AbstractDungeon.getCurrRoom() != null && AbstractDungeon.getCurrRoom().phase == RoomPhase.COMBAT;
    }
}
