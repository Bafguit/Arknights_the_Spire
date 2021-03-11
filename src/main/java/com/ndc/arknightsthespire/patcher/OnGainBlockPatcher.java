package com.ndc.arknightsthespire.patcher;

import com.evacipated.cardcrawl.modthespire.lib.ByRef;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpireReturn;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.FrailPower;
import com.ndc.arknightsthespire.interfaces.OnGainBlockPower;
import com.ndc.arknightsthespire.interfaces.PreGainBlockPower;

@SpirePatch(
        clz=AbstractCreature.class,
        method="addBlock"
)
public class OnGainBlockPatcher
{

    public static SpireReturn<Integer> Prefix(AbstractCreature __instance, @ByRef int[] blockAmount)
    {
        System.out.println("OnGainBlockPatcher");
        if (blockAmount[0] > 0) {
            for (AbstractPower power : __instance.powers) {
                if (power instanceof OnGainBlockPower) {
                    blockAmount[0] = ((OnGainBlockPower) power).onGainBlock(__instance, null, blockAmount[0]);
                }
            }
        }
        return SpireReturn.Continue();
    }
}