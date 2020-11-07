package com.ndc.arknightsthespire.patcher;

/*
public class AbstractCardPatcher {
    static Method dedupeKeyword;

    static {
        try {
            dedupeKeyword = AbstractCard.class.getMethod("dedupeKeyword");
            dedupeKeyword.setAccessible(true);
        } catch(NoSuchMethodException e) {
            System.out.println("Couldn't find dedupeKeyword");
        }
    }

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
        RenderCustomDynamicVariable.Inner
        public static String Replace(AbstractCard __instance, char key) {
            System.out.println(key);
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
        public static void Replace(AbstractCard __instance) {
            final StringBuilder sbuilder = (StringBuilder) ReflectionHacks.getPrivate(__instance, AbstractCard.class, "sbuilder");
            final GlyphLayout gl = (GlyphLayout) ReflectionHacks.getPrivate(__instance, AbstractCard.class, "gl");
            final float CN_DESC_BOX_WIDTH = (float) ReflectionHacks.getPrivate(__instance, AbstractCard.class, "CN_DESC_BOX_WIDTH");
            final float CARD_ENERGY_IMG_WIDTH = (float) ReflectionHacks.getPrivate(__instance, AbstractCard.class, "CARD_ENERGY_IMG_WIDTH");
            final Logger logger = (Logger) ReflectionHacks.getPrivate(__instance, AbstractCard.class, "logger");
            final float MAGIC_NUM_W = (float) ReflectionHacks.getPrivate(__instance, AbstractCard.class, "MAGIC_NUM_W");

            __instance.description.clear();
            int numLines = 1;
            sbuilder.setLength(0);
            float currentWidth = 0.0F;
            String[] var3 = __instance.rawDescription.split(" ");
            int i = var3.length;

            for(int var5 = 0; var5 < i; ++var5) {
                String word = var3[var5];
                word = word.trim();
                if (Settings.manualLineBreak || Settings.manualAndAutoLineBreak || !word.contains("NL")) {
                    if ((!word.equals("NL") || sbuilder.length() != 0) && !word.isEmpty()) {
                        String keywordTmp = word.toLowerCase();
                        try {
                            keywordTmp = (String) dedupeKeyword.invoke(__instance, keywordTmp);
                        } catch (IllegalAccessException e) {
                            e.printStackTrace();
                        } catch (InvocationTargetException e) {
                            e.printStackTrace();
                        }
                        if (GameDictionary.keywords.containsKey(keywordTmp)) {
                            if (!__instance.keywords.contains(keywordTmp)) {
                                __instance.keywords.add(keywordTmp);
                            }

                            gl.setText(FontHelper.cardDescFont_N, word);
                            if (currentWidth + gl.width > CN_DESC_BOX_WIDTH) {
                                ++numLines;
                                __instance.description.add(new DescriptionLine(sbuilder.toString(), currentWidth));
                                sbuilder.setLength(0);
                                currentWidth = gl.width;
                                sbuilder.append(" *").append(word).append(" ");
                            } else {
                                sbuilder.append(" *").append(word).append(" ");
                                currentWidth += gl.width;
                            }
                        } else if (!word.isEmpty() && word.charAt(0) == '[') {
                            switch(__instance.color) {
                                case RED:
                                    if (!__instance.keywords.contains("[R]")) {
                                        __instance.keywords.add("[R]");
                                    }
                                    break;
                                case GREEN:
                                    if (!__instance.keywords.contains("[G]")) {
                                        __instance.keywords.add("[G]");
                                    }
                                    break;
                                case BLUE:
                                    if (!__instance.keywords.contains("[B]")) {
                                        __instance.keywords.add("[B]");
                                    }
                                    break;
                                case PURPLE:
                                    if (!__instance.keywords.contains("[W]")) {
                                        __instance.keywords.add("[W]");
                                    }
                                    break;
                                case COLORLESS:
                                    if (!__instance.keywords.contains("[W]")) {
                                        __instance.keywords.add("[W]");
                                    }
                                    break;
                                default:
                                    logger.info("ERROR: Tried to display an invalid energy type: " + __instance.color.name());
                            }

                            if (currentWidth + CARD_ENERGY_IMG_WIDTH > CN_DESC_BOX_WIDTH) {
                                ++numLines;
                                __instance.description.add(new DescriptionLine(sbuilder.toString(), currentWidth));
                                sbuilder.setLength(0);
                                currentWidth = CARD_ENERGY_IMG_WIDTH;
                                sbuilder.append(" ").append(word).append(" ");
                            } else {
                                sbuilder.append(" ").append(word).append(" ");
                                currentWidth += CARD_ENERGY_IMG_WIDTH;
                            }
                        } else if (word.equals("!D!")) {
                            if (currentWidth + MAGIC_NUM_W > CN_DESC_BOX_WIDTH) {
                                ++numLines;
                                __instance.description.add(new DescriptionLine(sbuilder.toString(), currentWidth));
                                sbuilder.setLength(0);
                                currentWidth = MAGIC_NUM_W;
                                sbuilder.append(" D ");
                            } else {
                                sbuilder.append(" D ");
                                currentWidth += MAGIC_NUM_W;
                            }
                        } else if (word.equals("!B!")) {
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
                        } else if (word.equals("!M!")) {
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
                        } else if ((Settings.manualLineBreak || Settings.manualAndAutoLineBreak) && word.equals("NL") && sbuilder.length() != 0) {
                            gl.width = 0.0F;
                            word = "";
                            __instance.description.add(new DescriptionLine(sbuilder.toString().trim(), currentWidth));
                            currentWidth = 0.0F;
                            ++numLines;
                            sbuilder.setLength(0);
                        } else if (word.charAt(0) == '*') {
                            gl.setText(FontHelper.cardDescFont_N, word.substring(1));
                            if (currentWidth + gl.width > CN_DESC_BOX_WIDTH) {
                                ++numLines;
                                __instance.description.add(new DescriptionLine(sbuilder.toString(), currentWidth));
                                sbuilder.setLength(0);
                                currentWidth = gl.width;
                                sbuilder.append(" ").append(word).append(" ");
                            } else {
                                sbuilder.append(" ").append(word).append(" ");
                                currentWidth += gl.width;
                            }
                        } else {
                            word = word.trim();
                            char[] var8 = word.toCharArray();
                            int var9 = var8.length;

                            for(int var10 = 0; var10 < var9; ++var10) {
                                char c = var8[var10];
                                gl.setText(FontHelper.cardDescFont_N, String.valueOf(c));
                                sbuilder.append(c);
                                if (!Settings.manualLineBreak) {
                                    if (currentWidth + gl.width > CN_DESC_BOX_WIDTH) {
                                        ++numLines;
                                        __instance.description.add(new DescriptionLine(sbuilder.toString(), currentWidth));
                                        sbuilder.setLength(0);
                                        currentWidth = gl.width;
                                    } else {
                                        currentWidth += gl.width;
                                    }
                                }
                            }
                        }
                    } else {
                        currentWidth = 0.0F;
                    }
                }
            }

            if (sbuilder.length() != 0) {
                __instance.description.add(new DescriptionLine(sbuilder.toString(), currentWidth));
            }

            int removeLine = -1;

            for(i = 0; i < __instance.description.size(); ++i) {
                if (((DescriptionLine)__instance.description.get(i)).text.equals(LocalizedStrings.PERIOD)) {
                    StringBuilder var10000 = new StringBuilder();
                    DescriptionLine var10002 = (DescriptionLine)__instance.description.get(i - 1);
                    var10002.text = var10000.append(var10002.text).append(LocalizedStrings.PERIOD).toString();
                    removeLine = i;
                }
            }

            if (removeLine != -1) {
                __instance.description.remove(removeLine);
            }

            if (Settings.isDev && numLines > 5) {
                logger.info("WARNING: Card " + __instance.name + " has lots of text");
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
                if (cardSPBase.isInstance(__instance) && word.equals("!S!")) {
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
        */
