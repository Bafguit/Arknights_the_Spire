package com.ndc.arknightsthespire.cards;

import basemod.abstracts.CustomCard;
import basemod.interfaces.OnCardUseSubscriber;
import basemod.interfaces.OnStartBattleSubscriber;
import basemod.interfaces.PostBattleSubscriber;
import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.rooms.AbstractRoom;

import java.util.Iterator;

public abstract class CardSPBase extends CustomCard implements PostBattleSubscriber, OnStartBattleSubscriber, OnCardUseSubscriber {
    // 87 251 218
    //public static final Color CYAN_BORDER_GLOW_COLOR = new Color(0.34F, 0.98F, 0.85F, 0.25F);
    public static final Color CYAN_BORDER_GLOW_COLOR = new Color(1.0F, 0.14F, 0.14F, 0.5F);

    public int baseSP;
    public int sp;
    public int diff_sp = 0;
    public int default_SP = 0;
    public int current_SP = 0;
    public int receive_SP = 1;
    public int lastSPAmount;
    public String ats_class;
    public boolean isAuto;
    public boolean isSPModified;
    public boolean canUseSP;
    public boolean isSPUsed;
    public boolean upgradedSP;

    public CardSPBase(String id, String name, String img, int cost, String rawDescription, CardType type, CardColor color, CardRarity rarity, CardTarget target, boolean isAuto, String cardClass, boolean hasSP) {
        super(id, name, img, cost, rawDescription, type, color, rarity, target);
        this.isAuto = this.isAuto;
        this.ats_class = cardClass;
        this.canUseSP = hasSP;
        this.updateGlowColor(false);
    }

    public CardSPBase(String id, String name, RegionName img, int cost, String rawDescription, CardType type, CardColor color, CardRarity rarity, CardTarget target, boolean isAuto, String cardClass, boolean hasSP) {
        super(id, name, img, cost, rawDescription, type, color, rarity, target);
        this.isAuto = isAuto;
        this.ats_class = cardClass;
        this.canUseSP = hasSP;
        this.updateGlowColor(false);
    }

     protected void upgradeSP(int amount) {
         this.baseSP = amount;
         this.upgradedSP = true;
     }

     protected void upgradeSPName(int amount) {
         this.baseSP = amount;
         this.upgradedSP = true;
     }

     public void changeSP(int c_sp) {
         this.baseSP -= c_sp;
     }

    public void getSPChange(int c_sp) {

        Iterator var1 = AbstractDungeon.player.masterDeck.group.iterator();

        while (var1.hasNext()) {
            CardSPBase c = (CardSPBase) var1.next();
            if(c.ats_class == "SNIPER" && c.canUseSP == true) c.changeSP(c_sp);
        }
    }


    @Override
    public void receivePostBattle(AbstractRoom abstractRoom) {
        diff_sp = 0;
    }

    @Override
    public void receiveOnBattleStart(AbstractRoom abstractRoom) {
        current_SP = default_SP;
        current_SP += receive_SP;
    }

    @Override
    public void receiveCardUsed(AbstractCard abstractCard) {
        current_SP -= lastSPAmount;
    }

