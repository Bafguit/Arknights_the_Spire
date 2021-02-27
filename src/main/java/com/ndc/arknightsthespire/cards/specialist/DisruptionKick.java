package com.ndc.arknightsthespire.cards.specialist;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.unique.RemoveAllPowersAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.ndc.arknightsthespire.CardColors;
import com.ndc.arknightsthespire.actions.AtsSFX;
import com.ndc.arknightsthespire.actions.DamageAllMute;
import com.ndc.arknightsthespire.actions.DisruptionKickAction;
import com.ndc.arknightsthespire.cards.base.CardSPBase;
import com.ndc.arknightsthespire.cards.base.PositionType;
import com.ndc.arknightsthespire.character.AtsEnum;

public class DisruptionKick extends CardSPBase {
    public static final String ID = "ats:Disruption Kick";
    public static final String IMG_PATH = "img/cards/DisruptionKick.png";
    public static final PositionType POSITION = PositionType.SPECIALIST;
    private static final int COST = 3;
    private static final int UP_COST = 2;

    public DisruptionKick() {
        super(ID, IMG_PATH, COST,
                CardType.ATTACK, CardColors.AbstractCardEnum.DOCTOR_COLOR,
                CardRarity.RARE, CardTarget.ALL_ENEMY, false, POSITION, false, 1, 0, 0, 0);
        this.exhaust = true;
        this.selfRetain = true;
        this.isMultiDamage = true;
    }

    @Override
    public void useCard(AbstractPlayer p, AbstractMonster m, boolean isSpJustUsed) {
        addToBot(new AtsSFX("TIGER"));
        AbstractDungeon.actionManager.addToBottom(new DamageAllMute(this.multiDamage, AtsEnum.PHYS,
                AbstractGameAction.AttackEffect.LIGHTNING, true));
        for (final AbstractMonster mo : AbstractDungeon.getCurrRoom().monsters.monsters) {
            addToBot(new DisruptionKickAction(mo));
        }
    }

    @Override
    public AbstractCard makeCopy() {
        return new DisruptionKick();
    }

    @Override
    public void upgradeCard() {
        this.upgradeBaseCost(UP_COST);
    }

}
