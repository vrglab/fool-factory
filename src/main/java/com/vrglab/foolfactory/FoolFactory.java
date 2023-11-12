package com.vrglab.foolfactory;

import com.vrglab.foolfactory.Core.Generators.ModOreGeneration;
import com.vrglab.foolfactory.Core.Handling.Blocks.BlockLoader;
import com.vrglab.foolfactory.Core.Handling.Items.ItemLoader;
import com.vrglab.foolfactory.Helpers.ModInfo;
import net.fabricmc.api.ModInitializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FoolFactory implements ModInitializer {

	@Override
	public void onInitialize() {
		ModInfo.LOGGER.info("Mod Initialized");

		ItemLoader.getInstance().ItemRegister();
		BlockLoader.getInstance().BlockRegister();

		ModOreGeneration.GenerateOres();
	}
}