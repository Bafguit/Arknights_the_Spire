package com.ndc.arknightsthespire.cards;

import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.ndc.arknightsthespire.CardColors;

public class CardSniperRapidMagazine extends CardSPBase {
    public static final String ID = "Rapid Magazine";
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    // Get object containing the strings that are displayed in the game.
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/cards/overload.png";
    public static final String CLASS = "SNIPER";
    private static final int COST = 1;

    public CardSniperRapidMagazine() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION,
                CardType.POWER, CardColors.AbstractCardEnum.DOCTOR_COLOR,
                CardRarity.UNCOMMON, CardTarget.SELF, true, CLASS, false);

        this.setBackgroundTexture("img/512/pwr_sniper.png", "img/1024/pwr_sniper.png");

        this.setOrbTexture("img/orbs/cost.png", "img/orbs/cost_small.png");

    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {

        diff_sp += 1;
        this.getSPChange(1);
    }

    @Override
    public AbstractCard makeCopy() {
        return new CardSniperRapidMagazine();
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
        }
    }

}
