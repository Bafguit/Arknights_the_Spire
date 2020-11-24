

package com.ndc.arknightsthespire.character;

import basemod.abstracts.CustomPlayer;
import basemod.animations.SpriterAnimation;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.MathUtils;
import com.esotericsoftware.spine.AnimationState;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
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

public class CharacterDoctor extends CustomPlayer {
    public static final int ENERGY_PER_TURN = 3; // how much energy you get every turn
    public static final int STARTING_HP = 70;
    public static final int MAX_HP = 70;
    public static final int STARTING_GOLD = 99;
    public static final int HAND_SIZE = 5;
    private static final int ORB_SLOTS = 0;

    private static final CharacterStrings characterStrings;
    private static final String[] NAMES;
    private static final String[] TEXT;

    public static final String MY_CHARACTER_SHOULDER_2 = "img/char/shoulder2.png"; // campfire pose
    public static final String MY_CHARACTER_SHOULDER_1 = "img/char/shoulder1.png"; // another campfire pose
    public static final String MY_CHARACTER_CORPSE = "img/char/corpse.png"; // dead corpse
    public static final String MY_CHARACTER_SKELETON_ATLAS = "img/char/skeleton.atlas"; // spine animation atlas
    public static final String MY_CHARACTER_SKELETON_JSON = "img/char/skeleton.json"; // spine animation json
    public static final String[] orbTextures = {
            "img/char/orb/layer1.png",
            "img/char/orb/layer2.png",
            "img/char/orb/layer3.png",
            "img/char/orb/layer4.png",
            "img/char/orb/layer5.png",
            "img/char/orb/layer6.png",
            "img/char/orb/layer1d.png",
            "img/char/orb/layer2d.png",
            "img/char/orb/layer3d.png",
            "img/char/orb/layer4d.png",
            "img/char/orb/layer5d.png",};

    static {
        characterStrings = CardCrawlGame.languagePack.getCharacterString("Doctor");
        NAMES = characterStrings.NAMES;
        TEXT = characterStrings.TEXT;
    }

    public CharacterDoctor (String name) {
        super(name, ATSCharacterEnum.DOCTOR_CLASS, orbTextures, "img/char/orb/vfx.png", new SpriterAnimation(
                "img/char/Spriter/theDefaultAnimation.scml"));

        this.dialogX = (this.drawX + 0.0F * Settings.scale); // set location for text bubbles
        this.dialogY = (this.drawY + 220.0F * Settings.scale); // you can just copy these values

        initializeClass(null, MY_CHARACTER_SHOULDER_2, // required call to load textures and setup energy/loadout
                MY_CHARACTER_SHOULDER_1,
                MY_CHARACTER_CORPSE,
                getLoadout(), 20.0F, -10.0F, 220.0F, 290.0F, new EnergyManager(ENERGY_PER_TURN));

        loadAnimation(MY_CHARACTER_SKELETON_ATLAS, MY_CHARACTER_SKELETON_JSON, 1.0F); // if you're using modified versions of base game animations or made animations in spine make sure to include this bit and the following lines

        AnimationState.TrackEntry e = this.state.setAnimation(0, "animation", true);
        e.setTime(e.getEndTime() * MathUtils.random());
    }

    public ArrayList<String> getStartingDeck() { // starting deck 'nuff said
        ArrayList<String> retVal = new ArrayList<>();
        retVal.add("Powerful Strike");
        retVal.add("Powerful Strike");
        retVal.add("Powerful Strike");
        retVal.add("Armor Crushing Shot");
        retVal.add("Defend Up");
        retVal.add("Defend Up");
        retVal.add("Defend Up");
        retVal.add("Defend Up");
        retVal.add("Emotion Absorption");
        retVal.add("Emotion Absorption");
        return retVal;
    }

    public ArrayList<String> getStartingRelics() { // starting relics - also simple
        ArrayList<String> retVal = new ArrayList<>();
        retVal.add("Sanity");
        UnlockTracker.markRelicAsSeen("Sanity");
        return retVal;
    }



    public CharSelectInfo getLoadout() { // the rest of the character loadout so includes your character select screen info plus hp and starting gold
        return new CharSelectInfo("박사", "테라에서 구출되어 기억을 잃은 사람. NL 매우 뛰어난 작전 지휘 능력을 지니고 있다는 듯하다.",
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
    public AbstractGameAction.AttackEffect[] getSpireHeartSlashEffect() {
        return new AbstractGameAction.AttackEffect[0];
    }

    @Override
    public String getVampireText() {
        return Vampires.DESCRIPTIONS[0];
    }

}

