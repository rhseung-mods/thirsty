package com.rhseung.thirsty.event

import com.rhseung.thirsty.util.ThirstUtil
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents
import net.minecraft.server.MinecraftServer
import net.minecraft.text.Text
import java.util.Random

class PlayerTickHandler : ServerTickEvents.StartTick {
    override fun onStartTick(server: MinecraftServer) {
        server.playerManager.playerList.forEach { player ->
            if (Random().nextFloat() <= 0.005F) {
                ThirstUtil.removeThirst(player, 1);
            }
        }
    }
}