package com.ndc.arknightsthespire.cards.vanguard;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.ndc.arknightsthespire.CardColors;
import com.ndc.arknightsthespire.actions.AtsSFX;
import com.ndc.arknightsthespire.actions.DamageAllMute;
import com.ndc.arknightsthespire.cards.base.CardSPBase;
import com.ndc.arknightsthespire.cards.base.PositionType;
import com.ndc.arknightsthespire.character.AtsEnum;

public class SwordRain extends CardSPBase {
    public static final String ID = "ats:Sword Rain";
    public static final String IMG_PATH = "img/cards/SwordRain.png";
    public static final PositionType POSITION = PositionType.VANGUARD;
    private static final int COST = 2;
    private static final int DAMAGE = 11;
    private static final int UP_DAMAGE = 3;
    private static final int SP = 10;
    private static final int UP_SP = 7;

    public SwordRain() {
        super(ID, IMG_PATH, COST,
                CardType.ATTACK, CardColors.AbstractCardEnum.DOCTOR_COLOR,
                CardRarity.UNCOMMON, CardTarget.ALL_ENEMY, false, POSITION, true, DAMAGE, 0, 0, SP);
        this.isMultiDamage = true;
    }

    @Override
    public void useCard(AbstractPlayer p, AbstractMonster m, boolean isSpJustUsed) {
        addToBot(new AtsSFX("SWRODRAIN"));
        AbstractDungeon.actionManager.addToBottom(new DamageAllMute(this.multiDamage, AtsEnum.ARTS,
                AbstractGameAction.AttackEffect.SLASH_VERTICAL, false));
        if(isSpJustUsed) addToBot(new GainEnergyAction(2));
    }

    @Override
    public AbstractCard makeCopy() {
        return new SwordRain();
    }

    @Override
    public void upgradeCard() {
        this.upgradeDamage(UP_DAMAGE);
        this.upgradeSP(UP_SP);
    }

}
