package com.ndc.arknightsthespire.cards.sniper;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.ndc.arknightsthespire.CardColors;
import com.ndc.arknightsthespire.actions.AtsSFX;
import com.ndc.arknightsthespire.cards.base.CardSPBase;
import com.ndc.arknightsthespire.cards.base.PositionType;

public class ConShotAuto extends CardSPBase {
    public static final String ID = "ats:Consecutive Shot Auto";
    public static final String IMG_PATH = "atsImg/cards/ConShotAuto.png";
    public static final PositionType POSITION = PositionType.SNIPER;
    private static final int COST = 1;
    private static final int ATTACK_DMG = 5;
    private static final int UPGRADE_PLUS_DMG = 2;

    public ConShotAuto() {
        super(ID, IMG_PATH, COST,
                CardType.ATTACK, CardColors.AbstractCardEnum.DOCTOR_COLOR,
                CardRarity.COMMON, CardTarget.ENEMY, POSITION, ATTACK_DMG, 0, 0, 0);
    }

    @Override
    public void useCard(AbstractPlayer p, AbstractMonster m, boolean isSpJustUsed) {
        for (int forI = 0; forI < 2; forI++) {
            addToBot(new AtsSFX("CROSSBOW"));
            AbstractDungeon.actionManager.addToBottom(new com.megacrit.cardcrawl.actions.common.DamageAction(m,
                    this.getInfo(), AbstractGameAction.AttackEffect.BLUNT_LIGHT, true, false));
        }
    }

    @Override
    public AbstractCard makeCopy() {
        return new ConShotAuto();
    }

    @Override
    public void upgradeCard() {
        this.upgradeDamage(UPGRADE_PLUS_DMG);
    }

}
