package com.ndc.arknightsthespire.relics;

import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.CardLibrary;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.relics.Orrery;
import com.megacrit.cardcrawl.rewards.RewardItem;
import com.ndc.arknightsthespire.CardColors;
import com.ndc.arknightsthespire.cards.base.CardSPBase;
import com.ndc.arknightsthespire.cards.base.PositionType;
import com.ndc.arknightsthespire.character.AtsEnum;
import com.ndc.arknightsthespire.character.CharacterDoctor;
import com.ndc.arknightsthespire.util.CardUtil;
import com.ndc.arknightsthespire.util.TextureLoader;

import java.util.ArrayList;
import java.util.Iterator;

import static com.ndc.arknightsthespire.SPHandler.addSp;

public class Arts extends CustomRelic {
    public static final String ID = "ats:Arts";
    private static final Texture IMG = TextureLoader.getTexture("atsImg/relics/Arts.png");
    private static final PositionType POSITION = PositionType.CASTER;
    public CardSPBase spC;

    public Arts() {
        super(ID, IMG, RelicTier.SHOP, LandingSound.FLAT); // this relic is uncommon and sounds magic when you click it
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0]; // DESCRIPTIONS pulls from your localization file
    }

    @Override
    public void onEquip() {
        CardUtil.generateCardReward(CardUtil.getCardPool(POSITION), 3);
        AbstractDungeon.combatRewardScreen.open(this.DESCRIPTIONS[1]);
        AbstractDungeon.getCurrRoom().rewardPopOutTimer = 0.0F;
    }

    @Override
    public AbstractRelic makeCopy() { // always override this method to return a new instance of your relic
        return new Arts();
    }

}