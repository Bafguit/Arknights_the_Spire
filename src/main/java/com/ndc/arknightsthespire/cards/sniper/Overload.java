package com.ndc.arknightsthespire.cards.sniper;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.ndc.arknightsthespire.CardColors;
import com.ndc.arknightsthespire.actions.AtsSFX;
import com.ndc.arknightsthespire.cards.base.CardSPBase;
import com.ndc.arknightsthespire.cards.base.PositionType;

public class Overload extends CardSPBase {
    public static final String ID = "ats:Overload";
    public static final String IMG_PATH = "img/cards/AssaultMode.png";
    public static final String SP_IMG_PATH = "img/cards/overload.png";
    public static final PositionType POSITION = PositionType.SNIPER;
    private static final int COST = 1;
    private static final int ATTACK_DMG = 3;
    private static final int UPGRADE_PLUS_DMG = 1;
    private static final int REPEAT_ATK = 3;
    private static final int DEFAULT_SP = 5;

    public Overload() {
        super(ID, IMG_PATH, SP_IMG_PATH, COST,
                CardType.ATTACK, CardColors.AbstractCardEnum.DOCTOR_COLOR,
                CardRarity.UNCOMMON, CardTarget.ENEMY, true, POSITION, true, ATTACK_DMG, 0, REPEAT_ATK, DEFAULT_SP);
        this.setPercentage(0.7F, 0.5F);
    }

    @Override
    public void useCard(AbstractPlayer p, AbstractMonster m, boolean isSpJustUsed) {
        int repeat = (isSpJustUsed ? 5 : REPEAT_ATK);
        addToBot(new AtsSFX((isSpJustUsed ? "SMG_H" : "SMG")));
        for (int forI = 0; forI < repeat; forI++) {
            AbstractDungeon.actionManager.addToBottom(new com.megacrit.cardcrawl.actions.common.DamageAction(m,
                    this.getInfo(), AbstractGameAction.AttackEffect.BLUNT_LIGHT, true, true));
        }
    }

    @Override
    public AbstractCard makeCopy() {
        return new Overload();
    }

    @Override
    public void upgradeCard() {
        this.upgradePer(1.0F, 0.8F);
    }

}
