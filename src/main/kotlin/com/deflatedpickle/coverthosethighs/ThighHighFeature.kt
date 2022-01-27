/* Copyright (c) 2022 DeflatedPickle under the MIT license */

package com.deflatedpickle.coverthosethighs

import com.deflatedpickle.coverthosethighs.api.HasLegs
import net.minecraft.client.model.ModelPart
import net.minecraft.client.render.OverlayTexture
import net.minecraft.client.render.RenderLayer
import net.minecraft.client.render.VertexConsumerProvider
import net.minecraft.client.render.entity.LivingEntityRenderer
import net.minecraft.client.render.entity.feature.FeatureRenderer
import net.minecraft.client.render.entity.model.EntityModel
import net.minecraft.client.util.math.MatrixStack
import net.minecraft.entity.LivingEntity
import net.minecraft.util.Identifier

class ThighHighFeature<T : LivingEntity, M : EntityModel<T>>(entityRenderer: LivingEntityRenderer<T, M>) :
    FeatureRenderer<T, M>(entityRenderer) {
    companion object {
        const val offset = 0.1f
        val texture = Identifier(CoverThoseThighs.MOD_ID, "textures/entity/thighhighs.png")
    }

    override fun render(
        matrices: MatrixStack,
        vertexConsumers: VertexConsumerProvider,
        light: Int,
        entity: T,
        limbAngle: Float,
        limbDistance: Float,
        tickDelta: Float,
        animationProgress: Float,
        headYaw: Float,
        headPitch: Float
    ) {
        contextModel.let { model ->
            if (model is HasLegs) {
                for (leg in model.legs) {
                    leg.forEachCuboid(matrices) { entry: MatrixStack.Entry, _: String, _: Int, cuboid: ModelPart.Cuboid ->
                        val yShift = cuboid.maxY / 5

                        ModelPart(
                            listOf(
                                ModelPart.Cuboid(
                                    0,
                                    0,
                                    cuboid.minX - offset,
                                    cuboid.minY - offset + yShift,
                                    cuboid.minZ - offset,
                                    (cuboid.maxX + offset) * 2,
                                    cuboid.maxY + offset * 2 - yShift,
                                    (cuboid.maxZ + offset) * 2,
                                    0f,
                                    0f,
                                    0f,
                                    false,
                                    4f,
                                    4f,
                                )
                            ),
                            mapOf(),
                        )
                            .render(
                                matrices,
                                vertexConsumers.getBuffer(RenderLayer.getEntitySolid(texture)),
                                light, OverlayTexture.DEFAULT_UV,
                                1f, 1f, 1f, 1f,
                            )
                    }
                }
            }
        }
    }
}
