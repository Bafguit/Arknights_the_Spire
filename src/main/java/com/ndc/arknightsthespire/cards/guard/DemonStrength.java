package com.ndc.arknightsthespire.cards.guard;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.DexterityPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.ndc.arknightsthespire.CardColors;
import com.ndc.arknightsthespire.actions.ApplyDefAction;
import com.ndc.arknightsthespire.cards.base.CardSPBase;
import com.ndc.arknightsthespire.cards.base.PositionType;

public class DemonStrength extends CardSPBase {
    public static final String ID = "ats:Demon Strength";
    public static final String IMG_PATH = "atsImg/cards/DemonStrength.png";
    public static final PositionType POSITION = PositionType.GUARD;
    private static final int COST = 1;
    private static final int STR = 3;
    private static final int UP_STR = 1;
    private static final int DEX = -1;

    public DemonStrength() {
        super(ID, IMG_PATH, COST,
                CardType.POWER, CardColors.AbstractCardEnum.DOCTOR_COLOR,
                CardRarity.UNCOMMON, CardTarget.SELF, POSITION, 0, 0, STR, 0);
        this.setArm(2);
    }

    @Override
    public void useCard(AbstractPlayer p, AbstractMonster m, boolean isSpJustUsed) {
        addToBot(new ApplyPowerAction(p, p, new StrengthPower(p, this.magicNumber), this.magicNumber));
        addToBot(new ApplyPowerAction(p, p, new DexterityPower(p, -1), -1));
    }

    @Override
    public AbstractCard makeCopy() {
        return new DemonStrength();
    }

    @Override
    public void upgradeCard() {
        this.upgradeMagicNumber(UP_STR);
    }



}
