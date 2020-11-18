package com.ndc.arknightsthespire.cards.rare;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.RegenPower;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import com.megacrit.cardcrawl.powers.WeakPower;
import com.ndc.arknightsthespire.CardColors;
import com.ndc.arknightsthespire.cards.CardSPBase;
import com.ndc.arknightsthespire.cards.PositionType;

public class CardSupporterFoxfire extends CardSPBase {
    public static final String ID = "Nebulous Foxfire";
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    // Get object containing the strings that are displayed in the game.
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/cards/nff.png";
    public static final PositionType POSITION = PositionType.SUPPORT;
    private static final int COST = 3;
    private static final int REGEN_AMOUNT = 3;
    private static final int UPGRADE_REGEN = 1;

    public CardSupporterFoxfire() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION,
                CardType.SKILL, CardColors.AbstractCardEnum.DOCTOR_COLOR,
                CardRarity.RARE, CardTarget.ALL_ENEMY, true, POSITION, false);
        this.magicNumber = this.baseMagicNumber = REGEN_AMOUNT;

        this.setBackgroundTexture("img/512/supporter_512.png", "img/1024/supporter_512.png");

        this.setOrbTexture("img/orbs/cost.png", "img/orbs/cost_small.png");

    }

    @Override
    public void useCard(AbstractPlayer p, AbstractMonster m, boolean isSpJustUsed) {
        for (final AbstractMonster mo : AbstractDungeon.getCurrRoom().monsters.monsters) {
            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(mo, p,
                    new VulnerablePower(mo, this.magicNumber, false), this.magicNumber, true));
            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(mo, p,
                    new WeakPower(mo, this.magicNumber, false), this.magicNumber, true));
        }
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new RegenPower(p, this.magicNumber), this.magicNumber, true));

    }

    @Override
    public AbstractCard makeCopy() {
        return new CardSupporterFoxfire();
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeMagicNumber(UPGRADE_REGEN);
        }
    }



}
