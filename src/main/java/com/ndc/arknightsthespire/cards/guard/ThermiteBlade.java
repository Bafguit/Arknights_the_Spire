package com.ndc.arknightsthespire.cards.guard;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
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

public class ThermiteBlade extends CardSPBase {
    public static final String ID = "ats:Thermite Blade";
    public static final String IMG_PATH = "img/cards/ThermiteBlade.png";
    public static final PositionType POSITION = PositionType.GUARD;
    private static final int COST = 1;
    private static final int ATTACK_DMG = 10;
    private static final int UP_DMG = 5;
    private static final int DEFAULT_SP = 5;

    public ThermiteBlade() {
        super(ID, IMG_PATH, COST,
                CardType.ATTACK, CardColors.AbstractCardEnum.DOCTOR_COLOR,
                CardRarity.UNCOMMON, CardTarget.ENEMY, false, POSITION, true, ATTACK_DMG, 0, 0, DEFAULT_SP);
    }
//cat is cute!
    @Override
    public void useCard(AbstractPlayer p, AbstractMonster m, boolean isSpJustUsed) {
        addToBot(new AtsSFX("BLADE"));
        if(!isSpJustUsed) AbstractDungeon.actionManager.addToBottom(new DamageAction(m,
                new DamageInfo(p, this.damage, this.damageTypeForTurn),
                AbstractGameAction.AttackEffect.SLASH_VERTICAL, false, true));
        else AbstractDungeon.actionManager.addToBottom(new LoseHPAction(m, p,
                this.damage * 2, AbstractGameAction.AttackEffect.SLASH_VERTICAL));
    }

    @Override
    public AbstractCard makeCopy() {
        return new ThermiteBlade();
    }

    @Override
    public void upgradeCard() {
        this.upgradeDamage(UP_DMG);
    }

}
