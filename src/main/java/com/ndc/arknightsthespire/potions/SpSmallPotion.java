package com.ndc.arknightsthespire.potions;

import basemod.abstracts.CustomPotion;
import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.CardHelper;
import com.megacrit.cardcrawl.helpers.PowerTip;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.localization.PotionStrings;
import com.megacrit.cardcrawl.potions.AbstractPotion;
import com.megacrit.cardcrawl.potions.AbstractPotion.PotionEffect;
import com.ndc.arknightsthespire.SPHandler;

import java.awt.*;

public class SpSmallPotion extends AbstractPotion {

    public static final String ID = "ats:Emergency Sanity Potion";
    private static final PotionStrings potionStrings = CardCrawlGame.languagePack.getPotionString(ID);
    private static final String NAME = potionStrings.NAME;
    private static final String[] DESCRIPTIONS = potionStrings.DESCRIPTIONS;

    public SpSmallPotion() {
        super(NAME, ID, PotionRarity.COMMON,
                PotionSize.T, PotionColor.NONE);
    }

    @Override
    public void use(AbstractCreature abstractCreature) {
        SPHandler.addSp(this.potency);
    }

    @Override
    public void initializeData() {
        this.potency = this.getPotency();
        this.description = DESCRIPTIONS[0] + this.potency + DESCRIPTIONS[1];

        this.tips.clear();
        this.tips.add(new PowerTip(this.name, this.description));
    }

    @Override
    public int getPotency(int i) {
        return 10;
    }

    @Override
    public AbstractPotion makeCopy() {
        return new SpSmallPotion();
    }
}
