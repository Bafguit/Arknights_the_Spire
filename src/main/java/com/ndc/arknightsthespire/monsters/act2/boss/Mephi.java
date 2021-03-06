//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.ndc.arknightsthespire.monsters.act2.boss;

import basemod.abstracts.CustomMonster;
import basemod.abstracts.cardbuilder.actionbuilder.EffectActionBuilder;
import com.badlogic.gdx.math.MathUtils;
import com.esotericsoftware.spine.AnimationState;
import com.megacrit.cardcrawl.actions.AbstractGameAction.AttackEffect;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.HealAction;
import com.megacrit.cardcrawl.actions.common.RollMoveAction;
import com.megacrit.cardcrawl.actions.defect.EvokeOrbAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.MonsterStrings;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.unlock.UnlockTracker;
import com.megacrit.cardcrawl.vfx.combat.FrostOrbActivateEffect;
import com.ndc.arknightsthespire.actions.ApplyDefAction;
import com.ndc.arknightsthespire.actions.AtsSFX;
import com.ndc.arknightsthespire.actions.PlayAnimationAction;
import com.ndc.arknightsthespire.actions.WaitAnimAction;
import com.ndc.arknightsthespire.character.AtsEnum;

public class Mephi extends CustomMonster {
    public static final String ID = "ats:Mephi";
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
    private static final String ATLAS = "atsImg/monsters/act_2/boss/enemy_1507_mephi.atlas";
    private static final String SKEL = "atsImg/monsters/act_2/boss/enemy_1507_mephi.json";
    private int attackDamage;
    private int arm = 0;
    private int res = 0;
    private int heal = 10;
    private boolean healed = false;

    public Mephi() {
        this(100.0F, 0.0F);
    }

    public Mephi(float x, float y) {
        super(NAME, ID, 200, -5.0F, 0.0F, 300.0F, 330.0F, (String)null, x, y);
        this.loadAnimation(ATLAS, SKEL, 1.5F);
        AnimationState.TrackEntry e = state.setAnimation(0, "Idle", true);
        e.setTime(e.getEndTime() * MathUtils.random());
        this.flipHorizontal = true;
        this.type = EnemyType.BOSS;
        this.dialogX = (this.hb_x - 70.0F) * Settings.scale;
        this.dialogY -= (this.hb_y - 55.0F) * Settings.scale;

        if (AbstractDungeon.ascensionLevel >= 19) {
            this.heal = 15;
        } else {
            this.heal = 10;
        }

        if (AbstractDungeon.ascensionLevel >= 9) {
            this.setHp(230);
        } else {
            this.setHp(200);
        }

        if (AbstractDungeon.ascensionLevel >= 4) {
            this.attackDamage = 18;
        } else {
            this.attackDamage = 15;
        }

        this.damage.add(new DamageInfo(this, this.attackDamage, DamageInfo.DamageType.NORMAL));
    }

    public void takeTurn() {
        AbstractPlayer p = AbstractDungeon.player;
        switch (this.nextMove) {
            case 1:
                this.addToBot(new PlayAnimationAction(this, "Attack"));
                this.addToBot(new AtsSFX("MEPHI"));
                this.addToBot(new WaitAnimAction(this, 0.3F));
                this.healAll(this.heal);
                break;
            case 2:
                this.addToBot(new PlayAnimationAction(this, "Attack"));
                this.addToBot(new AtsSFX("MEPHI"));
                this.addToBot(new WaitAnimAction(this, 0.3F));
                this.healAll(this.heal * 2);
                break;
            case 3:
                this.addToBot(new PlayAnimationAction(this, "Attack"));
                this.addToBot(new AtsSFX("MEPHI"));
                this.addToBot(new WaitAnimAction(this, 0.3F));
                this.addToBot(new DamageAction(p, (DamageInfo)this.damage.get(0), AttackEffect.BLUNT_LIGHT, true, true));
                this.healAll(this.heal * 2);
        }

        AbstractDungeon.actionManager.addToBottom(new RollMoveAction(this));
    }

    protected void getMove(int num) {

        if(AbstractDungeon.getCurrRoom().monsters.monsters.get(0).isDead) {
            this.setMove((byte)3, Intent.ATTACK_BUFF, ((DamageInfo)this.damage.get(0)).base, 1, false);
        } else if(AbstractDungeon.getCurrRoom().monsters.monsters.get(0).currentHealth <= AbstractDungeon.getCurrRoom().monsters.monsters.get(0).maxHealth / 2){
            this.setMove((byte)2, Intent.BUFF);
        } else {
            this.setMove((byte)1, Intent.BUFF);
        }
    }

    public void healAll(int heal) {
        for(AbstractCreature t : AbstractDungeon.getCurrRoom().monsters.monsters) {
            this.addToBot(new HealAction(t, t, heal));
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
        monsterStrings = CardCrawlGame.languagePack.getMonsterStrings(ID);
        NAME = monsterStrings.NAME;
        MOVES = monsterStrings.MOVES;
        DIALOG = monsterStrings.DIALOG;
    }
}
