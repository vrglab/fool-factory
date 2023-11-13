package com.vrglab.foolfactory.Core;

import com.vrglab.foolfactory.Core.Database.BlockDatabase;
import com.vrglab.foolfactory.Helpers.ModInfo;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.minecraft.block.Block;
import net.minecraft.client.render.RenderLayer;

public class FoolFactoryClient implements ClientModInitializer {

    /**
     * Runs the mod initializer on the client environment.
     */
    @Override
    public void onInitializeClient() {
        ModInfo.LOGGER.info("Setting up block's with transparency");
        for (var block : BlockDatabase.getInstance().getDatalist()) {
            if(block.HasTransparentTexture()) {
                BlockRenderLayerMap.INSTANCE.putBlock((Block)block, RenderLayer.getCutout());
            }
        }
    }
}
