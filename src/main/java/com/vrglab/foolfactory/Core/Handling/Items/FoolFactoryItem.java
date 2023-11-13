package com.vrglab.foolfactory.Core.Handling.Items;

import com.vrglab.foolfactory.Core.Database.ItemGroupDatabase;
import com.vrglab.foolfactory.Core.Handling.ItemGroups.ItemGroupLoader;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.Item;

public abstract class FoolFactoryItem extends Item implements FoolFactoryItemType{

    public FoolFactoryItem() {
        super(new FabricItemSettings().group(ItemGroupDatabase.getInstance().GetEntry("foolfactory").getRegisteredItemGroup()));
    }

    public FoolFactoryItem(Settings settings) {
        super(settings);
    }
}
