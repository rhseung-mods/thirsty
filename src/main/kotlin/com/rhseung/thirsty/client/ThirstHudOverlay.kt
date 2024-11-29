package com.rhseung.thirsty.client

import com.rhseung.thirsty.Thirsty
import com.rhseung.thirsty.util.ThirstUtil
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback
import net.minecraft.client.MinecraftClient
import net.minecraft.client.gui.DrawContext
import net.minecraft.client.render.RenderLayer
import net.minecraft.client.render.RenderTickCounter
import net.minecraft.util.Identifier
import net.minecraft.util.math.MathHelper
import kotlin.math.max

object ThirstHudOverlay {
    val THIRST_TEXTURE: Identifier = Thirsty.id("hud/thirst_icons");

    fun onHudRender(context: DrawContext) {
        val player = MinecraftClient.getInstance().player ?: return;
        val thirst = ThirstUtil.getThirst(player);

        val full = thirst / 2;

        val x0 = context.scaledWindowWidth / 2 - 91;
        val n = context.scaledWindowHeight - 39;
        val f = 20; // max health
        val o = 0;  // absorption
        val p = MathHelper.ceil((f + o.toFloat()) / 2.0f / 10.0f);
        val q = max((10 - (p - 2)).toDouble(), 3.0).toInt();

        var x = x0;
        val y = n - (p - 1) * q - 10;

        for (i in 0..<10) {
            x = x0 + i * 8;
            val idx = if (i < full) 2 else if (i == full) (if (thirst % 2 == 0) 0 else 1) else 0;

//            context.drawTexture(
//                RenderLayer::getGuiTextured, THIRST_TEXTURE,
//                x, y, 9f * idx, 0f, 9, 9, 9 * 3, 9
//            );

            context.drawGuiTexture(RenderLayer::getGuiTextured, THIRST_TEXTURE,
                9 * 3, 9, 9 * idx, 0, x, y, 9, 9
            );
        }
    }
}