//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.ndc.arknightsthespire.monsters.act3;

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
import com.megacrit.cardcrawl.powers.AngryPower;
import com.megacrit.cardcrawl.powers.MinionPower;
import com.ndc.arknightsthespire.actions.PlayAnimationAction;
import com.ndc.arknightsthespire.power.ArmPower;

public class Guerilla2 extends CustomMonster {
    public static final String ID = "ats:Guerilla 2";
    private static final MonsterStrings monsterStrings;
    public static final String NAME;
    public static final String[] MOVES;
    public static final String[] DIALOG;
    private static final String ATLAS = "atsImg/monsters/act_3/enemy_1083_sotiab_2.atlas";
    private static final String SKEL = "atsImg/monsters/act_3/enemy_1083_sotiab_2.json";
    private int attackDamage;
    private int def;
    private int arm = 0;
    private int res = 0;
    private int atk = 2;

    public Guerilla2(float x, float y) {
        super(NAME, ID, 60, -5.0F, 0.0F, 200.0F, 250.0F, (String)null, x, y);
        this.loadAnimation(ATLAS, SKEL, 1.3F);
        this.flipHorizontal = true;
        this.type = EnemyType.NORMAL;
        this.dialogX = (this.hb_x - 70.0F) * Settings.scale;
        this.dialogY -= (this.hb_y - 55.0F) * Settings.scale;

        this.def = 3;
        this.atk = 1;

        if (AbstractDungeon.ascensionLevel >= 9) {
            this.setHp(62, 68);
        } else {
            this.setHp(60, 66);
        }

        this.damage.add(new DamageInfo(this, 8, DamageInfo.DamageType.NORMAL));

        this.state.setAnimation(0, "Start", false);
        this.state.addAnimation(0, "Idle", true, 0.0F);
        addToBot(new ApplyPowerAction(this, this, new ArmPower(this, this.def)));
        addToBot(new ApplyPowerAction(this, this, new AngryPower(this, this.atk)));
    }

    public void takeTurn() {
        AbstractPlayer p = AbstractDungeon.player;
        this.addToBot(new PlayAnimationAction(this, "Attack"));
        this.addToBot(new DamageAction(p, (DamageInfo)this.damage.get(0), AttackEffect.SLASH_DIAGONAL));
        AbstractDungeon.actionManager.addToBottom(new RollMoveAction(this));
    }

    protected void getMove(int num) {
        this.setMove((byte)0, Intent.ATTACK, ((DamageInfo)this.damage.get(0)).base);
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
