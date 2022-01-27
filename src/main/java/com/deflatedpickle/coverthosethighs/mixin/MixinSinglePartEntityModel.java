/* Copyright (c) 2022 DeflatedPickle under the MIT license */

package com.deflatedpickle.coverthosethighs.mixin;

import com.deflatedpickle.coverthosethighs.CoverThoseThighs;
import com.deflatedpickle.coverthosethighs.api.HasLegs;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import net.minecraft.client.model.ModelPart;
import net.minecraft.client.render.entity.model.SinglePartEntityModel;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@SuppressWarnings({"unused", "UnusedMixin"})
@Mixin(SinglePartEntityModel.class)
public abstract class MixinSinglePartEntityModel implements HasLegs {
  @Shadow
  public abstract ModelPart getPart();

  public void getAllKids(ModelPart origin, Map<String, ModelPart> map) {
    map.putAll(origin.children);
    origin.children.forEach((k, v) -> getAllKids(v, map));
  }

  public void addKids(Map<String, ModelPart> map, ArrayList<ModelPart> list) {
    map.entrySet().stream()
        .filter((m) -> CoverThoseThighs.INSTANCE.getPART_NAME().contains(m.getKey()))
        .map(Map.Entry::getValue)
        .collect(Collectors.toCollection(() -> list));
  }

  @NotNull
  @Override
  public List<ModelPart> getLegs() {
    var map = new HashMap<String, ModelPart>();
    getAllKids(getPart(), map);
    var list = new ArrayList<ModelPart>();
    addKids(map, list);

    return list;
  }
}