    @Override
    public void upgrade() {

    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {

    }

    public void updateGlowColor(boolean isSpEnabled) {
        if(isSpEnabled || isAuto) {
            this.glowColor = CYAN_BORDER_GLOW_COLOR;
        } else {
            this.glowColor = BLUE_BORDER_GLOW_COLOR;
        }
    }

     public boolean canAffordSP(int sp) {
        return sp >= this.sp;
     }
/*
    public void displayUpgrades() {
        if (this.upgradedCost) {
            this.isCostModified = true;
        }

        if (this.upgradedDamage) {
            this.damage = this.baseDamage;
            this.isDamageModified = true;
        }

        if (this.upgradedBlock) {
            this.block = this.baseBlock;
            this.isBlockModified = true;
        }

        if (this.upgradedMagicNumber) {
            this.magicNumber = this.baseMagicNumber;
            this.isMagicNumberModified = true;
        }

        if (this.upgradedSp) {
            this.sp = this.baseSp;
            this.isSpModified = true;
        }
    }

    protected void upgradeSp(int amount) {
        this.baseSp += amount;
        this.sp = this.baseSp;
        this.upgradedSp = true;
    }

    public AbstractCard makeCopy() {
        try {
            return this.getClass().newInstance();
        } catch (IllegalAccessException | InstantiationException var2) {
            throw new RuntimeException("BaseMod failed to auto-generate makeCopy for card: " + this.cardID);
        }
    }

    public AbstractCard makeStatEquivalentCopy() {
        CardSPBase card = (CardSPBase) this.makeCopy();

        for(int i = 0; i < this.timesUpgraded; ++i) {
            card.upgrade();
        }

        card.name = this.name;
        card.target = this.target;
        card.upgraded = this.upgraded;
        card.timesUpgraded = this.timesUpgraded;
        card.baseDamage = this.baseDamage;
        card.baseBlock = this.baseBlock;
        card.baseMagicNumber = this.baseMagicNumber;
        card.baseSp = this.baseSp;
        card.cost = this.cost;
        card.costForTurn = this.costForTurn;
        card.isCostModified = this.isCostModified;
        card.isCostModifiedForTurn = this.isCostModifiedForTurn;
        card.inBottleLightning = this.inBottleLightning;
        card.inBottleFlame = this.inBottleFlame;
        card.inBottleTornado = this.inBottleTornado;
        card.isSeen = this.isSeen;
        card.isLocked = this.isLocked;
        card.misc = this.misc;
        card.freeToPlayOnce = this.freeToPlayOnce;
        return card;
    }

    public void resetAttributes() {
        this.block = this.baseBlock;
        this.isBlockModified = false;
        this.damage = this.baseDamage;
        this.isDamageModified = false;
        this.magicNumber = this.baseMagicNumber;
        this.isMagicNumberModified = false;
        this.sp = this.baseSp;
        this.isSpModified = false;
        this.damageTypeForTurn = this.damageType;
        this.costForTurn = this.cost;
        this.isCostModifiedForTurn = false;
    }

    public static String gameDataUploadHeader() {
        GameDataStringBuilder builder = new GameDataStringBuilder();
        builder.addFieldData("name");
        builder.addFieldData("cardID");
        builder.addFieldData("rawDescription");
        builder.addFieldData("assetURL");
        builder.addFieldData("keywords");
        builder.addFieldData("color");
        builder.addFieldData("type");
        builder.addFieldData("rarity");
        builder.addFieldData("cost");
        builder.addFieldData("target");
        builder.addFieldData("damageType");
        builder.addFieldData("baseDamage");
        builder.addFieldData("baseBlock");
        builder.addFieldData("baseHeal");
        builder.addFieldData("baseDraw");
        builder.addFieldData("baseDiscard");
        builder.addFieldData("baseMagicNumber");
        builder.addFieldData("baseSP");
        builder.addFieldData("isMultiDamage");
        return builder.toString();
    }

    public String gameDataUploadData() {
        GameDataStringBuilder builder = new GameDataStringBuilder();
        builder.addFieldData(this.name);
        builder.addFieldData(this.cardID);
        builder.addFieldData(this.rawDescription);
        builder.addFieldData(this.assetUrl);
        builder.addFieldData(Arrays.toString(this.keywords.toArray()));
        builder.addFieldData(this.color.name());
        builder.addFieldData(this.type.name());
        builder.addFieldData(this.rarity.name());
        builder.addFieldData(this.cost);
        builder.addFieldData(this.target.name());
        builder.addFieldData(this.damageType.name());
        builder.addFieldData(this.baseDamage);
        builder.addFieldData(this.baseBlock);
        builder.addFieldData(this.baseHeal);
        builder.addFieldData(this.baseDraw);
        builder.addFieldData(this.baseDiscard);
        builder.addFieldData(this.baseMagicNumber);
        builder.addFieldData(this.baseSp);
        builder.addFieldData(this.isMultiDamage);
        return builder.toString();
    }*/
}
