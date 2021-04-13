//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.ndc.arknightsthespire.monsters.act1.boss;

import basemod.abstracts.CustomMonster;
import com.badlogic.gdx.math.MathUtils;
import com.esotericsoftware.spine.AnimationState;
import com.megacrit.cardcrawl.actions.AbstractGameAction.AttackEffect;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDrawPileAction;
import com.megacrit.cardcrawl.actions.common.RollMoveAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.cards.curses.Normality;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.MonsterStrings;
import com.megacrit.cardcrawl.powers.BlurPower;
import com.megacrit.cardcrawl.powers.BufferPower;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import com.megacrit.cardcrawl.powers.WeakPower;
import com.megacrit.cardcrawl.unlock.UnlockTracker;
import com.ndc.arknightsthespire.actions.ApplyDefAction;
import com.ndc.arknightsthespire.actions.AtsSFX;
import com.ndc.arknightsthespire.actions.PlayAnimationAction;
import com.ndc.arknightsthespire.actions.WaitAnimAction;
import com.ndc.arknightsthespire.character.AtsEnum;
import com.ndc.arknightsthespire.power.EvadePower;
import com.ndc.arknightsthespire.power.NewStrPower;

public class Crown extends CustomMonster {
    public static final String ID = "ats:Crown";
    private static final MonsterStrings monsterStrings;
    public static final String NAME;
    public static final String[] MOVES;
    public static final String[] DIALOG;
    private static final String ATLAS = "atsImg/monsters/act_1/boss/enemy_1502_crowns.atlas";
    private static final String SKEL = "atsImg/monsters/act_1/boss/enemy_1502_crowns.json";
    private int attackDamage;
    private int arm = 0;
    private int res = 0;
    private int str = 2;
    private int dmg = 5;
    private int evade = 30;
    private boolean isUped = false;

    public Crown() {
        this(0.0F, 0.0F);
    }

    public Crown(float x, float y) {
        super(NAME, ID, 200, -5.0F, 0.0F, 300.0F, 330.0F, (String)null, x, y);
        this.loadAnimation(ATLAS, SKEL, 1.5F);
        AnimationState.TrackEntry e = state.setAnimation(0, "Idle", true);
        e.setTime(e.getEndTime() * MathUtils.random());
        this.flipHorizontal = true;
        this.type = EnemyType.BOSS;
        this.dialogX = (this.hb_x - 70.0F) * Settings.scale;
        this.dialogY -= (this.hb_y - 55.0F) * Settings.scale;

        if (AbstractDungeon.ascensionLevel >= 19) {
            this.evade = 2;
            this.str = 2;
        } else {
            this.evade = 3;
            this.str = 1;
        }

        if (AbstractDungeon.ascensionLevel >= 9) {
            this.setHp(280);
        } else {
            this.setHp(250);
        }

        if (AbstractDungeon.ascensionLevel >= 4) {
            this.attackDamage = 9;
            this.dmg = 8;
        } else {
            this.attackDamage = 8;
            this.dmg = 7;
        }

        this.damage.add(new DamageInfo(this, this.attackDamage, DamageInfo.DamageType.NORMAL));
        this.damage.add(new DamageInfo(this, this.dmg, DamageInfo.DamageType.NORMAL));
    }

    public void takeTurn() {
        AbstractPlayer p = AbstractDungeon.player;
        switch (this.nextMove) {
            case 1:
                this.addToBot(new AtsSFX("CROWN"));
                this.addToBot(new MakeTempCardInDrawPileAction(new Normality(), 1, true, true));
                break;
            case 2:
                this.addToBot(new PlayAnimationAction(this, "Attack"));
                this.addToBot(new DamageAction(p, (DamageInfo)this.damage.get(0), AttackEffect.SLASH_DIAGONAL, false));
                break;
            case 3:
                this.addToBot(new PlayAnimationAction(this, "Attack"));
                this.addToBot(new DamageAction(p, (DamageInfo)this.damage.get(1), AttackEffect.SLASH_HORIZONTAL, false));
                this.addToBot(new DamageAction(p, (DamageInfo)this.damage.get(1), AttackEffect.SLASH_VERTICAL, true));
                break;
            case 4:
                this.addToBot(new ApplyPowerAction(p, this, new VulnerablePower(p, 2, true), 2));
        }

        AbstractDungeon.actionManager.addToBottom(new RollMoveAction(this));
    }

    protected void getMove(int num) {
        if(this.lastMove((byte) 3)) {
            this.setMove((byte) 4, Intent.DEBUFF);
        } else if(this.lastMove((byte) 2)) {
            this.setMove((byte)3, Intent.ATTACK, ((DamageInfo)this.damage.get(1)).base, 1, true);
        } else if(this.lastMove((byte) 1) || this.lastMove((byte) 4)){
            this.setMove((byte)2, Intent.ATTACK, ((DamageInfo)this.damage.get(0)).base);
        } else {
            this.setMove((byte)1, Intent.STRONG_DEBUFF);
        }
        this.createIntent();
    }

    @Override
    public void usePreBattleAction() {
        super.usePreBattleAction();
        UnlockTracker.markBossAsSeen(this.id);
        CardCrawlGame.music.unsilenceBGM();
        AbstractDungeon.scene.fadeOutAmbiance();
        AbstractDungeon.getCurrRoom().playBgmInstantly("atsBgm/act1_crown_loop.ogg");
        this.addToBot(new ApplyPowerAction(this, this, new EvadePower(this, this.evade, this.str)));
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
