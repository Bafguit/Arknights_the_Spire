package com.ndc.arknightsthespire.cards.base;

import basemod.ReflectionHacks;
import basemod.abstracts.CustomCard;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.HealAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.CardHelper;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.localization.UIStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.ndc.arknightsthespire.ArknightsTheSpire;
import com.ndc.arknightsthespire.SPHandler;
import com.ndc.arknightsthespire.character.AtsEnum;
import com.ndc.arknightsthespire.character.CharacterDoctor;
import com.ndc.arknightsthespire.power.AttackPower;
import com.ndc.arknightsthespire.power.DogmaticFieldPower;

import java.util.Date;
import java.util.Iterator;

//TODO TEST
public abstract class CardSPBase extends CustomCard {
    // 87 251 218
    //public static final Color CYAN_BORDER_GLOW_COLOR = new Color(0.34F, 0.98F, 0.85F, 0.25F);
    public static final Color DEFAULT_BORDER_GLOW_COLOR = new Color(0.0F, 1.0F, 1.0F, 0.5F);
    public static final Color SP_BORDER_GLOW_COLOR = Color.LIME;
    public static final Color NORMAL_AND_USABLE_COLOR = new Color(0x00E0FFFF);
    public static final Color NORMAL_AND_NOT_USABLE_COLOR = new Color(0x79A6B2FF);
    public static final Color NORMAL_AND_RESTRICTED_COLOR = new Color(0xC66F79FF);
    public static final Color MODIFIED_AND_USABLE_COLOR = new Color(0x0BFB7CFF);
    public static final Color MODIFIED_AND_NOT_USABLE_COLOR = new Color(0x7DA590FF);
    public static final Color MODIFIED_AND_RESTRICTED_COLOR = new Color(0x9B8077FF);
    private static final UIStrings uiSPStrings;


    //아니 이거 왜이래
    public int baseSP = 0;
    public int sp = 0;
    public int arm = 0;
    public int baseArm = 0;
    public int et = 0;
    public int baseEt = 0;
    public boolean isArmModified = false;
    public boolean upgradedArm = false;
    public boolean isEtModified = false;
    public boolean upgradedEt = false;
    public int res = 0;
    public int baseRes = 0;
    public boolean isResModified = false;
    public boolean upgradedRes = false;
    public PositionType position;
    public boolean isAuto = false;
    public boolean isSPModified = false;
    public boolean isPerModified = false;
    public boolean isSPModifiedForTurn = false; //TODO Not implemented
    public boolean canUseSP = false;
    public boolean upgradedSP = false;
    public boolean upgradedPer = false;
    public boolean isSpJustUsed;
    public CardSpPreview spCard;
    public String nameUp;
    private Color baseColor = new Color(0.2F, 0.9F, 1.0F, 0.25F);

    private boolean showSP;

    protected String normalCardImage;
    protected String spCardImage;

    protected CardStrings CARD_STRINGS;
    protected String NORMAL_NAME;
    protected String SP_NAME;
    protected String NORMAL_DESCRIPTION;
    protected String SP_DESCRIPTION;
    protected String UPGRADE_DESCRIPTION;

    public CardSPBase(String id, String img, int cost, CardType type, CardColor color, CardRarity rarity, CardTarget target, PositionType position) {
        this(id, CardCrawlGame.languagePack.getCardStrings(id).NAME, CardCrawlGame.languagePack.getCardStrings(id).DESCRIPTION,
                img, null, cost, type, color, rarity, target, false, position, false, 0, 0, 0, 0);
    }

    public CardSPBase(String id, String img, int cost, CardType type, CardColor color, CardRarity rarity, CardTarget target, PositionType position, int d, int b, int m, int s) {
        this(id, CardCrawlGame.languagePack.getCardStrings(id).NAME, CardCrawlGame.languagePack.getCardStrings(id).DESCRIPTION,
                img, null, cost, type, color, rarity, target, false, position, false, d, b, m, s);
    }

    public CardSPBase(String id, String img, String spImg, int cost, CardType type, CardColor color, CardRarity rarity, CardTarget target, boolean isAuto, PositionType position, boolean hasSP, int d, int b, int m, int s) {
        this(id, CardCrawlGame.languagePack.getCardStrings(id).NAME, CardCrawlGame.languagePack.getCardStrings(id).DESCRIPTION,
                img, spImg, cost, type, color, rarity, target, isAuto, position, hasSP, d, b, m, s);
    }

    public CardSPBase(String id, String img, int cost, CardType type, CardColor color, CardRarity rarity, CardTarget target, boolean isAuto, PositionType position, boolean hasSP, int d, int b, int m, int s) {
        this(id, CardCrawlGame.languagePack.getCardStrings(id).NAME, CardCrawlGame.languagePack.getCardStrings(id).DESCRIPTION,
                img, null, cost, type, color, rarity, target, isAuto, position, hasSP, d, b, m, s);
    }

