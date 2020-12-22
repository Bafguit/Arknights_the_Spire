package com.ndc.arknightsthespire.cards.supporter;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.RegenPower;
import com.megacrit.cardcrawl.powers.SlowPower;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import com.megacrit.cardcrawl.powers.WeakPower;
import com.ndc.arknightsthespire.CardColors;
import com.ndc.arknightsthespire.actions.AtsSFX;
import com.ndc.arknightsthespire.cards.base.CardSPBase;
import com.ndc.arknightsthespire.cards.base.PositionType;
import com.ndc.arknightsthespire.power.AtsSlowPower;

public class Foxfire extends CardSPBase {
    public static final String ID = "ats:Nebulous Foxfire";
    public static final String IMG_PATH = "img/cards/nff.png";
    public static final PositionType POSITION = PositionType.SUPPORTER;
    private static final int COST = 2;
    private static final int REGEN_AMOUNT = 2;
    private static final int UPGRADE_REGEN = 1;

    public Foxfire() {
        super(ID, IMG_PATH, COST,
                CardType.SKILL, CardColors.AbstractCardEnum.DOCTOR_COLOR,
                CardRarity.RARE, CardTarget.ALL_ENEMY, true, POSITION, false, 0, 0, REGEN_AMOUNT, 0);
    }

    @Override
    public void useCard(AbstractPlayer p, AbstractMonster m, boolean isSpJustUsed) {
        addToBot(new AtsSFX("CLING"));
        for (final AbstractMonster mo : AbstractDungeon.getCurrRoom().monsters.monsters) {
            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(mo, p,
                    new VulnerablePower(mo, this.magicNumber, false), this.magicNumber, true));
            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(mo, p,
                    new WeakPower(mo, this.magicNumber, false), this.magicNumber, true));
        }
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new RegenPower(p, 2), 2, true));

    }

    @Override
    public AbstractCard makeCopy() {
        return new Foxfire();
    }

    @Override
    public void upgradeCard() {
        this.upgradeMagicNumber(UPGRADE_REGEN);
    }



}
