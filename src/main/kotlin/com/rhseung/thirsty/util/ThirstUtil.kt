package com.rhseung.thirsty.util

import net.minecraft.entity.data.DataTracker
import net.minecraft.entity.data.TrackedDataHandlerRegistry
import net.minecraft.entity.player.PlayerEntity

object ThirstUtil {
    fun initialize() {}

    val THIRSTY = DataTracker.registerData(PlayerEntity::class.java, TrackedDataHandlerRegistry.INTEGER);
    val MAX_THIRST = 10;

    fun getThirst(player: PlayerEntity): Int {
        return player.dataTracker.get(THIRSTY);
    }

    fun setThirst(player: PlayerEntity, amount: Int) {
        player.dataTracker.set(THIRSTY, amount);
    }

    fun addThirst(player: PlayerEntity, amount: Int) {
        setThirst(player, (getThirst(player) + amount).coerceIn(0, MAX_THIRST));
    }

    fun removeThirst(player: PlayerEntity, amount: Int) {
        setThirst(player, (getThirst(player) - amount).coerceIn(0, MAX_THIRST));
    }
}