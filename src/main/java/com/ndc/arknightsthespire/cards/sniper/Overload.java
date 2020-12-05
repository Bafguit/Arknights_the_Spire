package com.ndc.arknightsthespire.cards.sniper;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.ndc.arknightsthespire.CardColors;
import com.ndc.arknightsthespire.cards.base.CardSPBase;
import com.ndc.arknightsthespire.cards.base.PositionType;

public class Overload extends CardSPBase {
    public static final String ID = "ats:Overload";
    public static final String IMG_PATH = "img/cards/overload.png";
    public static final PositionType POSITION = PositionType.SNIPER;
    private static final int COST = 1;
    private static final int ATTACK_DMG = 3;
    private static final int UPGRADE_PLUS_DMG = 1;
    private static final int REPEAT_ATK = 3;
    private static final int DEFAULT_SP = 6;

    public Overload() {
        super(ID, IMG_PATH, COST,
                CardType.ATTACK, CardColors.AbstractCardEnum.DOCTOR_COLOR,
                CardRarity.UNCOMMON, CardTarget.ENEMY, true, POSITION, true);
        this.damage = this.baseDamage = ATTACK_DMG;
        this.magicNumber = this.baseMagicNumber = REPEAT_ATK;
        this.sp = DEFAULT_SP;
        this.baseSP = DEFAULT_SP;

        this.setBackgroundTexture("img/512/sniper_512.png", "img/1024/sniper.png");

        this.setOrbTexture("img/orbs/cost.png", "img/orbs/cost_small.png");

    }

    @Override
    public void useCard(AbstractPlayer p, AbstractMonster m, boolean isSpJustUsed) {
        int repeat = (isSpJustUsed ? 5 : REPEAT_ATK);
        for (int forI = 0; forI < repeat; forI++) {
            AbstractDungeon.actionManager.addToBottom(new com.megacrit.cardcrawl.actions.common.DamageAction(m,
                    new DamageInfo(p, this.damage, this.damageTypeForTurn),
                    AbstractGameAction.AttackEffect.BLUNT_LIGHT, true));
        }
    }

    @Override
    public AbstractCard makeCopy() {
        return new Overload();
    }

    @Override
    public void upgradeCard() {
        this.upgradeDamage(UPGRADE_PLUS_DMG);
    }

}
