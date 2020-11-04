package com.ndc.arknightsthespire.patcher;

import basemod.ReflectionHacks;
import com.evacipated.cardcrawl.modthespire.lib.SpireInsertPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.cards.DescriptionLine;
import com.ndc.arknightsthespire.cards.CardSPBase;

import java.util.logging.Logger;

public class AbstractCardPatcher {

    @SpirePatch(
            clz= AbstractCard.class,
            method=SpirePatch.CONSTRUCTOR,
            paramtypez = {
                    String.class, String.class, String.class, int.class, String.class, AbstractCard.CardType.class, AbstractCard.CardColor.class, AbstractCard.CardRarity.class, AbstractCard.CardTarget.class, DamageInfo.DamageType.class
            }
    )
    @SpirePatch(
            clz= AbstractCard.class,
            method=SpirePatch.CONSTRUCTOR,
            paramtypez = {
                    AbstractCard.class
            }
    )
    public static class ConstructorPatcher {
        public static void Postfix(AbstractCard __instance, String id, String name, String imgUrl, int cost, String rawDescription, AbstractCard.CardType type, AbstractCard.CardColor color, AbstractCard.CardRarity rarity, AbstractCard.CardTarget target, DamageInfo.DamageType dType) {
            if(__instance instanceof CardSPBase) {
                CardSPBase inst = (CardSPBase) __instance;
                inst.baseSp = -1;
                inst.sp = -1;
                inst.isSpModified = false;
                inst.upgradedSp = false;
            }
        }
    }

    @SpirePatch(
            clz= AbstractCard.class,
            method="getDynamicValue"
    )
    public static class GetDynamicValuePatcher
    {
        public static String Replace(AbstractCard __instance, char key) {
            switch(key) {
                case 'B':
                    if (__instance.isBlockModified) {
                        if (__instance.block >= __instance.baseBlock) {
                            return "[#7fff00]" + Integer.toString(__instance.block) + "[]";
                        }

                        return "[#ff6563]" + Integer.toString(__instance.block) + "[]";
                    }

                    return Integer.toString(__instance.baseBlock);
                case 'D':
                    if (__instance.isDamageModified) {
                        if (__instance.damage >= __instance.baseDamage) {
                            return "[#7fff00]" + Integer.toString(__instance.damage) + "[]";
                        }

                        return "[#ff6563]" + Integer.toString(__instance.damage) + "[]";
                    }

                    return Integer.toString(__instance.baseDamage);
                case 'M':
                    if (__instance.isMagicNumberModified) {
                        if (__instance.magicNumber >= __instance.baseMagicNumber) {
                            return "[#7fff00]" + Integer.toString(__instance.magicNumber) + "[]";
                        }

                        return "[#ff6563]" + Integer.toString(__instance.magicNumber) + "[]";
                    }

                    return Integer.toString(__instance.baseMagicNumber);
                case 'S':
                    if(__instance instanceof CardSPBase) {
                        CardSPBase __inst = (CardSPBase) __instance;
                        if (__inst.isSpModified) {
                            if (__inst.sp >= __inst.baseSp) {
                                return "[#7fff00]" + Integer.toString(__inst.sp) + "[]";
                            }

                            return "[#ff6563]" + Integer.toString(__inst.sp) + "[]";
                        }

                        return Integer.toString(__inst.baseSp);
                    }
                default:
                    ((Logger) ReflectionHacks.getPrivate(__instance, AbstractCard.class, "logger")).info("KEY: " + key);
                    return Integer.toString(-99);
            }
        }
    }


        @SpirePatch(
                clz= AbstractCard.class,
                method="initializeDescriptionCN"
        )
        public static class InitializeDescriptionCNPatcher {
            @SpireInsertPatch(
                    loc=740,
                    localvars = {"numLines", "currentWidth", "word"}
            )
        public static void Insert(AbstractCard __instance, int numLines, float currentWidth, String word)
        {
            if(__instance instanceof CardSPBase && word.equals("!S!")) {
                    //int value = CardSPBase.class.getDeclaredField("sp").get(__instance);
                    final float MAGIC_NUM_W = (float) ReflectionHacks.getPrivate(__instance, AbstractCard.class, "MAGIC_NUM_W");
                    final float CN_DESC_BOX_WIDTH = (float) ReflectionHacks.getPrivate(__instance, AbstractCard.class, "CN_DESC_BOX_WIDTH");
                    final StringBuilder sbuilder = (StringBuilder) ReflectionHacks.getPrivate(__instance, AbstractCard.class, "sbuilder");
                    if (currentWidth + MAGIC_NUM_W > CN_DESC_BOX_WIDTH) {
                        ++numLines;
                        __instance.description.add(new DescriptionLine(sbuilder.toString(), currentWidth));
                        sbuilder.setLength(0);
                        currentWidth = MAGIC_NUM_W;
                        sbuilder.append(" ").append(word).append("! ");
                    } else {
                        sbuilder.append(" ").append(word).append("! ");
                        currentWidth += MAGIC_NUM_W;
                    }
            }
        }
    }

}
