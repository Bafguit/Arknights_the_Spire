package com.ndc.arknightsthespire.cards;

import basemod.ReflectionHacks;
import basemod.abstracts.CustomCard;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.actions.common.AttackDamageRandomEnemyAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.UIStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.screens.SingleCardViewPopup;
import com.ndc.arknightsthespire.SPHandler;

import java.util.Iterator;

public abstract class CardSPBase extends CustomCard {
    // 87 251 218
    //public static final Color CYAN_BORDER_GLOW_COLOR = new Color(0.34F, 0.98F, 0.85F, 0.25F);
    public static final Color DEFAULT_BORDER_GLOW_COLOR = new Color(0.0F, 1.0F, 1.0F, 0.5F);
    public static final Color SP_BORDER_GLOW_COLOR = new Color(0.0F, 0.0F, 0.63F, 0.5F);
    public static final Color SP_COST_RESTRICTED_COLOR = new Color(0.0F, 1.0F, 1.0F, 0.5F);
    public static final Color SP_COST_MODIFIED_COLOR = new Color(0.0F, 0.0F, 0.63F, 0.5F);
    private static final UIStrings uiSPStrings;

    public int baseSP;
    public int sp;
    public PositionType position;
    public boolean isAuto;
    public boolean isSPModified;
    public boolean isSPModifiedForTurn; //TODO Not implemented
    public boolean canUseSP;
    public boolean upgradedSP;
    public boolean onlySP;

    public CardSPBase(String id, String name, String img, int cost, String rawDescription, CardType type, CardColor color, CardRarity rarity, CardTarget target, boolean isAuto, PositionType position, boolean hasSP) {
        this(id, name, img, cost, rawDescription, type, color, rarity, target, isAuto, position, hasSP, false);
    }

    public CardSPBase(String id, String name, String img, int cost, String rawDescription, CardType type, CardColor color, CardRarity rarity, CardTarget target, boolean isAuto, PositionType position, boolean hasSP, boolean onlySP) {
        super(id, name, img, cost, rawDescription, type, color, rarity, target);
        this.isAuto = isAuto;
        this.position = position;
        this.canUseSP = hasSP;
        this.updateGlow(false);
    }

    public CardSPBase(String id, String name, RegionName img, int cost, String rawDescription, CardType type, CardColor color, CardRarity rarity, CardTarget target, boolean isAuto, PositionType position, boolean hasSP) {
        this(id, name, img, cost, rawDescription, type, color, rarity, target, isAuto, position, hasSP, false);
    }

    public CardSPBase(String id, String name, RegionName img, int cost, String rawDescription, CardType type, CardColor color, CardRarity rarity, CardTarget target, boolean isAuto, PositionType position, boolean hasSP, boolean onlySP) {
        super(id, name, img, cost, rawDescription, type, color, rarity, target);
        this.isAuto = isAuto;
        this.position = position;
        this.canUseSP = hasSP;
        this.updateGlow(false);
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
         if(this.sp + c_sp < 0) this.baseSP = 0;
         else this.baseSP = this.sp + c_sp;
     }

    public static void getGroupSPChange(PositionType cardClass, int c_sp) {

        SPHandler.addDiffSp(c_sp);

        Iterator var1 = AbstractDungeon.player.hand.group.iterator();

        while (var1.hasNext()) {
            CardSPBase card = (CardSPBase) var1.next();
            if(card.position == cardClass && card.canUseSP == true) card.changeSP(SPHandler.getDiffSp());
        }
    }

    public static void getSingleClassSPChange(AbstractCard card, PositionType cardClass) {
        if(card instanceof CardSPBase) {
            CardSPBase sCard = (CardSPBase) card;
            if (sCard.position == cardClass && sCard.canUseSP == true) sCard.changeSP(SPHandler.getDiffSp());
        }
    }

