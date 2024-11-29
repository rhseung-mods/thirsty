package com.rhseung.thirsty.network.payloads

import com.mojang.serialization.Codec
import com.rhseung.thirsty.Thirsty
import com.rhseung.thirsty.util.ThirstUtil
import io.netty.buffer.ByteBuf
import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking
import net.minecraft.block.Blocks
import net.minecraft.network.codec.PacketCodec
import net.minecraft.network.codec.PacketCodecs
import net.minecraft.network.packet.CustomPayload
import net.minecraft.text.Text
import net.minecraft.util.Formatting
import net.minecraft.util.math.BlockPos

class DrinkingC2S : CustomPayload {
    companion object : ServerPlayNetworking.PlayPayloadHandler<DrinkingC2S> {
        val PACKET_ID = CustomPayload.Id<DrinkingC2S>(Thirsty.id("drinking"));
        val PACKET_CODEC: PacketCodec<ByteBuf, DrinkingC2S> = PacketCodecs.codec(Codec.unit(DrinkingC2S()));

        fun register() {
            PayloadTypeRegistry.playC2S().register(PACKET_ID, PACKET_CODEC);
            ServerPlayNetworking.registerGlobalReceiver(PACKET_ID, DrinkingC2S::receive);
        }

        override fun receive(packet: DrinkingC2S, context: ServerPlayNetworking.Context) {
            val player = context.player();
            val world = player.world;

            val aroundWater = BlockPos
                .stream(player.boundingBox.expand(2.0))
                .map(world::getBlockState)
                .filter { it.isOf(Blocks.WATER) }
                .toArray().isNotEmpty();

            if (aroundWater) {
                ThirstUtil.addThirst(player, 1);
            }
        }
    }

    override fun getId(): CustomPayload.Id<out CustomPayload> {
        return PACKET_ID;
    }
}