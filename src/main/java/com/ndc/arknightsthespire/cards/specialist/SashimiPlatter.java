package com.ndc.arknightsthespire.cards.specialist;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.HealAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.ndc.arknightsthespire.CardColors;
import com.ndc.arknightsthespire.actions.AtsSFX;
import com.ndc.arknightsthespire.cards.base.CardSPBase;
import com.ndc.arknightsthespire.cards.base.PositionType;

public class SashimiPlatter extends CardSPBase {
    public static final String ID = "ats:Sashimi Platter";
    public static final String IMG_PATH = "img/cards/Sashimi.png";
    public static final PositionType POSITION = PositionType.SPECIALIST;
    private static final int COST = 1;
    private static final int ATTACK_DMG = 5;
    private static final int UP_DMG = 2;
    private static final int HEAL = 40;
    private static final int UP_HEAL = 10;

    public SashimiPlatter() {
        super(ID, IMG_PATH, COST,
                CardType.ATTACK, CardColors.AbstractCardEnum.DOCTOR_COLOR,
                CardRarity.UNCOMMON, CardTarget.ENEMY, false, POSITION, false, ATTACK_DMG, 0, HEAL, 0);
        this.setPercentage(1.5F);
    }

    @Override
    public void useCard(AbstractPlayer p, AbstractMonster m, boolean isSpJustUsed) {
        addToBot(new AtsSFX("BLADE"));
        AbstractDungeon.actionManager.addToBottom(new com.megacrit.cardcrawl.actions.common.DamageAction(m,
                this.getInfo(), AbstractGameAction.AttackEffect.SLASH_HORIZONTAL, false));
        AbstractDungeon.actionManager.addToBottom(new HealAction(p, p, m.lastDamageTaken * (this.magicNumber / 100)));
    }

    @Override
    public AbstractCard makeCopy() {
        return new SashimiPlatter();
    }

    @Override
    public void upgradeCard() {
        this.upgradePer(1.6F);
        this.upgradeMagicNumber(UP_HEAL);
    }

}
