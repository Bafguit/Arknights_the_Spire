package com.ndc.arknightsthespire.cards.supporter;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.ndc.arknightsthespire.CardColors;
import com.ndc.arknightsthespire.actions.AtsSFX;
import com.ndc.arknightsthespire.cards.base.CardSPBase;
import com.ndc.arknightsthespire.cards.base.PositionType;
import com.ndc.arknightsthespire.power.ShelterPower;
import com.ndc.arknightsthespire.power.SongOfBattlePower;

public class SongOfBattle extends CardSPBase {
    public static final String ID = "ats:Song Of Battle";
    public static final String IMG_PATH = "atsImg/cards/SongOfBattle.png";
    public static final PositionType POSITION = PositionType.SUPPORTER;
    private static final int COST = 1;
    private static final int SP = 3;
    private static final int UP_SP = 1;

    public SongOfBattle() {
        super(ID, IMG_PATH, COST,
                CardType.POWER, CardColors.AbstractCardEnum.DOCTOR_COLOR,
                CardRarity.UNCOMMON, CardTarget.SELF, POSITION, 0, 0, SP, 0);
    }

    @Override
    public void useCard(AbstractPlayer p, AbstractMonster m, boolean isSpJustUsed) {
        addToBot(new AtsSFX("BATTLESONG"));
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, m, new SongOfBattlePower(p, p,
                this.magicNumber), this.magicNumber));
    }

    @Override
    public AbstractCard makeCopy() {
        return new SongOfBattle();
    }

    @Override
    public void upgradeCard() {
        this.upgradeMagicNumber(UP_SP);
    }

}
