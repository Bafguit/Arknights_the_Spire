package com.ndc.arknightsthespire.cards.basic;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.ndc.arknightsthespire.CardColors;
import com.ndc.arknightsthespire.cards.CardSPBase;
import com.ndc.arknightsthespire.SPHandler;
import com.ndc.arknightsthespire.cards.PositionType;

public class CardSniperPowerfulStrike extends CardSPBase {
    public static final String ID = "Powerful Strike";
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    // Get object containing the strings that are displayed in the game.
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/cards/strike.png";
    public static final PositionType POSITION = PositionType.SNIPER;
    private static final int COST = 1;
    private static final int ATTACK_DMG = 7;
    private static final int DEFAULT_SP = 3;
    private static final int UPGRADE_SP = 2;

    public CardSniperPowerfulStrike() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION,
                CardType.ATTACK, CardColors.AbstractCardEnum.DOCTOR_COLOR,
                CardRarity.BASIC, CardTarget.ENEMY, true, POSITION, true);
        this.damage = this.baseDamage = ATTACK_DMG;
        this.sp = DEFAULT_SP;
        this.baseSP = DEFAULT_SP - SPHandler.getDiffSp();

        this.setBackgroundTexture("img/512/atk_sniper.png", "img/1024/atk_sniper_large.png");

        this.setOrbTexture("img/orbs/cost.png", "img/orbs/cost_small.png");

    }

    @Override
    public void useCard(AbstractPlayer p, AbstractMonster m, boolean isSpJustUsed) {
        int damage = this.damage * (isSpJustUsed ? 2 : 1);
        AbstractDungeon.actionManager.addToBottom(new com.megacrit.cardcrawl.actions.common.DamageAction(m,
                new DamageInfo(p, damage, this.damageTypeForTurn),
                AbstractGameAction.AttackEffect.BLUNT_HEAVY));
    }

    @Override
    public AbstractCard makeCopy() {
        return new CardSniperPowerfulStrike();
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeSP(UPGRADE_SP);
        }
    }

}
