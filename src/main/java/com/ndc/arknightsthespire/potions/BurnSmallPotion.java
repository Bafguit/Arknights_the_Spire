package com.ndc.arknightsthespire.potions;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.PowerTip;
import com.megacrit.cardcrawl.localization.PotionStrings;
import com.megacrit.cardcrawl.potions.AbstractPotion;
import com.ndc.arknightsthespire.SPHandler;
import com.ndc.arknightsthespire.power.BurnPower;

public class BurnSmallPotion extends AbstractPotion {

    public static final String ID = "ats:Burn Small Potion";
    private static final PotionStrings potionStrings = CardCrawlGame.languagePack.getPotionString(ID);
    private static final String NAME = potionStrings.NAME;
    private static final String[] DESCRIPTIONS = potionStrings.DESCRIPTIONS;

    public BurnSmallPotion() {
        super(NAME, ID, PotionRarity.COMMON,
                PotionSize.T, PotionColor.NONE);
        this.isThrown = true;
        this.targetRequired = true;
    }

    @Override
    public void use(AbstractCreature target) {
        this.addToBot(new ApplyPowerAction(target, AbstractDungeon.player, new BurnPower(target, AbstractDungeon.player, this.potency), this.potency));
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
        return 6;
    }

    @Override
    public AbstractPotion makeCopy() {
        return new BurnSmallPotion();
    }
}
