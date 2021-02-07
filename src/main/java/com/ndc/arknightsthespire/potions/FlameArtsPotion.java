package com.ndc.arknightsthespire.potions;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.PowerTip;
import com.megacrit.cardcrawl.localization.PotionStrings;
import com.megacrit.cardcrawl.potions.AbstractPotion;
import com.ndc.arknightsthespire.power.BurnPower;

public class FlameArtsPotion extends AbstractPotion {

    public static final String ID = "ats:Flame Arts Potion";
    private static final PotionStrings potionStrings = CardCrawlGame.languagePack.getPotionString(ID);
    private static final String NAME = potionStrings.NAME;
    private static final String[] DESCRIPTIONS = potionStrings.DESCRIPTIONS;

    public FlameArtsPotion() {
        super(NAME, ID, PotionRarity.RARE,
                PotionSize.SPHERE, PotionColor.NONE);
        this.isThrown = true;
        this.targetRequired = true;
    }

    @Override
    public void use(AbstractCreature target) {
        if (target.hasPower(BurnPower.POWER_ID)) {
            int amount = target.getPower(BurnPower.POWER_ID).amount;
            this.addToBot(new ApplyPowerAction(target, AbstractDungeon.player, new BurnPower(target, AbstractDungeon.player, amount), amount));
        }
    }

    @Override
    public void initializeData() {
        this.potency = this.getPotency();
        this.description = DESCRIPTIONS[0];

        this.tips.clear();
        this.tips.add(new PowerTip(this.name, this.description));
    }

    @Override
    public int getPotency(int i) {
        return -1;
    }

    @Override
    public AbstractPotion makeCopy() {
        return new FlameArtsPotion();
    }
}
