package com.ndc.arknightsthespire.cards.rare;

import com.evacipated.cardcrawl.mod.stslib.actions.common.StunMonsterAction;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.LoseHPAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.PenNibPower;
import com.ndc.arknightsthespire.CardColors;
import com.ndc.arknightsthespire.cards.CardSPBase;
import com.ndc.arknightsthespire.cards.PositionType;

import static com.megacrit.cardcrawl.actions.AbstractGameAction.*;

public class CardSpecialistDurian extends CardSPBase {
    public static final String ID = "ats:Durian";
    public static final String IMG_PATH = "img/cards/Durian.png";
    public static final PositionType POSITION = PositionType.SPECIALIST;
    private static final int COST = 1;
    private static final int UP_COST = 0;

    public CardSpecialistDurian() {
        super(ID, IMG_PATH, COST,
                CardType.SKILL, CardColors.AbstractCardEnum.DOCTOR_COLOR,
                CardRarity.RARE, CardTarget.SELF, false, POSITION, false);

        this.setBackgroundTexture("img/512/specialist_512.png", "img/1024/specialist.png");

        this.setOrbTexture("img/orbs/cost.png", "img/orbs/cost_small.png");

        this.exhaust = true;

    }

    @Override
    public void useCard(AbstractPlayer p, AbstractMonster m, boolean isSpJustUsed) {
        int d = 3;
        int dex = (p.hasPower("Dexterity") ? p.getPower("Dexterity").amount : 0);

        if(d - dex >= 1)
            d -= dex;
        else if (d - dex < 1)
            d = 1;

        for(int forI = 0; forI < 5; forI++) {
            AbstractDungeon.actionManager.addToBottom(new LoseHPAction(p, p, d, AttackEffect.FIRE));
        }

        addToBot(new ApplyPowerAction(p, p, new PenNibPower(p, 1), 1));
    }

    @Override
    public AbstractCard makeCopy() {
        return new CardSpecialistDurian();
    }

    @Override
    public void upgradeCard() {
        this.upgradeBaseCost(UP_COST);
    }

}
