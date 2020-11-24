package com.ndc.arknightsthespire.cards.uncommon;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.DexterityPower;
import com.megacrit.cardcrawl.powers.ThornsPower;
import com.ndc.arknightsthespire.CardColors;
import com.ndc.arknightsthespire.cards.CardSPBase;
import com.ndc.arknightsthespire.cards.PositionType;

public class CardDefenderThorns extends CardSPBase {
    public static final String ID = "Thorns";
    public static final String IMG_PATH = "img/cards/thorns.png";
    public static final PositionType POSITION = PositionType.DEFENDER;
    private static final int COST = 1;
    private static final int THORNS_AMOUNT = 2;
    private static final int UPGRADE_THORNS = 1;

    public CardDefenderThorns() {
        super(ID, IMG_PATH, COST,
                CardType.POWER, CardColors.AbstractCardEnum.DOCTOR_COLOR,
                CardRarity.UNCOMMON, CardTarget.SELF, false, POSITION, false);
        this.magicNumber = this.baseMagicNumber = THORNS_AMOUNT;

        this.setBackgroundTexture("img/512/defender_512.png", "img/1024/defender.png");

        this.setOrbTexture("img/orbs/cost.png", "img/orbs/cost_small.png");

    }

    @Override
    public void useCard(AbstractPlayer p, AbstractMonster m, boolean isSpJustUsed) {
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new ThornsPower(p, magicNumber), magicNumber));
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new DexterityPower(p, 1), 1));
    }

    @Override
    public AbstractCard makeCopy() {
        return new CardDefenderThorns();
    }

    @Override
    public void upgradeCard() {
        this.upgradeMagicNumber(UPGRADE_THORNS);
    }



}
