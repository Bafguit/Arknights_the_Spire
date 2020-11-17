package com.ndc.arknightsthespire.cards.rare;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.ndc.arknightsthespire.CardColors;
import com.ndc.arknightsthespire.cards.CardSPBase;
import com.ndc.arknightsthespire.cards.PositionType;
import com.ndc.arknightsthespire.power.DogmaticField;
import com.ndc.arknightsthespire.power.RapidMagazine;

public class CardMedicDogmaticField extends CardSPBase {
    public static final String ID = "Dogmatic Field";
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    // Get object containing the strings that are displayed in the game.
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/cards/dmf.png";
    public static final PositionType POSITION = PositionType.MEDIC;
    private static final int COST = 3;
    private static final int UPGRADE_COST = 2;

    public CardMedicDogmaticField() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION,
                CardType.POWER, CardColors.AbstractCardEnum.DOCTOR_COLOR,
                CardRarity.RARE, CardTarget.SELF, false, POSITION, false);

        this.setBackgroundTexture("img/512/medic_512.png", "img/1024/medic.png");

        this.setOrbTexture("img/orbs/cost.png", "img/orbs/cost_small.png");

    }

    @Override
    public void useCard(AbstractPlayer p, AbstractMonster m, boolean isSpJustUsed) {
        //Effect
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, m, new DogmaticField(p, p),
                1));

    }

    @Override
    public AbstractCard makeCopy() {
        return new CardMedicDogmaticField();
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeBaseCost(UPGRADE_COST);
        }
    }



}