    public CardSPBase(String id, String name, String des, String img, String spImg, int cost, CardType type, CardColor color, CardRarity rarity, CardTarget target, boolean isAuto, PositionType position, boolean hasSP, int d, int b, int m, int s) {
        super(id, name, img, cost, des, type, color, rarity, target);
        this.setBackgroundTexture("atsImg/512/" + bt(position) + "_512.png", "atsImg/1024/" + bt(position) + ".png");
        this.setOrbTexture("atsImg/orbs/cost.png", "atsImg/orbs/cost_small.png");
        CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(id);
        normalCardImage = img;
        this.spCardImage = spImg;
        NORMAL_DESCRIPTION = des;
        NORMAL_NAME = name;
        CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(id);
        if(hasSP) {
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
        this.showSP = hasSP;
        this.damage = this.baseDamage = d;
        this.magicNumber = this.baseMagicNumber = m;
        this.block = this.baseBlock = b;
        this.sp = this.baseSP = s;
        if(showSP) {
            if(cardStrings.EXTENDED_DESCRIPTION != null) {
                if (cardStrings.EXTENDED_DESCRIPTION.length > 1) {
                    if (this.spCardImage != null)
                        this.spCard = new CardSpPreview(id, cardStrings.EXTENDED_DESCRIPTION[0], cardStrings.EXTENDED_DESCRIPTION[1], this.spCardImage, cost, type, color, rarity, target, isAuto, position, d, b, m, s);
                    else
                        this.spCard = new CardSpPreview(id, cardStrings.EXTENDED_DESCRIPTION[0], cardStrings.EXTENDED_DESCRIPTION[1], img, cost, type, color, rarity, target, isAuto, position, d, b, m, s);
                } else if (cardStrings.EXTENDED_DESCRIPTION.length == 1) {
                    if (this.spCardImage != null)
                        this.spCard = new CardSpPreview(id, name, cardStrings.EXTENDED_DESCRIPTION[0], this.spCardImage, cost, type, color, rarity, target, isAuto, position, d, b, m, s);
                    else
                        this.spCard = new CardSpPreview(id, name, cardStrings.EXTENDED_DESCRIPTION[0], img, cost, type, color, rarity, target, isAuto, position, d, b, m, s);
                }
                this.spCard.nameUp = this.spCard.name;
            }
        }
        this.cardsToPreview = this.spCard;
        this.updateState(false);
    }

    public String bt(PositionType p) {
        return p.toString().toLowerCase();
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
    protected void upgradeDamage(int amount) {
        this.baseDamage += amount;
        this.upgradedDamage = true;
        if(this.spCard != null) {
            this.spCard.baseDamage += amount;
            this.spCard.upgradedDamage = true;
        }
    }

    @Override
    protected void upgradeBlock(int amount) {
        this.baseBlock += amount;
        this.upgradedBlock = true;
        if(this.spCard != null) {
            this.spCard.baseBlock += amount;
            this.spCard.upgradedBlock = true;
        }
    }

    @Override
    protected void upgradeMagicNumber(int amount) {
        this.baseMagicNumber += amount;
        this.magicNumber = this.baseMagicNumber;
        this.upgradedMagicNumber = true;
        if(this.spCard != null) {
            this.spCard.baseMagicNumber += amount;
            this.spCard.magicNumber = this.spCard.baseMagicNumber;
            this.spCard.upgradedMagicNumber = true;
        }
    }

    @Override
    protected void upgradeBaseCost(int newBaseCost) {
        int diff = this.costForTurn - this.cost;
        this.cost = newBaseCost;
        if(this.spCard != null) this.spCard.cost = newBaseCost;
        if (this.costForTurn > 0) {
            this.costForTurn = this.cost + diff;
            if(this.spCard != null) this.spCard.costForTurn = this.spCard.cost + diff;
        }

        if (this.costForTurn < 0) {
            this.costForTurn = 0;
            if(this.spCard != null) this.spCard.costForTurn = 0;
        }

        this.upgradedCost = true;
        if(this.spCard != null) this.spCard.upgradedCost = true;
    }

    public void upgradeSP(int sp) {
        this.sp = sp;
        this.upgradedSP = true;
        if(this.spCard != null) {
            this.spCard.sp = sp;
            this.spCard.upgradedSP = true;
        }
    }

    public void upgradeArm(int arm) {
        this.upgradedArm = true;
        this.baseArm += arm;
        this.arm = this.baseArm;
        if(this.spCard != null) {
            this.spCard.baseArm = this.baseArm;
            this.spCard.arm = this.arm;
            this.spCard.upgradedArm = true;
        }
    }

    public void upgradeRes(int res) {
        this.upgradedRes = true;
        this.baseRes += res;
        this.res = this.baseRes;
        if(this.spCard != null) {
            this.spCard.baseRes = this.baseRes;
            this.spCard.res = this.res;
            this.spCard.upgradedRes = true;
        }
    }

    public void changeSpForBattle(int c_sp) {
        this.baseSP = Math.max(0, this.sp + c_sp);
        if(this.spCard != null) {
            this.spCard.baseSP = Math.max(0, this.spCard.sp + c_sp);
        }
    }

    public DamageInfo getInfo() {
        return this.getInfo(false);
    }

    public DamageInfo getInfo(boolean isArts) {
        return new DamageInfo(AbstractDungeon.player, this.damage, DamageInfo.DamageType.NORMAL);
    }

    @Override
    public void resetAttributes() {
        this.baseSP = this.sp;
        if(this.spCard != null) {
            this.spCard.baseSP = this.spCard.sp;
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
        this.baseEt = this.et = SPHandler.getTurnEnergy();
        updateName();
        updateDescription();
        updateImage();
        updateSpView();
        updateGlow(shouldGlow);
    }

    public void updateSpView() {
        if(this.spCard != null) {
            this.cardsToPreview = this.spCard;
            if(ArknightsTheSpire.getBattle()) {
                if (this.isAuto && canAffordSP()) {
                    this.cardsToPreview = null;
                } else if (!this.isAuto && canAffordSP() && SPHandler.isSpModeEnabled()) {
                    this.cardsToPreview = null;
                }
            }
        }
    }

    public void updateGlow(boolean showGlow) {
        boolean checkGlow = checkGlow();
        if(!showGlow && ArknightsTheSpire.getBattle()) {
            if (AbstractDungeon.player.hand.group.contains(this)) {
                if (checkGlow && !this.isGlowing) this.beginGlowing();
                if (!checkGlow && this.isGlowing) this.stopGlowing();
            }
        }
    }

    public void updateName() {
        if(shouldUseSp() && SP_NAME != null) {
            this.name = SP_NAME;
        } else {
            this.name = NORMAL_NAME;
        }
        if(upgraded) {
            this.name += "+";
            if(this.spCard != null) {
                this.spCard.name = this.spCard.nameUp + "+";
                this.spCard.initializeTitle();
            }
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

    public void setArm(int a) {
        this.setDef(a, 0);
    }

    public void setRes(int r) {
        this.setDef(0, r);
    }

    public void setDef(int a, int r) {
        this.baseArm = this.arm = a;
        this.baseRes = this.res = r;
    }

    private boolean checkGlow() {
        if (this.canAffordSP() && this.canUseSP) {
            if (!this.isAuto && SPHandler.isSpModeEnabled()) {
                this.glowColor = SP_BORDER_GLOW_COLOR;
                return true;
            } else if (this.isAuto) {
                this.glowColor = SP_BORDER_GLOW_COLOR;
                return true;
            }
        }
        this.glowColor = this.baseColor.cpy();
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
        return SPHandler.getSp() >= this.sp;
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
        return this.canUseSP && canAffordSP() && (SPHandler.isSpModeEnabled() || isAuto) && this.sp > 0;
    }

    public void renderSp(SpriteBatch sb) {
            boolean darken = (boolean) ReflectionHacks.getPrivate(this, AbstractCard.class, "darken");
            if (this.cost > -2 && !darken && !this.isLocked && this.isSeen) {
                if (this.canUseSP) {
                    Color costColor;
                    if (this.isSPModified || this.isSPModifiedForTurn || this.freeToPlay()) {
                        if (this.canAffordSP()) {
                            costColor = MODIFIED_AND_USABLE_COLOR;
                        } else {
                            costColor = MODIFIED_AND_NOT_USABLE_COLOR;
                        }
                    } else {
                        if (this.canAffordSP()) {
                            costColor = NORMAL_AND_USABLE_COLOR;
                        } else {
                            costColor = NORMAL_AND_NOT_USABLE_COLOR;
                        }
                    }

                    costColor.a = this.transparency;
                    String text = String.valueOf(sp);
                    BitmapFont font = this.getSpFont();
                    if ((this.type != AbstractCard.CardType.STATUS || this.cardID.equals("Slimed")) && (this.color != AbstractCard.CardColor.CURSE || this.cardID.equals("Pride"))) {
                            FontHelper.renderRotatedText(sb, font, text, this.current_x, this.current_y, 125.0F * this.drawScale * Settings.scale, 180.0F * this.drawScale * Settings.scale, this.angle, false, costColor);
                    }
                }
            }
    }

    static {
        uiSPStrings = CardCrawlGame.languagePack.getUIString("SpSingleCardViewPopup");
    }
}
