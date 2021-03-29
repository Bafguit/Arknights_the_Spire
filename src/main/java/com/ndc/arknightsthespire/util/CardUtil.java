package com.ndc.arknightsthespire.util;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.CardLibrary;
import com.megacrit.cardcrawl.rewards.RewardItem;
import com.ndc.arknightsthespire.CardColors;
import com.ndc.arknightsthespire.cards.base.CardSPBase;
import com.ndc.arknightsthespire.cards.base.PositionType;

import java.util.ArrayList;
import java.util.Iterator;

public class CardUtil {

    public static ArrayList<AbstractCard> getCardPool(PositionType p) {
        ArrayList<AbstractCard> rc = new ArrayList<>();

        Iterator var1 = CardLibrary.getAllCards().iterator();

        while (var1.hasNext()) {
            AbstractCard c = (AbstractCard)var1.next();
            if(c.rarity != AbstractCard.CardRarity.SPECIAL && c.rarity != AbstractCard.CardRarity.BASIC && c.color == CardColors.AbstractCardEnum.DOCTOR_COLOR && c instanceof CardSPBase) {
                if(((CardSPBase)c).position == p) {
                    rc.add(c);
                }
            }
        }

        return rc;
    }

    public static void generateCardReward(ArrayList<AbstractCard> cards, int amount) {
        for (int i = 0; i < amount; i++) {
            RewardItem r = new RewardItem();
            int a;
            AbstractCard c;
            r.cards.clear();
            for(int j = 0; j < 3; j++) {
                do {
                    a = AbstractDungeon.cardRng.random.nextInt(cards.size());
                    c = cards.get(a);
                } while (r.cards.contains(c));
                System.out.println("No." + i + "  |  Card: " + c.cardID + "  |  Index: " + a);
                r.cards.add(c);
            }
            AbstractDungeon.getCurrRoom().rewards.add(r);
        }
    }

    public static void generateCardReward(CardGroup cards, int amount) {
        generateCardReward(cards.group, amount);
    }
}
