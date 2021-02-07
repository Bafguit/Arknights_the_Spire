//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.ndc.arknightsthespire.monsters;

import com.brashmonkey.spriter.Animation;
import com.brashmonkey.spriter.Mainline;
import com.brashmonkey.spriter.Player;
import com.megacrit.cardcrawl.actions.AbstractGameAction.AttackEffect;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.RollMoveAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.MonsterStrings;
import com.megacrit.cardcrawl.powers.FrailPower;
import com.ndc.arknightsthespire.util.AbstractSpriterMonster;
import com.ndc.arknightsthespire.util.BetterSpriterAnimation;

public class SlugB extends AbstractSpriterMonster {
    public static final String ID = "ats:Slug B";
    private static final MonsterStrings monsterStrings;
    public static final String NAME;
    public static final String[] MOVES;
    public static final String[] DIALOG;
    private static final byte DEBUFF_ATTACK = 1;
    private static final byte ATTACK = 2;
    private static final byte BUFF = 3;
    private static final int DEBUFF_ATTACK_DAMAGE = 7;
    private static final int A2_DEBUFF_ATTACK_DAMAGE = 8;
    private static final int ATTACK_DAMAGE = 5;
    private static final int A2_ATTACK_DAMAGE = 6;
    private static final int MULTI_HIT_AMT = 2;
    private static final int DEBUFF_AMOUNT = 1;
    private static final int STRENGTH = 2;
    private static final int A17_STRENGTH = 3;
    private static final int HP_MIN = 45;
    private static final int HP_MAX = 48;
    private static final int A7_HP_MIN = 47;
    private static final int A7_HP_MAX = 50;
    private int attackDamage;
    private int debuffDamage;

    public SlugB() {
        this(0.0F, 0.0F);
    }

    public SlugB(float x, float y) {
        super(NAME, ID, 15, -5.0F, 0.0F, 180.0F, 245.0F, (String)null, x, y);
        this.animation = new BetterSpriterAnimation("img/monsters/Slugs/B/Slug_B.scml");
        this.type = EnemyType.NORMAL;
        this.dialogX = (this.hb_x - 70.0F) * Settings.scale;
        this.dialogY -= (this.hb_y - 55.0F) * Settings.scale;

        if (AbstractDungeon.ascensionLevel >= 7) {
            this.setHp(15, 18);
        } else {
            this.setHp(12, 15);
        }

        if (AbstractDungeon.ascensionLevel >= 2) {
            this.attackDamage = 7;
        } else {
            this.attackDamage = 6;
        }

        this.damage.add(new DamageInfo(this, this.attackDamage));

        Player.PlayerListener listener = new SlugB.AnimationListener(this);
        ((BetterSpriterAnimation)this.animation).myPlayer.addListener(listener);
    }

    public void takeTurn() {
        AbstractPlayer p = AbstractDungeon.player;
        this.runAnim("attack");
        switch (this.nextMove) {
            case 1:
                this.addToBot(new DamageAction(p, (DamageInfo)this.damage.get(0), AttackEffect.BLUNT_LIGHT));
                break;
            case 2:
                this.addToBot(new ApplyPowerAction(p, this, new FrailPower(p, 1, true)));
        }

        AbstractDungeon.actionManager.addToBottom(new RollMoveAction(this));
    }

    protected void getMove(int num) {
        if(this.lastMove((byte)2)) {
            this.setMove((byte)1, Intent.ATTACK, ((DamageInfo)this.damage.get(0)).base, 1, false);
        } else {
            this.setMove((byte)2, Intent.DEBUFF);
        }
    }

    public void die(boolean triggerRelics) {
        this.runAnim("death");
        ((BetterSpriterAnimation)this.animation).startDying();
        super.die(triggerRelics);
    }

    public void runAnim(String animation) {
        ((BetterSpriterAnimation)this.animation).myPlayer.setAnimation(animation);
    }

    public void resetAnimation() {
        ((BetterSpriterAnimation)this.animation).myPlayer.setAnimation("idle");
    }

    public void stopAnimation() {
        int time = ((BetterSpriterAnimation)this.animation).myPlayer.getAnimation().length;
        ((BetterSpriterAnimation)this.animation).myPlayer.setTime(time);
        ((BetterSpriterAnimation)this.animation).myPlayer.speed = 0;
    }

    public class AnimationListener implements Player.PlayerListener {
        private SlugB character;

        public AnimationListener(SlugB character) {
            this.character = character;
        }

        public void animationFinished(Animation animation) {
            if (animation.name.equals("death")) {
                this.character.stopAnimation();
            } else if (!animation.name.equals("idle")) {
                this.character.resetAnimation();
            }

        }

        public void animationChanged(Animation var1, Animation var2) {
        }

        public void preProcess(Player var1) {
        }

        public void postProcess(Player var1) {
        }

        public void mainlineKeyChanged(Mainline.Key var1, Mainline.Key var2) {
        }
    }

    static {
        monsterStrings = CardCrawlGame.languagePack.getMonsterStrings(ID);
        NAME = monsterStrings.NAME;
        MOVES = monsterStrings.MOVES;
        DIALOG = monsterStrings.DIALOG;
    }
}
