package com.vrglab.foolfactory.Core;

import com.vrglab.foolfactory.Helpers.ModStarter;
import net.fabricmc.api.ModInitializer;

public class FoolFactory implements ModInitializer {

	@Override
	public void onInitialize() {
		ModStarter.StartMod(this.getClass());
	}
}