

package com.ndc.arknightsthespire.character;

import basemod.abstracts.CustomPlayer;
import basemod.animations.SpineAnimation;
import basemod.animations.SpriterAnimation;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.esotericsoftware.spine.AnimationState;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.red.Bash;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.EnergyManager;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.events.beyond.SpireHeart;
import com.megacrit.cardcrawl.events.city.Vampires;
import com.megacrit.cardcrawl.helpers.CardHelper;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.helpers.ScreenShake;
import com.megacrit.cardcrawl.localization.CharacterStrings;
import com.megacrit.cardcrawl.screens.CharSelectInfo;
import com.megacrit.cardcrawl.unlock.UnlockTracker;
import com.ndc.arknightsthespire.CardColors;

import java.util.ArrayList;

import static com.megacrit.cardcrawl.actions.AbstractGameAction.AttackEffect;

public class CharacterDoctor extends CustomPlayer {

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

    public static final String MY_CHARACTER_SHOULDER_2 = "img/char/shoulder2.png"; // campfire pose
    public static final String MY_CHARACTER_SHOULDER_1 = "img/char/shoulder1.png"; // another campfire pose
    public static final String MY_CHARACTER_CORPSE = "img/char/corpse.png"; // dead corpse
    public static final String MY_CHARACTER_SKELETON_ATLAS = "img/char/spine/doctor_anim.atlas"; // spine animation atlas
    public static final String MY_CHARACTER_SKELETON_JSON = "img/char/spine/doctor_anim.json"; // spine animation json
    public static final String[] orbTextures = {
            "img/char/orb/layer.png"};

    static {
        characterStrings = CardCrawlGame.languagePack.getCharacterString("ats:Doctor");
        NAMES = characterStrings.NAMES;
        TEXT = characterStrings.TEXT;
    }

    public CharacterDoctor (String name) {
        super(name, ATSCharacterEnum.DOCTOR_CLASS, orbTextures, "img/char/orb/vfx.png", new SpineAnimation(
                MY_CHARACTER_SKELETON_ATLAS, MY_CHARACTER_SKELETON_JSON, 1F));

        this.dialogX = (this.drawX + 0.0F * Settings.scale); // set location for text bubbles
        this.dialogY = (this.drawY + 220.0F * Settings.scale); // you can just copy these values

        initializeClass(null, MY_CHARACTER_SHOULDER_2, // required call to load textures and setup energy/loadout
                MY_CHARACTER_SHOULDER_1,
                MY_CHARACTER_CORPSE,
                getLoadout(), 20.0F, -10.0F, 220.0F, 290.0F, new EnergyManager(ENERGY_PER_TURN));

        loadAnimation(MY_CHARACTER_SKELETON_ATLAS, MY_CHARACTER_SKELETON_JSON, 1.0F); // if you're using modified versions of base game animations or made animations in spine make sure to include this bit and the following lines

        /*
        AnimationState.TrackEntry e = this.state.setAnimation(0, MY_CHARACTER_SKELETON_JSON, true);
        e.setTime(e.getEndTime() * MathUtils.random());
         */

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
        retVal.add("ats:Sanity");
        UnlockTracker.markRelicAsSeen("ats:Sanity");
        return retVal;
    }



    public CharSelectInfo getLoadout() { // the rest of the character loadout so includes your character select screen info plus hp and starting gold
        return new CharSelectInfo(NAMES[0], TEXT[0],
                STARTING_HP, MAX_HP, ORB_SLOTS, STARTING_GOLD, HAND_SIZE,
                this, getStartingRelics(), getStartingDeck(), false);
    }

    @Override
    public String getTitle(PlayerClass playerClass) {
        return "Doctor";
    }

    @Override
    public AbstractCard.CardColor getCardColor() {
        return CardColors.AbstractCardEnum.DOCTOR_COLOR;
    }

    @Override
    public Color getCardRenderColor() {
        return CardHelper.getColor(255, 255, 255);
    }

    @Override
    public AbstractCard getStartCardForEvent() {
        return new Bash();
    }

    @Override
    public Color getCardTrailColor() {
        return CardHelper.getColor(255, 255, 255);
    }

    @Override
    public int getAscensionMaxHPLoss() {
        return 0;
    }

    @Override
    public BitmapFont getEnergyNumFont() {
        return FontHelper.energyNumFontBlue;
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
        return new CharacterDoctor(this.name);
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

