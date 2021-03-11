package com.ndc.arknightsthespire.cards.vanguard;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.ndc.arknightsthespire.CardColors;
import com.ndc.arknightsthespire.SPHandler;
import com.ndc.arknightsthespire.actions.AtsSFX;
import com.ndc.arknightsthespire.cards.base.CardSPBase;
import com.ndc.arknightsthespire.cards.base.PositionType;

public class LeapingHammer extends CardSPBase {
    public static final String ID = "ats:Leaping Hammer";
    public static final String IMG_PATH = "atsImg/cards/LeapHammer.png";
    public static final PositionType POSITION = PositionType.VANGUARD;
    private static final int COST = 1;
    private static final int DAMAGE = 8;
    private static final int UP_DAMAGE = 4;
    private static final int SP = 3;

    public LeapingHammer() {
        super(ID, IMG_PATH, COST,
                CardType.ATTACK, CardColors.AbstractCardEnum.DOCTOR_COLOR,
                CardRarity.COMMON, CardTarget.ENEMY, POSITION, DAMAGE, 0, SP, 0);
    }

    @Override
    public void useCard(AbstractPlayer p, AbstractMonster m, boolean isSpJustUsed) {
        addToBot(new AtsSFX("JUMP"));
        addToBot(new DamageAction(m,
                this.getInfo(), AbstractGameAction.AttackEffect.BLUNT_HEAVY, false, true));
        SPHandler.addSp(this.magicNumber);
    }

    @Override
    public AbstractCard makeCopy() {
        return new LeapingHammer();
    }

    @Override
    public void upgradeCard() {
        this.upgradeDamage(UP_DAMAGE);
    }

}
