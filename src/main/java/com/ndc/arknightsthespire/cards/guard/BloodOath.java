package com.ndc.arknightsthespire.cards.guard;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.HealAction;
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

public class BloodOath extends CardSPBase {
    public static final String ID = "ats:Blood Oath";
    public static final String IMG_PATH = "img/cards/BloodOath.png";
    public static final PositionType POSITION = PositionType.GUARD;
    private static final int COST = 1;
    private static final int ATTACK_DMG = 8;
    private static final int UP_DMG = 2;
    private static final int DEFAULT_SP = 4;
    private static final int UP_SP = 3;
    private static final int HEAL = 3;
    private static final int UP_HEAL = 2;

    public BloodOath() {
        super(ID, IMG_PATH, COST,
                CardType.ATTACK, CardColors.AbstractCardEnum.DOCTOR_COLOR,
                CardRarity.COMMON, CardTarget.ENEMY, true, POSITION, true, ATTACK_DMG, 0, HEAL, DEFAULT_SP);
        this.setPercentage(1.0F, 1.9F);
        this.tags.add(CardTags.STRIKE);
    }

    @Override
    public void useCard(AbstractPlayer p, AbstractMonster m, boolean isSpJustUsed) {
        addToBot(new AtsSFX("BLADE"));
        AbstractDungeon.actionManager.addToBottom(new com.megacrit.cardcrawl.actions.common.DamageAction(m,
                this.getInfo(),
                AbstractGameAction.AttackEffect.SLASH_DIAGONAL, false, true));
        if(isSpJustUsed) AbstractDungeon.actionManager.addToBottom(new HealAction(p, p, magicNumber));
    }

    @Override
    public AbstractCard makeCopy() {
        return new BloodOath();
    }

    @Override
    public void upgradeCard() {
        this.upgradeMagicNumber(UP_HEAL);
        this.upgradeSP(UP_SP);
        this.upgradePer(1.0F, 2.2F);
    }

}
