package com.rhseung.thirsty.client

import com.rhseung.thirsty.Thirsty
import com.rhseung.thirsty.util.ThirstUtil
import net.minecraft.client.MinecraftClient
import net.minecraft.client.gui.DrawContext
import net.minecraft.client.render.RenderLayer
import net.minecraft.util.Identifier

object ThirstHudOverlay {
    val THIRST_TEXTURE: Identifier = Thirsty.id("hud/thirst_icons");
    val ICON_WIDTH = 9;
    val ICON_HEIGHT = 13;
    val TEXTURE_WIDTH = ICON_WIDTH * (ThirstUtil.MAX_THIRST + 1);   // 0, 1, 2, ..., max
    val TEXTURE_HEIGHT = ICON_HEIGHT;

    fun onHudRender(context: DrawContext) {
        val player = MinecraftClient.getInstance().player ?: return;
        val thirst = ThirstUtil.getThirst(player);

        val x = (context.scaledWindowWidth - ICON_WIDTH) / 2;
        val y = context.scaledWindowHeight - 31 - 20;

//        val f = 20; // max health
//        val o = 0;  // absorption
//        val p = MathHelper.ceil((f + o.toFloat()) / 2.0f / 10.0f);
//        val q = max((10 - (p - 2)).toDouble(), 3.0).toInt();
//        val health_y = n - (p - 1) * q - 10;

        context.drawGuiTexture(RenderLayer::getGuiTextured, THIRST_TEXTURE,
            TEXTURE_WIDTH, TEXTURE_HEIGHT, ICON_WIDTH * thirst, 0, x, y, ICON_WIDTH, ICON_HEIGHT
        );

//        context.drawText(MinecraftClient.getInstance().textRenderer, "Thirst: $thirst", x, y + 10, 0xFFFFFF, true);
    }
}