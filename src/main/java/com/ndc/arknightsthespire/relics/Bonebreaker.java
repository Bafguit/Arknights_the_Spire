package com.ndc.arknightsthespire.relics;

import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.ndc.arknightsthespire.util.TextureLoader;

public class Bonebreaker extends CustomRelic {
    public static final String ID = "Bonebreaker";
    private static final Texture IMG = TextureLoader.getTexture("img/relics/Bonebreaker.png");

    public Bonebreaker() {
        super(ID, IMG, RelicTier.UNCOMMON, LandingSound.SOLID); // this relic is uncommon and sounds magic when you click it
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0] + 2 + DESCRIPTIONS[1]; // DESCRIPTIONS pulls from your localization file
    }

    @Override
    public void onAttack(DamageInfo info, int damageAmount, AbstractCreature target) {

        if(damageAmount <= target.currentBlock && info.owner.isPlayer && !target.isPlayer)
        {
            flash();
            onAttackToChangeDamage(info, damageAmount + 2);
        }

    }

    @Override
    public AbstractRelic makeCopy() { // always override this method to return a new instance of your relic
        return new Bonebreaker();
    }

}