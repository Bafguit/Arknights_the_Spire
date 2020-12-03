package com.ndc.arknightsthespire.cards.rare;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.ndc.arknightsthespire.CardColors;
import com.ndc.arknightsthespire.cards.CardSPBase;
import com.ndc.arknightsthespire.cards.PositionType;
import com.ndc.arknightsthespire.power.Ambush;
import com.ndc.arknightsthespire.power.Neurotoxin;

public class CardSniperAmbush extends CardSPBase {
    public static final String ID = "ats:Ambush";
    public static final String IMG_PATH = "img/cards/Ambush.png";
    public static final PositionType POSITION = PositionType.SNIPER;
    private static final int COST = 1;
    private static final int EVADE = 40;
    private static final int UP_EVADE = 20;

    public CardSniperAmbush() {
        super(ID, IMG_PATH, COST,
                CardType.POWER, CardColors.AbstractCardEnum.DOCTOR_COLOR,
                CardRarity.RARE, CardTarget.SELF, false, POSITION, false);
        this.magicNumber = this.baseMagicNumber = EVADE;

        this.setBackgroundTexture("img/512/sniper_512.png", "img/1024/sniper.png");

        this.setOrbTexture("img/orbs/cost.png", "img/orbs/cost_small.png");

    }

    @Override
    public void useCard(AbstractPlayer p, AbstractMonster m, boolean isSpJustUsed) {
        addToBot(new ApplyPowerAction(p, p, new Ambush(p, p, this.magicNumber)));
    }

    @Override
    public AbstractCard makeCopy() {
        return new CardSniperAmbush();
    }

    @Override
    public void upgradeCard() {
        this.upgradeMagicNumber(UP_EVADE);
        this.isInnate = true;
    }

}
