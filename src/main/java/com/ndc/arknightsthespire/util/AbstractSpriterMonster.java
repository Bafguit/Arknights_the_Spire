//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.ndc.arknightsthespire.util;

import basemod.abstracts.CustomMonster;
import com.ndc.arknightsthespire.character.AtsEnum;

public abstract class AbstractSpriterMonster extends CustomMonster {
    public int defaultArm = 0;
    public int defaultRes = 0;

    public AbstractSpriterMonster(String name, String id, int maxHealth, float hb_x, float hb_y, float hb_w, float hb_h, String imgUrl, float offsetX, float offsetY) {
        super(name, id, maxHealth, hb_x, hb_y, hb_w, hb_h, imgUrl, offsetX, offsetY);
    }

    public void die(boolean triggerRelics) {
        this.useShakeAnimation(5.0F);
        ((BetterSpriterAnimation)this.animation).startDying();
        super.die(triggerRelics);
    }

    public void setDef(int a, int r) {
        this.defaultArm = a;
        this.defaultRes = r;
    }

}
