//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.ndc.arknightsthespire.monsters.act3.boss;

import basemod.abstracts.CustomMonster;
import com.badlogic.gdx.math.MathUtils;
import com.esotericsoftware.spine.AnimationState;
import com.megacrit.cardcrawl.actions.AbstractGameAction.AttackEffect;
import com.megacrit.cardcrawl.actions.ClearCardQueueAction;
import com.megacrit.cardcrawl.actions.GameActionManager;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.actions.unique.CanLoseAction;
import com.megacrit.cardcrawl.actions.unique.CannotLoseAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ModHelper;
import com.megacrit.cardcrawl.localization.MonsterStrings;
import com.megacrit.cardcrawl.monsters.ending.CorruptHeart;
import com.megacrit.cardcrawl.powers.*;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.unlock.UnlockTracker;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndAddToHandEffect;
import com.ndc.arknightsthespire.actions.AtsSFX;
import com.ndc.arknightsthespire.actions.PlayAnimationAction;
import com.ndc.arknightsthespire.actions.WaitAnimAction;
import com.ndc.arknightsthespire.cards.xCurse.SealedFloor;
import com.ndc.arknightsthespire.power.FrostPower;
import com.ndc.arknightsthespire.power.LoseAtkPower;

import java.util.ArrayList;
import java.util.Iterator;

import static com.megacrit.cardcrawl.dungeons.AbstractDungeon.player;

public class FrostWS extends CustomMonster {
    public static final String ID = "ats:FrostWS";
    private static final MonsterStrings monsterStrings;
    public static final String NAME;
    public static final String[] MOVES;
    public static final String[] DIALOG;
    private static final String ATLAS = "atsImg/monsters/act_3/boss/enemy_1510_frstar2.atlas";
    private static final String SKEL = "atsImg/monsters/act_3/boss/enemy_1510_frstar2.json";
    private int attackDamage;
    private int arm = 0;
    private int res = 0;
    private int iceCounter = 0;
    private int iceCount = 5;
    private int str = 2;
    private int pstr = 3;
    private int frost = 1;
    private boolean isUped = false;
    private boolean isDied = false;

    public FrostWS() {
        this(0.0F, 0.0F);
    }

    public FrostWS(float x, float y) {
        super(NAME, ID, 350, -5.0F, 0.0F, 400.0F, 330.0F, (String)null, x, y);
        this.loadAnimation(ATLAS, SKEL, 1.5F);
        AnimationState.TrackEntry e = state.setAnimation(0, "Idle", true);
        e.setTime(e.getEndTime() * MathUtils.random());
        this.flipHorizontal = true;
        this.type = EnemyType.BOSS;
        this.dialogX = (this.hb_x - 70.0F) * Settings.scale;
        this.dialogY -= (this.hb_y - 55.0F) * Settings.scale;

        if (AbstractDungeon.ascensionLevel >= 19) {
            this.str = 8;
            this.frost = 2;
            this.pstr = 6;
        } else {
            this.str = 5;
            this.frost = 1;
            this.pstr = 4;
        }
        this.iceCount = 4;
        this.iceCounter = this.iceCount;

        if (AbstractDungeon.ascensionLevel >= 9) {
            this.setHp(380);
        } else {
            this.setHp(350);
        }

        if (AbstractDungeon.ascensionLevel >= 4) {
            this.attackDamage = 27;
        } else {
            this.attackDamage = 25;
        }

        this.damage.add(new DamageInfo(this, this.attackDamage, DamageInfo.DamageType.NORMAL));
        this.damage.add(new DamageInfo(this, this.attackDamage + 15, DamageInfo.DamageType.NORMAL));
    }

    public void takeTurn() {
        if(this.halfDead) {
            this.addToBot(new HealAction(this, this, this.maxHealth));
            this.addToBot(new ApplyPowerAction(this, this, new FrostPower(this, this.frost, true), this.frost + 1));
            this.addToBot(new ApplyPowerAction(this, this, new StrengthPower(this, this.str)));
            this.addToBot(new ApplyPowerAction(this, this, new BufferPower(this, 4), 4));
            this.halfDead = false;
            this.addToBot(new CanLoseAction());
        }

        AbstractPlayer p = AbstractDungeon.player;
        switch (this.nextMove) {
            case 1:
                this.addToBot(new PlayAnimationAction(this, "Attack"));
                this.addToBot(new WaitAnimAction(this, 0.4F));
                this.addToBot(new AtsSFX("CASTER"));
                this.addToBot(new DamageAction(p, (DamageInfo)this.damage.get(0), AttackEffect.BLUNT_LIGHT, false, false));
                break;
            case 2:
                this.addToBot(new PlayAnimationAction(this, "Attack"));
                this.addToBot(new WaitAnimAction(this, 0.4F));
                this.addToBot(new AtsSFX("CASTER"));
                this.addToBot(new DamageAction(p, (DamageInfo)this.damage.get(0), AttackEffect.BLUNT_LIGHT, false, false));
                this.addToBot(new ApplyPowerAction(p, this, new StrengthPower(p, -this.pstr), -this.pstr));
                if(this.isDied) this.addToBot(new ApplyPowerAction(p, this, new DexterityPower(p, -this.pstr), -this.pstr));
                this.addToBot(new ApplyPowerAction(p, this, new LoseAtkPower(p, this.pstr, this.isDied ? true : false), this.pstr));
                break;
            case 3:
                this.addToBot(new PlayAnimationAction(this, "Skill_3", true, "FROST_I1"));
                this.addToBot(new WaitAnimAction(this, 2.8F));
                this.addToBot(new AtsSFX("FROST_I2"));
                this.addToBot(new DamageAction(p, (DamageInfo)this.damage.get(1), AttackEffect.BLUNT_HEAVY, false, false));
                this.addToBot(new ApplyPowerAction(p, this, new WeakPower(p, 2, true), 2));
                this.addToBot(new ApplyPowerAction(p, this, new FrailPower(p, 2, true), 2));
                break;
            case 4:
                this.addToBot(new ApplyPowerAction(p, this, new WeakPower(p, 2, true), 2));
                this.addToBot(new ApplyPowerAction(p, this, new FrailPower(p, 2, true), 2));
                break;
            case 5:
                /*
                this.addToBot(new AtsSFX("FROST_I1"));
                this.addToBot(new WaitAnimAction(this, 0.5F));
                this.addToBot(new PlayAnimationAction(this, "Skill_1"));
                this.addToBot(new WaitAnimAction(this, 1.0F));*/
                this.addToBot(new AtsSFX("FROST_A"));
                this.addIce();
                break;
        }

        if(this.iceCounter == this.iceCount) {
            this.iceCounter = 0;
        } else {
            this.iceCounter++;
        }
        AbstractDungeon.actionManager.addToBottom(new RollMoveAction(this));
    }

