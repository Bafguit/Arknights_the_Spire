package com.ndc.arknightsthespire.relics;

import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.ndc.arknightsthespire.util.TextureLoader;

public class Bonebreaker extends CustomRelic {
    public static final String ID = "ats:Bonebreaker";
    private static final Texture IMG = TextureLoader.getTexture("img/relics/Bonebreaker.png");

    public Bonebreaker() {
        super(ID, IMG, RelicTier.UNCOMMON, LandingSound.SOLID); // this relic is uncommon and sounds magic when you click it
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0] + 2 + DESCRIPTIONS[1]; // DESCRIPTIONS pulls from your localization file
    }

    @Override
    public int onAttackToChangeDamage(DamageInfo info, int damageAmount) {
        if (info.owner != null && info.type == DamageInfo.DamageType.NORMAL && damageAmount < 2) {
            this.flash();
            this.addToBot(new RelicAboveCreatureAction(AbstractDungeon.player, this));
            return 2;
        } else {
            return damageAmount;
        }
    }



    @Override
    public AbstractRelic makeCopy() { // always override this method to return a new instance of your relic
        return new Bonebreaker();
    }

}