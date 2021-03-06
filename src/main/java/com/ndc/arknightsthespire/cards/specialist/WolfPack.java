package com.ndc.arknightsthespire.cards.specialist;

import com.evacipated.cardcrawl.mod.stslib.actions.common.StunMonsterAction;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.ndc.arknightsthespire.CardColors;
import com.ndc.arknightsthespire.actions.AtsSFX;
import com.ndc.arknightsthespire.actions.DamageAllMute;
import com.ndc.arknightsthespire.cards.base.CardSPBase;
import com.ndc.arknightsthespire.cards.base.PositionType;
import com.ndc.arknightsthespire.character.AtsEnum;

public class WolfPack extends CardSPBase {
    public static final String ID = "ats:Wolf Pack";
    public static final String IMG_PATH = "atsImg/cards/WolfPack.png";
    public static final PositionType POSITION = PositionType.SPECIALIST;
    private static final int COST = 3;
    private static final int DAMAGE = 1;
    private static final int UP_DAMAGE = 6;

    public WolfPack() {
        super(ID, IMG_PATH, COST,
                CardType.ATTACK, CardColors.AbstractCardEnum.DOCTOR_COLOR,
                CardRarity.RARE, CardTarget.ALL_ENEMY, POSITION, DAMAGE, 0, 0, 0);
        this.exhaust = true;
        this.selfRetain = true;
        this.isMultiDamage = true;
    }

    @Override
    public void useCard(AbstractPlayer p, AbstractMonster m, boolean isSpJustUsed) {
        addToBot(new AtsSFX("WOLF"));
        AbstractDungeon.actionManager.addToBottom(new DamageAllMute(
                this.multiDamage, DamageInfo.DamageType.NORMAL,
                AbstractGameAction.AttackEffect.LIGHTNING, true));
        for (final AbstractMonster mo : AbstractDungeon.getCurrRoom().monsters.monsters) {
            addToBot(new StunMonsterAction(mo, p, 1));
        }
    }

    @Override
    public AbstractCard makeCopy() {
        return new WolfPack();
    }

    @Override
    public void upgradeCard() {
        this.upgradeDamage(UP_DAMAGE);
    }

}