    @Override
    public void upgrade() {

    }

/*
    @Override
    public boolean hasEnoughEnergy() {
        this.cantUseMessage = uiSPStrings.TEXT[1];
        return false;
    } // onlySP 체크는 여기서
*/
    @Override
    public boolean canUse(AbstractPlayer p, AbstractMonster m) {
        if(SPHandler.isSpModeEnabled() && (!canUseSP || !canAffordSP())) {
            this.cantUseMessage = uiSPStrings.TEXT[0];
            return false;
        }
        return true;
    }

    @Override
    public final void use(AbstractPlayer p, AbstractMonster m) {
        boolean isSpJustUsed = false;
        if(canAffordSP() && (this.isAuto || SPHandler.isSpModeEnabled())) {
            SPHandler.removeSp(this.baseSP);
            isSpJustUsed = true;
            updateAllGlowInHand();
        }
        useCard(p, m, isSpJustUsed);
        SPHandler.updateSp();
    }

    public abstract void useCard(AbstractPlayer p, AbstractMonster m, boolean isSpJustUsed);

    public static void updateAllGlowInHand() {
        Iterator var1 = AbstractDungeon.player.hand.group.iterator();
        while(var1.hasNext()) {
            AbstractCard c = (AbstractCard)var1.next();
            if(c instanceof CardSPBase) {
                ((CardSPBase) c).updateGlow(SPHandler.isSpModeEnabled());
            }
        }
    }

    public void updateGlow(boolean isSpEnabled) {
        boolean checkGlow = checkGlow(isSpEnabled);
        if(checkGlow && !this.isGlowing) this.beginGlowing();
        if(!checkGlow && this.isGlowing) this.stopGlowing();
    }

    private boolean checkGlow(boolean isSpEnabled) {
        System.out.println(SPHandler.getSp() + " " + canAffordSP());
        if(isSpEnabled) {
            if(canAffordSP()) {
                this.glowColor = SP_BORDER_GLOW_COLOR;
                System.out.println("CASE A");
                return true;
            }
        } else {
            if(isAuto) {
                if(canAffordSP()) {
                    this.glowColor = SP_BORDER_GLOW_COLOR;
                    System.out.println("CASE B");
                    return true;
                }
                if(!onlySP) {
                    this.glowColor = DEFAULT_BORDER_GLOW_COLOR;
                    System.out.println("CASE C");
                    return true;
                }
                System.out.println("CASE D");
                return false;
            }
        }
        System.out.println("CASE E");
        return false;
    }

     public boolean canAffordSP() {
        return SPHandler.getSp() >= this.baseSP;
     }

    private BitmapFont getSpFont() {
        FontHelper.cardEnergyFont_L.getData().setScale(this.drawScale);
        return FontHelper.cardEnergyFont_L;
    }

    public void renderSp(SpriteBatch sb) {
        boolean darken = (boolean) ReflectionHacks.getPrivate(this, boolean.class, "darken");
        if (this.cost > -2 && !darken && !this.isLocked && this.isSeen) {

            Color costColor = Color.WHITE.cpy();
            if (AbstractDungeon.player != null && AbstractDungeon.player.hand.contains(this) && !this.canAffordSP()) {
                costColor = SP_COST_RESTRICTED_COLOR;
            } else if (this.isSPModified || this.isSPModifiedForTurn || this.freeToPlay()) {
                costColor = SP_COST_MODIFIED_COLOR;
            }

            costColor.a = this.transparency;
            String text = String.valueOf(sp);
            BitmapFont font = this.getSpFont();
            if ((this.type != AbstractCard.CardType.STATUS || this.cardID.equals("Slimed")) && (this.color != AbstractCard.CardColor.CURSE || this.cardID.equals("Pride"))) {
                FontHelper.renderRotatedText(sb, font, text, this.current_x, this.current_y, -(132.0F+2.0F) * this.drawScale * Settings.scale, 192.0F * this.drawScale * Settings.scale, this.angle, false, costColor);
            }
        }
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

    static {
        uiSPStrings = CardCrawlGame.languagePack.getUIString("SpSingleCardViewPopup");
    }
}
