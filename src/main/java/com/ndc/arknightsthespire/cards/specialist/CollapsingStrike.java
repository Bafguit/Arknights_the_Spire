package com.ndc.arknightsthespire.cards.specialist;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.DiscardAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.ndc.arknightsthespire.CardColors.AbstractCardEnum;
import com.ndc.arknightsthespire.actions.AtsSFX;
import com.ndc.arknightsthespire.cards.base.CardSPBase;
import com.ndc.arknightsthespire.cards.base.PositionType;

public class CollapsingStrike extends CardSPBase {
    public static final String ID = "ats:Collapsing Strike";
    public static final String IMG_PATH = "atsImg/cards/CollapsingStrike.png";
    public static final String SP_IMG_PATH = "atsImg/cards/SteamPump.png";
    public static final PositionType POSITION = PositionType.SPECIALIST;
    private static final int COST = 2;
    private static final int D = 16;
    private static final int U_D = 4;
    private static final int B = 16;
    private static final int U_B = 4;
    private static final int M = 1;

    public CollapsingStrike() {
        super(ID, IMG_PATH, SP_IMG_PATH, COST,
                CardType.ATTACK, AbstractCardEnum.DOCTOR_COLOR,
                CardRarity.UNCOMMON, CardTarget.ENEMY, false, POSITION, true, D, B, M, 0);
        this.freeToSp = true;
    }

    @Override
    public void useCard(AbstractPlayer p, AbstractMonster m, boolean isSpJustUsed) {
        addToBot(new DiscardAction(p, p, this.magicNumber, false));
        if(isSpJustUsed) {
            addToBot(new AtsSFX("PUMP"));
            addToBot(new GainBlockAction(p, p, this.block));
        } else {
            addToBot(new AtsSFX("PUNCH"));
            addToBot(new DamageAction(m, this.getInfo(), AbstractGameAction.AttackEffect.BLUNT_HEAVY, false, true));
        }
    }

    @Override
    public AbstractCard makeCopy() {
        return new CollapsingStrike();
    }

    @Override
    public void upgradeCard() {
        this.upgradeDamage(U_D);
        this.upgradeBlock(U_B);
    }

}
