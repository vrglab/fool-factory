package com.vrglab.foolfactory.Core;


import com.vrglab.foolfactory.Helpers.ModStarter;
import net.fabricmc.api.ClientModInitializer;

public class FoolFactoryClient implements ClientModInitializer {

    /**
     * Runs the mod initializer on the client environment.
     */
    @Override
    public void onInitializeClient() {
        ModStarter.StartClient();
    }
}
