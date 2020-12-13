package com.ndc.arknightsthespire.cards.defender;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.DexterityPower;
import com.megacrit.cardcrawl.powers.ThornsPower;
import com.ndc.arknightsthespire.CardColors;
import com.ndc.arknightsthespire.cards.base.CardSPBase;
import com.ndc.arknightsthespire.cards.base.PositionType;

public class Thorns extends CardSPBase {
    public static final String ID = "ats:Thorns";
    public static final String IMG_PATH = "img/cards/thorns.png";
    public static final PositionType POSITION = PositionType.DEFENDER;
    private static final int COST = 1;
    private static final int THORNS_AMOUNT = 2;
    private static final int UPGRADE_THORNS = 1;

    public Thorns() {
        super(ID, IMG_PATH, COST,
                CardType.POWER, CardColors.AbstractCardEnum.DOCTOR_COLOR,
                CardRarity.UNCOMMON, CardTarget.SELF, POSITION, 0, 0, THORNS_AMOUNT, 0);
    }

    @Override
    public void useCard(AbstractPlayer p, AbstractMonster m, boolean isSpJustUsed) {
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new ThornsPower(p, this.magicNumber), this.magicNumber));
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new DexterityPower(p, this.upgraded ? 2 : 1), this.upgraded ? 2 : 1));
    }

    @Override
    public AbstractCard makeCopy() {
        return new Thorns();
    }

    @Override
    public void upgradeCard() {
        this.upgradeMagicNumber(UPGRADE_THORNS);
    }



}
