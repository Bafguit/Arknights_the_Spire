//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.ndc.arknightsthespire.monsters.act2.boss;

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
import com.megacrit.cardcrawl.monsters.city.TheCollector;
import com.megacrit.cardcrawl.monsters.exordium.TheGuardian;
import com.megacrit.cardcrawl.rooms.MonsterRoomBoss;
import com.megacrit.cardcrawl.unlock.UnlockTracker;
import com.ndc.arknightsthespire.actions.ApplyDefAction;
import com.ndc.arknightsthespire.actions.AtsSFX;
import com.ndc.arknightsthespire.actions.PlayAnimationAction;
import com.ndc.arknightsthespire.actions.WaitAnimAction;
import com.ndc.arknightsthespire.character.AtsEnum;
import com.ndc.arknightsthespire.power.BallistaPower;

public class Faust extends CustomMonster {
    public static final String ID = "ats:Faust";
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
    private static final String ATLAS = "atsImg/monsters/act_2/boss/enemy_1508_faust.atlas";
    private static final String SKEL = "atsImg/monsters/act_2/boss/enemy_1508_faust.json";
    private int attackDamage;
    private int arm = 0;
    private int res = 0;
    private int ballista = 6;
    private int countM = 5;
    private int count = 2;
    private int countBM = 7;
    private int countB = 4;

    public Faust() {
        this(-300.0F, 0.0F);
    }

    public Faust(float x, float y) {
        super(NAME, ID, 300, -5.0F, 0.0F, 320.0F, 330.0F, (String)null, x, y);
        this.loadAnimation(ATLAS, SKEL, 1.3F);
        AnimationState.TrackEntry e = state.setAnimation(0, "Idle", true);
        e.setTime(e.getEndTime() * MathUtils.random());
        this.flipHorizontal = true;
        this.type = EnemyType.BOSS;
        this.dialogX = (this.hb_x - 70.0F) * Settings.scale;
        this.dialogY -= (this.hb_y - 55.0F) * Settings.scale;

        if (AbstractDungeon.ascensionLevel >= 19) {
            this.countM = 3;
            this.count = 0;
            this.countBM = 5;
            this.countB = 2;
            this.ballista = 6;
        } else {
            this.countM = 4;
            this.count = 1;
            this.countBM = 7;
            this.countB = 4;
            this.ballista = 4;
        }

        if (AbstractDungeon.ascensionLevel >= 9) {
            this.setHp(280);
        } else {
            this.setHp(250);
        }

        if (AbstractDungeon.ascensionLevel >= 4) {
            this.attackDamage = 17;
        } else {
            this.attackDamage = 15;
        }

        this.damage.add(new DamageInfo(this, this.attackDamage, DamageInfo.DamageType.NORMAL));
        this.damage.add(new DamageInfo(this, this.attackDamage * 2, DamageInfo.DamageType.NORMAL));
    }

    public void takeTurn() {
        AbstractPlayer p = AbstractDungeon.player;
        switch (this.nextMove) {
            case 1:
                this.addToBot(new PlayAnimationAction(this, "Attack", 0.4F, "FAUST_N"));
                //this.addToBot(new AtsSFX("FAUST_N"));
                this.addToBot(new WaitAnimAction(this, 0.9F));
                this.addToBot(new DamageAction(p, (DamageInfo)this.damage.get(0), AttackEffect.BLUNT_LIGHT, false, true));
                break;
            case 2:
                this.addToBot(new PlayAnimationAction(this, "Skill_1"));
                this.addToBot(new AtsSFX("FAUST_H"));
                this.addToBot(new WaitAnimAction(this, 0.75F));
                this.addToBot(new DamageAction(p, (DamageInfo)this.damage.get(1), AttackEffect.BLUNT_HEAVY, false, true));
                break;
            case 3:
                this.addToBot(new PlayAnimationAction(this, "Skill_2"));
                this.addToBot(new WaitAnimAction(this, 0.2F));
                this.addToBot(new AtsSFX("FAUST_S"));
                this.addToBot(new WaitAnimAction(this, 0.2F));
                this.addToBot(new ApplyPowerAction(this, this, new BallistaPower(this, this, 1, this.ballista), 1));
        }

        AbstractDungeon.actionManager.addToBottom(new RollMoveAction(this));
    }

    protected void getMove(int num) {

        if(this.countB == this.countBM) {
            this.countB = 0;
            this.setMove((byte)3, Intent.BUFF, ((DamageInfo)this.damage.get(0)).base);
        } else if(this.count == this.countM){
            this.countB++;
            this.count = 0;
            this.setMove((byte)2, Intent.ATTACK, ((DamageInfo)this.damage.get(1)).base);
        } else {
            this.count++;
            this.countB++;
            this.setMove((byte)1, Intent.ATTACK, ((DamageInfo)this.damage.get(0)).base);
        }
    }

    @Override
    public void usePreBattleAction() {
        super.usePreBattleAction();
        CardCrawlGame.music.unsilenceBGM();
        AbstractDungeon.scene.fadeOutAmbiance();
        AbstractDungeon.getCurrRoom().playBgmInstantly("atsBgm/act2_faust_loop.ogg");
        UnlockTracker.markBossAsSeen(this.id);
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
