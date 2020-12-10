package com.ndc.arknightsthespire.cards.guard;

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

public class PowerfulStrikeG extends CardSPBase {
    public static final String ID = "ats:Powerful Strike G";
    public static final String IMG_PATH = "img/cards/ps_g.png";
    public static final PositionType POSITION = PositionType.GUARD;
    private static final int COST = 1;
    private static final int ATTACK_DMG = 6;
    private static final int UP_DMG = 1;
    private static final int DEFAULT_SP = 3;
    private static final int UPGRADE_SP = 2;

    public PowerfulStrikeG() {
        super(ID, IMG_PATH, COST,
                CardType.ATTACK, CardColors.AbstractCardEnum.DOCTOR_COLOR,
                CardRarity.BASIC, CardTarget.ENEMY, true, POSITION, true, ATTACK_DMG, 0, 0, DEFAULT_SP);
    }

    @Override
    public void useCard(AbstractPlayer p, AbstractMonster m, boolean isSpJustUsed) {
        addToBot(new AtsSFX((isSpJustUsed ? "HAWK_H" : "HAWK")));
        AbstractDungeon.actionManager.addToBottom(new com.megacrit.cardcrawl.actions.common.DamageAction(m,
                new DamageInfo(p, this.damage + (isSpJustUsed ? 5 : 0), this.damageTypeForTurn),
                AbstractGameAction.AttackEffect.SLASH_DIAGONAL, false, true));
    }

    @Override
    public AbstractCard makeCopy() {
        return new PowerfulStrikeG();
    }

    @Override
    public void upgradeCard() {
        this.upgradeSP(UPGRADE_SP);
        this.upgradeDamage(UP_DMG);
    }

}
