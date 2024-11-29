package com.rhseung.thirsty.data

import com.rhseung.thirsty.Thirsty
import com.rhseung.thirsty.event.KeyInputHandler
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider
import net.minecraft.registry.RegistryWrapper
import java.util.concurrent.CompletableFuture

class LanguageProvider(
    dataOutput: FabricDataOutput,
    registryLookup: CompletableFuture<RegistryWrapper.WrapperLookup>
) : FabricLanguageProvider(dataOutput, registryLookup) {

    override fun generateTranslations(lookUp: RegistryWrapper.WrapperLookup, translationBuilder: TranslationBuilder) {
        translationBuilder.add(Thirsty.KEY_CATEGORY_THIRSTY, "Thirsty");
        translationBuilder.add(Thirsty.KEY_DRINK_WATER, "Drink Water");
    }
}