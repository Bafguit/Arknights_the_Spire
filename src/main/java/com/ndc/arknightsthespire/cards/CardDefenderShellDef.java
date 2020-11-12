package com.ndc.arknightsthespire.cards;

import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.ndc.arknightsthespire.CardColors;

public class CardDefenderShellDef extends CardSPBase {
    public static final String ID = "Shell Defense";
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    // Get object containing the strings that are displayed in the game.
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/cards/defend.png";
    public static final String CLASS = "DEFENDER";
    private static final int COST = 1;
    private static final int BLOCK_AMT = 8;
    private static final int UPGRADE_BLOCK = 5;
    private static final int DEFAULT_SP = 12;

    public CardDefenderShellDef() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION,
                CardType.SKILL, CardColors.AbstractCardEnum.DOCTOR_COLOR,
                CardRarity.COMMON, CardTarget.SELF, false, CLASS, true);
        this.block = this.baseBlock = BLOCK_AMT;
        this.magicNumber = this.baseMagicNumber = UPGRADE_BLOCK;
        this.sp = this.baseSP = DEFAULT_SP;

        this.setBackgroundTexture("img/512/skill_defender.png", "img/1024/skill_defender.png");

        this.setOrbTexture("img/orbs/cost.png", "img/orbs/cost_small.png");

    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(new GainBlockAction(p, p, block));
        //SP Effect
    }

    @Override
    public AbstractCard makeCopy() {
        return new CardDefenderShellDef();
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeBlock(UPGRADE_BLOCK);
        }
    }



}
