//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.ndc.arknightsthespire.cards.base;

import basemod.AutoAdd.Ignore;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.helpers.CardHelper;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

@Ignore
public class CardSpPreview extends CardSPBase {
    public CardSpPreview(String id, String name, String des, String img, int cost, CardType type, CardColor color, CardRarity rarity, CardTarget target, boolean isAuto, PositionType position, int d, int b, int m, int s) {
        super(id, name, des, img, null, cost, type, color, rarity, target, isAuto, position, false, d, b, m, s);
        this.rawDescription = des;
        this.initializeDescription();
    }

    @Override
    public void upgradeCard() {
    }

    @Override
    public void useCard(AbstractPlayer p, AbstractMonster m, boolean isSpJustUsed) {
    }
}
