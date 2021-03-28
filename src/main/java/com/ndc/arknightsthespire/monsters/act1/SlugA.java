//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.ndc.arknightsthespire.monsters.act1;

import basemod.abstracts.CustomMonster;
import com.badlogic.gdx.math.MathUtils;
import com.esotericsoftware.spine.AnimationState;
import com.esotericsoftware.spine.Bone;
import com.megacrit.cardcrawl.actions.AbstractGameAction.AttackEffect;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.RollMoveAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.MonsterStrings;
import com.ndc.arknightsthespire.actions.ApplyDefAction;
import com.ndc.arknightsthespire.actions.PlayAnimationAction;

public class SlugA extends CustomMonster {
    public static final String ID = "ats:Slug A";
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
    private static final String ATLAS = "atsImg/monsters/Slugs/A/enemy_1007_slime.atlas";
    private static final String SKEL = "atsImg/monsters/Slugs/A/enemy_1007_slime.json";
    private Bone weapon;
    private int attackDamage;
    private int debuffDamage;
    private int arm = 0;
    private int res = 0;

    public SlugA() {
        this(-300.0F, 0.0F);
    }

    public SlugA(float x, float y) {
        super(NAME, ID, 15, -5.0F, 0.0F, 150.0F, 245.0F, (String)null, x, y);
        this.loadAnimation(ATLAS, SKEL, 1.7F);
        AnimationState.TrackEntry e = state.setAnimation(0, "Idle", true);
        e.setTime(e.getEndTime() * MathUtils.random());
        this.flipHorizontal = true;
        this.type = EnemyType.NORMAL;
        this.dialogX = (this.hb_x - 70.0F) * Settings.scale;
        this.dialogY -= (this.hb_y - 55.0F) * Settings.scale;

        if (AbstractDungeon.ascensionLevel >= 17) {

        } else {

        }

        if (AbstractDungeon.ascensionLevel >= 7) {
            this.setHp(11, 13);
        } else {
            this.setHp(10, 12);
        }

        if (AbstractDungeon.ascensionLevel >= 2) {
            this.attackDamage = 4;
        } else {
            this.attackDamage = 3;
        }

        this.damage.add(new DamageInfo(this, this.attackDamage));
    }

    @Override
    public void usePreBattleAction() {
        super.usePreBattleAction();
    }

    public void setDef(int a, int r) {
        this.arm = a;
        this.res = r;
    }

    public void takeTurn() {
        AbstractPlayer p = AbstractDungeon.player;
        AbstractDungeon.actionManager.addToBottom(new PlayAnimationAction(this, "Attack"));
        this.addToBot(new DamageAction(p, (DamageInfo)this.damage.get(0), AttackEffect.BLUNT_LIGHT));
        AbstractDungeon.actionManager.addToBottom(new RollMoveAction(this));
    }

    protected void getMove(int num) {
        this.setMove((byte)1, Intent.ATTACK, ((DamageInfo)this.damage.get(0)).base, 1, false);
    }

    public void die(){
        super.die();
        this.state.setAnimation(0, "Die", false);
    }

    static {
        monsterStrings = CardCrawlGame.languagePack.getMonsterStrings(ID);
        NAME = monsterStrings.NAME;
        MOVES = monsterStrings.MOVES;
        DIALOG = monsterStrings.DIALOG;
    }
}
