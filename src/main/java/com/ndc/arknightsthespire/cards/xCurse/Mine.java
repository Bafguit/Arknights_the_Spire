package com.ndc.arknightsthespire.cards.xCurse;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.LoseHPAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import com.ndc.arknightsthespire.CardColors;
import com.ndc.arknightsthespire.cards.base.CardSPBase;
import com.ndc.arknightsthespire.cards.base.PositionType;
import com.ndc.arknightsthespire.character.AtsEnum;
import com.ndc.arknightsthespire.character.CharacterDoctor;
import com.ndc.arknightsthespire.power.ArmPower;
import com.ndc.arknightsthespire.power.LoseArmPower;

import static com.megacrit.cardcrawl.dungeons.AbstractDungeon.player;

public class Mine extends CardSPBase {
    public static final String ID = "ats:Mine";
    public static final String IMG_PATH = "atsImg/cards/Mine.png";
    public static final PositionType POSITION = PositionType.NONE;
    private static final int COST = 0;

    public Mine() {
        super(ID, IMG_PATH, COST,
                CardType.SKILL, CardColors.AbstractCardEnum.DOCTOR_COLOR,
                CardRarity.SPECIAL, CardTarget.ENEMY, POSITION);
        this.selfRetain = true;
        this.exhaust = true;
    }

    @Override
    public void triggerOnManualDiscard() {
        this.addToBot(new MakeTempCardInHandAction(this.makeCopy()));
        player.discardPile.removeCard(this);
    }

    @Override
    public void useCard(AbstractPlayer p, AbstractMonster m, boolean isSpJustUsed) {
        this.addToBot(new LoseHPAction(m, p, 10, AbstractGameAction.AttackEffect.FIRE));
        this.addToBot(new ApplyPowerAction(m, p, new ArmPower(m, -5), -5));
        this.addToBot(new ApplyPowerAction(m, p, new LoseArmPower(m, -5), -5));
    }

    @Override
    public AbstractCard makeCopy() {
        return new Mine();
    }

    @Override
    public void upgradeCard() {
    }

}
