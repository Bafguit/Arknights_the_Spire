package com.ndc.arknightsthespire.cards;

import basemod.ReflectionHacks;
import basemod.abstracts.CustomCard;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.HealAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.localization.UIStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.ndc.arknightsthespire.SPHandler;
import com.ndc.arknightsthespire.power.DogmaticField;

import java.util.Iterator;

//TODO TEST
public abstract class CardSPBase extends CustomCard {
    // 87 251 218
    //public static final Color CYAN_BORDER_GLOW_COLOR = new Color(0.34F, 0.98F, 0.85F, 0.25F);
    public static final Color DEFAULT_BORDER_GLOW_COLOR = new Color(0.0F, 1.0F, 1.0F, 0.5F);
    public static final Color SP_BORDER_GLOW_COLOR = new Color(0.0F, 0.0F, 0.63F, 0.5F);
    public static final Color NORMAL_AND_USABLE_COLOR = new Color(0x00E0FFFF);
    public static final Color NORMAL_AND_NOT_USABLE_COLOR = new Color(0x79A6B2FF);
    public static final Color NORMAL_AND_RESTRICTED_COLOR = new Color(0xC66F79FF);
    public static final Color MODIFIED_AND_USABLE_COLOR = new Color(0x0BFB7CFF);
    public static final Color MODIFIED_AND_NOT_USABLE_COLOR = new Color(0x7DA590FF);
    public static final Color MODIFIED_AND_RESTRICTED_COLOR = new Color(0x9B8077FF);
    private static final UIStrings uiSPStrings;

    public int baseSP = 0;
    public int sp = 0;
    public PositionType position;
    public boolean isAuto = false;
    public boolean isSPModified = false;
    public boolean isSPModifiedForTurn = false; //TODO Not implemented
    public boolean canUseSP = false;
    public boolean upgradedSP = false;
    public boolean onlySP = false;

    public boolean isSpJustUsed;

    protected String normalCardImage;
    protected String spCardImage;

    protected CardStrings CARD_STRINGS;
    protected String NORMAL_NAME;
    protected String SP_NAME;
    protected String NORMAL_DESCRIPTION;
    protected String SP_DESCRIPTION;
    protected String UPGRADE_DESCRIPTION;

    public CardSPBase(String id, String img, int cost, CardType type, CardColor color, CardRarity rarity, CardTarget target, boolean isAuto, PositionType position, boolean hasSP) {
        this(id, CardCrawlGame.languagePack.getCardStrings(id),
                img, cost, type, color, rarity, target, isAuto, position, hasSP);
    }

    private CardSPBase(String id, CardStrings cardStrings, String img, int cost, CardType type, CardColor color, CardRarity rarity, CardTarget target, boolean isAuto, PositionType position, boolean hasSP) {
        super(id, cardStrings.NAME, img, cost, cardStrings.DESCRIPTION, type, color, rarity, target);
        CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(id);
        normalCardImage = img;
        NORMAL_DESCRIPTION = cardStrings.DESCRIPTION;
        NORMAL_NAME = cardStrings.NAME;
        if(cardStrings.EXTENDED_DESCRIPTION != null) {
            if (cardStrings.EXTENDED_DESCRIPTION.length == 1) {
                SP_DESCRIPTION = cardStrings.EXTENDED_DESCRIPTION[0];
            } else if (cardStrings.EXTENDED_DESCRIPTION.length > 1) {
                SP_NAME = cardStrings.EXTENDED_DESCRIPTION[0];
                SP_DESCRIPTION = cardStrings.EXTENDED_DESCRIPTION[1];
            }
        }
        UPGRADE_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;
        this.position = position;
        this.isAuto = isAuto;
        this.canUseSP = hasSP;
        this.updateState(false);
    }

    @Override
    public final void upgrade() {
        if(!upgraded) {
            upgraded = true;
            ++this.timesUpgraded; //TODO What does this do
            upgradeCard();
            updateState(false);
        }
    }

    public abstract void upgradeCard();

    @Override
    protected final void upgradeName() {}

    public void upgradeSP(int sp) {
        baseSP = sp;
        upgradedSP = true;
    }

    public void changeSP(int c_sp) {
        if(this.sp + c_sp < 0) this.baseSP = 0;
        else this.baseSP = this.sp + c_sp;
    }

    public static void getGroupSPChange(PositionType cardClass, int c_sp) {

        SPHandler.addDiffSp(c_sp);

        Iterator var1 = AbstractDungeon.player.hand.group.iterator();

        while (var1.hasNext()) {
            AbstractCard c = (AbstractCard) var1.next();
            if(c instanceof CardSPBase) {
                CardSPBase card = (CardSPBase) c;
                if (card.position == cardClass && card.canUseSP == true) card.changeSP(SPHandler.getDiffSp());
            }
        }
    }

    public static void getSingleClassSPChange(AbstractCard card, PositionType cardClass) {
        if(card instanceof CardSPBase) {
            CardSPBase sCard = (CardSPBase) card;
            if (sCard.position == cardClass && sCard.canUseSP == true) sCard.changeSP(SPHandler.getDiffSp());
        }
    }


    public static void updateAllStateInHand(boolean shouldGlow) {
        Iterator var1 = AbstractDungeon.player.hand.group.iterator();
        while(var1.hasNext()) {
            AbstractCard c = (AbstractCard)var1.next();
            if(c instanceof CardSPBase) {
                ((CardSPBase) c).updateState(shouldGlow);
            }
        }
    }

    public void updateState(boolean shouldGlow) {
        updateName();
        updateDescription();
        updateImage();
        updateGlow();
    }

