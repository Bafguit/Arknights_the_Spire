

package com.ndc.arknightsthespire.character;

import basemod.abstracts.CustomPlayer;
import basemod.animations.SpineAnimation;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.esotericsoftware.spine.AnimationState;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.cards.red.Bash;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.EnergyManager;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.events.beyond.SpireHeart;
import com.megacrit.cardcrawl.events.city.Vampires;
import com.megacrit.cardcrawl.helpers.CardHelper;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.helpers.ScreenShake;
import com.megacrit.cardcrawl.localization.CharacterStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.screens.CharSelectInfo;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;
import com.megacrit.cardcrawl.unlock.UnlockTracker;
import com.ndc.arknightsthespire.CardColors;
import com.ndc.arknightsthespire.relics.HeartsK;

import java.util.ArrayList;

import static com.megacrit.cardcrawl.actions.AbstractGameAction.AttackEffect;

public class CharacterW extends CustomPlayer {

    @Override
    public void renderPlayerImage(SpriteBatch sb) {
        sr.setPremultipliedAlpha(false);
        super.renderPlayerImage(sb);
        sr.setPremultipliedAlpha(true);
    }

    public static final int ENERGY_PER_TURN = 3; // how much energy you get every turn
    public static final int STARTING_HP = 72;
    public static final int MAX_HP = 72;
    public static final int STARTING_GOLD = 99;
    public static final int HAND_SIZE = 5;
    private static final int ORB_SLOTS = 0;

    private static final CharacterStrings characterStrings;
    private static final String[] NAMES;
    private static final String[] TEXT;

    public static final String MY_CHARACTER_SHOULDER_2 = "atsImg/char/shoulder_w.png"; // campfire pose
    public static final String MY_CHARACTER_SHOULDER_1 = "atsImg/char/shoulder_w.png"; // another campfire pose
    public static final String MY_CHARACTER_CORPSE = "atsImg/char/corpse_w.png"; // dead corpse
    public static final String MY_CHARACTER_SKELETON_ATLAS = "atsImg/char/w/enemy_1504_cqbw.atlas"; // spine animation atlas
    public static final String MY_CHARACTER_SKELETON_JSON = "atsImg/char/w/enemy_1504_cqbw.json"; // spine animation json
    public static final String[] orbTextures = {
            "atsImg/char/orb/layer.png"};

    static {
        characterStrings = CardCrawlGame.languagePack.getCharacterString("ats:W");
        NAMES = characterStrings.NAMES;
        TEXT = characterStrings.TEXT;
    }

    public static int defaultArm = 2;
    public static int defaultRes = 0;
    public static int defaultAtk = 6;

    public CharacterW(String name) {
        super(name, AtsEnum.W_CLASS, orbTextures, "atsImg/char/orb/vfx.png", new SpineAnimation(
                MY_CHARACTER_SKELETON_ATLAS, MY_CHARACTER_SKELETON_JSON, 1.5F));
        this.loadAnimation(MY_CHARACTER_SKELETON_ATLAS, MY_CHARACTER_SKELETON_JSON, 1.5F);
        AnimationState.TrackEntry e = state.setAnimation(0, "Idle", true);
        e.setTime(e.getEndTime() * MathUtils.random());

        this.dialogX = (this.drawX + 0.0F * Settings.scale); // set location for text bubbles
        this.dialogY = (this.drawY + 220.0F * Settings.scale); // you can just copy these values

        initializeClass(null, MY_CHARACTER_SHOULDER_2, // required call to load textures and setup energy/loadout
                MY_CHARACTER_SHOULDER_1,
                MY_CHARACTER_CORPSE,
                getLoadout(), 0.0F, -5.0F, 220.0F, 290.0F, new EnergyManager(ENERGY_PER_TURN));// if you're using modified versions of base game animations or made animations in spine make sure to include this bit and the following lines

    }

    public ArrayList<String> getStartingDeck() { // starting deck 'nuff said
        ArrayList<String> retVal = new ArrayList<>();
        retVal.add("ats:Strike C");
        retVal.add("ats:Strike G");
        retVal.add("ats:Strike S");
        retVal.add("ats:Strike V");
        retVal.add("ats:Armor Crushing Shot");
        retVal.add("ats:Defend");
        retVal.add("ats:Defend");
        retVal.add("ats:Defend");
        retVal.add("ats:Defend");
        retVal.add("ats:Emotion Absorption");
        return retVal;
    }

    public ArrayList<String> getStartingRelics() { // starting relics - also simple
        ArrayList<String> retVal = new ArrayList<>();
        retVal.add(HeartsK.ID);
        UnlockTracker.markRelicAsSeen(HeartsK.ID);
        return retVal;
    }



