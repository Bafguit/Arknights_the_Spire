package com.ndc.arknightsthespire.cards.supporter;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.red.Dropkick;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.SlowPower;
import com.ndc.arknightsthespire.CardColors;
import com.ndc.arknightsthespire.actions.AtsSFX;
import com.ndc.arknightsthespire.cards.base.CardSPBase;
import com.ndc.arknightsthespire.cards.base.PositionType;
import com.ndc.arknightsthespire.power.ArmPerTurnPower;
import com.ndc.arknightsthespire.power.ArmTurnPower;

import java.util.Iterator;

public class EchoReverb extends CardSPBase {
    public static final String ID = "ats:Echo Reverb";
    public static final String IMG_PATH = "atsImg/cards/EchoReverb.png";
    public static final PositionType POSITION = PositionType.SUPPORTER;
    private static final int COST = 0;

    public EchoReverb() {
        super(ID, IMG_PATH, COST,
                CardType.SKILL, CardColors.AbstractCardEnum.DOCTOR_COLOR,
                CardRarity.RARE, CardTarget.ENEMY, POSITION);
        this.exhaust = true;
    }

    public void triggerOnGlowCheck() {
        this.glowColor = this.baseColor.cpy();
        Iterator var1 = AbstractDungeon.getCurrRoom().monsters.monsters.iterator();

        while(var1.hasNext()) {
            AbstractMonster m = (AbstractMonster)var1.next();
            if (!m.isDeadOrEscaped() && m.hasPower("Artifact")) {
                this.glowColor = AbstractCard.GOLD_BORDER_GLOW_COLOR.cpy();
                break;
            }
        }

    }

    @Override
    public void useCard(AbstractPlayer p, AbstractMonster m, boolean isSpJustUsed) {
        if(m.hasPower("Artifact")) addToBot(new RemoveSpecificPowerAction(m, m, "Artifact"));
        addToBot(new AtsSFX("BELL"));
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(m, p,new SlowPower(m, 0), 0));
    }

    @Override
    public AbstractCard makeCopy() {
        return new EchoReverb();
    }

    @Override
    public void upgradeCard() {
        this.isInnate = true;
    }
}
