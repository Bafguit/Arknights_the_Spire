//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.ndc.arknightsthespire.monsters.act1;

import basemod.abstracts.CustomMonster;
import com.badlogic.gdx.math.MathUtils;
import com.esotericsoftware.spine.AnimationState;
import com.megacrit.cardcrawl.actions.AbstractGameAction.AttackEffect;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.RollMoveAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.cards.red.BloodForBlood;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.MonsterStrings;
import com.ndc.arknightsthespire.actions.ApplyDefAction;
import com.ndc.arknightsthespire.actions.PlayAnimationAction;
import com.ndc.arknightsthespire.character.AtsEnum;

public class Genji extends CustomMonster {
    public static final String ID = "ats:Genji";
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
    private static final String ATLAS = "atsImg/monsters/Avenger/enemy_1025_reveng.atlas";
    private static final String SKEL = "atsImg/monsters/Avenger/enemy_1025_reveng.json";
    private int attackDamage;
    private float per;
    private int arm = 0;
    private int res = 0;

    public Genji() {
        this(0.0F, 0.0F);
    }

    public Genji(float x, float y) {
        super(NAME, ID, 60, -5.0F, 0.0F, 220.0F, 245.0F, (String)null, x, y);
        this.loadAnimation(ATLAS, SKEL, 1.4F);
        AnimationState.TrackEntry e = state.setAnimation(0, "Idle", true);
        e.setTime(e.getEndTime() * MathUtils.random());
        this.flipHorizontal = true;
        this.type = EnemyType.NORMAL;
        this.dialogX = (this.hb_x - 70.0F) * Settings.scale;
        this.dialogY -= (this.hb_y - 55.0F) * Settings.scale;

        if (AbstractDungeon.ascensionLevel >= 17) {
            this.per = 2.5F;
        } else {
            this.per = 2.0F;
        }

        if (AbstractDungeon.ascensionLevel >= 7) {
            this.setHp(47, 50);
        } else {
            this.setHp(45, 48);
        }

        if (AbstractDungeon.ascensionLevel >= 2) {
            this.attackDamage = 8;
        } else {
            this.attackDamage = 7;
        }

        this.damage.add(new DamageInfo(this, this.attackDamage, AtsEnum.PHYS));
        this.damage.add(new DamageInfo(this, Math.round((float) this.attackDamage * this.per), AtsEnum.PHYS));
    }

    public void takeTurn() {
        AbstractPlayer p = AbstractDungeon.player;
        this.addToBot(new PlayAnimationAction(this, "Attack"));
        switch (this.nextMove) {
            case 1:
                this.addToBot(new DamageAction(p, (DamageInfo)this.damage.get(0), AttackEffect.SLASH_DIAGONAL));
                break;
            case 2:
                this.addToBot(new DamageAction(p, (DamageInfo)this.damage.get(1), AttackEffect.SLASH_DIAGONAL));
        }

        AbstractDungeon.actionManager.addToBottom(new RollMoveAction(this));
    }

    protected void getMove(int num) {

        if(this.currentHealth < this.maxHealth / 2) {
            this.setMove((byte)2, Intent.ATTACK, ((DamageInfo)this.damage.get(1)).base, 1, false);
        } else {
            this.setMove((byte)1, Intent.ATTACK, ((DamageInfo)this.damage.get(0)).base, 1, false);
        }
    }

    @Override
    public void usePreBattleAction() {
        super.usePreBattleAction();
    }

    public void setDef(int a, int r) {
        this.arm = a;
        this.res = r;
    }

    public void die() {
        super.die();
        this.addToBot(new PlayAnimationAction(this, "Die"));
    }

    static {
        monsterStrings = CardCrawlGame.languagePack.getMonsterStrings("ats:Genji");
        NAME = monsterStrings.NAME;
        MOVES = monsterStrings.MOVES;
        DIALOG = monsterStrings.DIALOG;
    }
}
