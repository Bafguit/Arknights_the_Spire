//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.ndc.arknightsthespire.act;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import com.megacrit.cardcrawl.rooms.CampfireUI;
import com.megacrit.cardcrawl.rooms.MonsterRoomBoss;
import com.megacrit.cardcrawl.rooms.ShopRoom;
import com.megacrit.cardcrawl.scenes.AbstractScene;
import java.util.Iterator;

public class ArknightsScene extends AbstractScene {
    private static Texture topBar;
    private AtlasRegion bg;
    private AtlasRegion fg;
    private AtlasRegion ceil;
    private AtlasRegion fgGlow;
    private AtlasRegion floor;
    private AtlasRegion mg1;
    private Texture campfirebg;
    private Texture campfire;
    private Texture fire;

    public ArknightsScene() {
        super("img/scene/bg_act.atlas");
        this.bg = this.atlas.findRegion("bg_shop");
        this.ambianceName = "AMBIANCE_CITY";
        this.fadeInAmbiance();
    }

    public void update() {
        super.update();
    }

    public void randomizeScene() {
    }

    public void nextRoom(AbstractRoom room) {
        super.nextRoom(room);
        this.randomizeScene();
        if (room instanceof MonsterRoomBoss) {
            CardCrawlGame.music.silenceBGM();
        }

        /*if (room.monsters != null) {
            Iterator var2 = room.monsters.monsters.iterator();

            label195:
            while(true) {
                while(true) {
                    if (!var2.hasNext()) {
                        break label195;
                    }

                    AbstractMonster mo = (AbstractMonster)var2.next();
                    if (mo instanceof Nitori) {
                        this.bg = this.atlas.findRegion("mod/GenbuRavineCloudy");
                    } else if (mo instanceof Mamizou) {
                        this.bg = this.atlas.findRegion("mod/TanukiForestNight");
                    } else if (mo instanceof Aya) {
                        this.bg = this.atlas.findRegion("mod/FancyPlace");
                    } else if (mo instanceof Cirno) {
                        this.bg = this.atlas.findRegion("mod/GenbuRavineCloudy");
                    } else if (mo instanceof Yukari) {
                        this.bg = this.atlas.findRegion("mod/ConcertStage");
                    } else if (mo instanceof Kokoro) {
                        this.bg = this.atlas.findRegion("mod/HumanVillage");
                    } else if (mo instanceof Reimu) {
                        this.bg = this.atlas.findRegion("mod/HakureiShrine");
                    } else if (mo instanceof Patchouli) {
                        this.bg = this.atlas.findRegion("mod/Palace");
                    } else if (mo instanceof Sumireko) {
                        this.bg = this.atlas.findRegion("mod/OutsideWorld");
                    } else if (mo instanceof Eiki) {
                        this.bg = this.atlas.findRegion("mod/ConcertStage");
                    } else if (mo instanceof Kaguya) {
                        this.bg = this.atlas.findRegion("mod/Eientei");
                    } else if (!(mo instanceof Byakuren) && !(mo instanceof Miko)) {
                        if (mo instanceof Reisen) {
                            this.bg = this.atlas.findRegion("mod/ElegantPlace");
                        } else if (mo instanceof Koishi) {
                            this.bg = this.atlas.findRegion("mod/PalaceBright");
                        } else if (mo instanceof Yuyuko) {
                            this.bg = this.atlas.findRegion("mod/ElegantPlace");
                        } else if (mo instanceof Tenshi) {
                            this.bg = this.atlas.findRegion("mod/GenbuRavineCloudy");
                        } else {
                            if (mo instanceof AngelMirror) {
                                this.bg = this.atlas.findRegion("mod/Ruins");
                                break label195;
                            }

                            if (mo instanceof Chomper) {
                                this.bg = this.atlas.findRegion("mod/Forest");
                            } else if (mo instanceof CosmicMonolith) {
                                this.bg = this.atlas.findRegion("mod/Cave");
                            } else if (mo instanceof TanukiDog) {
                                this.bg = this.atlas.findRegion("mod/Forest");
                            } else if (mo instanceof Gloop) {
                                this.bg = this.atlas.findRegion("mod/Island");
                            } else if (mo instanceof Wraith) {
                                this.bg = this.atlas.findRegion("mod/Cave");
                            } else if (mo instanceof Swordslinger) {
                                this.bg = this.atlas.findRegion("mod/Desert");
                            } else if (!(mo instanceof BigMudSlime) && !(mo instanceof SlimeBunny)) {
                                if (mo instanceof Doremy) {
                                    this.bg = this.atlas.findRegion("mod/DreamWorld");
                                } else if (!(mo instanceof Flandre) && !(mo instanceof Remilia)) {
                                    if (mo instanceof Mokou) {
                                        this.bg = this.atlas.findRegion("mod/BambooForest");
                                    } else if (mo instanceof Marisa) {
                                        this.bg = this.atlas.findRegion("mod/HakureiShrine");
                                    } else if (mo instanceof Shinki) {
                                        this.bg = this.atlas.findRegion("mod/FancyPlaceNight");
                                    } else if (mo instanceof Kasen) {
                                        this.bg = this.atlas.findRegion("mod/Hermit");
                                    } else if (mo instanceof SeedOfUnknown) {
                                        this.bg = this.atlas.findRegion("mod/Cave");
                                    } else if (mo instanceof AncientGuardian) {
                                        this.bg = this.atlas.findRegion("mod/Desert");
                                    } else if (mo instanceof Rafflesia) {
                                        this.bg = this.atlas.findRegion("mod/Forest");
                                    } else if (mo instanceof AtlasGolem) {
                                        this.bg = this.atlas.findRegion("mod/Cave");
                                    } else if (!(mo instanceof Duskaxe) && !(mo instanceof Dawnsword)) {
                                        if (!(mo instanceof VampireBat) && !(mo instanceof FeralBat) && !(mo instanceof LoudBat)) {
                                            if (mo instanceof Sharpion) {
                                                this.bg = this.atlas.findRegion("mod/Desert");
                                            } else if (mo instanceof MadBoulder) {
                                                this.bg = this.atlas.findRegion("mod/Island");
                                            } else if (CardCrawlGame.dungeon instanceof Gensokyoer) {
                                                this.bg = this.atlas.findRegion("mod/TanukiForestNight");
                                            } else {
                                                this.bg = this.atlas.findRegion("mod/TanukiForest");
                                            }
                                        } else {
                                            this.bg = this.atlas.findRegion("mod/Forest");
                                        }
                                    } else {
                                        this.bg = this.atlas.findRegion("mod/Ruins");
                                    }
                                } else {
                                    this.bg = this.atlas.findRegion("mod/Palace");
                                }
                            } else {
                                this.bg = this.atlas.findRegion("mod/Desert");
                            }
                        }
                    } else {
                        this.bg = this.atlas.findRegion("mod/FancyPlaceNight");
                    }
                }
            }
        }*/
        if (room instanceof ShopRoom) {
                this.bg = this.atlas.findRegion("bg_shop");
        } else {
            this.bg = this.atlas.findRegion("bg_act_0");
        }

        this.fadeInAmbiance();
    }

