/* Copyright (c) 2022 DeflatedPickle under the MIT license */

package com.deflatedpickle.coverthosethighs.mixin;

import com.deflatedpickle.coverthosethighs.api.HasLegs;
import java.util.List;
import net.minecraft.client.model.ModelPart;
import net.minecraft.client.render.entity.model.LlamaEntityModel;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@SuppressWarnings({"unused", "UnusedMixin"})
@Mixin(LlamaEntityModel.class)
public class MixinLlamaEntityModel implements HasLegs {
  @Shadow @Final private ModelPart rightHindLeg;
  @Shadow @Final private ModelPart leftHindLeg;
  @Shadow @Final private ModelPart rightFrontLeg;
  @Shadow @Final private ModelPart leftFrontLeg;

  @NotNull
  @Override
  public List<ModelPart> getLegs() {
    return List.of(rightHindLeg, leftHindLeg, rightFrontLeg, leftFrontLeg);
  }
}
