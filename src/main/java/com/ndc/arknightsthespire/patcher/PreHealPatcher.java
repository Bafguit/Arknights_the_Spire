package com.ndc.arknightsthespire.patcher;

import com.evacipated.cardcrawl.modthespire.lib.ByRef;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpireReturn;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.ndc.arknightsthespire.interfaces.OnGainBlockPower;
import com.ndc.arknightsthespire.interfaces.PreHealPower;

@SpirePatch(
        clz=AbstractCreature.class,
        method="heal",
        paramtypez={int.class, boolean.class}
)
public class PreHealPatcher
{

    public static SpireReturn<Integer> Prefix(AbstractCreature __instance, @ByRef int[] healAmount)
    {
        if (healAmount[0] > 0) {
            for (AbstractPower power : __instance.powers) {
                if (power instanceof PreHealPower) {
                    healAmount[0] = ((PreHealPower) power).preHeal(__instance, null, healAmount[0]);
                }
            }
        }

        return SpireReturn.Continue();
    }
}