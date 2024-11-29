package com.rhseung.thirsty.event

import com.rhseung.thirsty.Thirsty
import com.rhseung.thirsty.network.payloads.DrinkingC2S
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking
import net.minecraft.client.option.KeyBinding
import net.minecraft.client.util.InputUtil
import org.lwjgl.glfw.GLFW

object KeyInputHandler {
    val drinkingKey: KeyBinding = KeyBindingHelper.registerKeyBinding(KeyBinding(
        Thirsty.KEY_DRINK_WATER,
        InputUtil.Type.MOUSE,
        GLFW.GLFW_MOUSE_BUTTON_RIGHT,
        Thirsty.KEY_CATEGORY_THIRSTY
    ));

    fun registerKeyInputs() {
        ClientTickEvents.END_CLIENT_TICK.register { client ->
            if (drinkingKey.wasPressed()) {
                ClientPlayNetworking.send(DrinkingC2S());
            }
        }
    }
}