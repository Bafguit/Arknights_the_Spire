package com.ndc.arknightsthespire.cards.caster;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.ndc.arknightsthespire.CardColors;
import com.ndc.arknightsthespire.actions.AtsSFX;
import com.ndc.arknightsthespire.cards.base.CardSPBase;
import com.ndc.arknightsthespire.cards.base.PositionType;
import com.ndc.arknightsthespire.character.AtsEnum;
import com.ndc.arknightsthespire.power.BurnPower;

import static com.megacrit.cardcrawl.actions.AbstractGameAction.*;
import static com.megacrit.cardcrawl.cards.DamageInfo.*;
import static com.ndc.arknightsthespire.CardColors.*;

public class Ignite extends CardSPBase {
    public static final String ID = "ats:Ignite";
    public static final String IMG_PATH = "img/cards/Ignite.png";
    public static final PositionType POSITION = PositionType.CASTER;
    private static final int COST = 1;
    private static final int DAMAGE = 7;
    private static final int UP_DAMAGE = 2;
    private static final int BURN = 2;
    private static final int UP_BURN = 1;

    public Ignite() {
        super(ID, IMG_PATH, COST,
                CardType.ATTACK, AbstractCardEnum.DOCTOR_COLOR,
                CardRarity.COMMON, CardTarget.ENEMY, POSITION, DAMAGE, 0, BURN, 0);
        this.setPercentage(1.2F);
    }

    @Override
    public void useCard(AbstractPlayer p, AbstractMonster m, boolean isSpJustUsed) {
        addToBot(new AtsSFX("FIRE"));
        addToBot(new DamageAction(m, this.getInfo(true), AttackEffect.FIRE, false, true));
        addToBot(new ApplyPowerAction(m, p, new BurnPower(m, p, this.magicNumber), this.magicNumber));
    }

    @Override
    public AbstractCard makeCopy() {
        return new Ignite();
    }

    @Override
    public void upgradeCard() {
        this.upgradeMagicNumber(UP_BURN);
        this.upgradePer(1.5F);
    }

}
