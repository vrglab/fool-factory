package com.vrglab.foolfactory.Core;

import com.vrglab.foolfactory.Core.Generators.ModOreGeneration;
import com.vrglab.foolfactory.Core.Handling.Blocks.BlockLoader;
import com.vrglab.foolfactory.Core.Handling.ItemGroups.ItemGroupLoader;
import com.vrglab.foolfactory.Core.Handling.Items.ItemLoader;
import com.vrglab.foolfactory.Core.Handling.LootTable.LootTableModifierLoader;
import com.vrglab.foolfactory.Helpers.ModInfo;
import net.fabricmc.api.ModInitializer;

public class FoolFactory implements ModInitializer {

	@Override
	public void onInitialize() {
		ItemGroupLoader.getInstance().Register();
		ItemLoader.getInstance().ItemRegister();
		BlockLoader.getInstance().BlockRegister();
		LootTableModifierLoader.getInstance().Register();

		ModOreGeneration.GenerateOres();
		ModInfo.LOGGER.info("Mod Initialized");
	}
}