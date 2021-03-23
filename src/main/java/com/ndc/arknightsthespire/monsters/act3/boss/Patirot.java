//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.ndc.arknightsthespire.monsters.act3.boss;

import basemod.abstracts.CustomMonster;
import com.badlogic.gdx.math.MathUtils;
import com.esotericsoftware.spine.AnimationState;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
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
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.monsters.beyond.AwakenedOne;
import com.megacrit.cardcrawl.monsters.city.BronzeOrb;
import com.megacrit.cardcrawl.powers.*;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.unlock.UnlockTracker;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndAddToHandEffect;
import com.ndc.arknightsthespire.actions.*;
import com.ndc.arknightsthespire.cards.base.CardSPBase;
import com.ndc.arknightsthespire.cards.base.PositionType;
import com.ndc.arknightsthespire.cards.xCurse.SealedFloor;
import com.ndc.arknightsthespire.monsters.act3.Guerilla1;
import com.ndc.arknightsthespire.monsters.act3.Guerilla2;
import com.ndc.arknightsthespire.power.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

import static com.megacrit.cardcrawl.dungeons.AbstractDungeon.player;

public class Patirot extends CustomMonster {
    public static final String ID = "ats:Patriot";
    private static final MonsterStrings monsterStrings;
    public static final String NAME;
    public static final String[] MOVES;
    public static final String[] DIALOG;
    private static final String ATLAS = "atsImg/monsters/act_3/boss/enemy_1506_patrt.atlas";
    private static final String SKEL = "atsImg/monsters/act_3/boss/enemy_1506_patrt.json";
    private int attackDamage;
    private int arm = 0;
    private int res = 0;
    private int iceCounter = 0;
    private int iceCount = 5;
    private int str = 2;
    private int pstr = 3;
    public int phase = 1;
    private int frost = 1;
    public String curKey = "Idle_1";
    private boolean isUped = false;
    private boolean isFirst = true;
    public boolean isBlockLast = false;

    public Patirot() {
        this(-100.0F, 0.0F);
    }

    public Patirot(float x, float y) {
        super(NAME, ID, 450, -5.0F, 0.0F, 400.0F, 400.0F, (String)null, x, y);
        this.loadAnimation(ATLAS, SKEL, 1.3F);
        AnimationState.TrackEntry e = state.setAnimation(0, "Idle_1", true);
        e.setTime(e.getEndTime() * MathUtils.random());
        this.flipHorizontal = true;
        this.type = EnemyType.BOSS;
        this.dialogX = (this.hb_x - 70.0F) * Settings.scale;
        this.dialogY -= (this.hb_y - 55.0F) * Settings.scale;

        if (AbstractDungeon.ascensionLevel >= 19) {
            this.iceCount = 5;
            this.iceCounter = 3;
        } else {
            this.iceCount = 6;
            this.iceCounter = 4;
        }
        this.phase = 1;
        this.arm = 10;

        if (AbstractDungeon.ascensionLevel >= 9) {
            this.setHp(500);
        } else {
            this.setHp(450);
        }

        if (AbstractDungeon.ascensionLevel >= 4) {
            this.attackDamage = 18;
        } else {
            this.attackDamage = 17;
        }

        this.damage.add(new DamageInfo(this, this.attackDamage, DamageInfo.DamageType.NORMAL));
        this.damage.add(new DamageInfo(this, this.attackDamage + 18, DamageInfo.DamageType.NORMAL));
    }

