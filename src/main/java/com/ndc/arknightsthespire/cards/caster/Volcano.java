package com.ndc.arknightsthespire.cards.caster;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.NoBlockPower;
import com.ndc.arknightsthespire.CardColors;
import com.ndc.arknightsthespire.actions.AtsSFX;
import com.ndc.arknightsthespire.actions.DamageAllMute;
import com.ndc.arknightsthespire.cards.base.CardSPBase;
import com.ndc.arknightsthespire.cards.base.PositionType;
import com.ndc.arknightsthespire.power.BurnPower;

import java.util.Iterator;

public class Volcano extends CardSPBase {
    public static final String ID = "ats:Volcano";
    public static final String IMG_PATH = "img/cards/Volcano.png";
    public static final PositionType POSITION = PositionType.CASTER;
    private static final int COST = 2;
    private static final int ATTACK_DMG = 6;
    private static final int UP_DMG = 2;
    private static final int SP = 40;

    public Volcano() {
        super(ID, IMG_PATH, COST,
                CardType.ATTACK, CardColors.AbstractCardEnum.DOCTOR_COLOR,
                CardRarity.RARE, CardTarget.ALL_ENEMY, true, POSITION, true, ATTACK_DMG, 0, 0, SP);
    }

    public int getCasterDeck() {

        int casterCount = 0;
        Iterator var1 = AbstractDungeon.player.masterDeck.group.iterator();

        while (var1.hasNext()) {
            AbstractCard c = (AbstractCard) var1.next();
            if(c instanceof CardSPBase) {
                CardSPBase card = (CardSPBase) c;
                if (card.position == POSITION) casterCount++;
            }
        }

        return casterCount;
    }

    @Override
    public void useCard(AbstractPlayer p, AbstractMonster m, boolean isSpJustUsed) {
        if(isSpJustUsed) {
            this.isMultiDamage = true;
            this.exhaust = true;
            for (int for_i = 0; for_i < getCasterDeck(); for_i++) {
                addToBot(new AtsSFX("VOLCANO"));
                AbstractDungeon.actionManager.addToBottom(new DamageAllMute(p,
                        this.multiDamage, this.damageTypeForTurn,
                        AbstractGameAction.AttackEffect.FIRE, true, true));
            }
        } else {
            for (final AbstractMonster mo : AbstractDungeon.getCurrRoom().monsters.monsters) {
                addToBot(new AtsSFX("VOLCANO"));
                addToBot(new ApplyPowerAction(mo, p,
                        new BurnPower(mo, p, this.damage), this.damage, true));
            }
        }
    }

    @Override
    public AbstractCard makeCopy() {
        return new Volcano();
    }

    @Override
    public void upgradeCard() {
        this.upgradeDamage(UP_DMG);
    }

}
