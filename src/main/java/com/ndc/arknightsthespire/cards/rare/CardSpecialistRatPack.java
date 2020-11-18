package com.ndc.arknightsthespire.cards.rare;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ExhaustAction;
import com.megacrit.cardcrawl.actions.common.ExhaustSpecificCardAction;
import com.megacrit.cardcrawl.actions.common.HealAction;
import com.megacrit.cardcrawl.actions.utility.ExhaustToHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.cardManip.ExhaustCardEffect;
import com.ndc.arknightsthespire.CardColors;
import com.ndc.arknightsthespire.SPHandler;
import com.ndc.arknightsthespire.cards.CardSPBase;
import com.ndc.arknightsthespire.cards.PositionType;
import com.ndc.arknightsthespire.power.DogmaticField;

import javax.smartcardio.Card;
import java.sql.Time;

public class CardSpecialistRatPack extends CardSPBase {
    public static final String ID = "Rat Pack";
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    // Get object containing the strings that are displayed in the game.
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/cards/skill_beta.png";
    public static final PositionType POSITION = PositionType.SPECIALIST;
    private static final int COST = 3;
    private static final int UP_COST = 2;

    public CardSpecialistRatPack() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION,
                CardType.SKILL, CardColors.AbstractCardEnum.DOCTOR_COLOR,
                CardRarity.RARE, CardTarget.SELF, false, POSITION, false);

        this.setBackgroundTexture("img/512/specialist_512.png", "img/1024/specialist.png");

        this.setOrbTexture("img/orbs/cost.png", "img/orbs/cost_small.png");

    }

    @Override
    public void useCard(AbstractPlayer p, AbstractMonster m, boolean isSpJustUsed) {
        DogmaticField.checkGainBlock(AbstractDungeon.player.currentHealth);
    }

    @Override
    public AbstractCard makeCopy() {
        return new CardSpecialistRatPack();
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeBaseCost(UP_COST);
        }
    }

}
