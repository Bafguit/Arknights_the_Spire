package com.ndc.arknightsthespire.cards.vanguard;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;
import com.ndc.arknightsthespire.CardColors;
import com.ndc.arknightsthespire.SPHandler;
import com.ndc.arknightsthespire.actions.AtsSFX;
import com.ndc.arknightsthespire.cards.base.CardSPBase;
import com.ndc.arknightsthespire.cards.base.PositionType;

public class FierceGlare extends CardSPBase {
    public static final String ID = "ats:Fierce Glare";
    public static final String IMG_PATH = "atsImg/cards/FierceGlare.png";
    public static final PositionType POSITION = PositionType.VANGUARD;
    private static final int COST = -1;
    private static final int DAMAGE = 8;
    private static final int SP = 20;
    private static final int UP_SP = 10;

    public FierceGlare() {
        super(ID, IMG_PATH, COST,
                CardType.ATTACK, CardColors.AbstractCardEnum.DOCTOR_COLOR,
                CardRarity.RARE, CardTarget.ALL_ENEMY, false, POSITION, true, DAMAGE, 0, 0, SP);
        this.isMultiDamage = true;
    }

    @Override
    public void useCard(AbstractPlayer p, AbstractMonster m, boolean isSpJustUsed) {
        AbstractDungeon.player.energy.use(EnergyPanel.totalCount);
        if(isSpJustUsed) {
            for (int i = 0; i < this.energyOnUse; i++) {
                addToBot(new DamageAllEnemiesAction(p, DamageInfo.createDamageMatrix(SPHandler.getTurnEnergy() + 3), DamageInfo.DamageType.NORMAL, AbstractGameAction.AttackEffect.SMASH));
            }
        } else {
            for (int i = 0; i < this.energyOnUse; i++) {
                addToBot(new DamageAllEnemiesAction(p, this.multiDamage, DamageInfo.DamageType.NORMAL, AbstractGameAction.AttackEffect.SMASH));
            }
        }
    }

    @Override
    public AbstractCard makeCopy() {
        return new FierceGlare();
    }

    @Override
    public void upgradeCard() {
        this.upgradeSP(UP_SP);
    }

}
