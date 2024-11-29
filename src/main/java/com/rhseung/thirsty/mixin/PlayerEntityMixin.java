package com.rhseung.thirsty.mixin;

import com.rhseung.thirsty.util.ThirstUtil;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.NbtCompound;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(PlayerEntity.class)
public class PlayerEntityMixin {
	@Inject(
		method = "initDataTracker(Lnet/minecraft/entity/data/DataTracker$Builder;)V",
		at = @At("TAIL")
	)
	private void initDataTrackerMixin(DataTracker.Builder builder, CallbackInfo ci) {
		builder.add(ThirstUtil.INSTANCE.getTHIRSTY(), ThirstUtil.INSTANCE.getMAX_THIRST());
	}

	@Inject(
		method = "writeCustomDataToNbt(Lnet/minecraft/nbt/NbtCompound;)V",
		at = @At("TAIL")
	)
	public void writeCustomDataToNbtMixin(NbtCompound nbt, CallbackInfo ci) {
		var that = (PlayerEntity) (Object) this;

		nbt.putInt("Thirst", ThirstUtil.INSTANCE.getThirst(that));
	}

	@Inject(
		method = "readCustomDataFromNbt(Lnet/minecraft/nbt/NbtCompound;)V",
		at = @At("TAIL")
	)
	public void readCustomDataFromNbtMixin(NbtCompound nbt, CallbackInfo ci) {
		var that = (PlayerEntity) (Object) this;

		if (nbt.contains("Thirst"))
			ThirstUtil.INSTANCE.setThirst(that, nbt.getInt("Thirst"));
	}
}