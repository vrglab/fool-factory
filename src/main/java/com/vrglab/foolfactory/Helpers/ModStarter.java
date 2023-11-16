package com.vrglab.foolfactory.Helpers;

import com.vrglab.foolfactory.Core.Database.BlockDatabase;
import com.vrglab.foolfactory.Core.Database.FluidDatabase;
import com.vrglab.foolfactory.Core.Generators.ModOreGeneration;
import com.vrglab.foolfactory.Core.Handling.Blocks.BlockLoader;
import com.vrglab.foolfactory.Core.Handling.Fluid.FluidLoader;
import com.vrglab.foolfactory.Core.Handling.ItemGroups.ItemGroupLoader;
import com.vrglab.foolfactory.Core.Handling.Items.ItemLoader;
import com.vrglab.foolfactory.Core.Handling.LootTable.LootTableModifierLoader;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.render.fluid.v1.FluidRenderHandlerRegistry;
import net.fabricmc.fabric.api.client.render.fluid.v1.SimpleFluidRenderHandler;
import net.minecraft.block.Block;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.util.Identifier;

public class ModStarter {

    public static void StartMod(Class resourceLoader) {
        ModMetadata.LoadMetadata(resourceLoader);

        ItemGroupLoader.getInstance().Register();
        ItemLoader.getInstance().ItemRegister();
        BlockLoader.getInstance().BlockRegister();
        FluidLoader.getInstance().Register();
        LootTableModifierLoader.getInstance().Register();

        ModOreGeneration.GenerateOres();
        ModInfo.LOGGER.info("Mod Initialized");
    }

    public static void StartClient() {
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
