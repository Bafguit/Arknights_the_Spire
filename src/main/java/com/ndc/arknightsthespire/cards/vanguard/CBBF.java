package com.ndc.arknightsthespire.cards.vanguard;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.ndc.arknightsthespire.CardColors;
import com.ndc.arknightsthespire.actions.AtsSFX;
import com.ndc.arknightsthespire.actions.KillVanguard;
import com.ndc.arknightsthespire.cards.base.CardSPBase;
import com.ndc.arknightsthespire.cards.base.PositionType;

public class CBBF extends CardSPBase {
    public static final String ID = "ats:Closed Bolt Burst Fire";
    public static final String IMG_PATH = "img/cards/CBBF.png";
    public static final PositionType POSITION = PositionType.VANGUARD;
    private static final int COST = 2;
    private static final int DAMAGE = 6;
    private static final int UP_DAMAGE = 1;

    public CBBF() {
        super(ID, IMG_PATH, COST,
                CardType.ATTACK, CardColors.AbstractCardEnum.DOCTOR_COLOR,
                CardRarity.COMMON, CardTarget.ENEMY, POSITION, DAMAGE, 0, 0, 0);
    }

    @Override
    public void useCard(AbstractPlayer p, AbstractMonster m, boolean isSpJustUsed) {
        addToBot(new AtsSFX("BAGPIPE"));
        addToBot(new KillVanguard(m, this.getInfo(),
                AbstractGameAction.AttackEffect.SLASH_HORIZONTAL, 2, false, true, 3));
    }

    @Override
    public AbstractCard makeCopy() {
        return new CBBF();
    }

    @Override
    public void upgradeCard() {
        this.upgradeDamage(UP_DAMAGE);
    }

}
