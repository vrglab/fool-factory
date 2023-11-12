package com.vrglab.foolfactory.Core.Database;

import com.vrglab.foolfactory.Core.Handling.Blocks.FoolFactoryBlock;

public class BlockDatabase extends Database<FoolFactoryBlock> {
    private static BlockDatabase instance;

    private BlockDatabase() {

    }

    public static BlockDatabase getInstance() {
        if (instance == null) {
            instance = new BlockDatabase();
        }
        return instance;
    }
}