    public CharSelectInfo getLoadout() { // the rest of the character loadout so includes your character select screen info plus hp and starting gold
        return new CharSelectInfo(NAMES[0], TEXT[0],
                STARTING_HP, MAX_HP, ORB_SLOTS, STARTING_GOLD, HAND_SIZE,
                this, getStartingRelics(), getStartingDeck(), false);
    }

    @Override
    public String getTitle(PlayerClass playerClass) {
        return NAMES[0];
    }

    @Override
    public AbstractCard.CardColor getCardColor() {
        return CardColors.AbstractCardEnum.DOCTOR_COLOR;
    }

    @Override
    public Color getCardRenderColor() {
        return CardHelper.getColor(255, 0, 0);
    }

    @Override
    public AbstractCard getStartCardForEvent() {
        return new Bash();
    }

    @Override
    public Color getCardTrailColor() {
        return CardHelper.getColor(255, 0, 0);
    }

    @Override
    public int getAscensionMaxHPLoss() {
        return 0;
    }

    @Override
    public BitmapFont getEnergyNumFont() {
        return FontHelper.energyNumFontRed;
    }

    @Override
    public void doCharSelectScreenSelectEffect() {
        CardCrawlGame.sound.playA("ATTACK_HEAVY", MathUtils.random(-0.2F, 0.2F));
        CardCrawlGame.screenShake.shake(ScreenShake.ShakeIntensity.MED, ScreenShake.ShakeDur.SHORT, true);
    }

    @Override
    public String getCustomModeCharacterButtonSoundKey() {
        return "ATTACK_HEAVY";
    }

    @Override
    public String getLocalizedCharacterName() {
        return NAMES[0];
    }

    @Override
    public AbstractPlayer newInstance() {
        return new CharacterW(this.name);
    }

    @Override
    public void damage(DamageInfo info)
    {
        if (AbstractDungeon.getCurrRoom().combatEvent && info.owner != null && info.type != DamageInfo.DamageType.THORNS && info.output > currentBlock) {
            AnimationState.TrackEntry e = state.setAnimation(0, "Die_2", false);
            state.addAnimation(0,"Idle", true, 0.0f);
            e.setTimeScale(1f);
        }
        super.damage(info);
    }

    @Override
    public void useCard(AbstractCard c, AbstractMonster monster, int energyOnUse) {
        if (c.type == AbstractCard.CardType.ATTACK) {
            AnimationState.TrackEntry e = state.setAnimation(0, "Attack", false);
            state.addAnimation(0,"Idle", true, 0.0f);
            e.setTimeScale(1f);
        }

        c.calculateCardDamage(monster);
        if (c.cost == -1 && EnergyPanel.totalCount < energyOnUse && !c.ignoreEnergyOnUse) {
            c.energyOnUse = EnergyPanel.totalCount;
        }

        if (c.cost == -1 && c.isInAutoplay) {
            c.freeToPlayOnce = true;
        }

        c.use(this, monster);
        AbstractDungeon.actionManager.addToBottom(new UseCardAction(c, monster));
        if (!c.dontTriggerOnUseCard) {
            this.hand.triggerOnOtherCardPlayed(c);
        }

        this.hand.removeCard(c);
        this.cardInUse = c;
        c.target_x = (float)(Settings.WIDTH / 2);
        c.target_y = (float)(Settings.HEIGHT / 2);
        if (c.costForTurn > 0 && !c.freeToPlay() && !c.isInAutoplay && (!this.hasPower("Corruption") || c.type != AbstractCard.CardType.SKILL)) {
            this.energy.use(c.costForTurn);
        }

        if (!this.hand.canUseAnyCard() && !this.endTurnQueued) {
            AbstractDungeon.overlayMenu.endTurnButton.isGlowing = true;
        }
/*
        c.freeToPlayOnce = false;
        if(c.isCostModifiedForTurn) {
            c.costForTurn = c.cost;
            c.isCostModifiedForTurn = false;
        }*/
    }

    @Override
    public String getSpireHeartText() {
        return SpireHeart.DESCRIPTIONS[8];
    }

    @Override
    public Color getSlashAttackColor() {
        return CardHelper.getColor(255, 255, 255);
    }

    @Override
    public AttackEffect[] getSpireHeartSlashEffect() {
        return new AttackEffect[]{
                AttackEffect.SLASH_HORIZONTAL,
                AttackEffect.SLASH_VERTICAL,
                AttackEffect.SLASH_HORIZONTAL,
                AttackEffect.SLASH_VERTICAL,
                AttackEffect.SLASH_HORIZONTAL,
                AttackEffect.SLASH_VERTICAL,
                AttackEffect.SLASH_HORIZONTAL,
                AttackEffect.SLASH_VERTICAL,
                AttackEffect.SLASH_HORIZONTAL,
                AttackEffect.SLASH_HEAVY
        };
    }

    @Override
    public String getVampireText() {
        return Vampires.DESCRIPTIONS[0];
    }
}

