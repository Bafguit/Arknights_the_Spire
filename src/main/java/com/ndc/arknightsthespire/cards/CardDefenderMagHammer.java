package com.ndc.arknightsthespire.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.ndc.arknightsthespire.CardColors;

public class CardDefenderMagHammer extends CardSPBase {
    public static final String ID = "Magnetic Hammer";
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    // Get object containing the strings that are displayed in the game.
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/cards/defend.png";
    public static final String CLASS = "DEFENDER";
    private static final int COST = 2;
    private static final int DAMAGE = 7;
    private static final int DIFF_STRENGTH = -2;
    private static final int UP_STRENGTH = -1;
    private static final int DEFAULT_SP = 8;

    public CardDefenderMagHammer() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION,
                CardType.ATTACK, CardColors.AbstractCardEnum.DOCTOR_COLOR,
                CardRarity.UNCOMMON, CardTarget.ALL_ENEMY, false, CLASS, true);
        this.damage = this.baseDamage = DAMAGE;
        this.magicNumber = this.baseMagicNumber = DIFF_STRENGTH;
        this.sp = this.baseSP = DEFAULT_SP;

        this.setBackgroundTexture("img/512/skill_defender.png", "img/1024/skill_defender.png");

        this.setOrbTexture("img/orbs/cost.png", "img/orbs/cost_small.png");

    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {

        for (final AbstractMonster mo : AbstractDungeon.getCurrRoom().monsters.monsters) {
            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(mo, p,
                new StrengthPower(mo, magicNumber), magicNumber));
        }

        //SP
    }

    @Override
    public AbstractCard makeCopy() {
        return new CardDefenderMagHammer();
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeMagicNumber(UP_STRENGTH);
        }
    }



}
