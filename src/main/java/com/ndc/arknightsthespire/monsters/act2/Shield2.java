//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.ndc.arknightsthespire.monsters.act2;

import basemod.abstracts.CustomMonster;
import com.badlogic.gdx.math.MathUtils;
import com.esotericsoftware.spine.AnimationState;
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
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.ndc.arknightsthespire.actions.ApplyDefAction;
import com.ndc.arknightsthespire.actions.PlayAnimationAction;
import com.ndc.arknightsthespire.character.AtsEnum;

public class Shield2 extends CustomMonster {
    public static final String ID = "ats:Shield";
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
    private static final String ATLAS = "atsImg/monsters/act_1/enemy_1006_shield.atlas";
    private static final String SKEL = "atsImg/monsters/act_1/enemy_1006_shield.json";
    private int attackDamage;
    private int arm = 0;
    private int res = 0;
    private int atk = 2;

    public Shield2() {
        this(0.0F, 0.0F);
    }

    public Shield2(float x, float y) {
        super(NAME, ID, 60, -5.0F, 0.0F, 220.0F, 300.0F, (String)null, x, y);
        this.loadAnimation(ATLAS, SKEL, 1.4F);
        AnimationState.TrackEntry e = state.setAnimation(0, "Idle", true);
        e.setTime(e.getEndTime() * MathUtils.random());
        this.flipHorizontal = true;
        this.type = EnemyType.NORMAL;
        this.dialogX = (this.hb_x - 70.0F) * Settings.scale;
        this.dialogY -= (this.hb_y - 55.0F) * Settings.scale;

        if (AbstractDungeon.ascensionLevel >= 17) {
            this.setDef(8, 0);
            this.atk = 4;
        } else {
            this.setDef(7, 0);
            this.atk = 2;
        }

        if (AbstractDungeon.ascensionLevel >= 7) {
            this.setHp(57, 60);
        } else {
            this.setHp(55, 58);
        }

        if (AbstractDungeon.ascensionLevel >= 2) {
            this.attackDamage = 9;
        } else {
            this.attackDamage = 8;
        }

        this.damage.add(new DamageInfo(this, this.attackDamage, AtsEnum.PHYS));
    }

    public void takeTurn() {
        AbstractPlayer p = AbstractDungeon.player;
        switch (this.nextMove) {
            case 1:
                this.addToBot(new PlayAnimationAction(this, "Attack"));
                this.addToBot(new DamageAction(p, (DamageInfo)this.damage.get(0), AttackEffect.BLUNT_HEAVY));
                break;
            case 3:
                this.addToBot(new PlayAnimationAction(this, "Attack"));
                this.addToBot(new DamageAction(p, (DamageInfo)this.damage.get(0), AttackEffect.BLUNT_HEAVY));
                this.addToBot(new ApplyPowerAction(this, this, new StrengthPower(this, -this.atk)));
                ApplyDefAction.applyDef(this, this.atk, 0);
                ApplyDefAction.flash(this);
                break;
            case 2:
                this.addToBot(new ApplyPowerAction(this, this, new StrengthPower(this, this.atk)));
                ApplyDefAction.applyDef(this, -this.atk, 0);
                ApplyDefAction.flash(this);
        }

        AbstractDungeon.actionManager.addToBottom(new RollMoveAction(this));
    }

    protected void getMove(int num) {

        if(this.lastMove((byte) 2) ) {
            this.setMove((byte)3, Intent.ATTACK, ((DamageInfo)this.damage.get(0)).base, 1, false);
        } else if(this.lastMove((byte) 1)){
            this.moveName = MOVES[0];
            this.setMove((byte)2, Intent.BUFF, ((DamageInfo)this.damage.get(0)).base, 1, false);
        } else {
            this.setMove((byte)1, Intent.ATTACK, ((DamageInfo)this.damage.get(0)).base, 1, false);
        }
    }

    @Override
    public void usePreBattleAction() {
        super.usePreBattleAction();
        ApplyDefAction.applyDef(this, this.arm, this.res);
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
        monsterStrings = CardCrawlGame.languagePack.getMonsterStrings("ats:Shield");
        NAME = monsterStrings.NAME;
        MOVES = monsterStrings.MOVES;
        DIALOG = monsterStrings.DIALOG;
    }
}
