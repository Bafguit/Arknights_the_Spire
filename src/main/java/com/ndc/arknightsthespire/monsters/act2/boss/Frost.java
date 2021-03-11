//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.ndc.arknightsthespire.monsters.act2.boss;

import basemod.abstracts.CustomMonster;
import com.badlogic.gdx.math.MathUtils;
import com.esotericsoftware.spine.AnimationState;
import com.megacrit.cardcrawl.actions.AbstractGameAction.AttackEffect;
import com.megacrit.cardcrawl.actions.animations.TalkAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.RollMoveAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.MonsterStrings;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import com.megacrit.cardcrawl.powers.WeakPower;
import com.megacrit.cardcrawl.unlock.UnlockTracker;
import com.ndc.arknightsthespire.actions.AtsSFX;
import com.ndc.arknightsthespire.actions.PlayAnimationAction;
import com.ndc.arknightsthespire.actions.WaitAnimAction;
import com.ndc.arknightsthespire.power.NewStrPower;

public class Frost extends CustomMonster {
    public static final String ID = "ats:Frost";
    private static final MonsterStrings monsterStrings;
    public static final String NAME;
    public static final String[] MOVES;
    public static final String[] DIALOG;
    private static final String ATLAS = "atsImg/monsters/act_2/boss/enemy_1505_frstar.atlas";
    private static final String SKEL = "atsImg/monsters/act_2/boss/enemy_1505_frstar.json";
    private int attackDamage;
    private int arm = 0;
    private int res = 0;
    private int str = 2;
    private boolean isUped = false;

    public Frost() {
        this(0.0F, 0.0F);
    }

    public Frost(float x, float y) {
        super(NAME, ID, 300, -5.0F, 0.0F, 350.0F, 330.0F, (String)null, x, y);
        this.loadAnimation(ATLAS, SKEL, 1.3F);
        AnimationState.TrackEntry e = state.setAnimation(0, "Idle", true);
        e.setTime(e.getEndTime() * MathUtils.random());
        this.flipHorizontal = true;
        this.type = EnemyType.BOSS;
        this.dialogX = (this.hb_x - 70.0F) * Settings.scale;
        this.dialogY -= (this.hb_y - 55.0F) * Settings.scale;

        if (AbstractDungeon.ascensionLevel >= 19) {
            this.str = 5;
        } else {
            this.str = 3;
        }

        if (AbstractDungeon.ascensionLevel >= 9) {
            this.setHp(330);
        } else {
            this.setHp(300);
        }

        if (AbstractDungeon.ascensionLevel >= 4) {
            this.attackDamage = 10;
        } else {
            this.attackDamage = 9;
        }

        this.damage.add(new DamageInfo(this, this.attackDamage, DamageInfo.DamageType.NORMAL));
        this.damage.add(new DamageInfo(this, this.attackDamage * 2, DamageInfo.DamageType.NORMAL));
        this.damage.add(new DamageInfo(this, this.attackDamage * 3, DamageInfo.DamageType.NORMAL));
    }

    public void takeTurn() {
        AbstractPlayer p = AbstractDungeon.player;
        switch (this.nextMove) {
            case 1:
                this.addToBot(new PlayAnimationAction(this, "Attack"));
                this.addToBot(new DamageAction(p, (DamageInfo)this.damage.get(0), AttackEffect.BLUNT_LIGHT, false, false));
                break;
            case 2:
                this.addToBot(new PlayAnimationAction(this, "Attack"));
                this.addToBot(new AtsSFX("SKULL_R"));
                this.addToBot(new DamageAction(p, (DamageInfo)this.damage.get(0), AttackEffect.FIRE, false, false));
                this.addToBot(new ApplyPowerAction(p, this, new WeakPower(p, 1, true), 1));
                break;
            case 3:
                this.addToBot(new PlayAnimationAction(this, "Attack"));
                this.addToBot(new AtsSFX("SKULL_R"));
                this.addToBot(new DamageAction(p, (DamageInfo)this.damage.get(0), AttackEffect.FIRE, false, false));
                this.addToBot(new ApplyPowerAction(p, this, new VulnerablePower(p, 2, true), 2));
                break;
            case 4:
                AbstractDungeon.actionManager.addToBottom(new TalkAction(this, DIALOG[0], 1.0F, 2.5F));
                break;
            case 5:
                AbstractDungeon.actionManager.addToBottom(new TalkAction(this, DIALOG[1], 1.0F, 2.5F));
                this.addToBot(new ApplyPowerAction(this, this, new NewStrPower(this, this.str), this.str));
                this.isUped = true;
        }

        AbstractDungeon.actionManager.addToBottom(new RollMoveAction(this));
    }

    protected void getMove(int num) {

        if(this.currentHealth <= this.maxHealth / 2 && !this.isUped) {
            this.setMove((byte) 5, Intent.BUFF);
        } else if(this.lastMove((byte) 3)) {
            this.setMove((byte) 4, Intent.UNKNOWN);
        } else if(this.lastMove((byte) 2)){
            this.setMove((byte)3, Intent.ATTACK_DEBUFF, ((DamageInfo)this.damage.get(0)).base);
        } else if(this.lastMove((byte) 4) || this.lastMove((byte) 5)){
            this.setMove((byte)1, Intent.ATTACK, ((DamageInfo)this.damage.get(0)).base, 2, true);
        } else {
            this.setMove((byte)2, Intent.ATTACK_DEBUFF, ((DamageInfo)this.damage.get(0)).base);
        }
    }

    @Override
    public void usePreBattleAction() {
        super.usePreBattleAction();
        UnlockTracker.markBossAsSeen(this.id);
        CardCrawlGame.music.unsilenceBGM();
        AbstractDungeon.scene.fadeOutAmbiance();
        AbstractDungeon.getCurrRoom().playBgmInstantly("BOSS_BOTTOM");
    }

    public void setDef(int a, int r) {
        this.arm = a;
        this.res = r;
    }

    public void die() {
        AbstractDungeon.actionManager.addToBottom(new TalkAction(this, DIALOG[2], 1.0F, 2.5F));
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
