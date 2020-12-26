package com.ndc.arknightsthespire.patcher;

import com.evacipated.cardcrawl.modthespire.lib.ByRef;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpireReturn;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.ndc.arknightsthespire.interfaces.OnGainBlockPower;
import com.ndc.arknightsthespire.interfaces.OnGainEnergyPower;

@SpirePatch(
        clz= GainEnergyAction.class,
        method="GainEnergyAction"
)
public class OnGainEnergyPatcher
{
    public static SpireReturn<Integer> Prefix(@ByRef int[] e)
    {
        if (e[0] > 0) {
            for (AbstractPower power : AbstractDungeon.player.powers) {
                if (power instanceof OnGainEnergyPower) {
                    e[0] = ((OnGainEnergyPower) power).onGainEnergy(e[0]);
                }
            }
        }

        return SpireReturn.Continue();
    }
}