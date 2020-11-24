package com.ndc.arknightsthespire.cards.uncommon;

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
import com.ndc.arknightsthespire.power.RapidMagazine;

public class CardSniperRapidMagazine extends CardSPBase {
    public static final String ID = "Rapid Magazine";
    public static final String IMG_PATH = "img/cards/RapidMagazine.png";
    public static final PositionType POSITION = PositionType.SNIPER;
    private static final int COST = 2;
    private static final int UP_COST = 1;

    public CardSniperRapidMagazine() {
        super(ID, IMG_PATH, COST,
                CardType.POWER, CardColors.AbstractCardEnum.DOCTOR_COLOR,
                CardRarity.UNCOMMON, CardTarget.SELF, true, POSITION, false);

        this.setBackgroundTexture("img/512/sniper_512.png", "img/1024/sniper_512.png");

        this.setOrbTexture("img/orbs/cost.png", "img/orbs/cost_small.png");

    }

    @Override
    public void useCard(AbstractPlayer p, AbstractMonster m, boolean isSpJustUsed) {

        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, m, new RapidMagazine(p, p, 1),
                1));


}
    @Override
    public AbstractCard makeCopy() {
        return new CardSniperRapidMagazine();
    }

    @Override
    public void upgradeCard() {
        this.upgradeBaseCost(UP_COST);
    }

}