    public void takeTurn() {

        AbstractPlayer p = AbstractDungeon.player;

        if(this.phase == 2) {
            if (this.iceCounter == this.iceCount) {
                this.iceCounter = 0;
            } else {
                this.iceCounter++;
            }
        }

        switch (this.nextMove) {
            case 0:
                this.setIdle("Move_1");
                this.addToBot(new ApplyPowerAction(this, this, new ForwardingPower(this, 15)));
                this.addToBot(new WaitAnimAction(this, 2.0F));
                break;
            case -3:
                if(this.halfDead && !this.hasPower(NoDamagePower.POWER_ID)) {
                    this.powers.add(new NoDamagePower(this));
                    AbstractDungeon.actionManager.addToBottom(new SpawnMonsterAction(new Guerilla1(-450.0F, 0.0F), true));
                    AbstractDungeon.actionManager.addToBottom(new SpawnMonsterAction(new Guerilla2(250.0F, 0.0F), true));
                }
                this.addToBot(new WaitAnimAction(this, 2.0F));
                break;
            case -2:
                this.addToBot(new PlayAnimationAction(this, "Attack_1"));
                this.addToBot(new AtsSFX("PAT_A1"));
                this.addToBot(new InstantDamageAction(p, (DamageInfo)this.damage.get(0), AttackEffect.SLASH_HORIZONTAL, true, true));
                this.addToBot(new InstantDamageAction(p, (DamageInfo)this.damage.get(0), AttackEffect.SLASH_HORIZONTAL, true, true));
                this.addToBot(new InstantDamageAction(p, (DamageInfo)this.damage.get(0), AttackEffect.SLASH_HORIZONTAL, true, true));
                this.addToBot(new InstantDamageAction(p, (DamageInfo)this.damage.get(0), AttackEffect.SLASH_HORIZONTAL, true, true));
                this.isBlockLast = false;
                break;
            case -1:
                this.addToBot(new HealAction(this, this, MathUtils.ceil(this.maxHealth / 5)));
                this.addToBot(new ApplyPowerAction(this, this, new InvinciblePower(this, 0), 0));
                this.addToBot(new ApplyPowerAction(this, this, new RegenerateMonsterPower(this, MathUtils.ceil(this.maxHealth / 5))));
                break;
            case 1:
                this.curKey = "Idle_2";
                this.addToBot(new PlayAnimationAction(this, "revive_3", true, "PAT_D2"));
                this.addToBot(new AtsSFX("PAT_D3"));
                this.addToBot(new WaitAnimAction(this, 2.8F));
                this.addToBot(new AtsSFX("PAT_D4"));
                this.addToBot(new RemoveSpecificPowerAction(this, null, NoDamagePower.POWER_ID));
                this.addToBot(new ApplyPowerAction(this, null, new ArmPower(this, 5), 5, true));
                this.phase = 2;
                this.halfDead = false;
                this.addToBot(new CanLoseAction());
                break;
            case 2:
                this.addToBot(new PlayAnimationAction(this, "Attack_2"));
                this.addToBot(new AtsSFX("PAT_A2"));
                this.addToBot(new DamageAction(p, (DamageInfo)this.damage.get(1), AttackEffect.SMASH, false, true));
                break;
            case 3:
                this.addToBot(new PlayAnimationAction(this, "Skill", true, "PAT_S1"));
                this.addToBot(new WaitAnimAction(this, 1.0F));
                this.addToBot(new AtsSFX("PAT_S2"));
                this.addToBot(new WaitAnimAction(this, 1.3F));
                this.addToBot(new RemoveRangedCardAction(this, "PAT_S3"));
        }

        AbstractDungeon.actionManager.addToBottom(new RollMoveAction(this));
    }

    protected void getMove(int num) {

        if(this.phase == 1) {
            if (this.isFirst) {
                this.setMove((byte) 0, Intent.UNKNOWN);
                this.isFirst = false;
            } else if (this.halfDead) {
                if(this.currentHealth == this.maxHealth) {
                    this.setMove((byte) 1, Intent.UNKNOWN);
                } else {
                    this.setMove(MOVES[1], (byte) -3, Intent.BUFF);
                }
            } else {
                if (!this.isBlockLast && this.hasPower(ForwardingPower.POWER_ID)) {
                    if(this.curKey != "Move_1") {
                        this.curKey = "Move_1";
                        this.state.addAnimation(0, "Move_1", true, 0.0F);
                    }
                    this.setMove(MOVES[0], (byte) -3, Intent.UNKNOWN);
                } else {
                    if(this.curKey != "Idle_1") this.setIdle("Idle_1");
                    this.setMove((byte) -2, Intent.ATTACK, this.damage.get(0).base, 4, true);
                }
            }
        } else if (this.phase == 2) {
            if(this.iceCounter == this.iceCount && this.canRemove()) {
                this.setMove((byte)3, Intent.STRONG_DEBUFF);
            } else {
                this.setMove((byte)2, Intent.ATTACK, ((DamageInfo)this.damage.get(1)).base);
            }
        }

        this.createIntent();
    }

