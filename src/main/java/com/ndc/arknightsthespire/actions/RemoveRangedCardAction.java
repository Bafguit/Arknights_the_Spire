//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.ndc.arknightsthespire.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ExhaustAction;
import com.megacrit.cardcrawl.actions.common.ExhaustSpecificCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.vfx.cardManip.ExhaustCardEffect;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndAddToDrawPileEffect;
import com.ndc.arknightsthespire.cards.base.CardSPBase;
import com.ndc.arknightsthespire.cards.base.PositionType;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

public class RemoveRangedCardAction extends AbstractGameAction {
    private AtsSound sfx = new AtsSound();
    private String sfxKey;

    public RemoveRangedCardAction(AbstractCreature source, String key) {
        this.setValues((AbstractCreature)null, source, 0);
        this.duration = DEFAULT_DURATION;
        this.startDuration = DEFAULT_DURATION;
        this.sfxKey = key;

        this.actionType = ActionType.EXHAUST;
    }

    public void getRandomCard() {
        ArrayList<AbstractCard> draw = new ArrayList<>();
        ArrayList<AbstractCard> hand = new ArrayList<>();
        ArrayList<AbstractCard> disc = new ArrayList<>();

        if(AbstractDungeon.player.drawPile.size() > 0) {
            Iterator var1 = AbstractDungeon.player.drawPile.group.iterator();

            while (var1.hasNext()) {
                AbstractCard c = (AbstractCard) var1.next();
                if (c instanceof CardSPBase) {
                    CardSPBase sc = (CardSPBase) c;
                    if (sc.position == PositionType.MEDIC || sc.position == PositionType.SNIPER || sc.position == PositionType.SUPPORTER || sc.position == PositionType.CASTER) {
                        draw.add(c);
                    }
                }
            }
        }

        if(AbstractDungeon.player.hand.size() > 0) {
            Iterator var2 = AbstractDungeon.player.hand.group.iterator();

            while (var2.hasNext()) {
                AbstractCard c = (AbstractCard) var2.next();
                if (c instanceof CardSPBase) {
                    CardSPBase sc = (CardSPBase) c;
                    if (sc.position == PositionType.MEDIC || sc.position == PositionType.SNIPER || sc.position == PositionType.SUPPORTER || sc.position == PositionType.CASTER) {
                        hand.add(c);
                    }
                }
            }
        }

        if(AbstractDungeon.player.discardPile.size() > 0) {
            Iterator var3 = AbstractDungeon.player.discardPile.group.iterator();

            while (var3.hasNext()) {
                AbstractCard c = (AbstractCard) var3.next();
                if (c instanceof CardSPBase) {
                    CardSPBase sc = (CardSPBase) c;
                    if (sc.position == PositionType.MEDIC || sc.position == PositionType.SNIPER || sc.position == PositionType.SUPPORTER || sc.position == PositionType.CASTER) {
                        disc.add(c);
                    }
                }
            }
        }

        Random rand = new Random();

        ArrayList<AbstractCard> rc = new ArrayList();
        AbstractCard c1;
        AbstractCard c2;
        AbstractCard c3;

        if(draw.size() > 0) {
            c1 = draw.get(draw.size() == 1 ? 0 : rand.nextInt(draw.size() - 1));
            rc.add(c1);
        } else {
            c1 = null;
        }
        if(hand.size() > 0) {
            c2 = hand.get(hand.size() == 1 ? 0 : rand.nextInt(hand.size() - 1));
            rc.add(c2);
        } else {
            c2 = null;
        }
        if(disc.size() > 0) {
            c3 = disc.get(disc.size() == 1 ? 0 : rand.nextInt(disc.size() - 1));
            rc.add(c3);
        } else {
            c3 = null;
        }

        if(rc.size() > 0) {
            Random random = new Random();
            AbstractCard r = rc.get(rc.size() == 1 ? 0 : random.nextInt(rc.size() - 1));
            if(c1 != null) {
                if(r == c1) {
                    this.addToBot(new ExhaustSpecificCardAction(r, AbstractDungeon.player.drawPile));
                }
            } else if(c2 != null) {
                if(r == c2) {
                    this.addToBot(new ExhaustSpecificCardAction(r, AbstractDungeon.player.hand));
                }
            } else if(c3 != null) {
                if(r == c3) {
                    this.addToBot(new ExhaustSpecificCardAction(r, AbstractDungeon.player.discardPile));
                }
            } else {
                this.isDone = true;
            }
        }

    }

    public void update() {
        if(this.duration == this.startDuration) {
            if(this.sfxKey != null) {
                sfx.update();
                sfx.play(this.sfxKey, 0.0F);
            }
            this.getRandomCard();
        }
        this.tickDuration();
    }
}
