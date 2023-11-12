package com.vrglab.foolfactory.Core.Database;


import com.vrglab.foolfactory.Core.Handling.Items.FoolFactoryItem;
import net.minecraft.item.Item;

public class ItemDatabase extends Database<FoolFactoryItem> {
    private static ItemDatabase instance;

    private ItemDatabase() {

    }

    public static ItemDatabase getInstance() {
        if (instance == null) {
            instance = new ItemDatabase();
        }
        return instance;
    }
}
