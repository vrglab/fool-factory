package com.vrglab.foolfactory.Core;

import com.vrglab.foolfactory.Core.Database.BlockDatabase;
import com.vrglab.foolfactory.Core.Database.FluidDatabase;
import com.vrglab.foolfactory.Helpers.ModInfo;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.render.fluid.v1.FluidRenderHandlerRegistry;
import net.fabricmc.fabric.api.client.render.fluid.v1.SimpleFluidRenderHandler;
import net.minecraft.block.Block;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.block.FluidRenderer;
import net.minecraft.util.Identifier;

public class FoolFactoryClient implements ClientModInitializer {

    /**
     * Runs the mod initializer on the client environment.
     */
    @Override
    public void onInitializeClient() {
        ModInfo.LOGGER.info("Setting up block's with transparency");
        for (var block : BlockDatabase.getInstance().getDatalist()) {
            if(block.getCreativeTab() != null) {
                if(block.HasTransparentTexture()) {
                    BlockRenderLayerMap.INSTANCE.putBlock((Block)block, RenderLayer.getCutout());
                }
            }
        }
        ModInfo.LOGGER.info("Setting up Fluid block rendering");
        for (var fluid : FluidDatabase.getInstance().getDatalist()) {
            FluidRenderHandlerRegistry.INSTANCE.register(fluid.still_instance, fluid.flowing_instance, new SimpleFluidRenderHandler(
                    new Identifier(fluid.fluid.StillModel()),
                    new Identifier(fluid.fluid.FlowingModel()),
                    fluid.fluid.ColorTint()
            ));
            BlockRenderLayerMap.INSTANCE.putFluids(RenderLayer.getTranslucent(), fluid.still_instance, fluid.flowing_instance);
        }
    }
}
