package com.ndc.arknightsthespire.cards.vanguard;

import com.megacrit.cardcrawl.actions.common.DamageAction;
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

public class StrikeV extends CardSPBase {
    public static final String ID = "ats:Strike V";
    public static final String IMG_PATH = "atsImg/cards/Strike.png";
    public static final PositionType POSITION = PositionType.VANGUARD;
    private static final int COST = 1;
    private static final int DAMAGE = 6;
    private static final int UP_DAMAGE = 3;

    public StrikeV() {
        super(ID, IMG_PATH, COST,
                CardType.ATTACK, CardColors.AbstractCardEnum.DOCTOR_COLOR,
                CardRarity.BASIC, CardTarget.ENEMY, POSITION,
                DAMAGE, 0, 0, 0);
        this.tags.add(CardTags.STRIKE);
        this.tags.add(CardTags.STARTER_STRIKE);
    }

    @Override
    public void useCard(AbstractPlayer p, AbstractMonster m, boolean isSpJustUsed) {
        addToBot(new AtsSFX("SPEAR"));
        AbstractDungeon.actionManager.addToBottom(new DamageAction(m,
                this.getInfo(), AttackEffect.SLASH_DIAGONAL, false, true));
    }

    @Override
    public AbstractCard makeCopy() {
        return new StrikeV();
    }

    @Override
    public void upgradeCard() {
        this.upgradeDamage(UP_DAMAGE);
    }

}
