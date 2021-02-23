package com.ndc.arknightsthespire.cards.caster;

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

import static com.megacrit.cardcrawl.actions.AbstractGameAction.*;

public class StrikeC extends CardSPBase {
    public static final String ID = "ats:Strike C";
    public static final String IMG_PATH = "img/cards/Strike.png";
    public static final PositionType POSITION = PositionType.CASTER;
    private static final int COST = 1;
    private static final int DAMAGE = 6;
    private static final int UP_DAMAGE = 3;

    public StrikeC() {
        super(ID, IMG_PATH, COST,
                CardType.ATTACK, CardColors.AbstractCardEnum.DOCTOR_COLOR,
                CardRarity.BASIC, CardTarget.ENEMY, POSITION,
                DAMAGE, 0, 0, 0);
        this.tags.add(CardTags.STRIKE);
        this.tags.add(CardTags.STARTER_STRIKE);
        this.setPercentage(1.0F);
    }

    @Override
    public void useCard(AbstractPlayer p, AbstractMonster m, boolean isSpJustUsed) {
        addToBot(new AtsSFX("CASTER"));
        AbstractDungeon.actionManager.addToBottom(new com.megacrit.cardcrawl.actions.common.DamageAction(m,
                this.getInfo(true),
                AttackEffect.LIGHTNING, false, true));
    }

    @Override
    public AbstractCard makeCopy() {
        return new StrikeC();
    }

    @Override
    public void upgradeCard() {
        this.upgradePer(1.5F);
    }

}