    public void renderCombatRoomBg(SpriteBatch sb) {
        sb.setColor(Color.WHITE.cpy());
        this.renderAtlasRegionIf(sb, this.bg, true);
        GL20 var10001 = Gdx.gl20;
        GL20 var10002 = Gdx.gl20;
        sb.setBlendFunction(770, 771);
    }

    public void renderCombatRoomFg(SpriteBatch sb) {
        sb.setColor(Color.WHITE.cpy());
        sb.setColor(Color.WHITE.cpy());
    }

    public void renderCampfireRoom(SpriteBatch sb) {
        sb.setColor(Color.WHITE);
        this.renderAtlasRegionIf(sb, this.campfireBg, true);
        GL20 var10001 = Gdx.gl20;
        GL20 var10002 = Gdx.gl20;
        sb.setBlendFunction(770, 1);
        sb.setColor(new Color(1.0F, 1.0F, 1.0F, MathUtils.cosDeg((float)(System.currentTimeMillis() / 3L % 360L)) / 10.0F + 0.8F));
        this.renderQuadrupleSize(sb, this.campfireGlow, !CampfireUI.hidden);
        var10001 = Gdx.gl20;
        var10002 = Gdx.gl20;
        sb.setBlendFunction(770, 771);
        sb.setColor(Color.WHITE);
        this.renderAtlasRegionIf(sb, this.campfireKindling, true);
    }
}
