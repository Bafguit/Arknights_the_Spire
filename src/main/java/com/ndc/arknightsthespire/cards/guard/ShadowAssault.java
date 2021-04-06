package com.ndc.arknightsthespire.cards.guard;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.ndc.arknightsthespire.CardColors;
import com.ndc.arknightsthespire.actions.AtsSFX;
import com.ndc.arknightsthespire.actions.DamageAllMute;
import com.ndc.arknightsthespire.actions.RandomAttack;
import com.ndc.arknightsthespire.cards.base.CardSPBase;
import com.ndc.arknightsthespire.cards.base.PositionType;
import com.ndc.arknightsthespire.character.CharacterChen;

public class ShadowAssault extends CardSPBase {
    public static final String ID = "ats:Shadow Assault";
    public static final String IMG_PATH = "atsImg/cards/ShadowAssault.png";
    public static final String SP_IMG_PATH = "atsImg/cards/ShadowAssault_sp.png";
    public static final PositionType POSITION = PositionType.GUARD;
    private static final int COST = 2;
    private static final int ATTACK_DMG = 9;
    private static final int UP_DMG = 2;
    private static final int SP = 40;
    private static final int UP_SP = 30;

    public ShadowAssault() { //Not Using
        super(ID, IMG_PATH, SP_IMG_PATH, COST,
                CardType.ATTACK, CardColors.AbstractCardEnum.DOCTOR_COLOR,
                CardRarity.RARE, CardTarget.ALL_ENEMY, false, POSITION, true, ATTACK_DMG, 0, 0, SP);
        this.isMultiDamage = true;
        this.spCard.cost = 3;
    }

    @Override
    public void useCard(AbstractPlayer p, AbstractMonster m, boolean isSpJustUsed) {

        if(isSpJustUsed) {
            if(AbstractDungeon.player instanceof CharacterChen) {
                ((CharacterChen) AbstractDungeon.player).setAnimation("Skill_3", false);
                ((CharacterChen) AbstractDungeon.player).addAnimation("Skill_End_3", false, 0.0F);
                ((CharacterChen) AbstractDungeon.player).addAnimation("Idle", true, 0.0F);
            }

            for (int for_i = 0; for_i < 8; for_i++) {
                addToBot(new AtsSFX("JUEYING_1"));
                this.addToBot(new RandomAttack(this.getInfo(),
                        AbstractGameAction.AttackEffect.SLASH_HORIZONTAL, true, true));
            }
            addToBot(new AtsSFX("JUEYING_1"));
            this.addToBot(new RandomAttack(this.getInfo(),
                    AbstractGameAction.AttackEffect.SLASH_HORIZONTAL, false, true));
            addToBot(new AtsSFX("JUEYING_2"));
            this.addToBot(new RandomAttack(this.getInfo(),
                    AbstractGameAction.AttackEffect.SLASH_HEAVY, false, true, true));
        }
        else {
            addToBot(new AtsSFX("BADAO"));
            if(AbstractDungeon.player instanceof CharacterChen) {
                ((CharacterChen) AbstractDungeon.player).setAnimation("Skill_2", false);
                ((CharacterChen) AbstractDungeon.player).addAnimation("Skill_End_2", false, 0.0F);
                ((CharacterChen) AbstractDungeon.player).addAnimation("Idle", true, 0.0F);
            }
            AbstractDungeon.actionManager.addToBottom(new DamageAllMute(this.multiDamage, DamageInfo.DamageType.NORMAL,
                    AbstractGameAction.AttackEffect.SLASH_HORIZONTAL, true));
            AbstractDungeon.actionManager.addToBottom(new DamageAllMute(this.multiDamage, DamageInfo.DamageType.NORMAL,
                    AbstractGameAction.AttackEffect.SLASH_HORIZONTAL, true));
        }
    }

    @Override
    public AbstractCard makeCopy() {
        return new ShadowAssault();
    }

    @Override
    public void upgradeCard() {
        this.upgradeDamage(UP_DMG);
        this.upgradeSP(UP_SP);
    }

}
