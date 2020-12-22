package com.ndc.arknightsthespire.cards.specialist;

import com.megacrit.cardcrawl.actions.common.DiscardAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.ndc.arknightsthespire.CardColors.AbstractCardEnum;
import com.ndc.arknightsthespire.actions.AtsSFX;
import com.ndc.arknightsthespire.cards.base.CardSPBase;
import com.ndc.arknightsthespire.cards.base.PositionType;

public class SteamPump extends CardSPBase {
    public static final String ID = "ats:Steam Pump";
    public static final String IMG_PATH = "img/cards/SteamPump.png";
    public static final PositionType POSITION = PositionType.SPECIALIST;
    private static final int COST = 1;
    private static final int B = 8;
    private static final int U_B = 4;
    private static final int M = 1;
    private static final int U_M = 1;

    public SteamPump() {
        super(ID, IMG_PATH, COST,
                CardType.SKILL, AbstractCardEnum.DOCTOR_COLOR,
                CardRarity.UNCOMMON, CardTarget.SELF, POSITION, 0, B, M, 0);
    }

    @Override
    public void useCard(AbstractPlayer p, AbstractMonster m, boolean isSpJustUsed) {
        addToBot(new DiscardAction(p, p, this.magicNumber, false));
        addToBot(new AtsSFX("PUMP"));
        checkGainBlock(this.block);
    }

    @Override
    public AbstractCard makeCopy() {
        return new SteamPump();
    }

    @Override
    public void upgradeCard() {
        this.upgradeBlock(U_B);
        this.upgradeMagicNumber(U_M);
    }

}
