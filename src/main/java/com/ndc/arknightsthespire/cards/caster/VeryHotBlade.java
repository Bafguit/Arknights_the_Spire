package com.ndc.arknightsthespire.cards.caster;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.actions.common.LoseHPAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.ndc.arknightsthespire.CardColors;
import com.ndc.arknightsthespire.actions.AtsSFX;
import com.ndc.arknightsthespire.cards.base.CardSPBase;
import com.ndc.arknightsthespire.cards.base.PositionType;
import com.ndc.arknightsthespire.character.AtsEnum;
import com.ndc.arknightsthespire.power.BurnPower;

public class VeryHotBlade extends CardSPBase {
    public static final String ID = "ats:Very Hot Blade";
    public static final String IMG_PATH = "atsImg/cards/VeryHotBlade.png";
    public static final PositionType POSITION = PositionType.CASTER;
    private static final int COST = 1;
    private static final int DAMAGE = 3;
    private static final int UP_DAMAGE = 1;
    private static final int SP = 5;

    public VeryHotBlade() {
        super(ID, IMG_PATH, COST,
                CardType.ATTACK, CardColors.AbstractCardEnum.DOCTOR_COLOR,
                CardRarity.COMMON, CardTarget.ENEMY, true, POSITION, true, DAMAGE, 0, 0, SP);
    }

    @Override
    public void useCard(AbstractPlayer p, AbstractMonster m, boolean isSpJustUsed) {
        for(int i = 0; i < 3; i++) {
            addToBot(new AtsSFX("DAGGER"));
            addToBot(new DamageAction(m,
                    new DamageInfo(p, this.damage, DamageInfo.DamageType.NORMAL),
                    AbstractGameAction.AttackEffect.BLUNT_LIGHT, true, true));
        }
        if(isSpJustUsed) {
            if(m.hasPower(BurnPower.POWER_ID)) {
                addToBot(new AtsSFX("DAGGER"));
                addToBot(new LoseHPAction(m, p, m.getPower(BurnPower.POWER_ID).amount, AbstractGameAction.AttackEffect.BLUNT_HEAVY));
                addToBot(new GainEnergyAction(1));
            }
        }
        addToBot(new DrawCardAction(1));
    }

    @Override
    public AbstractCard makeCopy() {
        return new VeryHotBlade();
    }

    @Override
    public void upgradeCard() {
        this.upgradeDamage(UP_DAMAGE);
    }

}