    public void updateGlow() {
        boolean checkGlow = checkGlow();
        if(checkGlow && !this.isGlowing) this.beginGlowing();
        if(!checkGlow && this.isGlowing) this.stopGlowing();
    }

    public void updateName() {
        if(shouldUseSp() && SP_NAME != null) {
            this.name = SP_NAME;
        } else {
            this.name = NORMAL_NAME;
        }
        if(upgraded) {
            this.name += "+";
        }

        this.initializeTitle();
    }

    public void updateDescription() {
        if(shouldUseSp() && SP_DESCRIPTION != null) {
            this.rawDescription = SP_DESCRIPTION;
        } else if(upgraded && UPGRADE_DESCRIPTION != null) {
            this.rawDescription = UPGRADE_DESCRIPTION;
        } else {
            this.rawDescription = NORMAL_DESCRIPTION;
        }

        this.initializeDescription();
    }

    public void updateImage() {
        if(shouldUseSp() && spCardImage != null) {
            loadCardImage(spCardImage);
        } else {
            loadCardImage(normalCardImage);
        }
    }

    private boolean checkGlow() {
        if(SPHandler.isSpModeEnabled()) {
            if(canAffordSP()) {
                this.glowColor = SP_BORDER_GLOW_COLOR;
                return true;
            }
        } else {
            if(isAuto) {
                if(canAffordSP()) {
                    this.glowColor = SP_BORDER_GLOW_COLOR;
                    return true;
                }
                if(!onlySP) {
                    this.glowColor = DEFAULT_BORDER_GLOW_COLOR;
                    return true;
                }
                return false;
            }
        }
        return false;
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
        if (SPHandler.isSpModeEnabled() && this.canUseSP) {
            if (!this.isAuto && !canAffordSP()) {
                this.cantUseMessage = uiSPStrings.TEXT[0];
                return false;
            }
            else if (this.isAuto) {
                this.cantUseMessage = uiSPStrings.TEXT[1];
                return false;
            }
        }
        return true;
    }

    @Override
    public final void use(AbstractPlayer p, AbstractMonster m) {
        isSpJustUsed = false;
        if(canAffordSP() && (this.isAuto || SPHandler.isSpModeEnabled())) {
            SPHandler.removeSp(this.baseSP);
            isSpJustUsed = true;
            updateAllStateInHand(true);
        }
        useCard(p, m, isSpJustUsed);
        SPHandler.updateSp();
    }

    public abstract void useCard(AbstractPlayer p, AbstractMonster m, boolean isSpJustUsed);




    public boolean canAffordSP() {
        return SPHandler.getSp() >= this.baseSP;
    }

    private BitmapFont getSpFont() {
        FontHelper.cardEnergyFont_L.getData().setScale(this.drawScale*8/7);
        return FontHelper.cardEnergyFont_L;
    }
/*
    public static void changeCardInfo(CardSPBase card, String name, String des) {
        card.rawDescription = des;
        card.name = name;
        card.initializeTitle();
        card.initializeDescription();
    }

    public static void checkCardInfo(CardSPBase card, boolean usingSP) {
        if(usingSP) {
            card.rawDescription = CardLang.getMockCardString().SP_DESCRIPTION;
            CardLang spInfo = (CardLang) CardCrawlGame.languagePack.getCardStrings(card.cardID);
            changeCardInfo(card, spInfo.SP_NAME, spInfo.SP_DESCRIPTION);
        }
        else {
            CardStrings nInfo = CardCrawlGame.languagePack.getCardStrings(card.cardID);
            changeCardInfo(card, nInfo.NAME, nInfo.DESCRIPTION);
            if(card.upgraded) card.upgrade();
        }
    }*/

    public boolean shouldUseSp() {
        return canAffordSP() && (SPHandler.isSpModeEnabled() || isAuto);
    }

    public void renderSp(SpriteBatch sb) {
        boolean darken = (boolean) ReflectionHacks.getPrivate(this, AbstractCard.class, "darken");
        if (this.cost > -2 && !darken && !this.isLocked && this.isSeen) {

            Color costColor;
            if (this.isSPModified || this.isSPModifiedForTurn || this.freeToPlay()) {
                if(this.canAffordSP()) {
                    costColor = MODIFIED_AND_USABLE_COLOR;
                } else if(this.onlySP) {
                    costColor = MODIFIED_AND_RESTRICTED_COLOR;
                } else {
                    costColor = MODIFIED_AND_NOT_USABLE_COLOR;
                }
            } else {
                if(this.canAffordSP()) {
                    costColor = NORMAL_AND_USABLE_COLOR;
                } else if(this.onlySP) {
                    costColor = NORMAL_AND_RESTRICTED_COLOR;
                } else {
                    costColor = NORMAL_AND_NOT_USABLE_COLOR;
                }
            }

            costColor.a = this.transparency;
            String text = String.valueOf(baseSP);
            BitmapFont font = this.getSpFont();
            if ((this.type != AbstractCard.CardType.STATUS || this.cardID.equals("Slimed")) && (this.color != AbstractCard.CardColor.CURSE || this.cardID.equals("Pride"))) {
                FontHelper.renderRotatedText(sb, font, text, this.current_x, this.current_y, 125.0F * this.drawScale * Settings.scale, 180.0F * this.drawScale * Settings.scale, this.angle, false, costColor);
            }
        }
    }

    public static void checkGainBlock(int amt) {
        AbstractPlayer p = AbstractDungeon.player;

        if(p.hasPower(DogmaticField.POWER_ID)) {
            AbstractDungeon.actionManager.addToBottom(new HealAction(p, p, amt));
        }
        else {
            AbstractDungeon.actionManager.addToBottom(new GainBlockAction(p, p, amt));
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
