//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.ndc.arknightsthespire.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.AbstractGameAction.ActionType;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.common.ExhaustSpecificCardAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.AbstractCard.CardType;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import java.util.ArrayList;
import java.util.Iterator;

public class AttackPerNonAttackCard extends AbstractGameAction {
    private int[] multiDamage;
    private boolean hasSfx = false;
    private String sfxName;

    public AttackPerNonAttackCard(int[] multiDamage) {
        this.multiDamage = multiDamage;
        this.setValues(AbstractDungeon.player, AbstractDungeon.player);
        this.actionType = ActionType.DAMAGE;
    }

    public AttackPerNonAttackCard(int[] multiDamage, String sfxName) {
        this(multiDamage);
        this.hasSfx = true;
        this.sfxName = sfxName;
    }

    public void update() {
        ArrayList<AbstractCard> cardsToExhaust = new ArrayList();
        Iterator var2 = AbstractDungeon.player.hand.group.iterator();

        AbstractCard c;
        while(var2.hasNext()) {
            c = (AbstractCard)var2.next();
            if (c.type != CardType.ATTACK) {
                cardsToExhaust.add(c);
            }
        }

        var2 = cardsToExhaust.iterator();

        if(this.hasSfx) addToBot(new AtsSFX(this.sfxName));

        while(var2.hasNext()) {
            c = (AbstractCard)var2.next();
            this.addToTop(new DamageAllMute(AbstractDungeon.player, this.multiDamage, DamageInfo.DamageType.HP_LOSS, AttackEffect.SHIELD, true, true));
        }

        var2 = cardsToExhaust.iterator();

        while(var2.hasNext()) {
            c = (AbstractCard)var2.next();
            this.addToTop(new ExhaustSpecificCardAction(c, AbstractDungeon.player.hand));
        }

        this.isDone = true;
    }
}
