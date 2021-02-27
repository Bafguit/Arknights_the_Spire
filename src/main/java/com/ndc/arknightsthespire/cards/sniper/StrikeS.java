package com.ndc.arknightsthespire.cards.sniper;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.ndc.arknightsthespire.CardColors;
import com.ndc.arknightsthespire.actions.AtsSFX;
import com.ndc.arknightsthespire.cards.base.CardSPBase;
import com.ndc.arknightsthespire.cards.base.PositionType;

import static com.megacrit.cardcrawl.actions.AbstractGameAction.AttackEffect;

public class StrikeS extends CardSPBase {
    public static final String ID = "ats:Strike S";
    public static final String IMG_PATH = "img/cards/Strike.png";
    public static final PositionType POSITION = PositionType.SNIPER;
    private static final int COST = 1;
    private static final int DAMAGE = 6;
    private static final int UP_DAMAGE = 3;

    public StrikeS() {
        super(ID, IMG_PATH, COST,
                CardType.ATTACK, CardColors.AbstractCardEnum.DOCTOR_COLOR,
                CardRarity.BASIC, CardTarget.ENEMY, POSITION,
                DAMAGE, 0, 0, 0);
        this.tags.add(CardTags.STRIKE);
        this.tags.add(CardTags.STARTER_STRIKE);
    }

    @Override
    public void useCard(AbstractPlayer p, AbstractMonster m, boolean isSpJustUsed) {
        addToBot(new AtsSFX("ARROW_H"));
        AbstractDungeon.actionManager.addToBottom(new com.megacrit.cardcrawl.actions.common.DamageAction(m,
                this.getInfo(), AttackEffect.BLUNT_LIGHT, false, true));
    }

    @Override
    public AbstractCard makeCopy() {
        return new StrikeS();
    }

    @Override
    public void upgradeCard() {
        this.upgradeDamage(UP_DAMAGE);
    }

}
