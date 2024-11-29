package com.rhseung.thirsty

import com.rhseung.thirsty.event.PlayerTickHandler
import com.rhseung.thirsty.network.Messages
import com.rhseung.thirsty.util.ThirstUtil
import net.fabricmc.api.ModInitializer
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents
import net.minecraft.util.Identifier
import org.slf4j.LoggerFactory

object Thirsty : ModInitializer {
	const val MOD_ID = "thirsty";
    val LOGGER = LoggerFactory.getLogger(MOD_ID);

	fun id(path: String) = Identifier.of(MOD_ID, path);

	const val KEY_CATEGORY_THIRSTY = "key.category.$MOD_ID.thirsty";
	const val KEY_DRINK_WATER = "key.$MOD_ID.drink_water";

	override fun onInitialize() {
		ThirstUtil.initialize();
		Messages.registerC2SPayloads();
		ServerTickEvents.START_SERVER_TICK.register(PlayerTickHandler());
	}
}