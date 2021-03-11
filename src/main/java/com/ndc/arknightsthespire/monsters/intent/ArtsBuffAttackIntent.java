package com.ndc.arknightsthespire.monsters.intent;

import actlikeit.RazIntent.AssetLoader;
import actlikeit.RazIntent.CustomIntent;
import basemod.ReflectionHacks;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.UIStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import com.megacrit.cardcrawl.vfx.DebuffParticleEffect;
import com.megacrit.cardcrawl.vfx.combat.BuffParticleEffect;
import com.ndc.arknightsthespire.character.AtsEnum;
import com.ndc.arknightsthespire.util.TextureLoader;

import java.util.ArrayList;

public class ArtsBuffAttackIntent extends CustomIntent {
    public static final String ID = "ArtsAttack";

    private static final UIStrings uiStrings;
    private static final String[] TEXT;

    public ArtsBuffAttackIntent() {
        super(AtsEnum.ATTACK_ARTS_BUFF, TEXT[5], "atsImg/ui/intent/art.png", "atsImg/ui/intent/arts_tip.png");
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
        AbstractGameEffect sb = new BuffParticleEffect(mo.intentHb.cX, mo.intentHb.cY);
        sb.renderBehind = false;
        intentVfx.add(sb);

        return 2.0F;
    }

    static {
        uiStrings = CardCrawlGame.languagePack.getUIString(ID);
        TEXT = uiStrings.TEXT;
    }
}