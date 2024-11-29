package com.rhseung.thirsty.network

import com.rhseung.thirsty.network.payloads.DrinkingC2S

object Messages {
    fun registerC2SPayloads() {
        DrinkingC2S.register();
    }

    fun registerS2CPayloads() {
    }
}