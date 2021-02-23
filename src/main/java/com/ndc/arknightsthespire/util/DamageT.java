package com.ndc.arknightsthespire.util;

import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.ndc.arknightsthespire.character.AtsEnum;

public class DamageT extends DamageInfo {
    public boolean isArts;

    public DamageT(AbstractCreature owner, int base, boolean isArts) {
        super(owner, base);
        this.isArts = isArts;
    }

    public static int[] damageMatrix(int baseDamage, boolean isArts) {
        int[] retVal = new int[AbstractDungeon.getMonsters().monsters.size()];

        for(int i = 0; i < retVal.length; ++i) {
            DamageT info = new DamageT(AbstractDungeon.player, baseDamage, isArts);

            retVal[i] = info.output;
        }

        return retVal;
    }

}
