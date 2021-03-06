package com.ndc.arknightsthespire.cards.specialist;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.DexterityPower;
import com.ndc.arknightsthespire.CardColors;
import com.ndc.arknightsthespire.actions.ApplyDefAction;
import com.ndc.arknightsthespire.cards.base.CardSPBase;
import com.ndc.arknightsthespire.cards.base.PositionType;
import com.ndc.arknightsthespire.power.ShadowRaidPower;

public class ShadowRaid extends CardSPBase {
    public static final String ID = "ats:Shadow Raid";
    public static final String IMG_PATH = "atsImg/cards/ShadowRaid.png";
    public static final PositionType POSITION = PositionType.SPECIALIST;
    private static final int COST = 0;
    private static final int DEX = 7;
    private static final int UP_DEX = 3;

    public ShadowRaid() {
        super(ID, IMG_PATH, COST,
                CardType.SKILL, CardColors.AbstractCardEnum.DOCTOR_COLOR,
                CardRarity.UNCOMMON, CardTarget.SELF, POSITION, 0, 0, DEX, 0);
        this.exhaust = true;
        this.selfRetain = true;
    }

    @Override
    public void useCard(AbstractPlayer p, AbstractMonster m, boolean isSpJustUsed) {
        addToBot(new ApplyPowerAction(p, p, new DexterityPower(p, this.magicNumber), this.magicNumber));
        addToBot(new ApplyPowerAction(p, p, new ShadowRaidPower(p, p, this.magicNumber), this.magicNumber));
    }

    @Override
    public AbstractCard makeCopy() {
        return new ShadowRaid();
    }

    @Override
    public void upgradeCard() {
        this.upgradeMagicNumber(UP_DEX);
    }

}