    protected void getMove(int num) {

        if(this.iceCounter == this.iceCount && this.canAddIce()) {
            this.setMove((byte) 5, Intent.UNKNOWN);
        } else if(this.lastMove((byte) 2)){
            if(this.isDied) {
                this.setMove((byte)3, Intent.ATTACK_DEBUFF, ((DamageInfo)this.damage.get(1)).base);
            } else {
                this.setMove((byte) 4, Intent.STRONG_DEBUFF);
            }
        } else if(this.lastMove((byte) 1)){
            this.setMove((byte)2, Intent.ATTACK_DEBUFF, ((DamageInfo)this.damage.get(0)).base);
        } else {
            this.setMove((byte)1, Intent.ATTACK, ((DamageInfo)this.damage.get(0)).base);
        }
        this.createIntent();
    }

    private boolean canAddIce() {
        ArrayList<AbstractCard> ices = new ArrayList<>();

        for(AbstractCard c : player.hand.group) {
            if(c.cardID == SealedFloor.ID) {
                ices.add(c);
            }
        }

        if(ices.size() > 8) return false;
        else return true;
    }

    private void addIce() {
        if(player.hand.size() == 10) {
            ArrayList<AbstractCard> cards = new ArrayList<>();

            for(AbstractCard c : player.hand.group) {
                if(c.cardID != SealedFloor.ID) {
                    cards.add(c);
                }
            }

            if(cards.size() > 1) {
                AbstractCard c = cards.get(cards.size() - 1);
                player.hand.moveToDiscardPile(c);
                c.triggerOnManualDiscard();
                GameActionManager.incrementDiscard(false);
            }
        }
        this.addToBot(new VFXAction(new ShowCardAndAddToHandEffect(new SealedFloor())));
    }

    @Override
    public void usePreBattleAction() {
        super.usePreBattleAction();
        this.addToBot(new CannotLoseAction());
        this.addToBot(new ApplyPowerAction(this, this, new FrostPower(this, this.frost, false), this.frost));
        UnlockTracker.markBossAsSeen(this.id);
        CardCrawlGame.music.unsilenceBGM();
        AbstractDungeon.scene.fadeOutAmbiance();
        AbstractDungeon.getCurrRoom().playBgmInstantly("atsBgm/act3_frost_loop.ogg");
    }

    public void damage(DamageInfo info) {
        super.damage(info);

        if (this.currentHealth <= 0 && !this.halfDead && !this.isDied) {

            if (AbstractDungeon.getCurrRoom().cannotLose) {
                this.halfDead = true;
            }

            Iterator s = this.powers.iterator();

            AbstractPower p;
            while(s.hasNext()) {
                p = (AbstractPower)s.next();
                p.onDeath();
                p.onRemove();
                addToBot(new RemoveSpecificPowerAction(this, this, p));
            }

            s = AbstractDungeon.player.relics.iterator();

            while(s.hasNext()) {
                AbstractRelic r = (AbstractRelic)s.next();
                r.onMonsterDeath(this);
            }

            AbstractDungeon.actionManager.addToBottom(new RollMoveAction(this));

            this.addToTop(new ClearCardQueueAction());
            this.isDied = true;

            if (AbstractDungeon.ascensionLevel >= 9) {
                this.maxHealth = 440;
            } else {
                this.maxHealth = 400;
            }

            if (Settings.isEndless && AbstractDungeon.player.hasBlight("ToughEnemies")) {
                float mod = AbstractDungeon.player.getBlight("ToughEnemies").effectFloat();
                this.maxHealth = (int)((float)this.maxHealth * mod);
            }

            if (ModHelper.isModEnabled("MonsterHunter")) {
                this.currentHealth = (int)((float)this.currentHealth * 1.5F);
            }

            AbstractDungeon.actionManager.addToBottom(new SetMoveAction(this, (byte)5, Intent.UNKNOWN));

            this.addToBot(new PlayAnimationAction(this, "Skill_2", true, "FROST_R1"));
            this.addToBot(new WaitAnimAction(this, 6.0F));
            this.addToBot(new AtsSFX("FROST_R2"));

            this.powers.clear();
        }
    }

    public void setDef(int a, int r) {
        this.arm = a;
        this.res = r;
    }

    public void die() {
        if (!AbstractDungeon.getCurrRoom().cannotLose) {
            super.die();
            this.addToBot(new PlayAnimationAction(this, "Die"));
        }
    }

    static {
        monsterStrings = CardCrawlGame.languagePack.getMonsterStrings(ID);
        NAME = monsterStrings.NAME;
        MOVES = monsterStrings.MOVES;
        DIALOG = monsterStrings.DIALOG;
    }
}
