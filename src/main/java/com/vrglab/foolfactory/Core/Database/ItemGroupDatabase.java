package com.vrglab.foolfactory.Core.Database;

import com.vrglab.foolfactory.Core.Handling.ItemGroups.FoolFactoryItemGroup;
import com.vrglab.foolfactory.Core.Handling.Items.FoolFactoryItem;

public class ItemGroupDatabase extends Database<FoolFactoryItemGroup>{
    private static ItemGroupDatabase instance;

    private ItemGroupDatabase() {

    }

    public static ItemGroupDatabase getInstance() {
        if (instance == null) {
            instance = new ItemGroupDatabase();
        }
        return instance;
    }
}
