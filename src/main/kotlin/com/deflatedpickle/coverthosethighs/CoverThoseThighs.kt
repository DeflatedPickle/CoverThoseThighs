/* Copyright (c) 2022 DeflatedPickle under the MIT license */

package com.deflatedpickle.coverthosethighs

import net.fabricmc.api.ModInitializer
import net.minecraft.client.render.entity.model.BeeEntityModel
import net.minecraft.client.render.entity.model.BlazeEntityModel
import net.minecraft.client.render.entity.model.EntityModelPartNames
import net.minecraft.client.render.entity.model.GhastEntityModel
import net.minecraft.client.render.entity.model.GuardianEntityModel
import net.minecraft.client.render.entity.model.SquidEntityModel

@Suppress("UNUSED")
object CoverThoseThighs : ModInitializer {
    private const val MOD_ID = "$[id]"
    private const val NAME = "$[name]"
    private const val GROUP = "$[group]"
    private const val AUTHOR = "$[author]"
    private const val VERSION = "$[version]"

    val PART_NAME = listOf(
        EntityModelPartNames.LEFT_LEG,
        EntityModelPartNames.RIGHT_LEG,
        EntityModelPartNames.RIGHT_HIND_LEG,
        EntityModelPartNames.LEFT_HIND_LEG,
        EntityModelPartNames.RIGHT_FRONT_LEG,
        EntityModelPartNames.LEFT_FRONT_LEG,
        BeeEntityModel.FRONT_LEGS, BeeEntityModel.MIDDLE_LEGS, BeeEntityModel.BACK_LEGS,
        *List(8) { index -> SquidEntityModel.getTentacleName(index) }.toTypedArray(),
        *List(9) { index -> GhastEntityModel.getTentacleName(index) }.toTypedArray(),
        *List(12) { index -> GuardianEntityModel.getSpikeName(index) }.toTypedArray(),
        *List(12) { index -> BlazeEntityModel.getRodName(index) }.toTypedArray()
    )

    override fun onInitialize() {
        println(listOf(MOD_ID, NAME, GROUP, AUTHOR, VERSION))
    }
}
