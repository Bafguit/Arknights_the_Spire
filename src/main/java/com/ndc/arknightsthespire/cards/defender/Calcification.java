package com.ndc.arknightsthespire.cards.defender;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.DexterityPower;
import com.megacrit.cardcrawl.powers.RegenPower;
import com.ndc.arknightsthespire.CardColors;
import com.ndc.arknightsthespire.SPHandler;
import com.ndc.arknightsthespire.actions.ApplyDefAction;
import com.ndc.arknightsthespire.cards.base.CardSPBase;
import com.ndc.arknightsthespire.cards.base.PositionType;
import com.ndc.arknightsthespire.power.ShadowRaidPower;

public class Calcification extends CardSPBase {
    public static final String ID = "ats:Calcification";
    public static final String IMG_PATH = "img/cards/Calcification.png";
    public static final PositionType POSITION = PositionType.DEFENDER;
    private static final int COST = 1;
    private static final int BLOCK = 3;
    private static final int UP_BLOCK = 3;
    private static final int DEX = 1;

    public Calcification() {
        super(ID, IMG_PATH, COST,
                CardType.SKILL, CardColors.AbstractCardEnum.DOCTOR_COLOR,
                CardRarity.RARE, CardTarget.SELF, POSITION, 0, BLOCK, DEX, 0);
        this.setArm(1);
    }

    @Override
    public void useCard(AbstractPlayer p, AbstractMonster m, boolean isSpJustUsed) {
        ApplyDefAction.applyDef(p, this.arm, 0);
        if(this.upgraded) {
            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new RegenPower(p, this.magicNumber), this.magicNumber, true));
            SPHandler.addSp(this.magicNumber);
        }

    }

    @Override
    public AbstractCard makeCopy() {
        return new Calcification();
    }

    @Override
    public void upgradeCard() {
    }

}