    private boolean canRemove() {
        ArrayList<AbstractCard> draw = new ArrayList<>();
        ArrayList<AbstractCard> hand = new ArrayList<>();
        ArrayList<AbstractCard> disc = new ArrayList<>();

        if(AbstractDungeon.player.drawPile.size() > 0) {
            Iterator var1 = AbstractDungeon.player.drawPile.group.iterator();

            while (var1.hasNext()) {
                AbstractCard c = (AbstractCard) var1.next();
                if (c instanceof CardSPBase) {
                    CardSPBase sc = (CardSPBase) c;
                    if (sc.position == PositionType.MEDIC || sc.position == PositionType.SNIPER || sc.position == PositionType.SUPPORTER || sc.position == PositionType.CASTER) {
                        draw.add(c);
                    }
                }
            }
        }

        if(AbstractDungeon.player.hand.size() > 0) {
            Iterator var2 = AbstractDungeon.player.hand.group.iterator();

            while (var2.hasNext()) {
                AbstractCard c = (AbstractCard) var2.next();
                if (c instanceof CardSPBase) {
                    CardSPBase sc = (CardSPBase) c;
                    if (sc.position == PositionType.MEDIC || sc.position == PositionType.SNIPER || sc.position == PositionType.SUPPORTER || sc.position == PositionType.CASTER) {
                        hand.add(c);
                    }
                }
            }
        }

        if(AbstractDungeon.player.discardPile.size() > 0) {
            Iterator var3 = AbstractDungeon.player.discardPile.group.iterator();

            while (var3.hasNext()) {
                AbstractCard c = (AbstractCard) var3.next();
                if (c instanceof CardSPBase) {
                    CardSPBase sc = (CardSPBase) c;
                    if (sc.position == PositionType.MEDIC || sc.position == PositionType.SNIPER || sc.position == PositionType.SUPPORTER || sc.position == PositionType.CASTER) {
                        disc.add(c);
                    }
                }
            }
        }

        Random rand = new Random();

        ArrayList<AbstractCard> rc = new ArrayList();
        AbstractCard c1;
        AbstractCard c2;
        AbstractCard c3;

        if(draw.size() > 0) {
            c1 = draw.get(draw.size() == 1 ? 0 : rand.nextInt(draw.size() - 1));
            rc.add(c1);
        } else {
            c1 = null;
        }
        if(hand.size() > 0) {
            c2 = hand.get(hand.size() == 1 ? 0 : rand.nextInt(hand.size() - 1));
            rc.add(c2);
        } else {
            c2 = null;
        }
        if(disc.size() > 0) {
            c3 = disc.get(disc.size() == 1 ? 0 : rand.nextInt(disc.size() - 1));
            rc.add(c3);
        } else {
            c3 = null;
        }

        if(rc.size() > 0) {
            Random random = new Random();
            AbstractCard r = rc.get(rc.size() == 1 ? 0 : random.nextInt(rc.size() - 1));
            if(c1 != null) {
                if(r == c1) {
                    return true;
                }
            } else if(c2 != null) {
                if(r == c2) {
                    return true;
                }
            } else if(c3 != null) {
                if(r == c3) {
                    return true;
                }
            }
        }

        return false;
    }

    @Override
    public void usePreBattleAction() {
        super.usePreBattleAction();
        this.addToBot(new CannotLoseAction());
        this.addToBot(new ApplyPowerAction(this, this, new ArmPower(this, this.arm), this.arm));
        this.addToBot(new ApplyPowerAction(player, this, new MinePower(15)));
        UnlockTracker.markBossAsSeen(this.id);
        CardCrawlGame.music.unsilenceBGM();
        AbstractDungeon.scene.fadeOutAmbiance();
        AbstractDungeon.getCurrRoom().playBgmInstantly("atsBgm/act3_pat_loop.ogg");
    }

    public void setIdle(String key) {
        this.curKey = key;
        this.state.setAnimation(0, key, true);
    }

    public void damage(DamageInfo info) {
        super.damage(info);

        if (this.currentHealth <= 0 && !this.halfDead && this.phase == 1) {


            //this.addToBot(new ApplyPowerAction(this, this, new InvinciblePower(this, 0), 0, true));
            //this.addToBot(new ApplyPowerAction(this, this, new RegenerateMonsterPower(this, MathUtils.ceil(this.maxHealth / 5)), MathUtils.ceil(this.maxHealth / 5), true));

            if (AbstractDungeon.getCurrRoom().cannotLose) {
                this.halfDead = true;
            }

            AbstractDungeon.actionManager.addToBottom(new RollMoveAction(this));

            this.addToTop(new ClearCardQueueAction());

            if (AbstractDungeon.ascensionLevel >= 9) {
                this.maxHealth = 500;
            } else {
                this.maxHealth = 450;
            }

            if (Settings.isEndless && AbstractDungeon.player.hasBlight("ToughEnemies")) {
                float mod = AbstractDungeon.player.getBlight("ToughEnemies").effectFloat();
                this.maxHealth = (int)((float)this.maxHealth * mod);
            }

            if (ModHelper.isModEnabled("MonsterHunter")) {
                this.currentHealth = (int)((float)this.currentHealth * 1.5F);
            }

            this.powers.clear();


            this.addToBot(new AtsSFX("PAT_D1"));
            this.state.setAnimation(0, "revive_1", false);
            this.state.addAnimation(0, "revive_2", true, 0.0F);
            this.addToBot(new ApplyPowerAction(player, this, new OreMistPower(player, this, 1)));

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
