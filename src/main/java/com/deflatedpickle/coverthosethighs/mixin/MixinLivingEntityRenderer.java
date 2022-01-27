/* Copyright (c) 2022 DeflatedPickle under the MIT license */

package com.deflatedpickle.coverthosethighs.mixin;

import com.deflatedpickle.coverthosethighs.ThighHighFeature;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.LivingEntityRenderer;
import net.minecraft.client.render.entity.feature.FeatureRenderer;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@SuppressWarnings({"UnusedMixin", "rawtypes", "unchecked"})
@Mixin(LivingEntityRenderer.class)
public abstract class MixinLivingEntityRenderer {
  @Shadow
  protected abstract boolean addFeature(FeatureRenderer feature);

  @Inject(method = "<init>", at = @At("TAIL"))
  public void init(
      EntityRendererFactory.Context ctx, EntityModel model, float shadowRadius, CallbackInfo ci) {
    addFeature(
        new ThighHighFeature(
            (LivingEntityRenderer<LivingEntity, EntityModel<LivingEntity>>) (Object) this));
  }
}
