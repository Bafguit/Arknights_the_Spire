package com.ndc.arknightsthespire.monsters.intent;

import actlikeit.RazIntent.CustomIntent;
import basemod.ReflectionHacks;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.UIStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import com.megacrit.cardcrawl.vfx.DebuffParticleEffect;
import com.ndc.arknightsthespire.character.AtsEnum;

import java.util.ArrayList;

public class ArtsDebuffAttackIntent extends CustomIntent {
    public static final String ID = "ArtsAttack";

    private static final UIStrings uiStrings;
    private static final String[] TEXT;

    public ArtsDebuffAttackIntent() {
        super(AtsEnum.ATTACK_ARTS_BUFF, TEXT[5], "img/ui/intent/arts_1.png", "img/ui/intent/arts_1_tip.png");
    }

    @Override
    public String description(AbstractMonster mo) {
        String result = TEXT[1];
        result += ReflectionHacks.getPrivate(mo, AbstractMonster.class, "intentDmg");
        if(ReflectionHacks.getPrivate(mo, AbstractMonster.class, "isMultiDmg")) {
            result += TEXT[2];
            result += ReflectionHacks.getPrivate(mo, AbstractMonster.class, "intentMultiAmt");
            result += TEXT[3];
        } else {
            result += TEXT[4];
        }

        return result;
    }

    @Override
    public float updateVFXInInterval(AbstractMonster mo, ArrayList<AbstractGameEffect> intentVfx) {
        AbstractGameEffect sb = new DebuffParticleEffect(mo.intentHb.cX, mo.intentHb.cY);
        sb.renderBehind = false;
        intentVfx.add(sb);

        return 2.0F;
    }

    static {
        uiStrings = CardCrawlGame.languagePack.getUIString(ID);
        TEXT = uiStrings.TEXT;
    }
}