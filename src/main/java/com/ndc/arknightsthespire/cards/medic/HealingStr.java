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
import com.ndc.arknightsthespire.power.CurativePower;
import com.ndc.arknightsthespire.power.DogmaticFieldPower;

public class HealingStr extends CardSPBase {
    public static final String ID = "ats:Healing Strengthening";
    public static final String IMG_PATH = "img/cards/HealingStr.png";
    public static final PositionType POSITION = PositionType.MEDIC;
    private static final int COST = 1;
    private static final int HEAL = 1;
    private static final int UP_HEAL = 1;

    public HealingStr() {
        super(ID, IMG_PATH, COST,
                CardType.POWER, CardColors.AbstractCardEnum.DOCTOR_COLOR,
                CardRarity.UNCOMMON, CardTarget.SELF, POSITION, 0, 0, HEAL, 0);
    }

    @Override
    public void useCard(AbstractPlayer p, AbstractMonster m, boolean isSpJustUsed) {
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new CurativePower(p, this.magicNumber)));
    }

    @Override
    public AbstractCard makeCopy() {
        return new HealingStr();
    }

    @Override
    public void upgradeCard() {
        this.upgradeMagicNumber(UP_HEAL);
    }



}
