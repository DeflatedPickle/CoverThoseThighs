/* Copyright (c) 2022 DeflatedPickle under the MIT license */

package com.deflatedpickle.coverthosethighs.mixin;

import com.deflatedpickle.coverthosethighs.api.GetRoot;
import com.deflatedpickle.coverthosethighs.api.HasParent;
import net.minecraft.client.model.ModelPart;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@SuppressWarnings({"UnusedMixin", "ConstantConditions"})
@Mixin(ModelPart.class)
public class MixinModelPart implements GetRoot, HasParent {
  public ModelPart parent = null;

  @NotNull
  @Override
  public ModelPart getRoot() {
    var p = getParent();
    while (((HasParent) (Object) p).getParent() != null) {
      p = ((HasParent) (Object) parent).getParent();
    }
    return p;
  }

  @NotNull
  @Override
  public ModelPart getParent() {
    return parent;
  }

  @Override
  public void setParent(@NotNull ModelPart parent) {
    this.parent = parent;
  }

  @Inject(method = "getChild", at = @At("TAIL"), locals = LocalCapture.CAPTURE_FAILEXCEPTION)
  public void getChild(String name, CallbackInfoReturnable<ModelPart> cir, ModelPart modelPart) {
    ((HasParent) (Object) modelPart).setParent((ModelPart) (Object) this);
  }
}
