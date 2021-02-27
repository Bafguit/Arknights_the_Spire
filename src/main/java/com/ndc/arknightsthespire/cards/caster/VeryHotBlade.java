package com.ndc.arknightsthespire.cards.caster;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
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

public class VeryHotBlade extends CardSPBase {
    public static final String ID = "ats:Very Hot Blade";
    public static final String IMG_PATH = "img/cards/VeryHotBlade.png";
    public static final PositionType POSITION = PositionType.CASTER;
    private static final int COST = 1;
    private static final int DAMAGE = 8;
    private static final int UP_DAMAGE = 2;
    private static final int SP = 5;
    private static final int UP_SP = 3;

    public VeryHotBlade() {
        super(ID, IMG_PATH, COST,
                CardType.ATTACK, CardColors.AbstractCardEnum.DOCTOR_COLOR,
                CardRarity.COMMON, CardTarget.ENEMY, true, POSITION, true, DAMAGE, 0, 0, SP);
    }

    @Override
    public void useCard(AbstractPlayer p, AbstractMonster m, boolean isSpJustUsed) {
        addToBot(new AtsSFX("DAGGER"));
        AbstractDungeon.actionManager.addToBottom(new com.megacrit.cardcrawl.actions.common.DamageAction(m,
                new DamageInfo(p, (isSpJustUsed ? this.damage + (m.hasPower("ats:Burn") ? m.getPower("ats:Burn").amount : 0) : this.damage), AtsEnum.ARTS),
                AbstractGameAction.AttackEffect.BLUNT_LIGHT, true, true));
        addToBot(new DrawCardAction(1));
    }

    @Override
    public AbstractCard makeCopy() {
        return new VeryHotBlade();
    }

    @Override
    public void upgradeCard() {
        this.upgradeSP(UP_SP);
        this.upgradeDamage(UP_DAMAGE);
    }

}
