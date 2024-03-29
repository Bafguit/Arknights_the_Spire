package com.ndc.arknightsthespire.cards.medic;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.DexterityPower;
import com.ndc.arknightsthespire.CardColors;
import com.ndc.arknightsthespire.cards.base.CardSPBase;
import com.ndc.arknightsthespire.cards.base.PositionType;
import com.ndc.arknightsthespire.power.DogmaticFieldPower;

public class DogmaticField extends CardSPBase {
    public static final String ID = "ats:Dogmatic Field";
    public static final String IMG_PATH = "atsImg/cards/dmf.png";
    public static final PositionType POSITION = PositionType.MEDIC;
    private static final int COST = 2;

    public DogmaticField() {
        super(ID, IMG_PATH, COST,
                CardType.POWER, CardColors.AbstractCardEnum.DOCTOR_COLOR,
                CardRarity.RARE, CardTarget.SELF, POSITION);
    }

    @Override
    public void useCard(AbstractPlayer p, AbstractMonster m, boolean isSpJustUsed) {
        //Effect
        addToBot(new ApplyPowerAction(p, p, new DogmaticFieldPower(p)));
    }

    @Override
    public AbstractCard makeCopy() {
        return new DogmaticField();
    }

    @Override
    public void upgradeCard() {
        this.isInnate = true;
    }



}
