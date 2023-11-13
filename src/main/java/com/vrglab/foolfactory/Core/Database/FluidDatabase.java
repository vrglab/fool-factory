package com.vrglab.foolfactory.Core.Database;

import com.vrglab.foolfactory.Core.Handling.Fluid.FluidDatabaseEntry;

public class FluidDatabase extends Database<FluidDatabaseEntry>{

    private static FluidDatabase instance;

    private FluidDatabase() {

    }

    public static FluidDatabase getInstance() {
        if (instance == null) {
            instance = new FluidDatabase();
        }
        return instance;
    }
}
