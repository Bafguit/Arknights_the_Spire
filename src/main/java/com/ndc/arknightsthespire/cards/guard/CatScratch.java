package com.ndc.arknightsthespire.cards.guard;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.WeakPower;
import com.ndc.arknightsthespire.CardColors;
import com.ndc.arknightsthespire.actions.AtsSFX;
import com.ndc.arknightsthespire.cards.base.CardSPBase;
import com.ndc.arknightsthespire.cards.base.PositionType;
import com.ndc.arknightsthespire.character.AtsEnum;

public class CatScratch extends CardSPBase {
    public static final String ID = "ats:Cat Scratch";
    public static final String IMG_PATH = "img/cards/CatScratch.png";
    public static final PositionType POSITION = PositionType.GUARD;
    private static final int COST = 1;
    private static final int ATTACK_DMG = 8;
    private static final int UP_DMG = 2;
    private static final int DEFAULT_SP = 4;
    private static final int WEAK = 1;
    private static final int UP_WEAK = 1;

    public CatScratch() {
        super(ID, IMG_PATH, COST,
                CardType.ATTACK, CardColors.AbstractCardEnum.DOCTOR_COLOR,
                CardRarity.COMMON, CardTarget.ENEMY, true, POSITION, true, ATTACK_DMG, 0, WEAK, DEFAULT_SP);
        this.tags.add(CardTags.STRIKE);
    }

    @Override
    public void useCard(AbstractPlayer p, AbstractMonster m, boolean isSpJustUsed) {
        addToBot(new AtsSFX((isSpJustUsed ? "PAW_H" : "PAW")));
        addToBot(new DamageAction(m,
                this.getInfo(true),
                AbstractGameAction.AttackEffect.SLASH_DIAGONAL, false, true));
        if(isSpJustUsed) AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(m, p,
                new WeakPower(m, magicNumber, false), magicNumber));
    }

    @Override
    public AbstractCard makeCopy() {
        return new CatScratch();
    }

    @Override
    public void upgradeCard() {
        this.upgradeDamage(UP_DMG);
        this.upgradeMagicNumber(UP_WEAK);
    }

}
