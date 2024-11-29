package com.rhseung.thirsty.mixin;

import com.llamalad7.mixinextras.sugar.Local;
import com.rhseung.thirsty.client.ThirstHudOverlay;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.util.profiler.Profiler;
import net.minecraft.util.profiler.Profilers;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(InGameHud.class)
public class InGameHudMixin {
    @Redirect(
        method = "renderStatusBars(Lnet/minecraft/client/gui/DrawContext;)V",
        at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/util/profiler/Profiler;push(Ljava/lang/String;)V"
        )
    )
    public void renderStatusBarsMixin(Profiler instance, String s, @Local(argsOnly = true) DrawContext context) {
        Profilers.get().push("thirsty");
        ThirstHudOverlay.INSTANCE.onHudRender(context);
        Profilers.get().swap("armor");
    }
}
