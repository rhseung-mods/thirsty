package com.rhseung.thirsty

import com.rhseung.thirsty.client.ThirstHudOverlay
import com.rhseung.thirsty.event.KeyInputHandler
import com.rhseung.thirsty.network.Messages
import net.fabricmc.api.ClientModInitializer
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback

object ThirstyClient : ClientModInitializer {
    override fun onInitializeClient() {
        KeyInputHandler.registerKeyInputs();
        Messages.registerS2CPayloads();
    }
}