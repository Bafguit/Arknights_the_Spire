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
import com.ndc.arknightsthespire.power.ShadowRaidPower;

public class AutoCover extends CardSPBase {
    public static final String ID = "ats:Automatic Cover";
    public static final String IMG_PATH = "img/cards/AutomaticCover.png";
    public static final PositionType POSITION = PositionType.MEDIC;
    private static final int COST = 1;
    private static final int BLOCK = 5;
    private static final int UP_BLOCK = 3;
    private static final int DEX = 1;
    private static final int UP_DEX = 1;

    public AutoCover() {
        super(ID, IMG_PATH, COST,
                CardType.SKILL, CardColors.AbstractCardEnum.DOCTOR_COLOR,
                CardRarity.UNCOMMON, CardTarget.SELF, POSITION, 0, BLOCK, DEX, 0);
    }

    @Override
    public void useCard(AbstractPlayer p, AbstractMonster m, boolean isSpJustUsed) {
        checkGainBlock(this.block);
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new DexterityPower(p, this.magicNumber), this.magicNumber));
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new ShadowRaidPower(p, p, this.magicNumber), this.magicNumber));

    }

    @Override
    public AbstractCard makeCopy() {
        return new AutoCover();
    }

    @Override
    public void upgradeCard() {
        this.upgradeBlock(UP_BLOCK);
        this.upgradeMagicNumber(UP_DEX);
    }

}
