package com.ndc.arknightsthespire.cards.specialist;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.DiscardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.ndc.arknightsthespire.CardColors.AbstractCardEnum;
import com.ndc.arknightsthespire.actions.AtsSFX;
import com.ndc.arknightsthespire.cards.base.CardSPBase;
import com.ndc.arknightsthespire.cards.base.PositionType;

public class CollapsingStrike extends CardSPBase {
    public static final String ID = "ats:Collapsing Strike";
    public static final String IMG_PATH = "img/cards/CollapsingStrike.png";
    public static final PositionType POSITION = PositionType.SPECIALIST;
    private static final int COST = 1;
    private static final int D = 12;
    private static final int U_D = 4;
    private static final int M = 1;

    public CollapsingStrike() {
        super(ID, IMG_PATH, COST,
                CardType.ATTACK, AbstractCardEnum.DOCTOR_COLOR,
                CardRarity.UNCOMMON, CardTarget.ENEMY, POSITION, D, 0, M, 0);
        this.setPercentage(2.0F);
    }

    @Override
    public void useCard(AbstractPlayer p, AbstractMonster m, boolean isSpJustUsed) {
        addToBot(new DiscardAction(p, p, this.magicNumber, false));
        addToBot(new AtsSFX("PUNCH"));
        addToBot(new DamageAction(m, this.getInfo(), AbstractGameAction.AttackEffect.BLUNT_HEAVY, false, true));
    }

    @Override
    public AbstractCard makeCopy() {
        return new CollapsingStrike();
    }

    @Override
    public void upgradeCard() {
        this.upgradePer(2.5F);
    }

}
