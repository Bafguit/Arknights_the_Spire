package com.ndc.arknightsthespire.cards.sniper;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import com.ndc.arknightsthespire.CardColors;
import com.ndc.arknightsthespire.actions.ApplyDefAction;
import com.ndc.arknightsthespire.actions.AtsSFX;
import com.ndc.arknightsthespire.cards.base.CardSPBase;
import com.ndc.arknightsthespire.cards.base.PositionType;

public class ArmCrushShot extends CardSPBase {
    public static final String ID = "ats:Armor Crushing Shot";
    public static final String IMG_PATH = "img/cards/acs.png";
    public static final PositionType POSITION = PositionType.SNIPER;
    private static final int COST = 1;
    private static final int ATTACK_DMG = 5;
    private static final int UP_DMG = 2;
    private static final int DEFAULT_SP = 4;
    private static final int VULN = 35;
    private static final int UP_VULN = 15;

    public ArmCrushShot() {
        super(ID, IMG_PATH, COST,
                CardType.ATTACK, CardColors.AbstractCardEnum.DOCTOR_COLOR,
                CardRarity.BASIC, CardTarget.ENEMY, true, POSITION, true, ATTACK_DMG, 0, VULN, DEFAULT_SP);
        this.tags.add(CardTags.STRIKE);
        this.setArm(2);
    }

    @Override
    public void useCard(AbstractPlayer p, AbstractMonster m, boolean isSpJustUsed) {
        addToBot(new AtsSFX((isSpJustUsed ? "ARROW_H" : "ARROW")));
        AbstractDungeon.actionManager.addToBottom(new com.megacrit.cardcrawl.actions.common.DamageAction(m,
                this.getInfo(), AbstractGameAction.AttackEffect.BLUNT_LIGHT, false, true));
        if(isSpJustUsed) ApplyDefAction.applyTurn(m, p, -this.arm, 0);
    }

    @Override
    public AbstractCard makeCopy() {
        return new ArmCrushShot();
    }

    @Override
    public void upgradeCard() {
        this.upgradeArm(1);
        this.upgradeDamage(UP_DMG);
    }

}
