package com.ndc.arknightsthespire.cards.sniper;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.ndc.arknightsthespire.CardColors;
import com.ndc.arknightsthespire.actions.AtsSFX;
import com.ndc.arknightsthespire.cards.base.CardSPBase;
import com.ndc.arknightsthespire.cards.base.PositionType;
import com.ndc.arknightsthespire.power.RapidMagPower;

public class RapidMagazine extends CardSPBase {
    public static final String ID = "ats:Rapid Magazine";
    public static final String IMG_PATH = "atsImg/cards/RapidMagazine.png";
    public static final PositionType POSITION = PositionType.SNIPER;
    private static final int COST = 1;

    public RapidMagazine() {
        super(ID, IMG_PATH, COST,
                CardType.POWER, CardColors.AbstractCardEnum.DOCTOR_COLOR,
                CardRarity.UNCOMMON, CardTarget.SELF, POSITION);
    }

    @Override
    public void useCard(AbstractPlayer p, AbstractMonster m, boolean isSpJustUsed) {
        addToBot(new AtsSFX("SKY"));
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, m, new RapidMagPower(p, p, 2)));
    }

    @Override
    public AbstractCard makeCopy() {
        return new RapidMagazine();
    }

    @Override
    public void upgradeCard() {
        this.isInnate = true;
    }

}
