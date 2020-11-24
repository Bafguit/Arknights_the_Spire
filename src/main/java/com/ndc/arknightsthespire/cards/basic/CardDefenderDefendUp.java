package com.ndc.arknightsthespire.cards.basic;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.ndc.arknightsthespire.CardColors;
import com.ndc.arknightsthespire.cards.CardSPBase;
import com.ndc.arknightsthespire.cards.PositionType;

public class CardDefenderDefendUp extends CardSPBase {
    public static final String ID = "Defend Up";
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    // Get object containing the strings that are displayed in the game.
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String SP_NAME = cardStrings.EXTENDED_DESCRIPTION[0];
    public static final String SP_DESCRIPTION = cardStrings.EXTENDED_DESCRIPTION[1];
    public static final String IMG_PATH = "img/cards/defend.png";
    public static final PositionType POSITION = PositionType.DEFENDER;
    private static final int COST = 1;
    private static final int BLOCK_AMT = 5;
    private static final int UPGRADE_BLOCK = 3;
    private static final int DEFAULT_SP = 5;

    public CardDefenderDefendUp() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, SP_NAME, SP_DESCRIPTION,
                CardType.SKILL, CardColors.AbstractCardEnum.DOCTOR_COLOR,
                CardRarity.BASIC, CardTarget.SELF, false, POSITION, true);
        this.block = this.baseBlock = BLOCK_AMT;
        this.magicNumber = this.baseMagicNumber = UPGRADE_BLOCK;
        this.sp = this.baseSP = DEFAULT_SP;

        this.setBackgroundTexture("img/512/defender_512.png", "img/1024/defender.png");

        this.setOrbTexture("img/orbs/cost.png", "img/orbs/cost_small.png");

    }

    @Override
    public void useCard(AbstractPlayer p, AbstractMonster m, boolean isSpJustUsed) {
        int block = this.block * (isSpJustUsed ? 2 : 1);
        checkGainBlock(block);
/*        if(!DogmaticField.checkGainBlock()) {
            AbstractDungeon.actionManager.addToBottom(new GainBlockAction(p, p, block));
        }
        else {
            AbstractDungeon.actionManager.addToBottom(new HealAction(p, p, block));
        }*/
        //SP Effect
    }

    @Override
    public AbstractCard makeCopy() {
        return new CardDefenderDefendUp();
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeBlock(UPGRADE_BLOCK);
        }
    }



}
