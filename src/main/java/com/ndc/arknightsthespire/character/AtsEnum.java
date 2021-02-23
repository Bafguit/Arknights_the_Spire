package com.ndc.arknightsthespire.character;

import com.evacipated.cardcrawl.modthespire.lib.SpireEnum;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class AtsEnum {

    @SpireEnum
    public static AbstractPlayer.PlayerClass DOCTOR_CLASS;
    @SpireEnum
    public static AbstractPower.PowerType ATS_BASE;
    @SpireEnum
    public static DamageInfo.DamageType ARTS;
    @SpireEnum
    public static DamageInfo.DamageType PHYS;
    @SpireEnum
    public static AbstractMonster.Intent ATTACK_ARTS;
    @SpireEnum
    public static AbstractMonster.Intent ATTACK_ARTS_BUFF;
    @SpireEnum
    public static AbstractMonster.Intent ATTACK_ARTS_DEBUFF;

}
